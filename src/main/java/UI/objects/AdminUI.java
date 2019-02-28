package main.java.UI.objects;

import java.util.Map;

import main.java.objects.Admin;
import main.java.objects.User;
import main.java.system.constants.DBConstants;

public class AdminUI extends UserUI implements UIObject{
	
	private Admin admin = new Admin(); 
	
	
	
	public AdminUI(Admin admin){
		super(admin);
		this.admin = new Admin(admin);
		
	}
	public void manageAdminOptions(String input ){
		//u here/ now do the implementation... 
		if(input.equals("8")){
			//System.out.println("Admin option " + user.getName());
			displayUserByName();
			
		}
		else if(input.equals("9")){
			//System.out.println("Admin option " + user.getName());
			displayAllUser();
			
		}
		else if(input.equals("10")){
			deleteUserByName();
		}
		else if(input.equals("11")){
			deleteAllUsers();
		}
		
		else if(input.equals("12")){
			changeUserDetails();
		}
		else if(input.equals("13")){
			createNewUser();
		}
		
	}
	
	
	//Method for display specific user by name 
	public void displayUserByName(){
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nSHOW SPECIFIC USER BY NAME\n"+outputs.systemMessages1.get("asterisks"));

		uIEls.displayUserDetailsByName();
		
		inputs.inputDelay();
		
	}
	
	public void displayAllUser(){
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nALL USERS DETAILS:\n"+outputs.systemMessages1.get("asterisks"));

		uIEls.displayAllUser();
		inputs.inputDelay();
	}
	
	public void deleteUserByName(){
			System.out.println(outputs.systemMessages1.get("asterisks") + "\nDELETE USER BY NAME:\n"+outputs.systemMessages1.get("asterisks"));
			//uIEls.deleteUserByName();
			try{
			User user = uIEls.getUserByName();
			admin.deleteUserByName(user.getName());
			}
			catch(Exception ex){
				
			System.out.println("the user name NOT CORRECT... PLEASE TRY AGAIN");
			
		}
			inputs.inputDelay();
		
		
	}
	
	public void deleteAllUsers(){
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nDELETE USER BY NAME:\n"+outputs.systemMessages1.get("asterisks"));

		admin.deleteAllUsers();
		
		inputs.inputDelay();
		if(!admin.getName().equals(DBConstants.SUPER_ADMIN_NAME)){
			new MainUI().mainUI();
		}
		
	}
	
	//change user details (password/email) 
	public void changeUserDetails(){
		System.out.println(outputs.systemMessages1.get("asterisks") 
				+ "\nCHANGE USER DETAILS:\n"+outputs.systemMessages1.get("asterisks"));	
		User user ; String newValue = "";
			try {
				user = uIEls.getUserByName();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("the user name NOT CORRECT... PLEASE TRY AGAIN");
				inputs.inputDelay();
				return;
			}
			System.out.println("What details u want to change ? \n1-Password 2-Email");
			String userInput = inputs.getUserChoice(1, 2);
			int number = Integer.parseInt(userInput);
			if(number == 1){
				System.out.println("Enter the new password:");
				newValue = inputs.getUserChoiceSimple();
				System.out.println("new Password = NA ");
				//System.out.println("user id");
				//System.out.println(user.getId());
				admin.changeUserPassword(user, newValue);
			}
			else if(number == 2){
				System.out.println("Enter the new Email:");
				newValue = inputs.getUserChoiceSimple();
				System.out.println("new Email = " + newValue);
				//System.out.println("user id");
				//System.out.println(user.getId());
				admin.changeUserEmail(user, newValue);
			}
			/*
			
			
			
		
			else if(number == 2){
				System.out.println("Enter the new email:");
				newValue = inputs.getUserChoiceSimple();
				admin.changeUserEmail(user, newValue);
			}
			*/
			inputs.inputDelay();

	}
	
	//create new user - regualr or admin 
	public void createNewUser(){
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nCREATE NEW USER/ADMIN BY NAME:\n"+outputs.systemMessages1.get("asterisks"));
		Map<String, String > userDetails = uIEls.getNewUserDetails();
		
		String userName = userDetails.get(DBConstants.USER_NAME_KEY) ,email = userDetails.get(DBConstants.EMAIL_KEY),
				password =  userDetails.get(DBConstants.PASSWROD_KEY), isAdminString =  userDetails.get(DBConstants.USER_ADMIN_KEY);
		System.out.println(userDetails.toString());
		boolean isAdmin = Boolean.parseBoolean(isAdminString);
		admin.createNewUser(userName, email, password, isAdmin);
		inputs.inputDelay();
		
	}
}
