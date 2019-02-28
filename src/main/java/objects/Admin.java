package main.java.objects;

public class Admin extends User implements UserInterface{
	
	
	
	
	
	public Admin(){
		
	}
	
	//constructor 
	public Admin(String name, String email, String password ){
		
		super(name, email, password);
		this.admin = true;
		
	}
	//constructor 
	public Admin(String id, String name, String email, String password ){
		
		super(id,name, email, password);
		this.admin = true;
		
	}
	
	public Admin(String id, String name, String email, boolean approved) {
		super(id, name, email, approved);
		this.admin = true;
		
	}
	
	//Creating with user without password
	public Admin(User user){
		this(user.getId(), user.getName(), user.getEmail(), user.isApproved() );
	}
	
	
	/**
	 * TESTING METHODS
	 */
	public void overriden(){
		System.out.println("I am overriden from ADMIN  "+ name );
	}
	
	public void adminTest(){
		System.out.println("I am admin " + name);
	}
	
	
	/**
	 * GENERAL METHODS;
	 * display one/all, delete one/all, create new user, 
	 * change user details email/password(for securty and like that good to be, not userName) 
	 *
	 *chaeck other methods at the system. 
	 */
	
	public void createNewUser(String userName, String email, String password,
    		boolean isAdmin){
		MultiUsersSystem.createNewUser(userName, email, password, isAdmin);
	}
	
	public void displayOne(String id){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		MultiUsersSystem.displayOneUser(id);
	}
	
	public void displayAllUsers(int type){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		//Todo - according to the type number u will display the data - regular/ with / without password
		if (type == 0){
			MultiUsersSystem.displayAllUsers();
		}
		else if(type == 1){
			
			MultiUsersSystem.displayAllUsersAsTabel(true, 50);
			
		}
		else if(type  == 2 ){
			
			MultiUsersSystem.displayAllUsersAsTabel(false, 50);
			
		}
		else{
			
			System.out.println("Invalid value see doc");
		}
	}
	
	public void deleteUserByName(String userName){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		MultiUsersSystem.deleteUserByName(userName);
		
	}
	
	public void deleteAllUsers(){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		MultiUsersSystem.deleteAllUsers();
		
	}
	
	
	//this will use the change user details
	public void changeUserPassword(User user, String newValue){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		
		user.setPassword(newValue);
	}
	
	
	//this will use the change user details
	public void changeUserEmail(User user , String newValue){
		//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
		user.setEmail(newValue);
		
		
		
	}
	
	/**
	 * SERVICES AND HELPER METHODS
	 */
	//general method that should come from the system and more. 
	public void changeUserDetail(User user, String key, String newValue){
		connector.updateUserDetail(key, newValue, user);
	}
	/**
	 * READ ME :
	 * *UserName is not change able!
	 */
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", approved="
				+ approved + ", connector=" + connector + ", userType=" + userType + ", admin=" + admin + "]";
	}
	/*
	//check if user authenticated
		if(!approved){
			System.out.println("user isn't log-on");
			return;
		}
	*/
	
	
	
	
	
	

}
