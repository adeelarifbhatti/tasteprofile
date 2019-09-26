package Server;

import java.util.Comparator;

import Implementation.UserProfileImpl;

class ProfileSorting implements Comparator<UserProfileImpl> 
{ 
	@Override
	public int compare(UserProfileImpl a, UserProfileImpl u) {
		if(a.total_play_count < u.total_play_count)
			return 1;
		else return -1;

	} 
}
