package Client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import TasteProfile.Profiler;
import TasteProfile.ProfilerHelper;
import TasteProfile.TopThreeUsers;

public class ProfileClient {
	public static void main (String[] args) {
		try {
			
	
		ORB orb = ORB.init(args,null);
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		String name =  "Profile";
		Profiler profileRef = ProfilerHelper.narrow(ncRef.resolve_str(name));
		
		/*Call the getTimesPlayed method from server*/
//		int timesPlayed = profileRef.getTimesPlayed("SOAAADD12AB018A9DD");
//		System.out.println("Song SOAAADD12AB018A9DD played: " + timesPlayed);
		//TopThreeUsers t3 = profileref.getTopThreeUsersBySong1("SOAAADD12AB018A9DD");
		//InputFile inputFile = profileR.fileRead();
		//InputFile inputFile = new InputFile();
		
		//inputFile.fileRead(profileRef);
		//System.out.println(profileRef);


		}
		catch(Exception e) {
			System.out.println("Profiler Client Error"+e.getMessage());
			e.printStackTrace(System.out);
		}
		
	}

}
