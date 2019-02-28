package main.java.draft;
import java.io.Console;
public class HiddenCharacter {
//Not work on eclipse try to run it in cmd
//Here is the tutorial
	//http://www.tutorialspoint.com/java/io/console_readpassword.htm
	
	public static void main(String[] args) {
		System.out.println("Hello");
		char[] testMe = {'a','b','c'};
		System.out.println(testMe.toString());
		
		
		
		Console cnsl = null;
      String alpha = null;
      
      try {
      
         // creates a console object
         cnsl = System.console();

         // if console is not null
         if (cnsl != null) {
            
            // read line from the user input
            alpha = cnsl.readLine("Name: ");
            
            // prints
            System.out.println("Name is: " + alpha);
            
            // read password into the char array
            char[] pwd = cnsl.readPassword("Password: ");
            
            // prints
            System.out.println("Password is: "+pwd.toString());
         } 
         
      } catch(Exception ex) {
         
         // if any error occurs
         ex.printStackTrace();      
      }
		
	}
}
