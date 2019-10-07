package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.util.*;

import Implementation.SongCounterImpl;
import Implementation.SongProfileImpl;
import Implementation.TopThreeSongsImpl;
import Implementation.TopThreeUsersImpl;
import Implementation.UserCounterImpl;
import Implementation.UserProfileImpl;
import TasteProfile.ProfilerPOA;
import TasteProfile.SongCounter;
import TasteProfile.SongProfile;
import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;
import TasteProfile.UserCounter;
import TasteProfile.UserProfile;
import java.util.Map.Entry;





public class Servant extends ProfilerPOA {
//	private static String SET_1 = "train_triplets_test1.txt";
//	private static String SET_2 = "train_triplets_test2.txt";

	private static String SET_1 = "train_triplets_1.txt"; // PUT THE CORRECT FILE DIRECTORY HERE!!!
	private static String SET_2 = "train_triplets_2.txt"; // PUT THE CORRECT FILE DIRECTORY HERE!!!

	HashMap<String, SongProfileImpl> songProfiles = new HashMap<>();
	HashMap<String, UserProfile> userProfiles = new HashMap<>();
	public boolean cachingEnabled = true;


	public HashMap<String,UserProfile> getUserProfileMap(){
		System.out.println("Is MapEmpty?" + userProfiles.isEmpty());
		return userProfiles;
	}

	public void loadUserProfiles() {
		try {

			HashMap<String,UserInfo> top1000 = loadUserProfilesHelper(); // this takes around 2mins
			createProfilesForTop1000(top1000); // around another 2.5 mins
			System.out.println("Done loading PROFILES");

		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public void createProfilesForTop1000 (HashMap<String,UserInfo> users) {
		try {
			String filename = SET_1;
			Scanner sc = new Scanner(new File(filename));
			HashMap<String,UserInfo>  users_final = (HashMap<String, UserInfo>) users.clone();

			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(users_final.containsKey(userID)) {
					UserInfo tempUser = users_final.get(userID);
					tempUser.addSong(songID, timesPlayed);
				}
			}
			sc.close();
			System.out.println("Done adding songs from File 1");

			filename = SET_2;
			sc = new Scanner(new File(filename));
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(users_final.containsKey(userID)) {
					UserInfo tempUser = users_final.get(userID);
					tempUser.addSong(songID, timesPlayed);
				}
			}

			sc.close();

			for(String id : users_final.keySet()) {
				UserInfo tmp = users_final.get(id);

				UserProfileImpl profile = new UserProfileImpl();
				profile.user_id = tmp.userID;
				profile.total_play_count = tmp.playTime;
				profile.top_three_songs = new TopThreeSongsImpl(tmp.calcTopThreeSongs());
				profile.songs = tmp.getSongArray();

				userProfiles.put(id, profile);
			}

			System.out.println("Map is filled");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String,UserInfo> loadUserProfilesHelper() {
		try {
			String filename = SET_1;
			HashMap<String,UserInfo> usersAndPlayCount = new HashMap<>();
			Scanner sc = new Scanner (new File(filename));

			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				//String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(usersAndPlayCount.containsKey(userID)) {
					int oldPlayCount = usersAndPlayCount.get(userID).playTime;

					usersAndPlayCount.replace(userID, new UserInfo(userID,oldPlayCount + timesPlayed));
				}else {
					usersAndPlayCount.put(userID, new UserInfo(userID,timesPlayed));
				}
			}
			sc.close();

			System.out.println("File 1 is done");

			filename = SET_2;
			sc = new Scanner(new File(filename));
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				//String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(usersAndPlayCount.containsKey(userID)) {
					int oldPlayCount = usersAndPlayCount.get(userID).playTime;

					usersAndPlayCount.replace(userID, new UserInfo(userID,oldPlayCount + timesPlayed));
				}else {
					usersAndPlayCount.put(userID, new UserInfo(userID,timesPlayed));
				}
			}
			sc.close();

			System.out.println("File 2 is done");


			ArrayList<UserInfo> sortedUsers = new ArrayList<UserInfo>(usersAndPlayCount.values());
			usersAndPlayCount.clear();

