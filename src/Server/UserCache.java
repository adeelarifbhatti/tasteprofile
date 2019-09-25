package Server;
import TasteProfile.UserProfile;
import TasteProfile.TopThreeSongs;
import Implementation.UserProfileImpl;

import java.util.ArrayList;
import java.util.HashMap;



public class UserCache {
	ArrayList <UserProfile> userPrfl = new ArrayList<UserProfile>(1000);
	String user_id;
	HashMap<String,UserProfile> UserCache = new HashMap<String,UserProfile>();
	
	public boolean checkUserCache(String user_id) {
		if(UserCache.containsKey(user_id)) {
			return true;
		}
		else 
			return false;
	}
	public int getUserCache(String user_id) {
		
		if(UserCache.containsKey(user_id)) {
			if(UserCache.get(user_id).total_play_count != 0) { 
				return UserCache.get(user_id).total_play_count;
			}
		}
	return 0;
	}
	
	public void setUserCache(String user_id, int totalCount) {
				UserProfileImpl UserPrfl = new UserProfileImpl(user_id, totalCount);
				UserCache.put(user_id, UserPrfl);

			
	}
	public void setUserCacheTopThree(String user_id, TopThreeSongs topthreesongs) {
		UserProfileImpl UserPrfl = new UserProfileImpl(user_id,topthreesongs);
		UserCache.put(user_id, UserPrfl);

	
    }
	public TopThreeSongs getUserCacheTopThree(String user_id) {
		if(UserCache.containsKey(user_id)) {
			if(!UserCache.get(user_id).top_three_songs.equals(null)) { 
				return UserCache.get(user_id).top_three_songs;
			}
		}
		return null;
		
    }
	
	public UserProfile getUserProfCache(String user_id) {
		if(UserCache.containsKey(user_id)) {
			if(!UserCache.get(user_id).top_three_songs.equals(null) && !UserCache.get(user_id).songs.equals(null) && UserCache.get(user_id).total_play_count!=0) { 
				UserProfileImpl userPrfl = new UserProfileImpl(user_id,UserCache.get(user_id).total_play_count,UserCache.get(user_id).songs, UserCache.get(user_id).top_three_songs);
				return userPrfl;
			}
		}
		return null;
		
    }
	
}
