package main.java.testing;

import javax.swing.plaf.multi.MultiScrollBarUI;

import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.system.constants.DBConstants;

public class TestUser {
	
	
	
	
	public static void main(String[] args) {
		User user1 = new User();
		System.out.println("test display field of document:");
		//map1.put("a", "11");map1.put("b", "222");map1.put("c", "333"); map1.put("d", "444");
		
		//user1.displayDocumentFields("map1");
		MultiUsersSystem.createNewUser("user7", "sss@sssss", "ccccccc", false);
		User user2 = MultiUsersSystem.login("user7", "ccccccc"); 
		user2.displayDocumentFields(DBConstants.MESSAGES_KEY);
		
		//test if it's unauthorized login 
		User user3 = new User("user15", "1522s322c222@3555ssr", "1cccccc");
		user3.setApproved(true);
		System.out.println("HERE");
		user3.displayDocumentFields(DBConstants.MESSAGES_KEY);
		user3.displayMessages();
		user3.updateLevel2Field("messages", "msg1", "some new message4444");
		System.out.println("After update: ");
		user3.displayMessages();
		
		System.out.println("test the changeMessage method:");
		user3.changeMessage("msg2",  "SOME NEW VALUE TO MESSAGE 2");
		user3.displayMessages();
		
		//reset the value 
		user3.updateLevel2Field("messages", "msg1", "This is message 1 of user - user23");
		user3.updateLevel2Field("messages", "msg2", "This is message 2 of user - user23");
		System.out.println("test the log off");
		user3.logOff(); 
		user3.changeMessage("msg2",  "SOME NEW VALUE TO MESSAGE 2");
		user3.displayMessages();
		user3.displayDocumentFields("messages");
		user3.displayMessages();
		user3.updateLevel2Field("messages", "msg1", "some new message4444");
		System.out.println("After update: ");
		user3.displayMessages();
		
		System.out.println("test the changeMessage method:");
		user3.changeMessage("msg2",  "SOME NEW VALUE TO MESSAGE 2");
		user3.displayMessages();
		
		//reset the value 
		user3.updateLevel2Field("messages", "msg1", "This is message 1 of user - user23");
		user3.updateLevel2Field("messages", "msg2", "This is message 2 of user - user23");
		MultiUsersSystem.createNewUser("user25", "user23@u", "a111", false);
		user3 = MultiUsersSystem.login("user25", "a111"); 
		user3.changeMessage(DBConstants.USER_MESSAGES_KEYS[1],  "SOME NEW VALUE TO MESSAGE 2");
		user3.displayMessages();
		
		//reset the value 
		user3.updateLevel2Field("messages", "msg1", "This is message 1 of user - user23");
		user3.updateLevel2Field("messages", "msg2", "This is message 2 of user - user23");
		
		System.out.println("trying to change email...\nbefore:\n");
		System.out.println(user3);
		MultiUsersSystem.displayAllUsers();
		user3.setEmail("newEmail@user25");
		user3.setPassword("1234Passwrod");
		System.out.println("after:");
		MultiUsersSystem.displayAllUsers();
		System.out.println(user3);
		
		
	}

}
