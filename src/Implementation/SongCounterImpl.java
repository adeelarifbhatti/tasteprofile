package Implementation;

import TasteProfile.SongCounter;

public class SongCounterImpl extends SongCounter implements Comparable<SongCounterImpl>{

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
	
	@Override
	public int compareTo(SongCounterImpl u) {
		if(this.songid_play_time < u.songid_play_time) {
			return 1;
		}
		else if (this.songid_play_time == u.songid_play_time){
			return 0;
		}
		return -1;
	}
}
