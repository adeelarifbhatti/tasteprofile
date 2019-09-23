package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

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

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;
	int totalCount;
	HashMap<String,SongProfile> songCache = new HashMap<String,SongProfile>();
	//UserProfile userPrfl[] = new UserProfile[1000];
	ArrayList <UserProfile> userPrfl = new ArrayList<UserProfile>(1000);
	HashMap<String,UserProfile> userCache = new HashMap<String,UserProfile>();
	
	@Override
	public int getTimesPlayed(String song_id) { 

			try {
				if(songCache.containsKey(song_id)) {
					if(songCache.get(song_id).total_play_count != 0) { //Check if the song is in Cachee but without the totalplaycount 
						return songCache.get(song_id).total_play_count;
					}
				}
				Scanner sc = new Scanner(new File("train_triplets_test.txt"));
				int totalCount= 0;
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String[] parts = line.split("\t");
					String part1=parts[0];
					if(song_id.equals(part1)){
						Integer i = Integer.valueOf(parts[2]);
						totalCount=totalCount+i;
					}
				}
				
//Put things in cache.
				if(totalCount > 0) {
					//Here the second argument is null because we are going to fix it in gettoptheeusersbysong.
					if(songCache.containsKey(song_id)) {
						SongProfileImpl songPrfl = new SongProfileImpl(totalCount,songCache.get(song_id).top_three_users);
						songCache.replace(song_id, songPrfl);
					}
					else {
						SongProfileImpl songPrfl = new SongProfileImpl(totalCount,null);
						songCache.put(song_id, songPrfl);
					}
				}
				//test
//				for (String key: songCache.keySet()) {
//					System.out.println("IN Cache: " + songCache.get(key).total_play_count);
//				}
				sc.close();
				return totalCount;
			}
			catch (FileNotFoundException e) {
				System.out.println(new File(".").getAbsolutePath());
				System.out.println("no file");
				e.printStackTrace();
			}
			return -1;
	}


	@Override
	public UserProfile getUserProfile(String user_id) {
		return userCache.get(user_id);
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		try {
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			int totalCount= 0;
			int totalTimesPlayed = 0;
			ArrayList <SongCounter> songs = new ArrayList<SongCounter>();
			
//Check userCache.
			//TODO test that!!
			if (userCache.containsKey(user_id)) {
				if (userCache.get(user_id).total_play_count != 0) {
					for (int i = 0; i<userCache.get(user_id).songs.length; i++) {
						if (userCache.get(user_id).songs[i].song_id.equals(song_id)) {
							return userCache.get(user_id).songs[i].songid_play_time;
						}
					}
				}
			}
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				String song = parts[0];
				String user = parts[1];

				int timesPlayed = Integer.parseInt(parts[2]);
				
				if (user_id.equals(user)) {
					totalTimesPlayed += timesPlayed;
					SongCounterImpl songCounter = new SongCounterImpl(song,timesPlayed);
					songs.add(songCounter);
				}
				
				
				if(user_id.equals(user) && song_id.equals(song)){
					totalCount=timesPlayed;
				}
			}
//Put things to Cache
			//TODO maybe sort the arraylist or something like that.
			if(!songs.isEmpty() && userCache.size() < 1000) {
				if(userCache.containsKey(user_id)) { // That means that the user is in cache from the other method.
					UserProfileImpl uPrfl = new UserProfileImpl (user_id, totalTimesPlayed, songs.toArray(new SongCounter[songs.size()]), userCache.get(user_id).top_three_songs);
					userCache.replace(user_id, uPrfl);
				}
				else {
					UserProfileImpl uPrfl = new UserProfileImpl (user_id, totalTimesPlayed, songs.toArray(new SongCounter[songs.size()]), null);
					userCache.put(user_id,uPrfl);
				}
				
			}
			sc.close();
			
			return totalCount;
		}
			catch (FileNotFoundException e) {
				System.out.println(new File(".").getAbsolutePath());
				System.out.println("no file");
				e.printStackTrace();
			}

		return -1;
	}

	@Override
	public TopThreeUsers getTopThreeUsersBySong(String song_id) {
		try {
			if(songCache.containsKey(song_id)) {
				if(songCache.get(song_id).top_three_users != null) { //Check if the song is in Cachee but without the totalplaycount 
					return songCache.get(song_id).top_three_users;
				}
			}
			
			System.out.println("hello topthreeusersbysong");
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			ArrayList <UserCounterImpl> userList = new ArrayList<UserCounterImpl>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				int comp = song_id.compareTo(parts[0]);
				if(comp == 0){
					UserCounterImpl userCounter = new UserCounterImpl(parts[1],Integer.valueOf(parts[2]));
					userList.add(userCounter);
				}
			}
			
			sc.close();
			
			//TODO edge cases
			Collections.sort(userList);
			UserCounterImpl[] topThree = {userList.get(0),userList.get(1),userList.get(2)};
			TopThreeUsersImpl result = new TopThreeUsersImpl(topThree);
			
			if(songCache.containsKey(song_id)) {
				SongProfileImpl songPrfl = new SongProfileImpl(songCache.get(song_id).total_play_count,result);
				songCache.replace(song_id, songPrfl);
			}
			else {
				SongProfileImpl songPrfl = new SongProfileImpl(0,result);
				songCache.put(song_id, songPrfl);
			}
			return result;
		}
		catch (FileNotFoundException e) {
			System.out.println(new File(".").getAbsolutePath());
			System.out.println("no file");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TopThreeSongs getTopThreeSongsByUser(String user_id) {
		//TODO caching and don't duplicate an already existing song
				try {
			System.out.println("hello getTopThreeSongsByUser1");
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			ArrayList <SongCounterImpl> songList = new ArrayList<SongCounterImpl>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				int comp = user_id.compareTo(parts[1]);
				if(comp == 0){
					SongCounterImpl songCounter = new SongCounterImpl(parts[0],Integer.valueOf(parts[2]));
					songList.add(songCounter);
				}
			}
			
			sc.close();
			
			//TODO edge cases
			Collections.sort(songList);
			SongCounterImpl[] topThree = {songList.get(0),songList.get(1),songList.get(2)};
			TopThreeSongsImpl result = new TopThreeSongsImpl(topThree);
			return result;
		}
		catch (FileNotFoundException e) {
			System.out.println(new File(".").getAbsolutePath());
			System.out.println("no file");
			e.printStackTrace();
		}
		return null;
	}

}
