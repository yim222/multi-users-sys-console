package main.java.objects;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import main.java.services.DBConnector;

public class MultiUsersSystemV1 {
	
	
	//singelton 
	/*
	 * 
	 * Methods : authenticate user. approve user. cancelApprove user. addUser, removeUser, display all
	 */
	private static MultiUsersSystemV1 myObj;
    /**
     * Create private constructor
     */
    private MultiUsersSystemV1(){
         
    }
    
   public  int test = 5; 
    /**
     * Create a static method to get instance.
     */
    public static MultiUsersSystemV1 getInstance(){
        if(myObj == null){
            myObj = new MultiUsersSystemV1();
        }
        return myObj;
    }
    //Methods u here (in pararell to DB connector. 
    public static String createNewUser(String userName, String email, String password){
    	System.out.println("creating new user... ");
    	DBConnectorOld1.getInstance("localhost", 27017);
    	
    	//check if valid userName
    	boolean valid;
    	valid = isValidUserName(userName);
    	System.out.println(userName + " - valid user ? " +  valid);
    	if(!valid){
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
    	
    	//check if duplicate
    	boolean duplicate; 
    	duplicate = DBConnectorOld1.isDuplicate("userName", userName);
    	if(duplicate){
    		
    		System.out.println("The choosen user-name is already exist, please choose another");
    		return "duplicate user-name - not created" ;
    	}
    	
    	duplicate = DBConnectorOld1.isDuplicate("email", email);
    	if(duplicate){
    		
    		System.out.println("The choosen email is already exist, please choose another");
    		return "duplicate email - not created" ;
    	}
    	
    	DBConnectorOld1.createNewUser(userName, email, password);
    	return "success";
    }
    
    // authenticate user method 
    
    //Utilities Methods
    
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
    public void getSomeThing(){
        // do something here
        System.out.println("I am here....");
    }
    public void setSomething(int x ){
    	
    	test = x;
    	
    }
    
    
}
