package main.java.UI.objects.testing;

import main.java.UI.objects.AdminUI;
import main.java.objects.Admin;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;

public class TestAdminUI {
	public static void main(String[] args) {
		User user = MultiUsersSystem.login("a", "a");
		//admin user:
		
		Admin user2 = new Admin(MultiUsersSystem.login("superAdmin", "555A"));
		
	
		AdminUI userUI = new AdminUI(user2);
		//System.out.println("0 or 7 ");
		//userUI.manageOptions();
		userUI.mainUI();
		
	}
}
