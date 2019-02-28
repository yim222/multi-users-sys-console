package main.java.UI.elements.testing;

import main.java.UI.elements.OutputEls;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;

public class TestOutputEls {
	
	
	public static void main(String[] args) {
		
		//create instance
		
		OutputEls instance = new OutputEls();
		User user = MultiUsersSystem.login("a", "a");
		//admin user:
		
		User user2 = MultiUsersSystem.login("user15", "1cccccc");
		
		
		//System.out.println("\nTest ");
		System.out.println("\nTest displayWelcomeMessage()");
		instance.displayWelcomeMessage();
		System.out.println("\nTest changing output");
		instance.changeField("status");
		instance.changeEmail();
		instance.changePassword();
		System.out.println(instance.systemMessages1.get("message1"));
		
		System.out.println(instance.userTemplate(user));
		System.out.println("Test displayUserTemplate()\n");
		instance.displayUserTemplate(user);
		System.out.println("*****************");
		//System.out.println(user2.isAdmin());
		instance.displayUserTemplate(user2);
		instance.systemMessages1.put("a", "a");
		
		
	}
	
}
