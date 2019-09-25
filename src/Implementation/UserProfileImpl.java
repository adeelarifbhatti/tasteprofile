package Implementation;

import TasteProfile.UserProfile;

public class UserProfileImpl extends UserProfile{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserProfileImpl() {
		
	}
	public UserProfileImpl(String user_id, int total_play_count, TasteProfile.SongCounter songs[], TasteProfile.TopThreeSongs top_three_songs ) {
		this.user_id = user_id;
		this.total_play_count = total_play_count;
		this.songs = songs;
		this.top_three_songs = top_three_songs;
		
	}
	public UserProfileImpl(String user_id, TasteProfile.TopThreeSongs top_three_songs) {

		this.top_three_songs = top_three_songs;
		
	}
	public UserProfileImpl(String user_id, int total_play_count) {

		this.total_play_count = total_play_count;
		
	}
	public UserProfileImpl(String user_id, TasteProfile.SongCounter songs[]) {

		this.songs = songs;
		
	}
	public UserProfileImpl(String user_id,int total_play_count, TasteProfile.SongCounter songs[]) {


		this.songs=songs;
		this.total_play_count = total_play_count;
		
	}
	
	



}
