package main.java.testing;

import main.java.objects.Admin;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.system.constants.DBConstants;

public class TestAdmin {
	
	
	public static void main(String[] args) {
		
		 
		Admin admin1 = new Admin();
		admin1.isAdmin();
		System.out.println("this is how instanceof work : ");
		//MultiUsersSystem.createNewUser("admin1", "aaa2@aaa","lll");
		//Admin admin2 = (Admin)MultiUsersSystem.login("admin1", "lll");
		User admin2 = new Admin();
		System.out.println(admin2 instanceof Admin);
		User admin3 = new User();
		System.out.println(admin3 instanceof Admin);
		
		System.out.println("\nTest Admin\n");
		
		System.out.println("\nCreate New Admin");
		
		String adminName1 = "lingar", password1 = "B222", email = "lingar@lingaria.coll";
		
		MultiUsersSystem.createNewUser(adminName1, email, password1, true);
		
		System.out.println("\nLogin with adminn\n");
		 User temp1 = MultiUsersSystem.login(adminName1, password1);
		Admin admin5 = new Admin(temp1);
		System.out.println(admin5 + " this is the logon user ");
		
		System.out.println("\n testing all the methods\n");
		MultiUsersSystem.createNewUser("user7", "user7@7", "ccccccc", false);
		User user1 = MultiUsersSystem.login("user7", "ccccccc");
		admin5.displayOne(user1.getId());
		admin5.changeUserDetail(user1, DBConstants.EMAIL_KEY	, "cehcke@check");
		admin5.displayOne(user1.getId());
		admin5.changeUserEmail(user1, "old@old1");
		admin5.changeUserPassword(user1, "aaaa");
		admin5.displayOne(user1.getId());
		admin5.changeUserPassword(user1, "ccccccc");
		admin5.deleteUserByName("superAdmin");
		//admin5.deleteAllUsers();
		admin5.createNewUser("user223", "223@333", "a222", true);
		Admin admin6 = new Admin(MultiUsersSystem.login( "223@333", "a222"));
		System.out.println(user1);
		admin6.changeUserPassword(user1, "dddd");
		admin6.changeUserEmail(user1, "user88@8888");
		System.out.println(user1);
		admin6.changeUserPassword(user1, "ccccccc");
		admin6.displayMessages();
		admin6.changeMessage(DBConstants.USER_MESSAGES_KEYS[0], "very very new message 1 admin");
		admin6.displayMessages();
		admin6.displayOne(user1.getId());
		admin6.deleteUserByName(DBConstants.SUPER_ADMIN_NAME);
		//admin6.deleteAllUsers();
		
		admin5.createNewUser("testDelete", "czczx@zxczxc", "a222", false);
		admin5.deleteUserByName("testDelete");
		User test1 = MultiUsersSystem.login("test2", "t");
		//
		admin5.changeUserPassword(test1, "t");
		
		
		
		
	}
	

}
