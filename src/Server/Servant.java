package Server;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Client.OutputFile;
import Client.Timer;

import java.util.Scanner;

import TasteProfile.ProfilerPOA;
import Implementation.SongCounterImpl;
import Implementation.SongProfileImpl;
import TasteProfile.TopThreeSongs;
import Implementation.TopThreeSongsImpl;
import TasteProfile.TopThreeUsers;
import Implementation.TopThreeUsersImpl;
import TasteProfile.UserCounter;
import Implementation.UserCounterImpl;
import TasteProfile.UserProfile;
import Implementation.UserProfileImpl;

public class Servant extends ProfilerPOA {

	HashMap<String, SongProfileImpl> songProfiles = new HashMap<>();
	HashMap<String, UserProfileImpl> userProfiles = new HashMap<>();

	class UserInfo implements Comparable<UserInfo>{
		String UserID;
		int totalTimesPlayed;
		ArrayList<SongCounterImpl> songsListenedTo;

		@Override
		public int compareTo(UserInfo u) {
			if(this.totalTimesPlayed < u.totalTimesPlayed) {
				return 1;
			}else if(this.totalTimesPlayed > u.totalTimesPlayed) {
				return -1;
			}else {
				return 0;
			}
		}
	}

	public void loadUserProfiles() {
		HashMap<String,UserInfo> songInfo = new HashMap<>();
		HashMap<String,Integer> userPlayTime = new HashMap<String,Integer>();

		try {
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);
				ArrayList<SongCounterImpl> songs;

				if(songInfo.containsKey(userID)) {
					songs = songInfo.get(userID).songsListenedTo;
					SongCounterImpl song = new SongCounterImpl(songID,timesPlayed);
					songs.add(song);

					songInfo.get(userID).songsListenedTo = songs;
					songInfo.get(userID).totalTimesPlayed += timesPlayed;

				}else {
					songs = new ArrayList<>();
					songs.add(new SongCounterImpl(songID,timesPlayed));
					UserInfo ui = new UserInfo();
					ui.UserID = userID;
					ui.songsListenedTo = songs;
					ui.totalTimesPlayed = timesPlayed;
					songInfo.put(userID,ui);
				}
			}
			

				//ArrayList<UserInfo> allInfo = (ArrayList<UserInfo>) songInfo.values();
				List<UserInfo> allInfo = new ArrayList<UserInfo>(songInfo.values());
				Collections.sort(allInfo);

				ArrayList<UserInfo> top1000 = new ArrayList<>();
				for(int i = 0; i< allInfo.size();i++) {
					top1000.add(allInfo.get(i));
				}


