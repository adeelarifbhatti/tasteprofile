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
	
	/*  String user_id = null;
	  int total_play_count ;
	  SongCounter songs[] = null;
	  TopThreeSongs top_three_songs = null; */
	  UserProfile userProfile;
	  
		public boolean checkLocalCache(String user_id) {
			if(clientCache.containsKey(user_id))
			return true;
			else 
			return false;
		}
		
		public int getClientCache(String user_id) {
			return clientCache.get(user_id).total_play_count;
			}
		public TopThreeSongs getClientCacheSongs(String user_id) {
			return clientCache.get(user_id).top_three_songs;
			}
	  
	public void setUserPrfServer(String user_id) {
		profile.getUserProfile(user_id);
		if(profile.getUserProfile(user_id)!=null){
			UserProfileImpl userPrfl = new UserProfileImpl(user_id,clientCache.get(user_id).total_play_count,clientCache.get(user_id).songs,clientCache.get(user_id).top_three_songs);
			clientCache.put(user_id, userPrfl);
			
		}
	}
	



} //End of Class
