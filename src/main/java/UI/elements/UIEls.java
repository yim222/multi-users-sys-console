package main.java.UI.elements;

import java.util.HashMap;
import java.util.Map;

import main.java.objects.Admin;
import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.objects.UserInterface;
import main.java.system.constants.DBConstants;
import main.java.system.constants.SystemConstants;

public class UIEls {
	
	InputEls inputs = new InputEls();
	OutputEls outputs = new OutputEls();
	String userInput = null;
	public Admin initialRunning(){
		
		//The if-else conditions and the sequent before and after are in the object class (InitialUI)
		
		Admin admin = new Admin( MultiUsersSystem.initialRunning() );
		OutputEls.displayInput(outputs.welcomeMessage);
		
		changePassword(admin, false);/// change the password without confimiration.
		changeEmail(admin);
		return admin;
		
	
		
	}
	
	public void changeEmail(User user){
		
		outputs.changeEmail();
		userInput = inputs.getUserChoiceSimple();
		user.setEmail(userInput);
		//boolean for determine if it's the high level (not show twice) 
		boolean firstLoop = true; 
		//case for invalid value
		if(!userInput.equals(user.getEmail())){
			
			changeEmail(user);
			firstLoop = false;
		}
		
		if(firstLoop){
			System.out.println("Email has changed, those are the current user's details: ");

			outputs.displayUserDetails(user);
			
		}
		
	}
	/**
	 * Display userDetails by name
	 * 
	 * @param userName
	 */
	
	public void displayUserDetailsByName(){
		try{
			/*
			System.out.println("Insert the name of the user you want to disply : ");
			userInput = inputs.getUserChoiceSimple();
			*/
			User user = getUserByName();
			outputs.displayUserDetails((User)user);
		}catch(Exception ex){
			
			System.out.println("the user name NOT CORRECT... PLEASE TRY AGAIN");
			
		}
		
		
	}
	public void changePassword(User user){
		//check current password first
		System.out.println("Please enter your current password: ");
		userInput = inputs.getUserChoiceSimple();
		if(!MultiUsersSystem.authenticateCredentials(user.getName(), userInput)){
			
			System.out.println("The password isn't correct. Try again please.");
			return;
		}
		
		
		outputs.changePassword();
		userInput = inputs.getUserChoiceSimple();
		user.setPassword(userInput);
		//boolean for determine if it's the high level (not show twice) 
		boolean firstLoop = true; 
		//case for invalid value
		if(!userInput.equals(user.getPassword())){
			
			changePassword(user);
			firstLoop = false;
		}
		if(firstLoop){
			System.out.println("Password has changed, those are the current user's details: ");
		
			outputs.displayUserDetails(user);
		}
	}
	
	//change password with/out confimiration - without is for quick and short changing. like at the admin.
	public void changePassword(User user, boolean confimiration){
		//check current password first - if it's request
		if (confimiration){
			System.out.println("Please enter your current password: ");
			userInput = inputs.getUserChoiceSimple();
			if(!MultiUsersSystem.authenticateCredentials(user.getName(), userInput)){
			
			System.out.println("The password isn't correct. Try again please.");
			return;
		}
		
		}
		
		
		outputs.changePassword();
		userInput = inputs.getUserChoiceSimple();
		user.setPassword(userInput);
		//boolean for determine if it's the high level (not show twice) 
		boolean firstLoop = true; 
		//case for invalid value
		if(!userInput.equals(user.getPassword())){
			
			changePassword(user);
			firstLoop = false;
		}
		if(firstLoop && confimiration){
			System.out.println("Password has changed, those are the current user's details: ");
		
			outputs.displayUserDetails(user);
		}
	}
	
	
	public UserInterface createNewUser(boolean isAdmin){
		
		System.out.println("Please fill the following details...\n"
				+ "Enter User-Name:");
		System.out.println(outputs.systemMessages1.get("userBrief"));
		
		userInput = getValidUserName();
		System.out.println("The user name chosen: " + userInput);
		String userName = userInput;
		
		System.out.println("Enter Email");
		userInput = getValidEmail();
		System.out.println("The email chosen: " + userInput);
		String email = userInput;
		
		System.out.println("Enter Password");
		
		System.out.println(outputs.systemMessages1.get("passwordBrief"));
		userInput = getValidPassword();
		//System.out.println("The password chosen: " + userInput);
		String password = userInput;
		
		System.out.println("Creating new user...");
		
		MultiUsersSystem.createNewUser(userName, email, password, isAdmin);
		
		if(isAdmin){
			System.out.println("New user has been created. This user have ADMIN permissions.");
			System.out.println("Going to admin-area");
		}
		
		else{
			
			System.out.println("New user has been created. This user have NO ADMIN permissions.");
			System.out.println("Going to user-area");
		}
		
		return MultiUsersSystem.getUserByName(userName);
		
	}
	
