package Server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import Implementation.TopThreeUsersImpl;
import TasteProfile.Profiler;
import TasteProfile.ProfilerHelper;
import TasteProfile.TopThreeSongs;
import TasteProfile.TopThreeUsers;

public class Server {
	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			Servant servant = new Servant();
			//int testint = servant.getTimesPlayed("SOAAADD12AB018A9DD");
			//System.out.println("testint is: " + testint);
			
//			TopThreeUsers threeUsertest =servant.getTopThreeUsersBySong1("SOAAADD12AB018A9DD");
//			for (int i =0; i<3; i++) {
//				System.out.println("users: " + threeUsertest.topThreeUsers[i].user_id);
//			}
			
			TopThreeSongs threeSongstest =servant.getTopThreeSongsByUser1("8cf21d682f872dbe91296690359af2010e5195ca");
			for (int i =0; i<3; i++) {
				System.out.println("songs: " + threeSongstest.topThreeSongs[i].song_id);
			}
			
			 org.omg.CORBA.Object ref = rootpoa.servant_to_reference(servant); Profiler
			 href=ProfilerHelper.narrow(ref);
			 
			 org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			 NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			 
			 String name ="Profile"; NameComponent path[] = ncRef.to_name(name);
			 ncRef.rebind(path, href); orb.run();
			 

		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
			e.printStackTrace(System.out);
		}
	}

}
