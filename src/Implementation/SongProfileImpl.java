package Implementation;

import TasteProfile.SongProfile;

public class SongProfileImpl extends SongProfile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SongProfileImpl() {
		
	}
	public SongProfileImpl(int total_play_count, TasteProfile.TopThreeUsers top_three_users) {
		this.total_play_count = total_play_count;
		this.top_three_users = top_three_users;
		
	}

}
