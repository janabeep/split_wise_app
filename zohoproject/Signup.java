package zohoproject;

import java.util.*;

//class grp{
//	
//	HashMap<String, HashMap<String,Double> > grpaccount;
//	HashMap<String, ArrayList<String>> grpdetail;
//	
//}
//class user extends grp{
//	String username;
//	private String email;
//	private String password;
//	List<Group> myGroups;
//	public user(String username, String pass, String mail) {
//		
//		this.username = username;
//		this.email = mail;
//		this.password = pass;
//		myGroups = new ArrayList<>();
//	}

//	public String getPassword() {
//		return password;
//	}
//	public String getEmail() {
//		return email;
//	}
//}
public class Signup extends user {
	Scanner in = new Scanner(System.in);
	
	int userid=0;
	
	int friendnotify;
	ArrayList<user> waitfriends;
	ArrayList<user> friends;
	ArrayList<String[]> unknown_account_details;
	HashMap<String,Double> unknown_accounts ;
	ArrayList<String []> my_accounts_details;
	HashMap<String,Double> my_accounts ;
	
	
	
	public Signup(String usr,String pass,String mail) {
		super(usr, pass, mail);
		
		grpaccount = new HashMap<>();
		grpdetail = new HashMap<>();
		userid = userid+1;
		unknown_account_details = new ArrayList<>();
		friendnotify=0;
		friends = new ArrayList<>();
		waitfriends = new ArrayList<>();
		unknown_accounts = new HashMap<>();
		my_accounts = new HashMap<>();
		my_accounts_details = new ArrayList<>();
		
		
		
		System.out.println("Created successfully :)");
		System.out.println();
		
	}



	
}
