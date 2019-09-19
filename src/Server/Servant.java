package Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import TasteProfile.ProfilerPOA;
import TasteProfile.UserProfile;

public class Servant extends ProfilerPOA {
	
	FileInputStream inputStream;
	int totalCount;

	
	@Override
	public int getTimesPlayed(String song_id) { 

			try {
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
		try {
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