			Collections.sort(sortedUsers);
			System.out.println("UsersSorted!");

			HashMap<String,UserInfo> top1000 = new HashMap<>();

			for(int i = 0; i < 1000 ; i++) {
				top1000.put(sortedUsers.get(i).userID, sortedUsers.get(i));
			}

			sortedUsers.clear();


			return top1000;


		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void loadSongProfiles() {
		try {
			loadSongProfilesHelper(SET_1);
			System.out.println("Done with file 1");
			loadSongProfilesHelper(SET_2);
			System.out.println("Done with file 2");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void loadSongProfilesHelper(String filename) {
		try {
			HashMap<String,ArrayList<UserCounterImpl>> songListeners = new HashMap<String,ArrayList<UserCounterImpl>>();
			Scanner sc = new Scanner(new File(filename));
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
			sc.close();

			for (Entry<String, ArrayList<UserCounterImpl>> entry : songListeners.entrySet()) {

				int totalPlayTime = 0;
				ArrayList<UserCounterImpl> uCounters = songListeners.get(entry.getKey());
				for(UserCounter u : uCounters) {
					totalPlayTime += u.songid_play_time;
				}

				Collections.sort(uCounters);

				TopThreeUsersImpl top;
				if(uCounters.size() > 3) {
					UserCounterImpl[] t = {uCounters.get(2),uCounters.get(1),uCounters.get(0)};
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
			System.out.println("SongsProfiles For File are ready");


		}catch(Exception e) {
			e.printStackTrace();
		}
	}




	public HashMap<String, SongProfileImpl> getSongProfiles(){
		return songProfiles;
	}
	@Override
	public int getTimesPlayed(String song_id) {
		serverPause();
		System.out.println("METHOD: GetTimesPlayed(song_id)");
		int totalPlays = 0;
		try {
			if(cachingEnabled && songProfiles.containsKey(song_id)) {
				System.out.println("Song ["+ song_id + "] found in cache!");
				totalPlays = songProfiles.get(song_id).total_play_count;
			}else {
				System.out.println("Song ["+ song_id + "] not found in cache, fetching from files");
				totalPlays = getTimesPlayedHelper(song_id);
			}

			return totalPlays;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getTimesPlayedHelper(String song_id) {
		try {
			
			int totalPlayCount = 0;
			boolean startSongBlock = false;
			boolean endSongBlock = false;

			Scanner sc;

			//This check determines whether we search in DataSet_1 or DataSet_2 based on our song_id
			if(song_id.compareTo("SOMPBQG12AC3DF6169") >= 1) {
				System.out.println("File is in Data Set 2");
				sc = new Scanner(new File(SET_2));
			}else {
				System.out.println("File is in Data Set 1");
				sc = new Scanner(new File(SET_1));
			}

			//Now we go through file
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(songID.contentEquals(song_id)) {
					totalPlayCount += timesPlayed;
					if(startSongBlock == false) startSongBlock = true;
				}else {
//					System.out.println("Current Block [" + songID +"] \nWanted Block ["+song_id+"]");
//					System.out.println("Block ended.");
					if(startSongBlock == true) endSongBlock = true;
				}

				//We are out of the song block and there is no point in going further.
				if(startSongBlock == true && endSongBlock == true) {
					System.out.println("Song ["+ song_id +"] has play_count: "+ totalPlayCount);
					break;
				}
			}
			sc.close();
			return totalPlayCount;
		}catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}


	@Override
	public UserProfile getUserProfile(String user_id) {
		System.out.println("METHOD: getUserProfile(user_id)");
		UserProfile userP = null;
		try {
			if(cachingEnabled && userProfiles.containsKey(user_id)) {
				System.out.println("User [" + user_id + "] found in cache.");
				userP = userProfiles.get(user_id);
			}else {
				System.out.println("User ["+ user_id + "] not found in cache, fetching from files");
				userP = getUserProfileHelper(user_id);
			}
			return userP;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public UserProfile getUserProfileHelper(String user_id) {
		UserProfileImpl profile = null;

		try {
			Scanner sc = new Scanner(new File(SET_1));
			System.out.println("Reading from File 1");

			UserInfo user = new UserInfo(user_id);

			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(userID.equals(user_id)) {
					//We have found our user and now we collect his info.
					user.addSong(songID, timesPlayed);
					user.playTime += timesPlayed;
				}
			}
			sc.close(); //closing File 1;

			sc = new Scanner(new File(SET_2));
			System.out.println("Reading from File 2");

			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(userID.equals(user_id)) {
					//We have found our user and now we collect his info.
					user.addSong(songID, timesPlayed);
					user.playTime += timesPlayed;
				}
			}
			sc.close(); //closing File 2;

			profile = new UserProfileImpl();
			profile.user_id = user.userID;
			profile.total_play_count = user.playTime;
			profile.top_three_songs = new TopThreeSongsImpl (user.calcTopThreeSongs());
			profile.songs =  user.getSongArray();

			return profile;

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		serverPause();
		System.out.println("METHOD: GetTimesPlayedByUser(user_id,song_id)");
		int timesPlayedByUser = 0;
		try {
			serverPause();
			if(cachingEnabled && userProfiles.containsKey(user_id)) {
				System.out.println("User ["+user_id + "] found in cahce!");
				SongCounter[] userSongs = userProfiles.get(user_id).songs;
				System.out.println("Checking if user(cached) has song [" + song_id + "]");
				for(int i = 0 ; i < userSongs.length; i++) {
					if(userSongs[i].song_id.equals(song_id)) {
						System.out.println("Song [" + song_id + "] found in users cache!");
						return userSongs[i].songid_play_time;
					}
				}
				System.out.println("Cached user ["+user_id + "] did not have song [" + song_id + "] song list...");
			}else {
				timesPlayedByUser = getTimesPlayedByUserHelper(user_id,song_id);
			}

			return timesPlayedByUser;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getTimesPlayedByUserHelper(String user_id, String song_id) {
		int timesPlayedByUser = 0;
		try {
			
			boolean startSongBlock = false;
			boolean endSongBlock = false;

			Scanner sc;

			//This check determines whether we search in DataSet_1 or DataSet_2 based on our song_id
			if(song_id.compareTo("SOMPBQG12AC3DF6169") >= 1) {
				System.out.println("File is in Data Set 2");
				sc = new Scanner(new File(SET_2));
			}else {
				System.out.println("File is in Data Set 1");
				sc = new Scanner(new File(SET_1));
			}

			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(songID.equals(song_id) && userID.equals(user_id)) {
					System.out.println("Found " + userID + " "+ timesPlayed);
					timesPlayedByUser = timesPlayed;
					break;
				}
			}

			return timesPlayedByUser;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public TopThreeUsers getTopThreeUsersBySong(String song_id) {
		serverPause();
		System.out.println("METHOD: GetTopThreeUsersBySong(song_id)");
		TopThreeUsers topThree = null;
		try {
			if(cachingEnabled && songProfiles.containsKey(song_id)) {
				System.out.println("Song [" + song_id + "] is in cache.");
				SongProfile songP = songProfiles.get(song_id);
				topThree = songP.top_three_users;
			}else {
				System.out.println("Song [" + song_id + "] not found in cache, fetching from files.");
				topThree = getTopThreeUsersBySongHelper(song_id);
			}

			return topThree;

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public TopThreeUsers getTopThreeUsersBySongHelper(String song_id) {
		try {
			serverPause();
			boolean startSongBlock = false;
			boolean endSongBlock = false;

			Scanner sc;

			//This check determines whether we search in DataSet_1 or DataSet_2 based on our song_id
			if(song_id.compareTo("SOMPBQG12AC3DF6169") >= 1) {
				System.out.println("File is in Data Set 2");
				sc = new Scanner(new File(SET_2));
			}else {
				System.out.println("File is in Data Set 1");
				sc = new Scanner(new File(SET_1));
			}
			List<UserInfo> users = new ArrayList<UserInfo>();


			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(songID.equals(song_id)) {
					users.add(new UserInfo(userID,timesPlayed));
					if(startSongBlock == false) startSongBlock = true;
					System.out.println("Found " + userID + " "+ timesPlayed);
				}else {
					if(startSongBlock == true) {System.out.println("Hit last entry in song block."); endSongBlock = true;}
				}

				if(startSongBlock == true && endSongBlock == true) {
					System.out.println("Exiting song block. Song ["+song_id+"] has " + users.size() + " Users" );
					break;
				}

			}
			sc.close();
			Collections.sort(users);
			UserCounter[] userCounters = {
					new UserCounterImpl(users.get(2).userID,users.get(2).playTime),
					new UserCounterImpl(users.get(1).userID,users.get(1).playTime),
					new UserCounterImpl(users.get(0).userID,users.get(0).playTime)
			};

			TopThreeUsers topThree = new TopThreeUsersImpl(userCounters);
			return topThree;

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public TopThreeSongs getTopThreeSongsByUser(String user_id) {
		serverPause();
		System.out.println("METHOD: GetTopThreeSongsByUser(user_id)");
		TopThreeSongs topThree = null;
		try {
			if(cachingEnabled && userProfiles.containsKey(user_id)) {
				System.out.println("User ["+ user_id + "] was found in cache!");
				topThree = userProfiles.get(user_id).top_three_songs;
			}else {
				System.out.println("User ["+ user_id + "] was not found in cache, fetching from file...");
				topThree = getTopThreeSongsByUserHelper(user_id);
			}
			return topThree;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		// TODO Auto-generated method stub
	}

	public TopThreeSongs getTopThreeSongsByUserHelper(String user_id) {
		TopThreeSongs topThree = null;
		try {
			Scanner sc;
			sc = new Scanner(new File(SET_1));

			UserInfo info = new UserInfo(user_id);
			int numberOfSongs = 0;
			System.out.println("Starting with File 1");
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(userID.contentEquals(user_id)) {
					System.out.println("Song [" + songID +"] played by User ["+user_id+"]" );
					info.addSong(songID, timesPlayed);
					numberOfSongs++;
				}
			}
			System.out.println("Done with File 1. Found "+  numberOfSongs + " songs.\n");
			sc = new Scanner(new File(SET_2));
			numberOfSongs = 0;

			System.out.println("Starting with File 2");
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");

				String songID = parts[0];
				String userID = parts[1];
				int timesPlayed = Integer.parseInt(parts[2]);

				if(userID.contentEquals(user_id)) {
					System.out.println("Song [" + songID +"] played by User ["+user_id+"]" );
					info.addSong(songID, timesPlayed);
					numberOfSongs ++;
				}
			}
			System.out.println("Done with File 2. Found " + numberOfSongs + " songs.\n");

			Collections.sort(info.songs);
			System.out.println("Top3 sorted!");

			SongCounter[] songs = {info.songs.get(2), info.songs.get(1),info.songs.get(0) };
			topThree = new TopThreeSongsImpl(songs);



			return topThree;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public class UserInfo implements Comparable<UserInfo>{
		String userID = " ";
		int playTime = 0;
		ArrayList<SongCounterImpl> songs = null;



		UserInfo(String id){
			this.userID = id;
			this.songs = new ArrayList<>();
		}
		UserInfo(int timesPlayed){
			this.playTime = timesPlayed;
			this.userID = "";
			songs = null;
		}

		UserInfo(String id, int plays){
			this.userID = id;
			this.playTime = plays;
			this.songs = new ArrayList<>();
		}

		public void addSong(String songID,int songCount) {
			SongCounterImpl song = new SongCounterImpl(songID,songCount);
			songs.add(song);
		}

		public SongCounter[] calcTopThreeSongs() {
			Collections.sort(songs);

			SongCounter[] topThree = {
					songs.get(2),
					songs.get(1),
					songs.get(0)
			};
			return topThree;
		}
		public SongCounter[] getSongArray() {
			SongCounter[] songArray = new SongCounter[songs.size()];
			for(int i = 0; i < songs.size(); i++) {
				songArray[i] = songs.get(i);
			}

			return songArray;
		}

		@Override
		public int compareTo(UserInfo u) {
			if(this.playTime > u.playTime)  {
				return -1;
			}else if(this.playTime < u.playTime) {
				return 1;
			}else {
				return 0;
			}
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