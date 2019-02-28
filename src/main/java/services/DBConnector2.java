package main.java.services;


import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
//import com.sun.research.ws.wadl.Include;
//import com.mongodb.client.MongoCollection.find;
public class DBConnector2 {
	private MongoClient dbMongo ;
	private MongoDatabase database;
	private MongoCollection<Document> collection ;
	//private int userID ;
	private String host; 
	private int port;
	private static DBConnector2 instance; 
	
	//configuration 
	private static String dbName = "multi-users-sys"; 
	private static String collectionName = "users-list";
	
	//Singelton
	private DBConnector2(String host, int port){
		this.host = host;
		this.port = port;
		dbMongo = new MongoClient(host, port );
		createInitialTables();
		
	}
	
	//Return instance of the object. without args - need to think how to use it, if at all 
	/*
	public static DBConnectorSingelton getInstance() {
		
		return instance;
	}
	*/
	
	//Return instance of the object. with args
	public static DBConnector2 getInstance(String host, int port ){
		if(instance == null){
			instance = new DBConnector2(host, port);
	    }
		
		
		return instance;
		
		
	}
	
	
	/**
	 * FUNCTIONAL METHODS
	 */
	/**
	 * Todo - 
	 * //get user details 
	
	//remove user
	
	//more...  ???
	
	
	 */
	//create or get db
	public void createInitialTables(){
		
		//Creating primary database and users collection. U can config it from inside 
		database = dbMongo.getDatabase(dbName);
		collection = database.getCollection(collectionName);
		//make the user name and mail unique.
		Document userNameIndex = new Document("userName", 1);
		Document emailIndex = new Document("email", 1);
		collection.createIndex(userNameIndex, new IndexOptions().unique(true));
		collection.createIndex(emailIndex, new IndexOptions().unique(true));
		
		
	}
	
	
	
	//create new user 
	public void createNewUser(String userName, String email, String password){
		
		Document user = new Document();
		user.append("userName", userName).append("email", email ).append("password", password);		
		collection.insertOne(user);
		createInitialUserData(userName,user);
		
		
		
	}
	
	//create initial data to the user 
	public void createInitialUserData(String userName, Document userDetails){
		
		//create new collection for the user 
		MongoCollection<Document> userCollection =  database.getCollection("usersCollection." + userName);
		
		//Adding new initial document to that collection 
		Document doc = new Document();
		doc.append("userDetails", userDetails)
		.append("messages", new Document( "msg1", "This is message 1 of user - " + userName )
				.append("msg2", "This is message 2 of user - " + userName ));
		//userCollection.insertOne(userDetails); 
		userCollection.insertOne(doc); 
	}
	
	/**
	 * DB-SERVICES METHODS (INSTANCES)
	 */
	
	//check if the field value duplicat
	public boolean isDuplicate(String key, String value){
		
		
		BasicDBObject query = generateCaseInsensitiveQry(key, value);
		Document document = collection.find(query).first();
		
		if(document == null){
			
			return false;
		}
		return true;
	}
	
	//close DB
	public void closeDB(){
			//close
			dbMongo.close();			
	}
	
	/**
	 * STATIC SERVICES METHODS
	 */
	//Method that creates query for case - insensitive 
	public static BasicDBObject generateCaseInsensitiveQry(String where, String whereValue){
		
		BasicDBObject query =  new BasicDBObject(where, Pattern.compile("^" + whereValue + "$", Pattern.CASE_INSENSITIVE));
		
		return query;
	}
	
	
	/**
	 * 
	 * SETTERS AND GETTERS METHODS
	 */
	
	//It's not allowed to be visible, because I don't want multiple clients
	/*
	public  MongoClient getDbMongo() {
		return dbMongo;
	}
	*/

	//put here
	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public MongoCollection<Document> getCollection() {
		return collection;
	}

	public void setCollection(MongoCollection<Document> collection) {
		this.collection = collection;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static String getDbName() {
		return dbName;
	}

	public static void setDbName(String dbName) {
		DBConnector2.dbName = dbName;
	}

	public static String getCollectionName() {
		return collectionName;
	}

	public static void setCollectionName(String collectionName) {
		DBConnector2.collectionName = collectionName;
	}

	public void setDbMongo(MongoClient dbMongo) {
		this.dbMongo = dbMongo;
	}
	
	/**
	 * DRAFT AND ON-WORK METHODS
	 */
	
	
	
	
	
	
}
