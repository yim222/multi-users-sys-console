package main.java.UI.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.java.objects.User;

/**
 * //Output is methods that display elements
 * Do for any output method that display it. 
 * -Do it in methods for manipulate is if necessary
 * 
 * @author lingar
 *
 */
public class OutputEls {
	
	
	
	final String welcomeMessage = OutputEls.getFileContent("textContent/welcomeMessage.txt"),
			changeEmail = "insert yout new email", changePassword= "insert yout new password";
	
	final String changeMessage = "You've choosed to change your" , email = "email", password = "password";
	
	public final Map <String,String> systemMessages1 ;
	
	public OutputEls(){
		
		//Assigning messages:
		//systemMessages1.put("message1", "Example to system message");
		systemMessages1 = new HashMap<String, String >()
			{{
			     put("message1", "Example to system message");
			     put("passwordBrief", passwordUserBrief("Password"));
			     put("userBrief",passwordUserBrief("User-Name") );
			     put("asterisks","********************");
			}};
		
		
	}
	
	
	
	//Output directly methods. (display text) 
	//Output is methods that display elements
	public void displayUserDetails(User user){
		
		user.displayUserDetails(user);
		
	}
	
	
	public void displayWelcomeMessage(){
		
		System.out.println(welcomeMessage);
		
	}
	
	//For change messages (email/password/messages) 
	public void changeField(String field){
		System.out.println("Change your " + field +":");
				
		
	}
	
	public void changePassword(){
		changeField(password);
		System.out.println("Please insert your new " + password);
		
	}
	
	public void changeEmail(){
		changeField(email);
		System.out.println("Please insert your new " + email);
		
	}
	
	public void displayUserTemplate(User user){
		System.out.println(systemMessages1.get("asterisks"));
		//String template = ""
		//System.out.println(userOptions(user.isAdmin()));
		String adminText = "";
		if (user.isAdmin()){adminText = " (Admin User ) ";}
		System.out.println("Hi " + user.getName() + adminText 
				+"\nThis is your Messages : " );
		user.displayMessages();
		System.out.println(userOptions(user.isAdmin()));//display user options...

		
		//return template;
				
	}
	
	public void displayUserMessages(User user){
		
		user.displayMessages();
	}
	
	
	/**
	 * TEXT GENERATED METHODS
	 * @param type
	 * @return
	 */
	public String passwordUserBrief(String type){
		/*
		System.out.println("Enter " + type);
		System.out.println(type + " must contain English letters. ");
		System.out.println(type + " can contain only English letters and numbers. ");
		*/
		return type + " must contain English letters. \n" + type + " can contain only English letters and numbers. \n";
	}
	
	//Templates
	//User template text - Not in use right now 
	public String userTemplate(User user){
		

		String template = ""
				+"Hi " + user.getName()
				+"\nThis is your Messages : " ;
		user.displayMessages();
				

		
		return template;
				
	}
	
	public String userOptions(boolean admin){
		String string = "Please choose one of the following options: "
			+"\nAt any time type x for exit or m for the main menu"
			+"\n"
			+"\n1- Change messages. "
			+"\n2- change password. "
			+"\n3- Change Email. "
			+"\n4- Show my details. (with password ? – yeah  ) "
			+"\n5- show messages. "
			+"\n6- log off. "
			+"\n7- Delete this account (with password) . ";
		
		if(admin){
			string += "\n8 – show specific user by name "
			+"\n9 – show all users"
			+"\n10- delete one user . "
			+"\n11 – delete all users!"
			+"\n12- change user details (password/email) "
			+"\n13- create new user (regular/admin) ";
		
			
		}
		return string;//u here. continue with doing it. add it to the template methods properly. 
		
	}
	
	//Services methods and helpers / utils 
	public static String getFileContent(String fileName){
		
		//read all line file. 
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
			return stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "There was a problem";
		}
		
	}
	
	public static void displayInput(String input){
		
		
		System.out.println(input);
		
		
		
	}
	
	public static void initialOutputs(){
		displayInput("");
	}
	
}
