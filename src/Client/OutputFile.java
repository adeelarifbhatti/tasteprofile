package Client;

import java.io.FileWriter;
import java.io.Writer;

import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;



public class OutputFile {
	

	public static void Writer_TimesPlayed(String method, String argument1, int result, long totalTime) {
		
		try {
			//if (result !=0){
			Writer writer = new FileWriter("output.txt",true);
			writer.write("Song " + argument1 + " Played  " + result + " times " + totalTime +"ms\n" );
			writer.close();
			//}
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
	public static void Writer_CacheTimesPlayed(String method, String argument1, int result, long totalTime) {
		
		try {
			//if (result !=0){
			Writer writer = new FileWriter("clientside_cache_on.txt",true);
			writer.write("Song " + argument1 + " Played  " + result + " times " + totalTime +"ms\n" );
			writer.close();
			//}
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
	
	public static void Writer_UserPlayed(String method, String argument1, String argument2, int result, long totalTime) {
		
		try {
			//if (result !=0){
			Writer writer = new FileWriter("output.txt",true);
			writer.write("Song " + argument1 + " Played  " + result + " times " + totalTime +"ms\n" );
			writer.close();
			//}
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
	public static void Writer_CacheUserPlayed(String method, String argument1, String argument2, int result, long totalTime) {
		
		try {
			//if (result !=0){
			Writer writer = new FileWriter("clientside_cache_on.txt",true);
			writer.write("Song " + argument1 + " Played  " + result + " times " +" By User " + totalTime +"ms\n" );
			writer.close();
			//}
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
	
public static void outputWriter_topthreeusers(String method, String argument1, TopThreeUsers topThreeUsers, long totalTime) {
		
	try {

		//if (topThreeUsers.topThreeUsers.length>0){
		Writer writer = new FileWriter("topusers.txt",true);
		for (int i =0; i<topThreeUsers.topThreeUsers.length; i++)
		writer.write("Top users for Song " + argument1 + " are " + topThreeUsers.topThreeUsers[i].user_id + " times played " +topThreeUsers.topThreeUsers[i].songid_play_time+" By User "+ totalTime +"ms\n" );
		writer.close();
		//}
	}

	catch(Exception e) {
	System.err.println("Error:" + e.getMessage());
	e.printStackTrace(System.out);
	}
}


public static void outputWriter_topthreesongs(String method, String argument1, TopThreeSongs topThreeSongs, long totalTime) {
	
	try {

		//if (topThreeSongs.topThreeSongs.length>0){
		Writer writer = new FileWriter("topsongs.txt",true);
		for (int i =0; i<topThreeSongs.topThreeSongs.length; i++)
		writer.write("Top Songs " + argument1 + " are " + topThreeSongs.topThreeSongs[i].song_id + " times played " +topThreeSongs.topThreeSongs[i].songid_play_time+" By User "+ totalTime +"ms\n" );
		writer.close();
		//}
	}

	catch(Exception e) {
	System.err.println("Error:" + e.getMessage());
	e.printStackTrace(System.out);
	}
 }
}
