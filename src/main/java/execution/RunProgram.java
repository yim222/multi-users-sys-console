package main.java.execution;

import main.java.objects.MultiUsersSystem;
import main.java.UI.objects.*;

public class RunProgram {
	//This is the main execution
	
	
	
	public static void main(String[] args) {
		
		if (MultiUsersSystem.isInitialRunning()){
			new InitialUI().mainUI();
			return; 
			
		}
		//all the main area 
		else{
			
			//create connection to the DB 
			MultiUsersSystem.getInstance();
			for( ; ; ){
				
				new MainUI().mainUI();
			}
			
			
		}
		
	}
	
	
}
