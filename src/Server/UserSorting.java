package Server;

import java.util.Comparator;

import Implementation.UserCounterImpl;

class UserSorting implements Comparator<UserCounterImpl> 
{ 
	@Override
	public int compare(UserCounterImpl a, UserCounterImpl u) {
		if(a.songid_play_time < u.songid_play_time)
			return 1;
		else return -1;

	} 
}
