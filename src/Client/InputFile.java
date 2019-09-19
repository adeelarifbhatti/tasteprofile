package Client;

import java.io.File;
import java.util.Scanner;
import TasteProfile.Profiler;


public class InputFile {
	public String method;
	public String argument1;
	public String argument2;
	int result;
	Profiler profile;
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
			    	profile.getTimesPlayedByUser(argument1,argument2);
			    	System.out.println("Method getTimesPlayedByUser and Result is " +  result);
			    	
			   }
			   else if (method.equals("getTimesPlayed")) {
			    	int result= profile.getTimesPlayed(argument1);
			    	 System.out.println("Method getTimesPlayedByUser and Result is " +  result);
			    }
			    else if (method=="getTopThreeUsersBySong") {
			    	profile.getTopThreeUsersBySong(argument1);
			    	System.out.println("Method getTopThreeUsersBySong and Result is " +  result);
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
