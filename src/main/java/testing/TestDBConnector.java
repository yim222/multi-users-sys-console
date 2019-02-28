package main.java.testing;

import java.awt.DisplayMode;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.bson.Document;

import java.util.Set;
import java.util.TreeMap;

import com.mongodb.MongoWriteException;

import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.objects.UserInterface;
import main.java.services.DBConnector;
import main.java.system.constants.DBConstants;

public class TestDBConnector {
	
	
	
	
	public static void main(String[] args) {
		
		DBConnector connector0 = DBConnector.getInstance();
		
		//DBConnectorSingelton connector =  DBConnectorSingelton.getInstance("localhost", 27017);
		DBConnector connector =  DBConnector.getInstance("localhost", 27017);
		//DBConnectorSingelton connector =  DBConnectorSingelton.getInstance();
		connector0 = DBConnector.getInstance();
		System.out.println("change user and email for it's will work any time ");
		 System.out.println(connector.getCollection().find(new Document().append(""	, "")).first());
		
		
		try{
			connector.createNewUser("user23", "2124@3a3,c1", "111", false); 
		}catch(MongoWriteException  ex){
			System.out.println("The user or the mail are duplicate!");
		}
		
		System.out.println("test connector.getUserID ");
		String email1 = "2124@3a3,c1"; 
		String str = connector.getUserID(email1, DBConstants.EMAIL_KEY);
		System.out.println("This is the id of user wth email : " + email1 + " - " + str);
		
		
		//System.out.println(connector.getUserDetails(str));
		
		System.out.println("test authenticateCredentials");
		System.out.println(connector.authenticateCredentials("ssc222@3555r", "ccccccc", "email"));
		User user1 = new User("lingar", "f@s"," kkk");user1.setApproved(true);
		System.out.println("Test getUserTopLevelField(...) ");
		connector.getUserTopLevelField(DBConstants.MESSAGES_KEY, user1);
		Iterator it1 = connector.getUserTopLevelField(DBConstants.MESSAGES_KEY, user1).iterator();
		Set t = connector.getUserTopLevelField(DBConstants.MESSAGES_KEY, user1);
		System.out.println(t +" = t");
		//HashMap h = t;
		//TreeSet sortedSet = new TreeSet<Integer>(set);
		
		//HashMap sortedSet = new HashMap(t);
		Iterator<Entry<String, Object>> it2 = t.iterator();
		Entry data = null;
		while(it2.hasNext()){
			//Entry entry1 = it1;
			System.out.println("Hi clown");
			System.out.println(data  = it2.next());
			String key = (String)data.getKey();
			String value = (String)data.getValue();
			System.out.println("key = " + key +" , value = " + value);
			
			
		}
		System.out.println("testing update");
		connector.updateLevel2Key(DBConstants.MESSAGES_KEY, DBConstants.USER_MESSAGES_KEYS[0]	, "I am the new message", user1);
		
		//
		t = connector.getUserTopLevelField(DBConstants.MESSAGES_KEY, user1);
		System.out.println(t +" = t");
		//HashMap h = t;
		//TreeSet sortedSet = new TreeSet<Integer>(set);
		
		//HashMap sortedSet = new HashMap(t);
		it2 = t.iterator();
		data = null;
		while(it2.hasNext()){
			//Entry entry1 = it1;
			System.out.println("Hi clown");
			System.out.println(data  = it2.next());
			String key = (String)data.getKey();
			String value = (String)data.getValue();
			System.out.println("key = " + key +" , value = " + value);
			
			
		}
		//reset te value: 
		connector.updateLevel2Key(DBConstants.MESSAGES_KEY,DBConstants.USER_MESSAGES_KEYS[0]	, "I am the Primary  message", user1);
		
		System.out.println("\nTest deleteUser(userName)");
		try {
			connector.deleteUserByName("user222");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Maybe the user u try to delete isn't exists");
		}
		
		System.out.println("Delete all users ... (uncomment it - hard action");
		connector.getAllUsersData();
		//connector.deleteAllUsers();
		System.out.println("all users : ");
		
		//!
		System.out.println("CHECK HERE THE DISPLAY ALL USER AND WORK ON ");
		TreeMap< String, Set<Map.Entry<String, Object>>>  data2 =  connector.getAllUsersData();
		System.out.println(data2);
		
		//prepare to the display users metod. (I can do it by get each but it's unnecessary) 
		Iterator<Map.Entry<String,Set<Map.Entry<String,Object>>>> it22 = data2.entrySet().iterator(); 
		String key, value; Map.Entry me ;
		String dataRow = "";
		while(it22.hasNext()){
			Map.Entry<String,Set<Map.Entry<String,Object>>> element= it22.next();
			Iterator it23 = element.getValue().iterator();
			while(it23.hasNext()){
				Map.Entry me2 = (Map.Entry<String, Object>) it23.next() ;
				String key2 = me2.getKey().toString(); //String.format("%10s", me2.getKey().toString() );
				String value2 = String.format("%15s", me2.getValue().toString() );
				System.out.println(me2.getKey().toString() + "   sss = " + me2.getValue().toString());
				dataRow += key2 + " = " + value2 + " | ";
				//System.out.println(it23.next());
				//System.out.format("%50s", args)
			}
			System.out.println(dataRow);
			dataRow = "";
			System.out.println("The user : " + element.getKey().toString());
			System.out.println("Details:\n" + element.getValue().toString());
			
			
		}
		
		System.out.println("\nTesting updateUserDetail(String key, String newValue, User user) \n");
		//creating new user and login with him
		
		MultiUsersSystem.createNewUser("test11", "test1@t.com", "a1111", false);
		
		User test11 = MultiUsersSystem.login("lingar", "B222");
		System.out.println(test11);
		//MultiUsersSystem.displayAllUsers();
		connector.updateUserDetail(DBConstants.EMAIL_KEY, "aaabbb@aa2a5", test11);
		//Scanner sc = new Scanner(System.in);
		//sc.nextLine();
		
		System.out.println("after changing the email: \n"  + test11);
		connector.updateUserDetail(DBConstants.PASSWROD_KEY, "B222", test11);
		//MultiUsersSystem.displayAllUsers();
		System.out.println("Test getPassword: ");
		System.out.println(connector.getPassword(test11));
		
		System.out.println(connector.getUser(test11.getId()));
		connector.deleteUserByName(DBConstants.SUPER_ADMIN_NAME);
		
		//test  UserInterface getUser(String id, int x)
		System.out.println("UserInterface getUser(String id, int x)");
		//String id = connector.getUserID("user33", DBConstants.USER_NAME_KEY);
		String id = connector.getUserID("lingar", DBConstants.USER_NAME_KEY);//admin user
		System.out.println(id);
		System.out.println(connector.getUser(id, 0));
		
		connector.closeDB();
	}

	
	
}
