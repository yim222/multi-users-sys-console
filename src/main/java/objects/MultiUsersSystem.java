package main.java.objects;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import main.java.services.DBConnector;
import main.java.system.constants.DBConstants;
/**
 * Check wheere is constants, or admin and user methods (sign it as "admin too"). 
 * @author lingar
 *
 */
public class MultiUsersSystem {
	//finals values - see at /MultiUsersSystem/src/main/java/system/constants/DBConstants.java
	
	
	//end of finals
	
	//In the argument insert the right credintials
	private static DBConnector connector = DBConnector.getInstance(DBConstants.HOST, DBConstants.port);
	//singelton 
	/*
	 * 
	 * Methods 
	 */
	private static MultiUsersSystem myObj;
    /**
     * Create private constructor
     */
    private MultiUsersSystem(){
         
    }
    
   public  int test = 5; 
    /**
     * Create a static method to get instance.
     */
    public static MultiUsersSystem getInstance(){
        if(myObj == null){
            myObj = new MultiUsersSystem();
        }
        return myObj;
    }
    /**
     * Here u defined what's happen on the initial run (U'll be need to create something to regualr run too,
     * to check if it's initial running or not. 
     */
    public static User initialRunning(){
    	
    	//connector.createInitialTables(); - it's already happen in any connector creation
    	connector.createSuperAdmin();
    	return login(DBConstants.SUPER_ADMIN_NAME, DBConstants.SUPER_ADMIN_DEFAULT_PASSWORD);
    	
    	
    }
    //General Methods  
    
    /**
     * DISPLAY ALL USERS
     * 
     */
    public static void displayAllUsers(){
    	
    	TreeMap< String, Set<Map.Entry<String, Object>>>  data =  connector.getAllUsersData();
		System.out.println("This is all users data: ");
		
		
		Iterator<Map.Entry<String,Set<Map.Entry<String,Object>>>> it22 = data.entrySet().iterator(); 
		while(it22.hasNext()){
			Map.Entry<String,Set<Map.Entry<String,Object>>> element= it22.next();
			
			System.out.println("The user : " + element.getKey().toString());
			System.out.println("Details:\n" + element.getValue().toString());
		}
    	
    }
     
    /**
     * DISPLAY ALL USERS AS TABLE - WITH/WITHOUT PASSWORD
     * 
     */
     public static void displayAllUsersAsTabel(boolean displayPassword, int rowWidth){
    	 
    	 TreeMap< String, Set<Map.Entry<String, Object>>>  data2 =  connector.getAllUsersData();
		//System.out.println(data2);
		
		//prepare to the display users metod. (I can do it by get each but it's unnecessary) 
		Iterator<Map.Entry<String,Set<Map.Entry<String,Object>>>> it22 = data2.entrySet().iterator(); 
		
		//table head - NEED TO FIX - IN THE FUTURE 
		//System.out.println(String.format("%"+rowWidth +"s","user ID") +  String.format("%"+rowWidth +"s","|USER NAME")
		//+ String.format("%"+rowWidth +"s","|EMAIL" ));
		String dataRow = "";
		while(it22.hasNext()){
			Map.Entry<String,Set<Map.Entry<String,Object>>> element= it22.next();
			Iterator<Map.Entry<String,Object>> it23 = element.getValue().iterator();
			while(it23.hasNext()){
				Map.Entry<String, Object> me2 = (Map.Entry<String, Object>) it23.next() ;
				if(me2.getKey().equals(DBConstants.PASSWROD_KEY) && !displayPassword){
					continue;
				}
				String key2 = me2.getKey().toString(); 
				String value2 = String.format("%"+rowWidth +"s", me2.getValue().toString() );
				//System.out.println(me2.getKey().toString() + " = " + me2.getValue().toString());
				dataRow += key2 + " = " + value2 + " | ";
				//System.out.println(it23.next());
				//System.out.format("%50s", args)
			}
			System.out.println(dataRow);
			dataRow = "";
			
			
			
		}
		
    	
     }
     
     /**
      * Display ONE USER
     
      */
    public static void displayOneUser(String id){
    	
    	System.out.println(connector.getUser(id));
    }
     //do here method that get the userDetails, as data set (primary userDetials) 
    
