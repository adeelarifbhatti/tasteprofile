package Implementation;

import TasteProfile.SongCounter;

public class SongCounterImpl extends SongCounter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SongCounterImpl() {
	
	}
	
	public SongCounterImpl(String song_id, int songid_play_time) {
		this.song_id = song_id;
		this.songid_play_time = songid_play_time;
		
		
	}
}
