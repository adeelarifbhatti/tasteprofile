package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Implementation.TopThreeUsersImpl;
import Implementation.SongCounterImpl;
import Implementation.TopThreeSongsImpl;
import Implementation.UserCounterImpl;
import TasteProfile.ProfilerPOA;
import TasteProfile.SongCounter;
import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;
import TasteProfile.UserProfile;

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;
	int totalCount;
	SongCache songCache=new SongCache();
	UserCache userCache=new UserCache();

	
	@Override
	public int getTimesPlayed(String song_id) { 
		serverPause();
			try {
				if(songCache.checkSongCache(song_id)) {
					return songCache.getSongCache(song_id);
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
				sc.close();
				songCache.setSongCache(song_id,totalCount);
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
		serverPause();
		try {
			if(userCache.checkuserCache(user_id)) {
				return userCache.getUserProfCache(user_id);
			}
		}
		catch (Exception e) {
		System.err.println("InterruptedException: " + e.getMessage());
		e.printStackTrace(System.out);
	}
		return null;
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		serverPause();
		SongCounterImpl songCount;
		try {
			
			if(userCache.checkuserCache(user_id)) {
			
			//return userCache.getuserCacheSongs(user_id,song_id);
				
			}
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			int totalCount= 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				String part1=parts[0];
				String part2=parts[1];
				int part3=Integer.parseInt(parts[2]);

				if(user_id.equals(part1)&&song_id.equals(part2)){
					totalCount=part3;

				}
			}
			sc.close();
			/*
			ArrayList <SongCounterImpl> songCounter = new ArrayList<SongCounterImpl>();
			songCount= new SongCounterImpl(song_id, totalCount);
			songCounter.add(songCount);
			SongCounterImpl[] song= {songCounter.get(0)};
			
			userCache.setuserCacheSongs(user_id,song);
			*/
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
		serverPause();
		TopThreeUsersImpl result;
		try {
			if(songCache.checkSongCache(song_id)) {
				return songCache.getSongCacheTopThree(song_id);
			}
			
			UserCounterImpl userCounter;
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			ArrayList <UserCounterImpl> userList = new ArrayList<UserCounterImpl>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				
				String part1=parts[0];
				
				if(song_id.equals(part1)){

					String part2=parts[1];
					int part3=Integer.parseInt(parts[2]);
					userCounter = new UserCounterImpl(part2,part3);
					userList.add(userCounter);
					
				     }
	
			}
			sc.close();
			Collections.sort(userList, new UserSorting());
			if(userList.size()>2) {
			UserCounterImpl[] topThree = {userList.get(0),userList.get(1),userList.get(2)};
			result = new TopThreeUsersImpl(topThree);
			songCache.setSongCacheTopThree(song_id, result);
			return result;
			}
			else {
				UserCounterImpl[] topThree = {};
				result = new TopThreeUsersImpl(topThree);
				return result;
			}
	
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
		serverPause();
		TopThreeSongsImpl result;
		try {
			if(userCache.checkuserCache(user_id)) {
				return userCache.getuserCacheTopThree(user_id);
			}
			SongCounterImpl songCounter;
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			ArrayList <SongCounterImpl> songList = new ArrayList<SongCounterImpl>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				
				String part1=parts[1];
				
				if(user_id.equals(part1)){

					String part2=parts[0];
					int part3=Integer.parseInt(parts[2]);
					songCounter = new SongCounterImpl(part2,part3);
					songList.add(songCounter);
					
				     }
	
			}
			sc.close();
			Collections.sort(songList, new SongSorting());
			if(songList.size()>2) {
			SongCounterImpl[] topThree = {songList.get(0),songList.get(1),songList.get(2)};
			result = new TopThreeSongsImpl(topThree);
			/* Wrong Code userCache.setuserCacheTopThree(user_id, result);*/
			return result;
			}
			else {
				SongCounterImpl[] topThree = {};
				result = new TopThreeSongsImpl(topThree);
				return result;
			}
	
		}
			catch (FileNotFoundException e) {
				System.out.println(new File(".").getAbsolutePath());
				System.out.println("no file");
				e.printStackTrace();
			}
		return null;
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
