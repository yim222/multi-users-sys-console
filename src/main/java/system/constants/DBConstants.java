package main.java.system.constants;
/**
 * 
 * @author lingar
 *Here I write the constants of the DB as fields names, collections names, port, and like that. 
 */
public class DBConstants {
	
	final static String A = "A";
	public static final String HOST = "localhost", ID_KEY = "_id",USER_NAME_KEY = "userName" , EMAIL_KEY = "email", PASSWROD_KEY = "password",
			
			USERS_LIST_NAME = "users-list" , DB_NAME = "multi-users-sys-1000" , USERS_COLLECTION_NAME = "users-list" ,
			MESSAGES_KEY = "messages" , USERS_UNIQUE_COLL_PREFIX = "usersCollection.", USER_DETAILS_KEY = "userDetails"
			, USER_ADMIN_KEY = "admin" , SUPER_ADMIN_NAME = "superAdmin", SUPER_ADMIN_DEFAULT_PASSWORD = "A12345678" ;
	public static final int port = 27017;
	
	//Message keys. in array 
	public static final String[] USER_MESSAGES_KEYS = {"msg1", "msg2"};

}