	/**
	 * Get inputs of new user : 
	 * return Map with user details
	 */
	public Map<String, String> getNewUserDetails(){
		Map<String, String>  userDetails= new HashMap<String, String>();
		
		System.out.println("Please fill the following details...\n"
				+ "Enter User-Name:");
		System.out.println(outputs.systemMessages1.get("userBrief"));
		
		
		userInput = getValidUserName();
		
		System.out.println("The user name chosen: " + userInput);
		userDetails.put(DBConstants.USER_NAME_KEY, userInput);
		
		System.out.println("Enter Email");
		userInput = getValidEmail();
		System.out.println("The email chosen: " + userInput);
		userDetails.put(DBConstants.EMAIL_KEY, userInput);
		
		System.out.println("Enter Password");
		
		System.out.println(outputs.systemMessages1.get("passwordBrief"));
		userInput = getValidPassword();
		//System.out.println("The password chosen: " + userInput);
		userDetails.put(DBConstants.PASSWROD_KEY, userInput);
		
		System.out.println("User or Admin ? \n1-User 2-Admin");
		userInput = inputs.getUserChoice(1, 2);
		boolean admin = false;
		int choice = Integer.parseInt(userInput);
		if(choice == 1){
			admin = false;
		}
		
		else if (choice == 2 ){
			
			admin = true; 
			
		}
		
		
		userDetails.put(DBConstants.USER_ADMIN_KEY	, admin+"");
		
		System.out.println("Creating new user...");
		
		
		
		return userDetails;
	}
	
	/**
	 * Login to system element either user or admin. 
	 * Should return user
	 */
	public User login(){
		String identification , password;
		
		System.out.println("Enter User-Name or Email");
		identification = inputs.getUserChoiceSimple();
		System.out.println("Enter Password");
		password = inputs.getUserChoiceSimple();
		User user = MultiUsersSystem.login(identification, password);
		if(user == null){
			System.out.println("Try Again");
			return login();
		}
		
		return user;
		
		
		
	}
	
	/**
	 * Element for chage user messages
	 * @param user
	 */
	public void changeMessages(User user){
		
		String userInput= "";
		
		//creating outputs and inputs . 
		user.displayMessages();
		System.out.println("Please choose the message you want to chagne : 1 or 2");//, "please right the new message u want to insert: ");
		userInput = inputs.getUserChoice(1, 2);
		int msgNumber = Integer.parseInt(userInput);
		System.out.println("You choose to chagne message " + msgNumber);
		System.out.println("please write the new message that you want to insert: ");
		userInput = inputs.getUserChoiceSimple();
		System.out.println("This is the new message : \n" + userInput);
		
		//changing the message 
		user.changeMessage(DBConstants.USER_MESSAGES_KEYS[msgNumber-1], userInput);
		
		System.out.println("Congratulation! this is your new messages : ");
			user.displayMessages();
		
		
		
		
		
		
	}
	
