package Server;

import java.util.Comparator;

import Implementation.SongCounterImpl;

class SongSorting implements Comparator<SongCounterImpl> 
{ 
	@Override
	public int compare(SongCounterImpl a, SongCounterImpl u) {
		if(a.songid_play_time < u.songid_play_time)
			return 1;
		else return -1;

	} 
}
