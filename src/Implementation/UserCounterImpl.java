package Implementation;

import TasteProfile.UserCounter;

public class UserCounterImpl extends UserCounter{

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
}
