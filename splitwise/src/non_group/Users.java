package non_group;
import java.util.*;

import group.Group;


public class Users {
	
	Scanner in = new Scanner(System.in);
	
	// Primary Detail
	String username;
	private String email;
	private String password;
	
	// Notifications
	
	
	// My Accounts
	ArrayList<Activity> myActivity;
	HashMap<String,Double> myAccount ;
	
	// Notifications
	LinkedList<String> notifications;
	
	// Unknown Accounts
	HashMap<String,Double> unknownAccount ;
	
	// My Activity
	HashMap<String,String> transfer;
	
	// My Account Info
	double personalexpense=0;
	double totalexpense=0;
	double actualexpense=0;
	
	// Getter Methods
	
	public String getEmail() {
		return email;
	}
	
	//
	HashMap<Integer,Group> myGroups;
	
	public Users(String username, String email, String password) {
		
		// Set Primary Details
		this.username = username;
		this.email=email;
		this.password=password;
		
		notifications=new LinkedList<>();

		
		// My Account Details
		
		myAccount = new HashMap<>();
		myActivity = new ArrayList<>();
		
		// Unknown Account Details
		unknownAccount = new HashMap<>();
		
		// Transfer 
		transfer = new HashMap<>();
		
		// Groups
		myGroups = new HashMap<>();
		
		System.out.println("Account Created Successfully");
		System.out.println();
	}
	public static boolean checkPassword(String password2, Users user) {
		if(user.password.equals(password2)) {
			return true;
		}
		return false;
	}
	
}
