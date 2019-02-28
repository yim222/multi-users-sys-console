package main.java.draft;

import org.bson.Document;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import main.java.services.DBConnectorOld1;
import main.java.services.DBConnector;

public class DBSingelton {
	
	
	//Trying to test How it's work with singelton 
	
	//1- check if in the first method it's can make problem one with eachother 
	
	public static void checkOrder(){
		
		/*
		 * public static void createNewUser(String userName, String email, String password){
		connectDBData();
		Document user = new Document();
		user.append("id", userID).append("userName", userName).append("email", email ).append("password", password);
		userID++;
		collection.insertOne(user);
		closeDB();
	}
		 */
		DBConnector.createNewUser("eserttt", "asdasd", "pass");
		DBConnector.createNewUser("eserttt222", "asdasd", "pass");
		
	}
	public static void main(String[] args) {
		DBConnector.getInstance("localhost", 27017);
		DBConnector.createNewUser("eserttt", "asdasd", "pass");
		DBConnector.createNewUser("eserttt222", "asdasd", "pass");
		DBConnector.closeDB();
		
	}

}
