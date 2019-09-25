package Client;

import TasteProfile.UserProfile;
import TasteProfile.Profiler;
import TasteProfile.SongCounter;
import TasteProfile.TopThreeSongs;
import Implementation.UserProfileImpl;

import java.util.HashMap;



public class ClientCache {
	Profiler profile;
	HashMap<String,UserProfile> clientCache = new HashMap<String,UserProfile>();
	  UserProfile userProfile;
	  
		public boolean checkLocalCache(String user_id) {
			if(clientCache.containsKey(user_id))
			return true;
			else 
			return false;
		}
		public TopThreeSongs getClientCacheSongs(String user_id) {
			return clientCache.get(user_id).top_three_songs;
			}
		
		public int getClientCacheCount(String user_id) {
			return clientCache.get(user_id).total_play_count;
			}


} //End of Class