	//Delete User 
	public boolean deleteUser(User user, boolean passwordConfimiration){
				
		System.out.println("Are you sure you want to delete user " + user.getName()
		+ "??\nFor yes press y - for cancel - any other key ");
		String input = inputs.getUserChoiceSimple();
		if(input.equals("y")){
			if(passwordConfimiration && !authenticatePassword(user)){
					/*case for super admin */
					//|| user.getName().equals(DBConstants.SUPER_ADMIN_NAME)){
			System.out.println("Action Cancelled");
			return false;
			
			}
			
			
			return MultiUsersSystem.deleteUserByName(user.getName());
		}
		else{
			System.out.println("Action cancelled");
			return false;
		}
		
	}
	
	//Delete user by name - without password confimiration/*
	public void deleteUserByName(){
		
		try{
			User user = getUserByName();
			deleteUser(user, false);
		}
		catch(Exception ex){
			
			System.out.println("the user name NOT CORRECT... PLEASE TRY AGAIN");
			
		}
		
		
		
	}
	
	public boolean authenticatePassword(User user){
		/*
		 * //check current password first
		System.out.println("Please enter your current password: ");
		userInput = inputs.getUserChoiceSimple();
		if(!MultiUsersSystem.authenticateCredentials(user.getName(), userInput)){
			
			System.out.println("The password isn't correct. Try again please.");
			return;
		}
		 */
		System.out.println("Please enter your password: ");
		userInput = inputs.getUserChoiceSimple();
		if(!MultiUsersSystem.authenticateCredentials(user.getName(), userInput)){
			
			System.out.println("The password isn't correct. Try again please.");
			return false;
		}
		
		
		return true;
	}
	
	public void displayAllUser(){
		
		MultiUsersSystem.displayAllUsersAsTabel(true, SystemConstants.USERS_TABLE_ROW_WIDTH);
	}
	
	public void exit(){
		System.out.println("Exit... Please Press Enter to close the window and finish ");
				inputs.getUserChoiceSimple();
				System.exit(0);
	}
	
	public User getUserByName(){
		System.out.println("Enter the user-name you want to work on");
			userInput = inputs.getUserChoiceSimple();
			User user = (User)MultiUsersSystem.getUserByName(userInput); // - this lines do as seperate method
			return user;
	}
	/**
	 * Service, Helpers, and Utils methods.
	 * @return
	 */
	
	//Check and return new valid user-name
	public String getValidUserName(){
		
		userInput = inputs.getUserChoiceSimple();
		
		//check if valid
		boolean valid, duplicate;
    	valid = MultiUsersSystem.isValidUserName(userInput);
    	duplicate = MultiUsersSystem.isDuplicate(DBConstants.USER_NAME_KEY, userInput) ;
    	
    	
    	//If not valid - continue until it's valid . 
    	
    	if(!valid){
			System.out.println("invalid userName - just English letters or digits" );
    		return getValidUserName();
    		
    		
    	}
		//check if  duplicate
    	else if(duplicate){
    		System.out.println("The choosen user-name is already exist, please choose another");
    		return getValidUserName();
    		
    	}
		
		return userInput;
	}
	
	//Check and return new valid email
	public String getValidEmail(){
		
		userInput = inputs.getUserChoiceSimple();
		
		//check if valid
		boolean valid, duplicate;
    	valid = MultiUsersSystem.isValidEmail(userInput);
    	duplicate = MultiUsersSystem.isDuplicate(DBConstants.EMAIL_KEY, userInput) ;
    	
    	
    	//If not valid - continue until it's valid . 
    	
    	if(!valid){
			System.out.println("Email address  is invalid, please enter valid Email" );
    		return getValidEmail();
    		
    		
    	}
		//check if  duplicate
    	else if(duplicate){
    		System.out.println("The choosen email is already exist, please choose another");
    		return getValidEmail();
    		
    	}
		
		return userInput;
	}
	
	
	//Check and return new valid password
	public String getValidPassword(){
		
		userInput = inputs.getUserChoiceSimple();
		
		//check if valid
		boolean valid ;
    	valid = MultiUsersSystem.isValidPassword(userInput);
    	
    	
    	
    	//If not valid - continue until it's valid . 
    	
    	if(!valid){
			System.out.println("invalid Password -  ONLY English letters or digits" );
    		return getValidUserName();
    		
    		
    	}
		
		
		return userInput;
	}
	
	
}