    //Methods (in pararell to DB connector. 
     //admin too - Done
    public static String createNewUser(String userName, String email, String password,
    		boolean isAdmin){
    	System.out.println("creating new user... ");
    	//DBConnector.getInstance("localhost", 27017);
    	
    	//check if valid userName
    	boolean valid;
    	valid = isValidUserName(userName);
    	System.out.println(userName + " - valid user ? " +  valid);
    	if(!valid){
    		System.out.println("userName is invalid, please enter valid userName");
    		System.out.println("userName must contain English letters. ");
    		System.out.println("userName can contain only English letters and numbers. ");
    		return "invalid userName - just English letters or digits";
    	}
    	
    	valid = isValidEmail(email);
    	System.out.println(email + " - valid email ? " +  valid);
    	if(!valid){
    		System.out.println("email address  is invalid, please enter valid email");
    		
    		return "invalid email format";
    	}
    	valid = isValidPassword(password);
    	System.out.println(" - valid password ? " +  valid);
    	if(!valid){
    		System.out.println("password is invalid, please enter valid password");
    		System.out.println("password must contain English letters. ");
    		System.out.println("password can contain only English letters and numbers. ");
    		
    		return "invalid password";
    	}
    	//check if duplicate
    	boolean duplicate; 
    	duplicate = connector.isDuplicate(DBConstants.USER_NAME_KEY, userName);
    	if(duplicate){
    		
    		System.out.println("The choosen user-name is already exist, please choose another");
    		return "duplicate user-name - not created" ;
    	}
    	
    	duplicate = connector.isDuplicate(DBConstants.EMAIL_KEY, email);
    	if(duplicate){
    		
    		System.out.println("The choosen email is already exist, please choose another");
    		return "duplicate email - not created" ;
    	}
    	
    	//create the new user
    	connector.createNewUser(userName, email, password, isAdmin);
    	System.out.println("The User : " + userName + " has created successfully"); 
    	return "User" + userName + " has created successfully";
    }
    /**
     * 
     * @param identification
     * @param passwrod
     * @param user
     * @return!
     * 
     * authenticate user credintials 
     */
    // 
    public static boolean authenticateCredentials( String identification, String password){
    	String identificationType; 
    	//- check if it mail or user 
    	if(isValidEmail(identification)){
    		identificationType = DBConstants.EMAIL_KEY;
    	}
    	else{
    		identificationType = DBConstants.USER_NAME_KEY;
    	}
    
    	//checking the credintials. 
    	//....
    	if(connector.authenticateCredentials(identification, password, identificationType)){
    		//- if true – 
    		return true;
    		
    		
    		
    		
    		
    	}
    	else{
    		//if false return false (with message) 
    		
    		System.out.println("One of the details not correct");
    		return false;
    	}
			
    }
    
    public static User login( String identification, String password){

    	
    	if(!authenticateCredentials(identification, password)){
    		
    		System.out.println("Authentication failed");
    		return null;
    	}
    	String identificationType; 
    	//- check if it mail or user 
    	if(isValidEmail(identification)){
    		identificationType = DBConstants.EMAIL_KEY;
    	}
    	else{
    		identificationType = DBConstants.USER_NAME_KEY;
    	}
	    //create the user with the creditials (from the DB) create message
		String userID = connector.getUserID(identification, identificationType);
		Map<String, String > userDetails= connector.getUserDetails(userID);
		//For Testing - Don't delete! (just comment)
		//System.out.println(" user details from login :\n" +  userDetails);
		
		
		
		
		//Determine if it's admin user
		boolean isAdmin = Boolean.valueOf(userDetails.get(DBConstants.USER_ADMIN_KEY));
		//System.out.println(" is Admin? " + isAdmin);
		
		//creating or user or admin object
		User user;
		
		
		if (isAdmin){
			user = new Admin (userID, userDetails.get(DBConstants.USER_NAME_KEY), userDetails.get(DBConstants.EMAIL_KEY), 
				 true);
			//System.out.println(user);
			
		} 
		else{
			
			user = new User (userID, userDetails.get(DBConstants.USER_NAME_KEY), userDetails.get(DBConstants.EMAIL_KEY), 
				 true);
			
		}
		//U here - solve the problem
		System.out.println("Login successfully.");
		System.out.println("The user - " + user + "\n is logon");
		//System.out.println(user);
		///Admin user2 = new Admin();
    	return user;
    }
    /**
     * DELETE ONE USER BY NAME AND WITH PASSWORD CONFIMIRATION
     * @param sequence
     * @return!
     */
    public static boolean deleteUserByName(String userName){
    	//check if it's not super user
    	if (userName.equals(DBConstants.SUPER_ADMIN_NAME)){
			System.out.println("Super Admin cannot be deleted. ");
			return false;
			
		}
    	// Comment it. because - 1:It's cause problem when I use it in other methods
    	//2- Not good practice to do , because It's core object and in the web and like that I cannot or 
    	// It's more work anyway to handle the console input. 
    	/*
    	Scanner check = new Scanner(System.in);
    	System.out.println("U going to delete the user " + userName + " \n"
    			+"Are you sure you want to do it ? \n"
    			+"For cancel - press any key, for continue - press y ");
    	
    	String checkString = check.next();
    	if (!checkString.equals("y")){
    		System.out.println("cancelled");
    		check.close();
    		return;
    	}
    	*/
    	connector.deleteUserByName(userName);
    	System.out.println(userName + " user has deleted");
    	//check.close(); //cause problem and not right to do
    	
    	return true;
    	
    }
    
