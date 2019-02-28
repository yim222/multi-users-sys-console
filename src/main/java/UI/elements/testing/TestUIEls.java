package main.java.UI.elements.testing;

import main.java.UI.elements.UIEls;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;

public class TestUIEls {
	public static void main(String[] args){
		UIEls instance = new UIEls();
		System.out.println("instance.displayUserDetailsByName(userName);");
		instance.displayUserDetailsByName();
		//MultiUsersSystem.login("lingar", "f222");
		
		/*
		String userName = "lingar", password = "1234r";
		User user = MultiUsersSystem.login(userName, password);
		
		System.out.println("\n Testing changeEmail function");
		instance.changeEmail(user);  
		System.out.println("\n Testing changePassword function");
		instance.changePassword(user);  
		*/
		//instance.initialRunning(); // Will work only in initial running. 
		User user = MultiUsersSystem.login("lingar", "kkk");
		instance.changePassword(user);  
		
		instance.changeMessages(user);
		
	
	}
}
