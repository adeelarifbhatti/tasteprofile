package Server;

import java.io.FileInputStream;
import java.util.Scanner;

//import TasteProfile.ProfilerPOA;

public class Test {
	int totalCount= 0;
	String song_id;
		FileInputStream inputStream;
		public int getTimesPlayed(String song_id) { 
			this.song_id=song_id;
			try {
				inputStream = new FileInputStream("train_triplets_test.txt");
			} catch (Exception e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace(System.out);
			}
			Scanner sc = new Scanner(inputStream, "UTF-8");
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				if(song_id == song_id){
					Integer i = Integer.valueOf(parts[2]);
					totalCount=totalCount+i;
					//System.out.println("totalCount is " + totalCount);
				}
			
		}
			sc.close();
			System.out.println(totalCount);
			return totalCount;
			
		}
		public static void main (String [] args){
			Test test= new Test();
			test.getTimesPlayed("SOAAADD12AB018A9DD");
			
			
		}

}
