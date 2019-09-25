package Server;
import TasteProfile.UserProfile;
import TasteProfile.TopThreeSongs;
import TasteProfile.SongCounter;
import Implementation.UserProfileImpl;

import java.util.ArrayList;
import java.util.HashMap;



public class UserCache {
	ArrayList <UserProfile> userPrfl = new ArrayList<UserProfile>(1000);
	String user_id;
	HashMap<String,UserProfile> userCache = new HashMap<String,UserProfile>();
	
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
	
	public void setuserCache(String user_id, int totalCount) {
				UserProfileImpl UserPrfl = new UserProfileImpl(user_id, totalCount);
				userCache.put(user_id, UserPrfl);

			
	}
	
	public void setuserCacheSongs(String user_id, TasteProfile.SongCounter songs[]) {
		UserProfileImpl UserPrfl = new UserProfileImpl(user_id,songs);
		userCache.put(user_id, UserPrfl);
		
	}
	
	public int getuserCacheSongs(String user_id,String song_id) {
		// This needs fixing
		 if(userCache.get(user_id).equals(user_id) && userCache.get(song_id).equals(song_id)){
			 return userCache.get(user_id).total_play_count;
			
		 }
		 return -1;
		
	}
		
	public void setuserCacheTopThree(String user_id, TopThreeSongs topthreesongs) {
		UserProfileImpl UserPrfl = new UserProfileImpl(user_id,topthreesongs);
		userCache.put(user_id, UserPrfl);

	
    }
	public TopThreeSongs getuserCacheTopThree(String user_id) {
		if(userCache.containsKey(user_id)) {
			if(!userCache.get(user_id).top_three_songs.equals(null)) { 
				return userCache.get(user_id).top_three_songs;
			}
		}
		return null;
		
    }
	
	public UserProfile getUserProfCache(String user_id) {
		if(userCache.containsKey(user_id)) {
			if(!userCache.get(user_id).top_three_songs.equals(null) && !userCache.get(user_id).songs.equals(null) && userCache.get(user_id).total_play_count!=0) { 
				UserProfileImpl userPrfl = new UserProfileImpl(user_id,userCache.get(user_id).total_play_count,userCache.get(user_id).songs, userCache.get(user_id).top_three_songs);
				return userPrfl;
			}
		}
		return null;
		
    }
	
}
