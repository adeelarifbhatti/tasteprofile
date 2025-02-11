package Client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import TasteProfile.Profiler;
import TasteProfile.ProfilerHelper;

public class ProfileClient {
	public static void main (String[] args) {
		try {
			
	
		ORB orb = ORB.init(args,null);
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		String name =  "Profile";
		Profiler profileRef = ProfilerHelper.narrow(ncRef.resolve_str(name));
		InputFile inputFile = new InputFile();
		inputFile.fileRead(profileRef);
		ClientCache clientCache= new ClientCache();
		clientCache.getUserProfiles();
		}
		catch(Exception e) {
			System.out.println("Profiler Client Error"+e.getMessage());
			e.printStackTrace(System.out);
		}
		
	}

}
