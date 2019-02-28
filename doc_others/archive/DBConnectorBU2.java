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
public class DBConnectorBU2 {
	private  MongoClient dbMongo ;
	private  MongoDatabase database;
	private  MongoCollection<Document> collection ;
	private  int userID ;
	private  String host; 
	private  int port;
	private  DBConnectorBU2 instance; 
	
	//configuration 
	private  String dbName = "multi-users-sys"; 
	private  String collectionName = "users-list";
	
	//Singelton
	private DBConnectorBU2(String host, int port){
		this.host = host;
		this.port = port;
		
	}
	
	//Creating insatnce 
	public  DBConnectorBU2 getInstance(String host, int port ){
		if(instance == null){
			instance = new DBConnectorBU2(host, port);
	    }
		dbMongo = new MongoClient(host, port );
		return instance;
		
		
	}
	
	//setters and getters : 
	
	
	public  MongoClient getDbMongo() {
		return dbMongo;
	}

	public  void setDbMongo(MongoClient dbMongo) {
		this.dbMongo = dbMongo;
	}

	public  MongoDatabase getDatabase() {
		return database;
	}

	public  void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public  MongoCollection<Document> getCollection() {
		return collection;
	}

	public  void setCollection(MongoCollection<Document> collection) {
		this.collection = collection;
	}

	public  int getUserID() {
		return userID;
	}

	public  void setUserID(int userID) {
		this.userID = userID;
	}

	public  String getHost() {
		return host;
	}

	public  void setHost(String host) {
		this.host = host;
	}

	public  int getPort() {
		return port;
	}

	public  void setPort(int port) {
		this.port = port;
	}

	public  DBConnectorBU2 getInstance() {
		return instance;
	}

	public  void setInstance(DBConnectorBU2 instance) {
		this.instance = instance;
	}
	
	
	//service methods
	
	//Connect the DB 
	public void connectDB(){
		//dbMongo = new MongoClient(host, port );
		DBConnectorBU2 db =  getInstance ( host,  port );
		
	}
	
	//close DB
	public  void closeDB(){
		
		
		//close
		dbMongo.close();
		
	}
	
	//Connect to Db and its collections
	public  void connectDBData(){
		connectDB();
		createGetPrimaryTables();
		
	}
	
	//create or get db
	public  void createGetPrimaryTables(){
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
	public  void createNewUser(String userName, String email, String password){
		connectDBData();
		Document user = new Document();
		user.append("id", userID).append("userName", userName).append("email", email ).append("password", password);
		userID++;
		collection.insertOne(user);
		closeDB();
	}
	
	//get all id's 
	//For now - 1/23/18 I won't use it. just use the _id field. 
	public  void getCurrentIDs( ){
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
	public  boolean isDuplicate(String key, String value){
		
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
	public  BasicDBObject generateCaseInsensitiveQry(String where, String whereValue){
		
		BasicDBObject query =  new BasicDBObject(where, Pattern.compile("^" + whereValue + "$", Pattern.CASE_INSENSITIVE));
		
		return query;
	}
	
	
	//get user details 
	
	//remove user
	
	//more...  ???
	
	
}
