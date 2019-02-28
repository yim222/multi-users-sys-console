package main.java.services;


import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import com.sun.research.ws.wadl.Include;
//import com.mongodb.client.MongoCollection.find;
public class DBConnectorOld1 {
	private static MongoClient dbMongo ;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection ;
	private static int userID ;
	private static String host; 
	private static int port;
	private static DBConnectorOld1 instance; 
	
	//configuration 
	private static String dbName = "multi-users-sys"; 
	private static String collectionName = "users-list";
	
	//Singelton
	private DBConnectorOld1(String host, int port){
		DBConnectorOld1.host = host;
		DBConnectorOld1.port = port;
		
	}
	
	//Creating insatnce 
	public static DBConnectorOld1 getInstance(String host, int port ){
		if(instance == null){
			instance = new DBConnectorOld1(host, port);
	    }
		dbMongo = new MongoClient(host, port );
		return instance;
		
		
	}
	
	//setters and getters : 
	
	
	public static MongoClient getDbMongo() {
		return dbMongo;
	}

	public static void setDbMongo(MongoClient dbMongo) {
		DBConnectorOld1.dbMongo = dbMongo;
	}

	public static MongoDatabase getDatabase() {
		return database;
	}

	public static void setDatabase(MongoDatabase database) {
		DBConnectorOld1.database = database;
	}

	public static MongoCollection<Document> getCollection() {
		return collection;
	}

	public static void setCollection(MongoCollection<Document> collection) {
		DBConnectorOld1.collection = collection;
	}

	public static int getUserID() {
		return userID;
	}

	public static void setUserID(int userID) {
		DBConnectorOld1.userID = userID;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		DBConnectorOld1.host = host;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		DBConnectorOld1.port = port;
	}

	public static DBConnectorOld1 getInstance() {
		return instance;
	}

	public static void setInstance(DBConnectorOld1 instance) {
		DBConnectorOld1.instance = instance;
	}
	
	
	//service methods
	
	//Connect the DB 
	public static void connectDB(){
		//dbMongo = new MongoClient(host, port );
		//trying to make it insatnce 
		
		if(dbMongo == null ){
			dbMongo = new MongoClient(host, port );
			
		}
		
	}
	
	//close DB
	public static void closeDB(){
		
		
		//close
		dbMongo.close();
		
	}
	
	//Connect to Db and its collections
	public static void connectDBData(){
		connectDB();
		createGetPrimaryTables();
		
	}
	
	//create or get db
	public static void createGetPrimaryTables(){
		//dbMongo = new MongoClient("localhost", 27017 );
		//create or get database
		database = dbMongo.getDatabase(dbName);
		
		//create collection (until u not insert document it won't be displayed
		collection = database.getCollection(collectionName);
		
		/* if u want to test it - uncomment it
		Document exampleUser = new Document();
		exampleUser.append("id", 0).append("userName", "user1").append("mail", "agaf58@gmail.com" ).append("age", 22);
		
		//insert it to the collection
		
		collection.insertOne(exampleUser);
		*/
		
	}
	
	//generate right id 
	//More methods will be demand with the development process. among others : 
	//create new user 
	public static void createNewUser(String userName, String email, String password){
		connectDBData();
		Document user = new Document();
		user.append("id", userID).append("userName", userName).append("email", email ).append("password", password);
		userID++;
		collection.insertOne(user);
		closeDB();
	}
	
	//get all id's 
	//For now - 1/23/18 I won't use it. just use the _id field. 
	public static void getCurrentIDs( ){
			//setCollection(collection);
			connectDBData();
			/*
			//show all documents - U need to iterate this  
			 FindIterable<Document> iterDoc = collection.find().projection( excludeId());
					 //.projection(fields(include("item", "status"), excludeId()));
			 //Iterate this//item", "status", "size.uom"
			 Iterator it = iterDoc.iterator(); 
			   
			 while(it.hasNext()){
				 
				 System.out.println(it.next());
				 
			 }
			 */
		//	findIterable = collection.find()
			        
			
		}
	
	//check if the field value duplicat
	public static boolean isDuplicate(String key, String value){
		
		connectDBData();
		BasicDBObject query = generateCaseInsensitiveQry(key, value);
		Document document = collection.find(query).first();
		
		if(document == null){
			
			closeDB();
			return false;
		}
		closeDB();
		return true;
	}
	
	//Method that creates query for case - insensitive 
	//BasicDBObject bd =  new BasicDBObject("car", Pattern.compile("^" + "hoNDa" + "$", Pattern.CASE_INSENSITIVE));
	public static BasicDBObject generateCaseInsensitiveQry(String where, String whereValue){
		
		BasicDBObject query =  new BasicDBObject(where, Pattern.compile("^" + whereValue + "$", Pattern.CASE_INSENSITIVE));
		
		return query;
	}
	
	
	//get user details 
	
	//remove user
	
	//more...  ???
	
	
}
