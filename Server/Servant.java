package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Implementation.SongCounterImpl;
import Implementation.TopThreeSongsImpl;
import Implementation.TopThreeUsersImpl;
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
	return 0;
}
//TODO change the name when the is fixed.
	//@Override
	public TopThreeUsersImpl getTopThreeUsersBySong1(String song_id) {
		
		try {
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
			
			Collections.sort(userList);
			UserCounterImpl[] topThree = {userList.get(0),userList.get(1),userList.get(2)};
			TopThreeUsersImpl result = new TopThreeUsersImpl(topThree);
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
	public int getTopThreeSongsByUser(String song_id) {
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	public TopThreeSongsImpl getTopThreeSongsByUser1(String user_id) {
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
