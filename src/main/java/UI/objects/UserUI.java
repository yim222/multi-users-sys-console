package main.java.UI.objects;

import main.java.UI.elements.InputEls;
import main.java.UI.elements.OutputEls;
import main.java.UI.elements.UIEls;
import main.java.objects.User;

public class UserUI implements UIObject{
	protected InputEls inputs = new InputEls();
	protected OutputEls outputs = new OutputEls();
	protected UIEls uIEls = new UIEls();
	
	protected User user = new User();
	
	public UserUI(){
		//mainUI();
		//Don't do it, I want flexibility 
		
	}
	
	public UserUI(User user){
		
		this.user = user;
	}
	
	@Override
	public void mainUI() {
		
		
		System.out.println("Hello, at any time, press m to go to main menu or x to exit");
		for(;;){
			
			outputs.displayUserTemplate(user);//make overriden specific methods todo
			manageOptions();
			
			
			
			
		}
		
	}
	
	public void manageOptions(){
		//System.out.println("this instanceof AdminUI" + (this instanceof AdminUI));
			String userInput = "";
			if(this instanceof AdminUI){
				
				userInput = inputs.getUserChoice(1, 13, "x", "m");//make overriden specific methods todo
			}
			else {
				userInput = inputs.getUserChoice(1, 7, "x", "m");//make overriden specific methods todo
			}
			int userDigitInput = -1;
			
			//convert string to digit if it's digit. For checking values
			try {
				userDigitInput = Integer.parseInt(userInput);
			} catch (Exception e) {
				// TODO: handle exception
				//System.out.println("Input isn't digit");
			}
			//System.out.println("hi ? ");
			if(userInput.equals("m")){
				
				System.out.println("Go to menu...");
				new MainUI().mainUI();
				return;
				
			}
			//Exit
			else if(userInput.equals("x")){
				
				uIEls.exit();
			}
			
			//Here implement the user general option 
			else if(userDigitInput > 0 && userDigitInput < 8){
				
				//change messages
				if(userDigitInput == 1){
					
					changeMessages();
					
				}
				
				//change password
				else if(userDigitInput == 2){
					
					changePassword();
					
				}
				
				//change email
				else if(userDigitInput == 3){
					
					changeEmail();
					
				}
				
				//display User Details
				else if(userDigitInput == 4){
					
					displayDetails();
					
				}
				
				
				//Display User Messages
				else if(userDigitInput == 5){
					
					displayMessages();
					
				}
				//Log-off user
				else if(userDigitInput == 6){
					
					logOff();
					new MainUI().mainUI();
					
				}
				
				//Delete this account
				else if(userDigitInput == 7){
					System.out.println(this.user);
					boolean approvedAction = deleteUser();
					if(!approvedAction){
						
						
						return;
					}
					System.out.println("Going to main area...");
					new MainUI().mainUI();
					
				}
			}
			
			//Here implement the admin option. Need to think how. 
			else if(userDigitInput > 7 && userDigitInput < 14){
				
				manageAdminOptions(userInput);
				
			}
			
		/*
		if (userInput.equals("1") ){
			
			System.out.println(user.getName() + " options ");
			changeMessages();
			
		}
		else if(userInput.equals("7") && user.isAdmin()){
			AdminUI adminUI = new AdminUI((Admin)user); 
			adminUI.manageAdminOptions(userInput);
		}
		else{
			
			System.out.println("Not available");
		}
		*/
		
	}
	
	//just for the code will compile
	public void manageAdminOptions(String input ){
		
		System.out.println("This is NA on user");
		
		
	}
	//Method for change the user messages
	public void changeMessages(){
		
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nCHANGE MESSAGES\n "+outputs.systemMessages1.get("asterisks"));
		uIEls.changeMessages(this.user);
		inputs.inputDelay();
		
		
	}
	//System.out.println(outputs.systemMessages1.get("asterisks") + "\nCHANGE MESSAGES\n "+outputs.systemMessages1.get("asterisks"));
	
	//Method for change the user password
	public void changePassword(){
		
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nCHANGE PASSWORD\n "+outputs.systemMessages1.get("asterisks"));
		uIEls.changePassword(this.user);
		inputs.inputDelay();
		
	}
	
	
	//Method for change the user password
	public void changeEmail(){
		
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nCHANGE EMAIL\n "+outputs.systemMessages1.get("asterisks"));
		uIEls.changeEmail(this.user);
		inputs.inputDelay();
		
	}
	//Method for display the user details
	public void displayDetails(){
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nUSER-DETAILS\n"+outputs.systemMessages1.get("asterisks"));
		user.displayUserDetails(this.user);//Think this argument is unnecssary 
		inputs.inputDelay();
	}
			
	//Method for display the user messages
	public void displayMessages(){
			System.out.println(outputs.systemMessages1.get("asterisks") + "\nUSER-DETAILS...\n"+outputs.systemMessages1.get("asterisks"));
			outputs.displayUserMessages(this.user);
			inputs.inputDelay();
		
	}
	
	//Method for user log-off
	public void logOff(){
			System.out.println(outputs.systemMessages1.get("asterisks") + "\nLOGGING-OFF...\n"+outputs.systemMessages1.get("asterisks"));
			user.logOff();
			inputs.inputDelay();
			System.out.println("Going to main area...");
	}
	
	//Method for user log-off
	public boolean deleteUser(){
			
			boolean approved = true;
			System.out.println(outputs.systemMessages1.get("asterisks") + "\nDELETE THIS ACCOUNT(!ATTENTION)...\n"+outputs.systemMessages1.get("asterisks"));
			if(!uIEls.deleteUser(this.user, true
					)){
				
				approved = false;
			}
			inputs.inputDelay();
			//System.out.println();
			
			return approved;
			//inputs.getUserChoiceSimple();
	}
	/**
	 * GETTER AND SETTERS
	 */
}
