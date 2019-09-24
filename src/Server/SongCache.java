package Server;
import TasteProfile.SongProfile;
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
			if(songCache.get(song_id).total_play_count != 0) { //Check if the song is in Cachee but without the totalplaycount 
				return songCache.get(song_id).total_play_count;
			}
		}
	return 0;
	}
	
	public void setSongCache(String song_id, int totalCount) {
		//Here the second argument is null because we are going to fix it in gettoptheeusersbysong.
				SongProfileImpl songPrfl = new SongProfileImpl(totalCount,songCache.get(song_id).top_three_users);
				songCache.replace(song_id, songPrfl);

			
	}
}
