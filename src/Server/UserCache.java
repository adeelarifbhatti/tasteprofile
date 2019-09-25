package Server;
import TasteProfile.UserProfile;
import TasteProfile.TopThreeSongs;
import java.util.HashMap;

import Implementation.UserProfileImpl;

public class UserCache {
	
	String User_id;
	HashMap<String,UserProfile> UserCache = new HashMap<String,UserProfile>();
	
	public boolean checkUserCache(String User_id) {
		if(UserCache.containsKey(User_id)) {
			return true;
		}
		else 
			return false;
	}
	public int getUserCache(String User_id) {
		
		if(UserCache.containsKey(User_id)) {
			if(UserCache.get(User_id).total_play_count != 0) { 
				return UserCache.get(User_id).total_play_count;
			}
		}
	return 0;
	}
	
	public void setUserCache(String User_id, int totalCount) {
				UserProfileImpl UserPrfl = new UserProfileImpl(User_id, totalCount);
				UserCache.put(User_id, UserPrfl);

			
	}
	public void setUserCacheTopThree(String User_id, TopThreeSongs topthreesongs) {
		UserProfileImpl UserPrfl = new UserProfileImpl(User_id,topthreesongs);
		UserCache.put(User_id, UserPrfl);

	
    }
	public TopThreeSongs getUserCacheTopThree(String User_id) {
		if(UserCache.containsKey(User_id)) {
			if(!UserCache.get(User_id).top_three_songs.equals(null)) { 
				return UserCache.get(User_id).top_three_songs;
			}
		}
		return null;
		
    }
	
}
