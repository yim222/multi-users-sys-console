package main.java.testing;

import java.util.concurrent.TimeUnit;

import main.java.services.DBConnectorOld1;

public class TestDBConnectorOld {
	
	  
	
	public static void main(String[] args){
		
		System.out.println("DBConnector.dbMongo =  " + DBConnectorOld1.getDbMongo());
		DBConnectorOld1.getInstance("localhost", 27017); 
		System.out.println("DBConnector.dbMongo =  " + DBConnectorOld1.getDbMongo());
		DBConnectorOld1.connectDB();
		System.out.println("DBConnector.dbMongo =  " + DBConnectorOld1.getDbMongo());
		DBConnectorOld1.connectDBData();
		System.out.println("DBConnector.dbMongo =  " + DBConnectorOld1.getDbMongo());
		DBConnectorOld1.createGetPrimaryTables();
		DBConnectorOld1.createNewUser("new user 11", "email", "password");
		
		//if u remove the delay , it's won't work
		//because the DB closed before the next query be excuted 
		try        
		{
		    Thread.sleep(2000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		DBConnectorOld1.createNewUser("new user 12", "email", "password");
		DBConnectorOld1.closeDB();
		
		//test is duplicate:
		//boolean b = DBConnector.isDuplicate("email", "email2");
		
		//System.out.println("Is duplicate ? " + b);
		
		
		
	}
}
