package main.java.objects;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import main.java.services.DBConnector;
import main.java.system.constants.DBConstants;

public class User implements UserInterface {
	
	protected String id;
	final protected String name; // Up to today - March 18 - name s immutable (cannot be changed)
	protected String email;
	protected String password; 
	protected boolean approved = false;
	protected DBConnector connector = DBConnector.getInstance();
	protected String userType;// for future others types - like editor, viewer etc..
	protected boolean admin = false;
	
	//Non argument constructor. 
	public User(){
		
		name = "NA";
		
	}
	
	//constructor - right now not in use . Doesn't want to assign it the password, 
	public User( String name, String email, String password ){
		
		this.name = name; 
		this.email = email;
		this.password = password;
		this.approved = true;
		
	}
	//constructor - 
	public User(String id, String name, String email, String password ){
		this.id = id;
		this.name = name; 
		this.email = email;
		this.password = password;
		this.approved = true;
		
	}
	
	//Constructor that will be used on the authentication method,
	public User(String id, String name, String email, boolean approved){
		this.id = id;
		this.name = name;
		this.email = email;
		this.approved = approved; 
		
	}
	
	/**
	 * Testing methods
	 */
	public void overriden(){
		System.out.println("I am overriden from USER  "+ name );
	}
	
	public void userTest(){
		System.out.println("I am user " + name);
	}
	
	//General Methods
	
	/**
	 * Display all the messages
	 * @param levelOneKey
	 */
	public void displayDocumentFields(String levelOneKey){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		
		//Map<String, String> allFields =  connector.getAllUserFieldsByDocumnet(levelOneKey);
		//mock object
		//Map<String, String> allFields = new HashMap<String, String>();
		Set<Map.Entry<String, Object>> documents = connector.getUserTopLevelField(levelOneKey, this);
		
		System.out.println("This is all fields of  " + levelOneKey + " document of the user: " );
		Iterator <Map.Entry<String, Object>> iterator = documents.iterator();
		Map.Entry<String, Object> entry ;
		while(iterator.hasNext()){
			entry = iterator.next();
			String key = entry.getKey(); String value = entry.getValue().toString();
			System.out.println(key + " : " + value);
			
			
		}
		
	}
	/**
	 * UPDATE level 2 fields by args
	 */
	public void updateLevel2Field(String levelOne, String levelTwo, String newValue){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		connector.updateLevel2Key(levelOne, levelTwo, newValue, this);
		
	}
	
	/**
	 * UPDATE the messages by message type
	 */
	public void changeMessage(String messageType, String newMessage){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		connector.updateLevel2Key(DBConstants.MESSAGES_KEY, messageType, newMessage, this);
		
	}
	
	
	/**
	 * Method that display the messages of the user...
	 */
	public void displayMessages(){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		System.out.println("User Messages: ");
		displayDocumentFields(DBConstants.MESSAGES_KEY);
		
	}
	
	public void displayUserDetails(User user){
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		System.out.println("User Details: ");
		displayDocumentFields(DBConstants.USER_DETAILS_KEY);
	}
	/**
	 * Log off - WORK
	 * @param id
	 */
	public void logOff(){
		
		approved = false;
	}
	
	//Getter and Setters 
	
	
	public void setId(String id) {
		this.id = id;
	}
	public DBConnector getConnector() {
		return connector;
	}
	public void setConnector(DBConnector connector) {
		this.connector = connector;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	/* - name is final!
	public void setName(String name) {
		this.name = name;
	}
	*/

	public String getEmail() {
		return email;
	}

	
	//can do it only after proper login and ssigning the Id value
	public void setEmail(String email) {
		//check if vald & not duplicate
		if(!MultiUsersSystem.isValidEmail(email)  
				|| connector.isDuplicate(DBConstants.EMAIL_KEY, email)
				){
			System.out.println("Email not valid or duplicate");
			return;
			
		}
		
		//insert it to db
		connector.updateUserDetail(DBConstants.EMAIL_KEY, email, this);
		this.email = email;
	}

	//because it's commonly not construct with password, we do it get from the db. 
	public String getPassword() {
		
		password = connector.getPassword(this);
		return password;
	}
	


	public void setPassword( String password) {
		
		//check if the the user know the current password - should be in another place. future...
		//Todo - u should do it here maybe but as args not as input. or not. It's your decision.
		//Think not, becaue I'll maybe use it for admin and for his convenience it's should be not authorized
		//because there are case that he'll want to do it straight not be depend on the user password. 
		
		//check if valid
		if(!MultiUsersSystem.isValidPassword(password)){
			System.out.println("Password not valid Must english lettrs. Only letters or numbers...");
			return;
			
		}
		
		
		//update the db
		connector.updateUserDetail(DBConstants.PASSWROD_KEY, password, this);
		this.password = password;
	}
	
	public boolean isApproved(){
		return approved;
	}
	
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "User [id = " + id + ", name=" + name + ", email=" + email + ", password=" + password + ", approved= "+ approved +  ", admin = " + admin +" ]";
	}
	
	
	
	
	

}
