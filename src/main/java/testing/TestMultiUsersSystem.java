package main.java.testing;

import main.java.objects.Admin;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.objects.UserInterface;

public class TestMultiUsersSystem {
public static void main(String[] args) {
		MultiUsersSystem.initialRunning();// UNCOMMENT IT FOR TEST INITIAL RUNNING. 
    	MultiUsersSystem a = MultiUsersSystem.getInstance();
    	System.out.println(a.test);
    	//DBConnector.connectDB("localhost", 27017);
    	a.setSomething(7);
    	System.out.println(a.test);
    	MultiUsersSystem b = MultiUsersSystem.getInstance();
    	System.out.println(b.test);
    	System.out.println( a == b);
    	
    	System.out.println("is valid user " + MultiUsersSystem.isValidUserName("rrr"));
    	
    	System.out.println(MultiUsersSystem.isValidEmail("dsaasdasd@a"));
    	System.out.println(MultiUsersSystem.createNewUser("user15", "1522s322c222@3555ssr", "1cccccc", true));
    	System.out.println(MultiUsersSystem.createNewUser("user14", "1422s322c222@3555ssr", "1cccccc", true));
    	System.out.println("Test authentication method");
    	
    	//System.out.println("user1 before = " + user1);
    	System.out.println(MultiUsersSystem.authenticateCredentials("user14", "1cccccc")); 
    	//System.out.println("user1 after = " + user1);
    	User user1 = MultiUsersSystem.login("user15", "1cccccc"); 
    	System.out.println(user1);
    	//MultiUsersSystem.deleteUserByName("user1");
    	//MultiUsersSystem.deleteAllUsers(); //DELETE ALL (UNCOMMENT IT) 
    	System.out.println("\nDisplay al user - ordinary way:\n");
    	
    	MultiUsersSystem.displayAllUsers();
    	System.out.println("\nDisplay all user - As Table:\n");
    	MultiUsersSystem.displayAllUsersAsTabel(true, 20);
    	System.out.println("\nDisplay all user - As Table and without password :\n");
    	MultiUsersSystem.displayAllUsersAsTabel(false, 20);
    	
    	System.out.println("Test delete one user - \nfirst creating new user user1024***first uncommenct it because it's demand approved****" );
    	MultiUsersSystem.createNewUser("user2024", "user1024@a", "abcd", true);
    	
    	MultiUsersSystem.displayAllUsers();
    	
    	System.out.println("\nNow delete him\n ");
    	
    	//MultiUsersSystem.deleteUserByName("user1024");/// - FOR TEST DELETE USER - UNCOMMNENT IT:
    	System.out.println("\nThe updated list: \n ");
    	MultiUsersSystem.displayAllUsers();
    	
    	
    	
    	System.out.println("\nTESTING ADMIN methods\n");
    	user1 = MultiUsersSystem.login("user14", "b"); 
    	System.out.println(user1);
    	User user2 = MultiUsersSystem.login("user14", "b"); 
    	System.out.println(user1);
    	
    	    	
    	user1.userTest();
    	user2.userTest();
    	user1.overriden();
    	user2.overriden();
    	
    	if(user1.isAdmin()){
    		
    		Admin admin = new Admin(user1);
    		admin.adminTest();
    	}
    	else{
    		System.out.println(user1.getName() + " is Not Admin");
    	}
    	
    	if(user2.isAdmin()){
    		
    		Admin admin = new Admin(user2);
    		admin.adminTest();
    	}
    	
    	else{
    		System.out.println(user2.getName() + " is Not Admin");
    	}
    	
    	//Admin admin1 = (Admin)MultiUsersSystem.login("user15", "1cccccc"); 
    	
    	String[] userDetails1 ={"user31","aaa", "a@a"};
    	String[] userDetails2 ={"user33", "zxyw1", "3b@b"};
    	System.out.println("\nTESTING CREATING (U NEED TO CHANGE TO NEW DETAILS ) \n");
    	
    	MultiUsersSystem.createNewUser(userDetails1[0], userDetails1[2], userDetails1[1], true);
    	MultiUsersSystem.createNewUser(userDetails2[0], userDetails2[2], userDetails2[1], false);
    	
    	MultiUsersSystem.displayAllUsers();
    	
    	
    	System.out.println("\nTESTING LOGIN\n");
    	User user11 = MultiUsersSystem.login(userDetails1[0], userDetails1[1]);
    	User user12 = MultiUsersSystem.login(userDetails2[0], userDetails2[1]);
    	
    	user11.userTest();
    	user12.userTest();
    	user11.overriden();
    	user12.overriden();
    	
    	User test1 = user11;
    	if (test1.isAdmin()){
    		
    		Admin admin = new Admin(test1);
    		admin.adminTest();
    	}else{
    		
    		System.out.println(test1.getName() + " Isn't admin") ;
    	}
    	test1 = user12;
    	if (test1.isAdmin()){
    		
    		Admin admin = new Admin(test1);
    		admin.adminTest();
    	}else{
    		
    		System.out.println(test1.getName() + " Isn't admin") ;
    	}
    	
    	System.out.println("Test get user Details by name");
    	MultiUsersSystem.getUserDetailsByName("user14");
    	System.out.println("Test getUserByName (return object)");
    	UserInterface userInterface1 = MultiUsersSystem.getUserByName("lingar");//admin
    	//UserInterface userInterface1 = MultiUsersSystem.getUserByName("user31");//only user
    	System.out.println(userInterface1);
    	System.out.println("\"Now I can play with that as I wish and create admin if relevant...\"");
    	if(userInterface1 instanceof Admin){
    		
    		Admin admin1 = (Admin)userInterface1;
    		admin1.adminTest();
    	}
    	else{
    		
    		System.out.println("I am just user ");
    	}
    	
    	//
    	
    	
    	
    	
    	
    	
	}



}












