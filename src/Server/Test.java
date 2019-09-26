package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import Implementation.SongCounterImpl;
import Implementation.TopThreeUsersImpl;
import Implementation.UserCounterImpl;
import TasteProfile.UserProfile;

//import TasteProfile.ProfilerPOA;

public class Test {
	
		FileInputStream inputStream;

		// SongCounterImpl[] song= {songCounter.get(0)};
		List<String> listWithoutDuplicates;
		public void createUserID() {
			ArrayList<String>  col0 = new ArrayList<String>();
			
			try {
				Scanner sc = new Scanner(new File("train_triplets_test.txt"));
				while (sc.hasNext()) {
					String line = sc.nextLine();
					String[] parts = line.split("\t");
					String myUser_id=parts[1];
			
					Scanner sc3 = new Scanner(new File("train_triplets_test.txt"));
					while (sc3.hasNext()) {
						String line2 = sc3.nextLine();
						String[] parts2 = line2.split("\t");
						String user= parts2[1];
						if(myUser_id.equals(user)) {
							col0.add(myUser_id);
						}
					}
					sc3.close();
				}
				sc.close();
				
					listWithoutDuplicates = col0.stream()
							.collect(Collectors.toSet())
							.stream()
							.collect(Collectors.toList());
					Writer writer = new FileWriter("users.txt",true);

					
					writer.write(listWithoutDuplicates.toString());
				
					writer.close();
					
			}
			
			
					catch (FileNotFoundException e) {
						System.out.println(new File(".").getAbsolutePath());
						System.out.println("no file");
						e.printStackTrace();
						}
					catch(Exception e) {
						System.err.println("Error:" + e.getMessage());
						e.printStackTrace(System.out);
					}
		}

	
		public void createProfiles() {
			SongCounterImpl songCount;
			ArrayList <UserProfile> userPrfl = new ArrayList<UserProfile>(10);
			ArrayList <SongCounterImpl> songList = new ArrayList<SongCounterImpl>();

			try {
					Scanner sc2 = new Scanner(new File("users.txt"));
		
					int i=0;
					while (sc2.hasNext()) {
						
						String line2 = sc2.nextLine();
						String[] parts2 = line2.split(",");
						String user= parts2[i];
						
						System.out.println("From user file " + user);
						
						Scanner sc3 = new Scanner(new File("train_triplets_test.txt"));
							while (sc3.hasNext()) { 
								String line3 = sc3.nextLine();
								String[] parts3 = line3.split("\t");
								if(user.equals(parts3[1])) {
									
								
								String song_id=parts3[0];
								int totalCount= Integer.parseInt(parts3[2]);
								songCount= new SongCounterImpl(song_id, totalCount);
								songList.add(songCount);
								SongCounterImpl[] song= {songList.get(0)};
								totalCount=totalCount+Integer.parseInt(parts3[2]);
								
								
								System.out.println(user+ " EQUALS  "+ user+" Song Array "+ song +" TotalPlayTime "+ totalCount);
								serverPause();
							}
							else {
								System.out.println(user + " NOT EQUALS  "+ parts3[1]);
							
							
							}
								}
							i++;
						}
						
					
					sc2.close(); 
			}
		
					catch (FileNotFoundException e) {
						System.out.println(new File(".").getAbsolutePath());
						System.out.println("no file");
						e.printStackTrace();
						}

				
		}
			

		public static void main (String [] args){
			Test test= new Test();
			test.createUserID();
			test.createProfiles();
		
			
			
		}
		private void serverPause() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.err.println("InterruptedException: " + e.getMessage());
				e.printStackTrace(System.out);
			}
		}

}
