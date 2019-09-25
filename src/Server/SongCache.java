package Server;
import TasteProfile.SongProfile;
import TasteProfile.TopThreeUsers;


import java.util.HashMap;

import Implementation.SongProfileImpl;

public class SongCache {
	
	String song_id;
	HashMap<String,SongProfile> songCache = new HashMap<String,SongProfile>();
	
	public boolean checkSongCache(String song_id) {
		if(songCache.containsKey(song_id)) {
			return true;
		}
		else 
			return false;
	}
	public int getSongCache(String song_id) {
		
		if(songCache.containsKey(song_id)) {
			if(songCache.get(song_id).total_play_count != 0) { 
				return songCache.get(song_id).total_play_count;
			}
		}
	return 0;
	}
	
	public void setSongCache(String song_id, int totalCount) {
				SongProfileImpl songPrfl = new SongProfileImpl(song_id, totalCount);
				songCache.put(song_id, songPrfl);

			
	}
	public void setSongCacheTopThree(String song_id, TopThreeUsers topthreeusers) {
		SongProfileImpl songPrfl = new SongProfileImpl(song_id,topthreeusers);
		songCache.replace(song_id, songPrfl);

	
    }
	public TopThreeUsers getSongCacheTopThree(String song_id) {
		if(songCache.containsKey(song_id)) {
			if(!songCache.get(song_id).top_three_users.equals(null)) { 
				return songCache.get(song_id).top_three_users;
			}
		}
		return null;
		
    }
	
}
