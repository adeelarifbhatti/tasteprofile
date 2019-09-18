package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import TasteProfile.Profiler;

public class InputFile {
	private Scanner sc;
	public String method;
	public String argument1;
	public String argument2;
	int result;
	Profiler profile;
		public int fileRead(Profiler profile) {
			this.profile=profile;
		 try {
			/*FileInputStream inputStream = new FileInputStream("input.txt");
			sc = new Scanner(inputStream, "UTF-8");
			int lineNumber= 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				++lineNumber;
				String[] parts = line.split("\t");
				String method = parts[0];
				argument1 = parts[1];
			    argument2 = parts.length > 2 ? parts[2] : null;
			   
			    System.out.println(lineNumber + " Method is "+ method + " With  Argument  " + argument1 + " 2nd Argument is " + argument2);
			    if (method=="getTimesPlayedByUser")
			    	profile.getTimesPlayedByUser(argument1,argument2);
			    else if (method=="getTimesPlayed")
			    	 result = profile.getTimesPlayed(argument1);
			     else if (method=="getTopThreeUsersBySong")
			    	profile.getTopThreeUsersBySong(argument1);
			}*/
			result = profile.getTimesPlayed("SOAAADD12AB018A9DD");
			System.out.println(result);
			
			
		} catch(Exception e) {
			System.err.println("Error:" + e.getMessage());
			e.printStackTrace(System.out);
		}
		 return result;
	
	}
	
	/*public static void main(String[] args) throws FileNotFoundException {
		InputFile input= new InputFile();
		input.fileRead();
	}
	*/

}
