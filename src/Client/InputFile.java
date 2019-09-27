package Client;

import java.io.File;
import java.util.Scanner;
import TasteProfile.Profiler;
import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;
import TasteProfile.UserProfile;


public class InputFile {
	public String method;
	public String argument1;
	public String argument2;
	Profiler profile;
	boolean cache =  true;
	ClientCache clientCache= new ClientCache();

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

				
					if(clientCache.checkLocalCache(argument1)) {
						
				  	Timer time=new Timer();
				   time.setStart(System.nanoTime());
				   int result= clientCache.getUserCache(argument1,argument2);
				   time.setFinish(System.nanoTime());
			    	OutputFile.Writer_CacheUserPlayed(method, argument2, argument1, result, time.timing());
					}
				   
				  	Timer time=new Timer();
				   time.setStart(System.nanoTime());
			    	int result=profile.getTimesPlayedByUser(argument2,argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.Writer_UserPlayed(method, argument2, argument1, result, time.timing());
			    	
			   }
			  
			    if (method.equals("getTimesPlayed")) {
			    	 if(clientCache.checkLocalCache(argument1)) {
			    	
			    	Timer time=new Timer();
			    	time.setStart(System.nanoTime());
			    	int result= clientCache.getUserCache(argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.Writer_CacheTimesPlayed(method, argument1, result, time.timing());
			    }
					 else {
						   Timer time=new Timer();
					    	time.setStart(System.nanoTime());
					    	int result= profile.getTimesPlayed(argument1);
					    	time.setFinish(System.nanoTime());
					    	OutputFile.Writer_TimesPlayed(method, argument1, result, time.timing());
					    
					   }
			    
			    }
			    
			    

			    else if (method.equals("getTopThreeUsersBySong")) {
			    	Timer time=new Timer();
			    	time.setStart(System.nanoTime());
			    	TopThreeUsers result=profile.getTopThreeUsersBySong(argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.outputWriter_topthreeusers(method, argument1, result, time.timing());
			    }
			    
			    
			    
			    
			    
			    else if (method.equals("getTopThreeSongsByUser")) {
				   if (cache) {
					ClientCache clientCache= new ClientCache();
					if(clientCache.checkLocalCache(argument1)) {
					
					Timer time=new Timer();
			    	time.setStart(System.nanoTime());
			    	TopThreeSongs result=clientCache.getTTSongs(argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.outputWriter_topthreesongs(method, argument1, result, time.timing());
					}
				   }
			    	Timer time=new Timer();
			    	time.setStart(System.nanoTime());
			    	TopThreeSongs result=profile.getTopThreeSongsByUser(argument1);
			    	time.setFinish(System.nanoTime());
			    	OutputFile.outputWriter_topthreesongs(method, argument1, result, time.timing());
			    }
			}
			sc.close();
		}

			
			catch(Exception e) {
			System.err.println("Error:" + e.getMessage());
			e.printStackTrace(System.out);
		}

	
	

		}
}
