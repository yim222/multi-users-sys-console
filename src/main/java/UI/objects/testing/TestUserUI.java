package main.java.UI.objects.testing;

import main.java.UI.objects.UserUI;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;

public class TestUserUI {
	
	public static void main(String[] args) {
		User user = MultiUsersSystem.login("user31", "aaa");
		//admin user:
		
		User user2 = MultiUsersSystem.login("user15", "1cccccc");
		//User user3 = MultiUsersSystem.login("test1", "t");
	
		UserUI userUI = new UserUI(user);
		//System.out.println("0 or 7 ");
		//userUI.manageOptions();
		userUI.mainUI();
		
	}

}
