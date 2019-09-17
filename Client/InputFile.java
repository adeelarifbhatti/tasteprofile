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
	Profiler profile;
		public Profiler fileRead() throws FileNotFoundException{
		
			FileInputStream inputStream = new FileInputStream("input.txt");
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
			    	profile.getTimesPlayed(argument1);
			    else if (method=="getTopThreeUsersBySong")
			    	profile.getTopThreeUsersBySong(argument1);
			}
			return profile;
	
	}
	
	/*public static void main(String[] args) throws FileNotFoundException {
		InputFile input= new InputFile();
		input.fileRead();
	}
	*/

}
