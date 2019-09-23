package Client;

import java.io.FileWriter;
import java.io.Writer;

import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;



public class OutputFile {
	

	public static void Writer_TimesPlayed(String method, String argument1, int result, long totalTime) {
		
		try {
			if (result !=0){
			Writer writer = new FileWriter("output.txt",true);
			writer.write("Song " + argument1 + " Played  " + result + " times " + totalTime +"ms\n" );
			writer.close();
			}
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
	
	public static void Writer_UserPlayed(String method, String argument1, String argument2, int result, long totalTime) {
		
		try {
			if (result !=0){
			Writer writer = new FileWriter("output.txt",true);
			writer.write("Song " + argument1 + " Played  " + result + " times " +" By User "+ argument2  +" "+ totalTime +"ms\n" );
			writer.close();
			}
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
	
public static void outputWriter_topthreeusers(String method, String argument1, TopThreeUsers topThreeUsers, long totalTime) {
		
	try {

		if (topThreeUsers!=null ){
		Writer writer = new FileWriter("TopThreeUsers.txt",true);
		for (int i =0; i<3; i++)
		writer.write("TOPTHREEUSERS for Song" + argument1 + " are Users  " + topThreeUsers.topThreeUsers[i].user_id + " times played " +topThreeUsers.topThreeUsers[i].songid_play_time+" By User "+ totalTime +"ms\n" );
		writer.close();
		}
	}

	catch(Exception e) {
	System.err.println("Error:" + e.getMessage());
	e.printStackTrace(System.out);
}
	}





public static void outputWriter(String method, String argument1, String argument2, TopThreeSongs topThreeSongs) {
	
	try {
		
		Writer writer = new FileWriter("output.txt",true);
		writer.write("Method is " + method + " 1stArgument is " + argument1 + " 2nd Argument is " + argument2 + " and the Result is " + topThreeSongs+"\n");
		writer.close();
	}

	catch(Exception e) {
	System.err.println("Error:" + e.getMessage());
	e.printStackTrace(System.out);
}
}
}
