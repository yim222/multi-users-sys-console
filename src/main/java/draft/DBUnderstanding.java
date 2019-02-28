package main.java.draft;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import main.java.services.DBConnectorOld1;

public class DBUnderstanding {
	
	
	//Trying to test the db execution and performance of some methods
	
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
		DBConnectorOld1.connectDBData();
		Document user = new Document();
		DBConnectorOld1.createNewUser("user222", "m222", "222");
		user.append("id", "111").append("user333", "userName1").append("email", "mail1").append("password", "password1");
		MongoCollection<Document> collection1 = DBConnectorOld1.getCollection();
		collection1.insertOne(user);
		DBConnectorOld1.closeDB();
		
	}
	public static void main(String[] args) {
		DBConnectorOld1.getInstance("localhost", 27017);
		DBConnectorOld1.createGetPrimaryTables();
		checkOrder();
	}

}
