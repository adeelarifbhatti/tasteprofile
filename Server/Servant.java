package Server;
import java.io.FileInputStream;

import java.util.Scanner;

import TasteProfile.ProfilerPOA;
import TasteProfile.UserProfile;

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;
	int totalCount;

	
	@Override
	public int getTimesPlayed(String song_id) { 
				
		try {
			inputStream = new FileInputStream("src/Server/train_triplets_test.txt");
		 
		Scanner sc = new Scanner(inputStream);
		
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split("\t");
			if(song_id == parts[0]){
				Integer i = Integer.valueOf(parts[2]);
				totalCount=++i;
			}
		
	}
		sc.close();
		
		}
		
		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		return 100;
		
		/* String song_id1= "SOAAADD12AB018A9DD";
		boolean b = song_id.equals(song_id1);
		if(b) {
		return 10;
		}
		else 
			return 1000000;*/
	
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
