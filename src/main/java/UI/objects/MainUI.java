package main.java.UI.objects;

import main.java.UI.elements.InputEls;
import main.java.UI.elements.OutputEls;
import main.java.UI.elements.UIEls;
import main.java.objects.Admin;
import main.java.objects.User;

public class MainUI implements UIObject {
	
	UIEls elements = new UIEls();
	OutputEls outputs = new OutputEls(); InputEls inputs = new InputEls();
	
	//Main execution.
	public void mainUI(){
		
		//for(;;){
			
			
				
			
		System.out.println("Welcome to Mutil-Users-System.\nFor contact me please email to yimprogramming@gmail.com\n"
				+ "Or just visit my website: yimprogramming.com");
		//Here is the main execution. The initial is defined at the running execution. 
		
		String userChoice = "";
		InputEls input = new InputEls();
		
		System.out.println(outputs.systemMessages1.get("asterisks") + "\nPlease choose one of the options:"
				+ "\n" +outputs.systemMessages1.get("asterisks") );
		System.out.println("1-Create New User | 2-Login | x-Exit");
		 
		userChoice = input.getUserChoice(1, 2, "x");
		UserUI userUI ;
		switch (userChoice){
			case "1":
				System.out.println("Going to Creating");
				User user =  (User)elements.createNewUser(false);
				System.out.println("Going to user area from the main UI. ");
				
				userUI = new UserUI(user);
				userUI.mainUI();
				break;
			case "2":
				System.out.println("Going to sigining");
				User user1 = elements.login();
				System.out.println("Going to " + user1.getName() + " user area");
				
				
				if(user1.isAdmin()){
					Admin admin = new Admin(user1);
					userUI = new AdminUI(admin);
				}
				else{
					userUI = new UserUI(user1);
				}
				
				userUI.mainUI();
				
				break;
			case "x":
				
				elements.exit();
				
		
		
		}
		//}
		
		
		
	}
	
	//General methods
	
}
