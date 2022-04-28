package zoho_final;

import java.util.*;



public class Account_Details {

Scanner in = new Scanner(System.in);
	//String name; (hold)
	String username;
	private String email;
	private String password; 

	HashMap<Integer,New_Group> my_Groups;
	HashMap<Integer,New_Group_Account> Group_Account;//edited
	
	int notify;
	LinkedList<String> waitfriends;
	HashSet<String> friends;
	
	
	//ArrayList<Activities1> unknown_account_details2;  //(important)
	HashMap<String,Double> unknown_accounts ;
	
	//ArrayList<Activities2> unknown_account_details3;
	HashMap<String,ArrayList<Activities2>> unknown_account_details3;
	
	ArrayList<Activities2> my_accounts_details3;
	//ArrayList<Activities1> my_accounts_details2;    //(important)
	HashMap<String,String> transfer;
	HashMap<String,Double> my_accounts ;
	double personalexpense=0;
	double totalexpense=0;
	double actualexpense=0;
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public Account_Details(String username,String pass,String email) {
		

		//this.name =name;(hold)
		Group_Account = new HashMap<>();
		this.username = username;
		this.email = email;
		this.password = pass;
		notify=0;
		friends = new HashSet<>();
		waitfriends = new LinkedList<>();
		unknown_accounts = new HashMap<>();
		my_accounts = new HashMap<>();
		my_Groups = new HashMap<>();
		
		transfer = new HashMap<>();
		//my_accounts_details2=new ArrayList<>();
		//unknown_account_details3=new ArrayList<>();
		unknown_account_details3 = new HashMap<>();
		my_accounts_details3 = new ArrayList<>();
		//unknown_account_details2 = new ArrayList<>();
		System.out.println("Created successfully :)");
		System.out.println();
	}
	
}
