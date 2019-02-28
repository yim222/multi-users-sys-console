package main.java.services;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;

import main.java.objects.Admin;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.objects.UserInterface;
import main.java.system.constants.DBConstants;
//import com.sun.research.ws.wadl.Include;
//import com.mongodb.client.MongoCollection.find;
public class DBConnector {
	private MongoClient dbMongo ;
	private MongoDatabase database;
	private MongoCollection<Document> collection ;
	//private int userID ;
	private String host; 
	private int port;
	private static DBConnector instance; 
	
	//configuration 
	private static String dbName = DBConstants.DB_NAME; 
	private static String usersCollectionName = DBConstants.USERS_COLLECTION_NAME;
	
	//Singelton
	private DBConnector(String host, int port){
		this.host = host;
		this.port = port;
		dbMongo = new MongoClient(host, port );
		createInitialTables();//Not sure it need to be here . U don't need to run it in any running. > right now the collection and db assigned there, 
		// so u need to leave it. in the future maybe we'll change it. 
		
	}
	
	//constants fields - see /MultiUsersSystem/src/main/java/system/constants/DBConstants.java
	
	
	//Return instance of the object. without args - need to think how to use it, if at all 
	public static DBConnector getInstance() {
		if(instance == null){
			System.out.println("U must create proper instance first with host , port pars");
	    }
		System.out.println("Ok instance");
		return instance;
	}
	
	
	//Return instance of the object. with args
	public static DBConnector getInstance(String host, int port ){
		if(instance == null){
			instance = new DBConnector(host, port);
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
		database = dbMongo.getDatabase(DBConstants.DB_NAME);
		collection = database.getCollection(DBConstants.USERS_COLLECTION_NAME);
		//make the user name and mail unique.
		Document userNameIndex = new Document(DBConstants.USER_NAME_KEY, 1);
		Document emailIndex = new Document(DBConstants.EMAIL_KEY, 1);
		collection.createIndex(userNameIndex, new IndexOptions().unique(true));
		collection.createIndex(emailIndex, new IndexOptions().unique(true));
		
		
	}
	
	
	public void createSuperAdmin(){
		
		Document query = new Document().append(DBConstants.USER_NAME_KEY, DBConstants.SUPER_ADMIN_NAME);
		Document doc = collection.find(query).first();
		//System.out.println("Doc = " + doc);
		if (doc != null){
			System.out.println("Not initial running. Continue...");
			return ; 
			
		}
		
		
		
		
		//if ()
		createNewUser(DBConstants.SUPER_ADMIN_NAME, "NA@NA.NA", DBConstants.SUPER_ADMIN_DEFAULT_PASSWORD, true);
		System.out.println("super admin has been created : \n" + getUser(getUserID(DBConstants.SUPER_ADMIN_NAME, DBConstants.USER_NAME_KEY)) );
		
		System.out.println("****NOTICE:\n" 
				+ "THE DEFAULT PASSWORD  IS " + DBConstants.SUPER_ADMIN_DEFAULT_PASSWORD 
				+" AND THE EMAIL IS NA@NA.NA. CONSIDER TO CHANGE IT PLEASE .\n ");
		
	}
	 
	//get all users
	public TreeMap< String, Set<Map.Entry<String, Object>>>  getAllUsersData(){
		
		FindIterable<Document> iterDoc = collection.find(); 
		Iterator<Document> it = iterDoc.iterator(); 
		
		TreeMap< String, Set<Map.Entry<String, Object>>>  data2 = new TreeMap< String, Set<Map.Entry<String, Object>> >();
		
		while(it.hasNext()){
			Document doc = it.next();
			Set<Map.Entry<String, Object>> set4 = doc.entrySet();
			
			
			data2.put(doc.get(DBConstants.USER_NAME_KEY).toString(), set4);
			
			
		}
		
		return data2;
		
	}
	
	
	//return one user Data 
	public Set<Map.Entry<String,Object>> getUser(String id){
		
		Document doc = collection.find(new Document(DBConstants.ID_KEY, new ObjectId(id))).first();
		
		//System.out.println(doc.entrySet());
		
		
		// ... do here 
		return doc.entrySet();
	}
	
	//return one userInterface  object either admin or user... 
	public UserInterface getUser(String id, int x){
		
		Document doc = collection.find(new Document(DBConstants.ID_KEY, new ObjectId(id))).first();
		String /*id = doc.getString(DBConstants.ID_KEY), */userName = doc.getString(DBConstants.USER_NAME_KEY), email = doc.getString(DBConstants.EMAIL_KEY),
				password = doc.getString(DBConstants.EMAIL_KEY);
		//System.out.println(doc.entrySet());
		//if(Boolean.parseBoolean((String)doc.get(DBConstants.USER_ADMIN_KEY))){
		System.out.println(doc.getBoolean(DBConstants.USER_ADMIN_KEY));
		
		if(doc.getBoolean(DBConstants.USER_ADMIN_KEY)){
			UserInterface admin = new Admin(id,userName,email, password);
			//Admin amini = (Admin)admin;
			//amini.adminTest();
			//admin.adminTest();//won't work
			return admin;
			
		}
		else{
			
			UserInterface user = new User(id,userName,email, password);
			return user;
			//u here
		}
		
		
		
	}
	
	//create new user 
	public void createNewUser(String userName, String email, String password, boolean isAdmin){
		
		Document user = new Document();
		user.append(DBConstants.USER_NAME_KEY, userName).append(DBConstants.EMAIL_KEY, email ).append(DBConstants.PASSWROD_KEY, password)
		.append(DBConstants.USER_ADMIN_KEY, isAdmin);	
		collection.insertOne(user);
		createInitialUserData(userName,user);
		
		
		
	}
	
	//create initial data to the user 
	public void createInitialUserData(String userName, Document userDetails){
		
		//create new collection for the user 
		MongoCollection<Document> userCollection =  database.getCollection(DBConstants.USERS_UNIQUE_COLL_PREFIX + userName);
		
		//Adding new initial document to that collection 
		Document doc = new Document();
		doc.append(DBConstants.USER_DETAILS_KEY, userDetails)
		.append(DBConstants.MESSAGES_KEY, new Document( DBConstants.USER_MESSAGES_KEYS[0], "This is message 1 of user - " + userName )
				.append( DBConstants.USER_MESSAGES_KEYS[1], "This is message 2 of user - " + userName ));
		//userCollection.insertOne(userDetails); 
		userCollection.insertOne(doc); 
	}
	
	/**
	 * 
	 * @param identification
	 * @param passwrod
	 * @param identificationType
	 * @return
	 * check if the creditials correct and login if does. (by the boolean approved) . 
	 */
	public boolean authenticateCredentials( String identification, String password, String key ){
		
		//find the user by the key, and check his password.
		Document query = new Document().append(key, identification);
		Document user = collection.find(query).first();
		String password1;
		//maybe should do here try/catch
		try {
			password1 = user.get(DBConstants.PASSWROD_KEY).toString();
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("error - maybe the identification not correct");
			return false;
		}
		
		
		if(password.equals(password1)){
			
			return true; 
		}
		return false; 
	}
	/**
	 * 
	 * @param identification
	 * @param key
	 * @return
	 * //return user ID by identification (mail, userName) 
	 * WORK
	 */
	
	public String getUserID(String identification, String key){
		//get the object id and return it as string either by mail or identification 
		Document query = new Document().append(key, identification);
		Document document = collection.find(query).first();
		String objectID = document.get(DBConstants.ID_KEY).toString();
		return objectID;
		
		
		
	}
	
	public String getPassword(User user){
		
		Document userDoc = collection.find(new Document(DBConstants.ID_KEY, new ObjectId(user.getId()))).first();
		String password1 = userDoc.get(DBConstants.PASSWROD_KEY).toString();
		return password1;
		
	}
	
	/**
	 * 
	 * @return user details - id, userName, not - password. by id 
	 * WORK
	 */
	public Map<String, String> getUserDetails(String id){
		ObjectId objID = new ObjectId(id);//from the API of Java
		Document query = new Document().append(DBConstants.ID_KEY, objID);
		Document document = collection.find(query).first();
		//System.out.println(document.ge);
		//to consider: 
		//u need here to do action that insert here the other details too. 
		//automation recommended, and make service method for recommended too
		Map<String, String> userDetails = new HashMap<String, String>();
		userDetails.put(DBConstants.USER_NAME_KEY, document.getString(DBConstants.USER_NAME_KEY));
		userDetails.put(DBConstants.EMAIL_KEY, document.getString(DBConstants.EMAIL_KEY));
		userDetails.put(DBConstants.USER_ADMIN_KEY, document.getBoolean(DBConstants.USER_ADMIN_KEY).toString());
		return userDetails;
	}
	/**
	 * 
	 * something like getAllFieldsbyKey
that will retrun specific documents values and keys by the level1 key and the specific user in the DBConnector, 
	admin too
	 */
	public Set<Map.Entry<String,Object>> getUserTopLevelField(String key, User user){
		
		if (!user.isApproved()){
			System.out.println("Authenticate failed");
			return null;
			
		}
		//get user name for the collection
		String userName = user.getName();
		//get the collection
		String collectionName = DBConstants.USERS_UNIQUE_COLL_PREFIX + userName;
		MongoCollection<Document> userCollcetion = database.getCollection(collectionName);
		
		//get the specific field object -
		//first get the object - only!
		Document doc1 = userCollcetion.find(new Document(key, new Document("$exists", true)))
				.projection(Projections.fields(Projections.include(key), Projections.excludeId())).first();
		
		//Then return just this keys
		Document data = (Document)doc1.get(key);
		
		//convert to into MapEntries		
		Set<Entry<String, Object>> set1 =  data.entrySet();
		
		//return it
		return set1;
	}
	
	/**
	 * update Level 2 key (regardless to its value)
	 * admin too
	 */
	public void updateLevel2Key(String levelOne, String levelTwo, String newValue, User user){
		if (!user.isApproved()){
			System.out.println("Authenticate failed");
			return ;
			
		}
		//get user name for the collection
		String userName = user.getName();
		//get the collection
		String collectionName = DBConstants.USERS_UNIQUE_COLL_PREFIX +userName;
		MongoCollection<Document> userCollcetion = database.getCollection(collectionName);
		
		String key = levelOne+"."+levelTwo;
		userCollcetion.updateOne(Filters.eq(key, new Document("$exists", true)), Updates.set(key, newValue));
		
		
		
	}
	/**
	 * update user detial keys. bot the list and the collection
	 */
	public void updateUserDetail(String key, String newValue, User user){
		
		// U here - do if for update the key of the details. Consider do helper methods and do
		// it in the efficient way. thne continue with the user and admin work
		
		
		//try to get the user with DOc
		//System.out.println(DBConstants.ID_KEY + " " + user.getId());
		Document doc = collection.find(new Document(DBConstants.ID_KEY, new ObjectId(user.getId()))).first();
		//1 for collection userDetails
		//userCollcetion.updateOne(Filters.eq(key, new Document("$exists", true)), Updates.set(key, newValue));
		//		 collection.updateOne(Filters.eq("name", "Moshe"), Updates.set("age", 60)); 
		//collection.updateOne(Filters.eq("userName", "test11"), Updates.set("userName", "aaa")); 
		collection.updateOne(Filters.eq(DBConstants.ID_KEY, new ObjectId(user.getId())), Updates.set(key 	,newValue));
		
		
		updateLevel2Key(DBConstants.USER_DETAILS_KEY, key, newValue, user);
		//need checking to the new email too... but u can do it in the system. 
		//2 for the userDetails itself 
		
		
		

	}
	/**
	 * DELETE USER BY NAME 
	 * admin too
	 */
	
	
	public void deleteUserByName(String userName){
		
		if (userName.equals(DBConstants.SUPER_ADMIN_NAME)){
			System.out.println("Super Admin cannot be deleted. ");
			return;
			
		}
		//delete the document from the users collection
		Document user = collection.find(new Document(DBConstants.USER_NAME_KEY, userName)).first();
		
		System.out.println("user = "  + user);
		collection.deleteOne(user);
		//delete the collection of the user. 
		MongoCollection<Document> userCollection = database.getCollection(DBConstants.USERS_UNIQUE_COLL_PREFIX + userName);
		userCollection.drop();
		
		
	}
	
	/**
	 * DELETE ALL USERS
	 *  Not admins (admin too ? ) 
	 */
	public void deleteAllUsers(){
		String userCollectionName ="";
		//get all collection 
		Iterator<String> iterator = database.listCollectionNames().iterator();
		
		
		//iterate and delete them except users-list USERS_LIST_NAME and super admin 
		while(iterator.hasNext()){
			
			userCollectionName = iterator.next();
			if(userCollectionName.equals(DBConstants.USERS_LIST_NAME)
					|| userCollectionName.equals(DBConstants.USERS_UNIQUE_COLL_PREFIX + DBConstants.SUPER_ADMIN_NAME) ){
				continue;
			}
			//System.out.println("col name = " + userCollectionName); // for testing. just comment it
			System.out.println(userCollectionName + " has been deleted");
			database.getCollection(userCollectionName).drop();
			
			
		}
		//collection.deleteMany(new Document());
		/*
		 * BasicDBObject query = new BasicDBObject();
    query.append(fieldname,new BasicDBObject("$ne", value));
		 */
		Document query = new Document().append(DBConstants.USER_NAME_KEY, 
				new Document("$ne", DBConstants.SUPER_ADMIN_NAME));
		collection.deleteMany(query);
	}
	
	/**
	 * DB-SERVICES(Helper) METHODS (INSTANCES)
	 */
	//check if the field value duplicat
	public boolean isDuplicate(String key, String value){
		
		
		Document query = generateCaseInsensitiveQry(key, value);
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
	
	//is initial running
	public boolean isInitialRunning(){
		Document query = new Document().append(DBConstants.USER_NAME_KEY, DBConstants.SUPER_ADMIN_NAME);
		Document doc = collection.find(query).first();
		if (doc != null){
			System.out.println("Not initial running. Continue...");
			return false; 
			
		}
		return true;
	}
	
	/** 
	 * STATIC SERVICES METHODS
	 */
	//Method that creates query for case - insensitive 
	public static Document generateCaseInsensitiveQry(String where, String whereValue){
		
		Document query =  new Document(where, Pattern.compile("^" + whereValue + "$", Pattern.CASE_INSENSITIVE));
		
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
		DBConnector.dbName = dbName;
	}

	public static String getCollectionName() {
		return usersCollectionName;
	}

	public static void setCollectionName(String collectionName) {
		DBConnector.usersCollectionName = collectionName;
	}

	public void setDbMongo(MongoClient dbMongo) {
		this.dbMongo = dbMongo;
	}
	
	/**
	 * DRAFT AND ON-WORK METHODS
	 */
	
	
	
	
	
	
}
