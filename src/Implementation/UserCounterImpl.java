package Implementation;

import TasteProfile.UserCounter;

public class UserCounterImpl extends UserCounter implements Comparable<UserCounterImpl>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserCounterImpl() {
		
	}
	public UserCounterImpl(String user_id, int songid_play_time) {
		this.user_id = user_id;
		this.songid_play_time = songid_play_time;
	}
	
//create this method so we can sort the arraylist
	@Override
	public int compareTo(UserCounterImpl u) {
		if(this.songid_play_time < u.songid_play_time) {
			return 1;
		}
		else if (this.songid_play_time == u.songid_play_time){
			return 0;
		}
		return -1;
	}
}