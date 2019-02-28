package main.java.UI.objects;

import main.java.UI.elements.UIEls;
import main.java.objects.Admin;
import main.java.objects.MultiUsersSystem;

public class InitialUI implements UIObject{
	
	UIEls uiEls = new UIEls();
	
	@Override
	public void mainUI() {
		if (MultiUsersSystem.isInitialRunning()){
			
			Admin admin = uiEls.initialRunning();
			System.out.println("Great you hvae defined the primary super-admin user... ");
			System.out.println("Please consider to test out and check our system. \n and feel free to call me about it...\n"
					+ "And for any other issue that related to contact me yimprogramming@gmail.com  ");
			
			System.out.println("\nGoing to adminUI, AdminUI(admin)  ");
			AdminUI adminUI = new AdminUI(admin);
			adminUI.mainUI();
			
			
		}
		else{
			System.out.println("Do nothing.");
			//.... 
		}
		
	}
	
	
	
	
}
