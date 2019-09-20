package Client;

import java.io.FileWriter;
import java.io.Writer;



public class OutputFile {
	

	public static void outputWriter(String method, String argument1, String argument2, int result) {
		
		try {
			
			Writer writer = new FileWriter("output.txt",true);
			writer.write("Method is " + method + " 1stArgument is " + argument1 + " 2nd Argument is " + argument2 + " and the Result is " + result+"\n");
			writer.close();
		}

		catch(Exception e) {
		System.err.println("Error:" + e.getMessage());
		e.printStackTrace(System.out);
	}
	}
}
