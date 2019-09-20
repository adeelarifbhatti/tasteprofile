package Implementation;

import TasteProfile.TopThreeUsers;

public class TopThreeUsersImpl extends TopThreeUsers{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TopThreeUsersImpl() {
		
	}
	public TopThreeUsersImpl(TasteProfile.UserCounter topThreeUsers[]) {
		System.out.println("inside topimpl");
		this.topThreeUsers=topThreeUsers;
		
	}

}
