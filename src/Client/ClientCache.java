package Client;

import TasteProfile.UserProfile;
import TasteProfile.TopThreeSongs;


import java.util.HashMap;



public class ClientCache {

	HashMap<String,UserProfile> clientCache = new HashMap<String,UserProfile>();
 
		public boolean checkLocalCache(String user_id) {
			if(clientCache.containsKey(user_id))
			return true;
			else 
			return false;
		}
	  
	public int getUserCache(String user_id,String song_id) {
		
			if(clientCache.get(song_id).total_play_count != 0) { 
				return clientCache.get(user_id).total_play_count;
			}
		
	return 0;
	}
	public int getUserCache(String user_id) {
		
		if(clientCache.get(user_id).total_play_count != 0) { 
			return clientCache.get(user_id).total_play_count;
		}

	
		return 0;
	}
	public TopThreeSongs getTTSongs(String user_id) {
		
		if(clientCache.get(user_id).total_play_count != 0) { 
			return clientCache.get(user_id).top_three_songs;
	}
		return null;
	}

	
	public void getUserProfiles(){
		 clientCache.clone();
		 System.out.println(clientCache.clone());
		}
	



} //End of Class
