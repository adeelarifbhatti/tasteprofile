package Server;
import java.io.FileInputStream;

import java.util.Scanner;

import TasteProfile.ProfilerPOA;
import TasteProfile.UserProfile;

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;

	@Override
	public int getTimesPlayed(String song_id) { 
		try {
			inputStream = new FileInputStream("train_triplets_test.txt");
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		Scanner sc = new Scanner(inputStream, "UTF-8");
		int totalCount= 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split("\t");
			if(song_id == parts[0]){
				Integer i = Integer.valueOf(parts[2]);
				totalCount=totalCount+i;
				System.out.println("totalCount is " + totalCount);
			}
		
	}
		sc.close();
		
		return totalCount;
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopThreeSongsByUser(String song_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
