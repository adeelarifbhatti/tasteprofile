package Server;
import TasteProfile.UserProfile;
import TasteProfile.TopThreeSongs;
import TasteProfile.SongCounter;
import Implementation.SongCounterImpl;
import Implementation.TopThreeSongsImpl;
import Implementation.UserProfileImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;



public class UserCache {
	
	String user_id;
	FileInputStream inputStream;
	TopThreeSongsImpl result;
	UserProfileImpl userprofile;
	HashMap<String,UserProfile> userCache = new HashMap<String,UserProfile>();
	ArrayList <UserProfileImpl> userPrfl = new ArrayList<UserProfileImpl>(100);
	ArrayList <SongCounterImpl> songList = new ArrayList<SongCounterImpl>();
		public boolean checkuserCache(String user_id) {
		if(userCache.containsKey(user_id)) {
			return true;
		}
		else 
			return false;
	}
	
	public int getuserCache(String user_id) {
		
		if(userCache.containsKey(user_id)) {
			if(userCache.get(user_id).total_play_count != 0) { 
				//return userCache.get(user_id).total_play_count;
			}
		}
	return 0;
	}
	/* May be wrong
	public int getuserCacheSongs(String user_id,String song_id) {
		// This needs fixing
		 if(userCache.get(user_id).equals(user_id) && userCache.get(song_id).equals(song_id)){
			 return userCache.get(user_id).total_play_count;
			
		 }
		 return -1;
		
	}
	*/
		

	public TopThreeSongs getuserCacheTopThree(String user_id) {
		if(userCache.containsKey(user_id)) {
			if(!userCache.get(user_id).top_three_songs.equals(null)) { 
				return userCache.get(user_id).top_three_songs;
			}
		}
		return null;
    }	
/*	
 * Wrong Code
 * public void setuserCache(String user_id, int totalCount) {
		UserProfileImpl UserPrfl = new UserProfileImpl(user_id, totalCount);
		userCache.put(user_id, UserPrfl);

	
	}
	public void setuserCacheTopThree(String user_id, TopThreeSongs topthreesongs) {
		UserProfileImpl UserPrfl = new UserProfileImpl(user_id,topthreesongs);
		userCache.put(user_id, UserPrfl);
	}

public void setuserCacheSongs(String user_id, TasteProfile.SongCounter songs[]) {
UserProfileImpl UserPrfl = new UserProfileImpl(user_id,songs);
userCache.put(user_id, UserPrfl);

}*/
	
	public UserProfile getUserProfCache(String user_id) {
		if(userCache.containsKey(user_id)) {
			if(!userCache.get(user_id).top_three_songs.equals(null) && !userCache.get(user_id).songs.equals(null) && userCache.get(user_id).total_play_count!=0) { 
				UserProfileImpl userPrfl = new UserProfileImpl(user_id,userCache.get(user_id).total_play_count,userCache.get(user_id).songs, userCache.get(user_id).top_three_songs);
				return userPrfl;
			}
		}
		return null;
		
    }

	public void createUserID() {
		try {
			Scanner sc = new Scanner(new File("train_triplets_test.txt"));
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] parts = line.split("\t");
				String myUser_id=parts[1];
				
				
				
				Writer writer = new FileWriter("users.txt",true);
				writer.write(myUser_id+"\n");
				writer.close();
			}
			
			sc.close();

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

		
		try {
				Scanner sc2 = new Scanner(new File("users.txt"));
				while (sc2.hasNext()) {
					
					String line2 = sc2.nextLine();
					String user= line2;
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
							Collections.sort(songList, new SongSorting());
							if(songList.size()>2) {
							SongCounter[] topThree = {songList.get(0),songList.get(1),songList.get(2)};
							result = new TopThreeSongsImpl(topThree);
							Collections.sort(userPrfl, new ProfileSorting());
							userprofile= new UserProfileImpl(user,totalCount, topThree,result);
							System.out.println("Profile is "+ userprofile.total_play_count + " Songs are "+ song +" "+ result);
							userCache.put(user,userprofile);
							}
							
						
							else {
								
							}
					}
						
					}
					sc3.close();
}
				sc2.close(); 
		}
	
				catch (FileNotFoundException e) {
					System.out.println(new File(".").getAbsolutePath());
					System.out.println("no file");
					e.printStackTrace();
					}

			
	}
	
}