				for(UserInfo ui : top1000) {
					Collections.sort(ui.songsListenedTo);
					SongCounterImpl[] top3 = null;
					if(ui.songsListenedTo.size() < 3) {
						top3 = new SongCounterImpl[ui.songsListenedTo.size()];
						for(int i = 0; i< ui.songsListenedTo.size();i++) {
							top3[i] = ui.songsListenedTo.get(i);
						}
					}else {
						SongCounterImpl[] tmp = {ui.songsListenedTo.get(0),ui.songsListenedTo.get(1),ui.songsListenedTo.get(2)};
						top3 = tmp;
					}

					ArrayList<SongCounterImpl> songsListenedTo = ui.songsListenedTo;
					SongCounterImpl[] item = songsListenedTo.toArray(new SongCounterImpl[songsListenedTo.size()]);

					UserProfileImpl up = new UserProfileImpl(ui.UserID,ui.totalTimesPlayed,item,new TopThreeSongsImpl(top3));
					userProfiles.put(ui.UserID,up);
				}
				System.out.println("UserProfiles are ready");
				sc.close();

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}


	}
	public void loadSongProfiles() {
		try {
			HashMap<String,ArrayList<UserCounterImpl>> songListeners = new HashMap<String,ArrayList<UserCounterImpl>>();
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				UserCounterImpl u = new UserCounterImpl(userID,timesPlayed);
				ArrayList<UserCounterImpl> temp;
				if(songListeners.containsKey(songID)){
					temp = songListeners.get(songID);
					temp.add(u);
					songListeners.replace(songID,temp);
				}else {
					temp = new ArrayList<UserCounterImpl>();
					temp.add(u);
					songListeners.put(songID, temp);
				}
			}
			for (Entry<String, ArrayList<UserCounterImpl>> entry : songListeners.entrySet()) {

				int totalPlayTime = 0;
				ArrayList<UserCounterImpl> uCounters = songListeners.get(entry.getKey());
				for(UserCounter u : uCounters) {
					totalPlayTime += u.songid_play_time;
				}

				Collections.sort(uCounters);

				TopThreeUsersImpl top;
				if(uCounters.size() > 3) {
					UserCounterImpl[] t = {uCounters.get(0),uCounters.get(1),uCounters.get(2)};
					top = new TopThreeUsersImpl(t);
				}else {
					UserCounterImpl[] t = new UserCounterImpl[uCounters.size()];
					for(int i = 0; i < uCounters.size(); i++) {
						t[i] = uCounters.get(i);
					}
					top = new TopThreeUsersImpl(t);
				}

				SongProfileImpl sp = new SongProfileImpl(totalPlayTime,top);
				songProfiles.put(entry.getKey(), sp);
			}
			System.out.println("SongsProfiles are ready");
			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String,SongProfileImpl> getSongProfilesMap(){
		//System.out.println("IN METHOD: IsEmpty? : " + songProfiles.isEmpty());
		return songProfiles;
	}

	public HashMap<String,UserProfileImpl> getUserProfilesMap(){
		System.out.println("IN METHOD: getUserProfilesMap : IsEmpty? : " + userProfiles.isEmpty());
		return userProfiles;
	}


	@Override
	public int getTimesPlayed(String song_id) {
		try {
			serverPause();
			if (songProfiles.containsKey(song_id)) {
				return songProfiles.get(song_id).total_play_count;
			}
			
			
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));

			int totalPlayCount = 0;
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(songID.equals(song_id)) {
					totalPlayCount += timesPlayed;
				}
			}
			sc.close();
			return totalPlayCount;
			

		}
		catch(Exception e) {
			System.out.println(new File(".").getAbsolutePath());
			System.out.println("no file");
			e.printStackTrace();
			return -1;
		}

	}


	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		try{
			serverPause();
			if(userProfiles.containsKey(user_id)) {
				
				System.out.println("From Server Cache");
				for(int i = 0; i < userProfiles.get(user_id).songs.length ; i++){
					if(userProfiles.get(user_id).songs[i].song_id.equals(song_id)){
						return userProfiles.get(user_id).songs[i].songid_play_time;
						
					}
			}
			}
			
			
			
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			int playTimeByUser = 0;
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(songID.equals(song_id) && userID.equals(user_id)) {
					System.out.println(songID + " " + userID + " " + timesPlayed );
					playTimeByUser += timesPlayed;
					
				}
			}
			
			sc.close();
			return playTimeByUser;
		}
			
		
			
		
		
			catch(Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public UserProfile getUserProfile(String user_id) {
		if (userProfiles.containsKey(user_id)) {
			return userProfiles.get(user_id);
		}
		
		
		return null;
	}


	@Override
	public TopThreeUsers getTopThreeUsersBySong(String song_id) {
		try {
			serverPause();
			if(songProfiles.containsKey(song_id)) {
				return songProfiles.get(song_id).top_three_users;
				
			}
			
			
			
			
			
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			List<UserCounterImpl> users = new ArrayList<UserCounterImpl>();

			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(songID.equals(song_id)) {
					UserCounterImpl u = new UserCounterImpl(userID,timesPlayed);
					users.add(u);
				}
			}
			sc.close();

			Collections.sort(users);

			TopThreeUsersImpl top;
			if(users.size() > 3) {
				UserCounterImpl[] t = {users.get(2),users.get(1),users.get(0)};
				top = new TopThreeUsersImpl(t);
			}else {
				UserCounterImpl[] t = new UserCounterImpl[users.size()];
				for(int i = users.size(); i < 0; i--) {
					t[i] = users.get(i);
				}
				top = new TopThreeUsersImpl(t);
			}
			return top;


		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public TopThreeSongs getTopThreeSongsByUser(String user_id) {
		try {
			serverPause();
		  	 if(userProfiles.containsKey(user_id)) {
	    		 Timer time=new Timer();
	    		 time.setStart(System.nanoTime());
	    		 TopThreeSongs result=userProfiles.get(user_id).top_three_songs;
	    		 return result;
			
		   }
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			List<SongCounterImpl> songs = new ArrayList<SongCounterImpl>();

			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(userID.equals(user_id)) {
					SongCounterImpl u = new SongCounterImpl(songID,timesPlayed);
					songs.add(u);
				}
			}
			sc.close();

			Collections.sort(songs);

			TopThreeSongsImpl top;
			if(songs.size() > 3) {
				SongCounterImpl[] t = {songs.get(2),songs.get(1),songs.get(0)};
				top = new TopThreeSongsImpl(t);
			}else {
				SongCounterImpl[] t = new SongCounterImpl[songs.size()];
				for(int i = songs.size(); i < 0; i--) {
					t[i] = songs.get(i);
				}
				top = new TopThreeSongsImpl(t);
			}
			return top;


		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void serverPause() {
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			System.err.println("InterruptedException: " + e.getMessage());
			e.printStackTrace(System.out);
		}
	}

}