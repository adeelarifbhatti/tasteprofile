package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Implementation.UserCounterImpl;
import TasteProfile.ProfilerPOA;
import TasteProfile.UserProfile;

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;
	int totalCount;

	
	@Override
	public int getTimesPlayed(String song_id) { 

		try {
			System.out.println("hello there");
			String filePath = new File(".").getAbsolutePath();
			System.out.println(filePath);
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			int totalCount= 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				int comp = song_id.compareTo(parts[0]);
				if(comp == 0){
					Integer i = Integer.valueOf(parts[2]);
					totalCount=totalCount+i;
					//System.out.println("totalCount is " + totalCount);
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
	public UserProfile getUserProfile(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTimesPlayedByUser(String user_id, String song_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopThreeUsersBySong(String song_id) {
		
		try {
			System.out.println("hello topthreeusersbysong");
//			String filePath = new File(".").getAbsolutePath();
//			System.out.println(filePath);
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			int totalCount= 0;
			ArrayList <UserCounterImpl> userList = new ArrayList<UserCounterImpl>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				int comp = song_id.compareTo(parts[0]);
				if(comp == 0){
					UserCounterImpl userCounter = new UserCounterImpl(parts[1],Integer.valueOf(parts[2]));
					userList.add(userCounter);
					Integer i = Integer.valueOf(parts[2]);
					totalCount=totalCount+i;
				}
			}
			//TODO sort the list according to songid_play_time and return the first three.
			Collections.sort(userList);
			for (UserCounterImpl u : userList) {
				System.out.println(u.songid_play_time);
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
		// TODO Auto-generated method stub
	}

	@Override
	public int getTopThreeSongsByUser(String song_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