     /**
     * DELETE ONE USER BY NAME WITHOUT PASSWORD CONFIMIRATION
     * @param sequence
     * @return!
     */
    public static void deleteUserByName(String userName, int x){
    	
    	
    }
    
    /**
     * DELETE ALL USERS - WARNING!!!
     */
    public static void deleteAllUsers(){
    	
    	
    	Scanner check = new Scanner(System.in);
    	System.out.println("U going to delete all users ? are you sure ??  \n"
    			+"Are you sure you want to do it ? \n"
    			+"For cancel - press any key, for continue - press y ");
    	
    	String checkString = check.next();
    	if (!checkString.equals("y")){
    		System.out.println("cancelled");
    		//check.close();//this close problem at outer scanner methods.
    		return;
    	}
    	
    	System.out.println("Please confirm your action by typing this number");
    	int checkInt; Random rnd = new Random();
    	System.out.println(checkInt  = rnd.nextInt(1000));
    	int input = check.nextInt();
    	
    	if(input == checkInt){
    		
    		System.out.println("OK, Please confirm the next number");
    	}
    	else{
    		System.out.println("Wrong answer - cancelled");
    		return;
    	}
    	System.out.println(checkInt  = rnd.nextInt(1000));
    	input = check.nextInt();
    	//check.close();
    	if(input == checkInt){
    		
    		System.out.println("OK, all users deleted (except the super admin)");
    		connector.deleteAllUsers();
    	}
    	else{
    		System.out.println("Wrong answer - cancelled");
    		return;
    	}
    	
    	
    }
    
    /**
     * getUser by name - return new UserInterface object without authentication for use in the admin (Maybe need to be more secure.) 
     * @param userName
     * @return User
     */
    
    public static UserInterface getUserByName(String userName){
    	
    	String id = connector.getUserID(userName, DBConstants.USER_NAME_KEY);
    	return connector.getUser(id, 0);
    	
    }
    
    //get the details by id. - method
    
    //use the details for create new instance. (admin or user ??) - solution do it with UserInterface
    
    
    public static void getUserDetailsByName(String userName){
    	
    	//get id by name
    	String id = connector.getUserID(userName, DBConstants.USER_NAME_KEY);
    	System.out.println("id = " + id);
    	Map<String, String> userDetails = connector.getUserDetails(id);
    	System.out.println(userDetails.toString());
    	System.out.println(userDetails.get("email"));
    	System.out.println(connector.getUser(id,1));
    	
    }
    
    //Utilities Methods (Services / helpers)
    
    //user name - just english letters or digits. max 16 charcters . 
    public static boolean isValidUserName(String sequence){
    	//if not contain just english letter  or just english letters and numbers. 
    	//if((!sequence.matches("[a-zA-Z]*") &&  !sequence.matches("[a-zA-Z0-9]*"))){
    	if((sequence.matches("[a-zA-Z0-9]*") 
				 && !sequence.matches("[0-9]*") && !sequence.matches("[a-zA-Z]*") )
    			
    			|| 
    			sequence.matches("[a-zA-Z]*")
    			){
    		
    		return true;

    		
    	}
    	
    	return false; 
    }
    
    //password- just english letters or digits. max 16 charcters . 
    public static boolean isValidPassword(String sequence){
    	//if not contain just english letter  or just english letters and numbers. 
    	//if((!sequence.matches("[a-zA-Z]*") &&  !sequence.matches("[a-zA-Z0-9]*"))){
    	if((sequence.matches("[a-zA-Z0-9]*") 
				 && !sequence.matches("[0-9]*") && !sequence.matches("[a-zA-Z]*") )
    			
    			|| 
    			sequence.matches("[a-zA-Z]*")
    			){
    		
    		return true;

    		
    	}
    	
    	return false;
    }
    public static boolean isValidEmail(String email){
    	//boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		     //System.out.println("invalid email " + email);
		     return false;
		     
		   }
		   //System.out.println("valid email " + email);
		   return true;
    }
    
    public static boolean isDuplicate(String key, String str){
    	
    	//check if duplicate
    	boolean duplicate; 
    	duplicate = connector.isDuplicate(key, str);
    	return duplicate;
    	
    	/*
    	
    	if(duplicate){
    		
    		System.out.println("The choosen user-name is already exist, please choose another");
    		return "duplicate user-name - not created" ;
    	}
    	
    	duplicate = connector.isDuplicate(DBConstants.EMAIL_KEY, email);
    	if(duplicate){
    		
    		System.out.println("The choosen email is already exist, please choose another");
    		return "duplicate email - not created" ;
    	
    	return true; /// U HERE
    	*/
    }
    
    //Is initial runnning
    public static boolean isInitialRunning(){
    	
    	
    	return connector.isInitialRunning();
    }
    
    public void getSomeThing(){
        // do something here
        System.out.println("I am here....");
    }
    public void setSomething(int x ){
    	
    	test = x;
    	
    }
    
    
}
