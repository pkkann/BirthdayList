package dk.pkkann.birthdaylist.model;

public class FriendRegisterSingle {
	
	private static FriendRegister instance;
	
	private FriendRegisterSingle() {
		
	}
	
	public static FriendRegister getInstance() {
		if(instance != null) {
			return instance;
		} else {
			makeInstance();
			return instance;
		}
	}
	
	private static void makeInstance() {
		instance = new FriendRegister();
	}

}
