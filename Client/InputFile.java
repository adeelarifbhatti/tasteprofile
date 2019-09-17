package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile {
	private Scanner sc;
	public String method;
	public String argument1;
	public String argument2;
	public void fileRead() throws FileNotFoundException{
		
			FileInputStream inputStream = new FileInputStream("input.txt");
			sc = new Scanner(inputStream, "UTF-8");
			int lineNumber= 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				++lineNumber;
				String[] parts = line.split("\t");
				String method = parts[0];
				String argument1 = parts[1];
			    String argument2 = parts.length > 2 ? parts[2] : null;
			   
			    System.out.println(lineNumber + " Method is "+ method + " With  Argument  " + argument1 + " 2nd Argument is " + argument2);
				
			}
		
		
	}
	/*public static void main(String[] args) throws FileNotFoundException {
		InputFile input= new InputFile();
		input.fileRead();
	}
	*/

}
