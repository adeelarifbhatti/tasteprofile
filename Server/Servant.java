package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import TasteProfile.ProfilerPOA;
import TasteProfile.UserProfile;

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;

	@Override
	public int getTimesPlayed(String song_id) { 

		try {
			System.out.println("hello there");
//			String filePath = new File(".").getAbsolutePath();
//			System.out.println(filePath);
			Scanner sc = new Scanner(new File(".../Server/train_triplets_test.txt"));
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopThreeSongsByUser(String song_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
