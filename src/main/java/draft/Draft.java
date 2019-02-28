package main.java.draft;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.services.DBConnector;
import main.java.system.constants.DBConstants;

public class Draft {
		int port = 27017;
		MongoClient mongo = new MongoClient( "localhost" , port );
		
		//use the db
		//How it's work just with interface
		MongoDatabase database = mongo.getDatabase("multi-users-sys");
		
		//create collection
		//database.createCollection("2nd-Collection");
		
		//get collection : 		(It's also create if not exist
		MongoCollection<Document> collection = database.getCollection("users-list");
	public static final int EMAIL= 222;
 	public static void checkSequence(String sequence){
		
		//if it just word in english - say 
		
		//if it's word and numbers 
		System.out.println(sequence + " is english and  numbers ? "  + (sequence.matches("[a-zA-Z0-9]*") 
				 && !sequence.matches("[0-9]*") && !sequence.matches("[a-zA-Z]*") )); 
		//if it's just numbers
		System.out.println(sequence + " is just numbers ? " + sequence.matches("[0-9]*"));
		//there is other characters
		
		//if it's not all the above
		
		
		
		
	}
	
	public static void checkMail(String email){
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		     System.out.println("invalid email " + email);
		     return;
		     
		   }finally {
			System.out.println("anyway...");
		}
		   System.out.println("valid email " + email);
	}
	
	public static void drafti1(int key){
		
		switch(key){
			case EMAIL:
				System.out.println("mail?");
				break;
			
		
		
		
		}
		
	}
	
	/**
	 * THis is primary method that I tried to do for display all users. U can learn here some things about set. 
	 * 
	 * @param args
	 */
	
	//display all users
	public  void displayAllUsers(){
		
		MongoCollection<Document> collection = database.getCollection("");
		FindIterable<Document> iterDoc = collection.find(); 
		Iterator<Document> it = iterDoc.iterator(); 
		Iterator it2;
		TreeMap< String, TreeMap<String, String>>  data1 = new TreeMap< String, TreeMap<String, String>>();
		TreeMap< String, Set<Map.Entry<String, Object>>>  data2 = new TreeMap< String, Set<Map.Entry<String, Object>> >();
		//
		while(it.hasNext()){
			Document doc = it.next();
			Set set1 = doc.keySet();
			Collection<Object> set2 = doc.values();
			Set set3 = doc.entrySet();
			Set<Map.Entry<String, Object>> set4 = doc.entrySet();
			//Map.EntryString, Object> set5 = doc.e
			System.out.println(doc);
			System.out.println(set1);
			System.out.println(set2);
			System.out.println(set3);
			System.out.println("set4");
			System.out.println(set4);
			System.out.println("set4");
			it2 = set4.iterator();
			Map.Entry<String, Object> entry  = set4.iterator().next();
			System.out.println("entry = " + entry );entry.getValue();entry.getKey();
			data2.put(doc.get("userName").toString(), set4);
			System.out.println("?????");
			set4.forEach(System.out::println);
			set4.forEach(System.out::println);
			
			//data1.put()
			//u here - u need to make map object with the values, that every key of user-name contain set
			// like that: 
			//(Tree - Map-Element)user1 :(Set)"userid": "222", userName: "user1" , "email" : "dd@sss", ....etc|
		}
		System.out.println("the whole data : \n\n");
		System.out.println(data2);
		
		
	}
	public static void main(String[] args) {
		
		//generate ids  
		
		//the smallest Integer. 
		
		
		
		
		//do from all id's of the users array . (query ) 
		
		//str.matches("[a-zA-Z0-9]*")
		String sequence = "rrr132";
		System.out.println(sequence.matches("[a-zA-Z]*") || sequence.matches("[a-zA-Z0-9]*"));
		System.out.println(sequence.matches("[0-9]*"));
		System.out.println(sequence.matches("[a-zA-Z0-9]*"));
		System.out.println(sequence.matches("[a-zA-Z]*") );
		System.out.println(sequence.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$"));
		System.out.println(sequence.matches("[a-zA-Z]*") && sequence.matches("[a-zA-Z0-9]*"));
		
		checkSequence(sequence);
		String email = "a@a.c";
		checkMail(email);
		DBConnector connector =  DBConnector.getInstance("localhost", 27017);connector.createInitialTables();
		//Scanner scanner = new Scanner(System.in);
		//scanner.nextLine();
		
		//final int EMAIL = 1;
		//final int GOOGLE = 2;
		drafti1(EMAIL);
		Map<String, String> map1 = new HashMap<String, String>();
		
		map1.put("a", "11");map1.put("b", "222");
		
		for (Map.Entry i : map1.entrySet()){
			//System.out.println(i +" = i");
			System.out.println(i.getKey() + " = " +  i.getValue());
			//System.out.println(i.getValue());
		}
		
		
		Draft t1 = new Draft();
		System.out.println(t1);
		Document query = new Document().append(DBConstants.USER_NAME_KEY, DBConstants.SUPER_ADMIN_NAME);
		Document doc = collection.find(query).first();
		System.out.println("Doc = " + doc);
		query = new Document().append(DBConstants.USER_NAME_KEY, "asffacxvcxxgxgfgxg");
		doc = collection.find(query).first();
		System.out.println("Doc = " + doc);
		
		
	}

	@Override
	public String toString() {
		return "Draft [port=" + port + ", mongo=" + mongo + ", database=" + database + ", collection=" + collection
				+ "]";
	}
	
}
