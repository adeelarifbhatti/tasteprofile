package Client;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Implementation.SongCounterImpl;
import TasteProfile.Profiler;
import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;
import TasteProfile.UserProfile;


public class InputFile {
	public String method;
	public String argument1;
	public String argument2;
	Profiler profile;
	HashMap<String,UserProfile> clientC = new HashMap<>(1000);
	UserProfile userProfile;
	

	

		public void fileRead(Profiler profile) {
			this.profile=profile;
			
		
		try {
			
			Scanner sc = new Scanner(new File("input.txt"));
			int lineNumber= 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				++lineNumber;
				String[] parts = line.split("\t");
				String method = parts[0];
				argument1 = parts[1];
			    argument2 = parts.length > 2 ? parts[2] : null;

			   
			   System.out.println(lineNumber + " Method is "+ method + " With  Argument  " + argument1 + " 2nd Argument is " + argument2);
		
			   if (method.equals("getTimesPlayedByUser")) {
				   //Comment following section till it says "EnD"  to disable the cache     ####################################################
				   userProfile=profile.getUserProfile(argument1);
				   if (userProfile!=null) {
					    
					   clientC.put(argument1, userProfile);
					   System.out.println("  UserID is   "+ userProfile.user_id + " userProfile play time is " +userProfile.total_play_count);
					   for(int i = 0; i < clientC.get(argument1).songs.length ; i++){
							if(clientC.get(argument1).songs[i].song_id.equals(argument2)){
							 	Timer time=new Timer();
							 	time.setStart(System.nanoTime());
							 	int result= clientC.get(argument1).songs[i].songid_play_time;
							 	time.setFinish(System.nanoTime());
							    OutputFile.Writer_UserPlayed(method, argument2, argument1, result, time.timing());
	System.out.println("#############" + clientC.get(argument1).songs[i] +" ###  Time is ## " + clientC.get(argument1).songs[i].songid_play_time);
							}
							  						
					   }
						

				   }
					  
				// EnD              ##################################################################################
				 // also comment following else line with { } brakets 
				else { 
				
					Timer time=new Timer();
				   time.setStart(System.nanoTime());
			    	int result=profile.getTimesPlayedByUser(argument2,argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.Writer_UserPlayed(method, argument2, argument1, result, time.timing());
			    	// also comment following else line with { } brakets #####################################################
				}
			    	
			   }
			   if (method.equals("getTimesPlayed")) {
			    	
							   Timer time=new Timer();
					    	time.setStart(System.nanoTime());
					    	int result= profile.getTimesPlayed(argument1);
					    	time.setFinish(System.nanoTime());
					    	OutputFile.Writer_TimesPlayed(method, argument1, result, time.timing());
					    	
			    }
			   else if (method.equals("getTopThreeUsersBySong")) {
			    	Timer time=new Timer();
			    	time.setStart(System.nanoTime());
			    	TopThreeUsers result=profile.getTopThreeUsersBySong(argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.outputWriter_topthreeusers(method, argument1, result, time.timing());
			    }
			    
			    
			    
			    
			    
			    else if (method.equals("getTopThreeSongsByUser")) {
			    	
			    	if(!clientC.containsKey(argument1)) {
			    		
			    
					   userProfile=profile.getUserProfile(argument1);
					   if (userProfile!=null) {
						   // To disable the client cache, comment the following line
						 //  clientC.put(argument1, userProfile);
						   System.out.println(userProfile.total_play_count +"userProfile play time is "+ userProfile.user_id);
					   }
			    	}
			    	 if(clientC.containsKey(argument1)) {
			    		 Timer time=new Timer();
			    		 time.setStart(System.nanoTime());
			    		 TopThreeSongs result=clientC.get(argument1).top_three_songs;
			    		 time.setFinish(System.nanoTime());
			    		 OutputFile.outputWriter_topthreesongs(method, argument1, result, time.timing());
					
				   }
			    else {
			    		Timer time=new Timer();
			    		time.setStart(System.nanoTime());
			    		TopThreeSongs result=profile.getTopThreeSongsByUser(argument1);
			    		time.setFinish(System.nanoTime());
			    		OutputFile.outputWriter_topthreesongs(method, argument1, result, time.timing());
			    	 }
			    }
			}
			sc.close();
		}
		   catch(NullPointerException e) 
        	{ 
			   
            System.out.print("NullPointerException Caught"); 
            
        	} 
			
			catch(Exception e) {
			System.err.println("Error:" + e.getMessage());
			e.printStackTrace(System.out);
		}

	
	

		}
}
