package non_group;

import java.time.LocalDateTime;



import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import group.Group;
import group.GroupAccount;
import group.GroupHome;




public final class Utility {
	
	 
	private Utility(){	
	}
	
	static Scanner in = new Scanner(System.in);
	
	// Date
	public static String getDate() {
		System.out.println();
			LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  	// Current Date and time
		 return formattedDate;
	}
			
	// Goback function
	public static boolean back(String s) {
		System.out.println();
		if(s.equals("*")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	// Check Username 
	public static boolean checkName(String s) {
		System.out.println();
		String usernameRegex ="^[A-Za-z]\\w{1,29}$";  // regex for username
	    Pattern pattern = Pattern.compile(usernameRegex);
	    Matcher matcher = pattern.matcher(s);
	    if(matcher.matches()) {
	    	return true;
	    }
	    else {
	    	return false;
	    }	
	}
	
	// Check Email
	public static boolean checkEmail(String s) {
		System.out.println();
		String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // regex for email
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(s);
		if(matcher.matches()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Check Password
	public static boolean checkPassword(String s) {
		System.out.println();
		String passwordRegex ="^(?=.*[0-9])"
	            + "(?=.*[a-z])(?=.*[A-Z])"
	            + "(?=.*[!@#$%^&+=])"
	            + "(?=\\S+$).{8,20}$";//**************** regex for password
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(s);
		if(matcher.matches()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Check Number
	public static int checkNumber() {
		System.out.println();
		while(true) {
			String s = in.nextLine();
			if(s.equals("*")) {
				return -1;
			}
			try {
				int n = Integer.parseInt(s);
				return n;
			}
			catch(Exception e) {
				System.out.println("Enter a number");
				System.out.println();
			}
		}
	}

	
	// Add Expense
	public static void addExpense(HashMap<String, Users> allAccount, Users user) {
		System.out.println();
		System.out.println("For what purpose you spend money ");
		System.out.println();
		String description = in.nextLine();
		System.out.println();
		while(true) {
			System.out.println("Are you going split your expense with friends ? type 'Y' or 'N' ");
			System.out.println();
			String option = in.nextLine();
			if(option.equals("Y") || option.equals("y")) {
				
				System.out.println("1. Split expense equally with friends");
				System.out.println("2. Split expense unequally with friends");
				System.out.println();
				int splitOption =0;
				while(true) {
					try {
						String so = in.nextLine();
						if(Utility.back(so)) {
							return;
						}
						splitOption = Integer.parseInt(so);
						if(splitOption ==1) {
							Utility.splitEqual(allAccount,user,description);
							break;
						}
						else if(splitOption==2) {
							Utility.splitUnequal(allAccount,user,description);
							break;
						}
						System.out.println("Enter 1 or 2");
						
					}
					catch(Exception e){
						System.out.println("Enter a number ");
						System.out.println();
					}
				}
				
				break;
			}
			else if(option.equals("N") || option.equals("n")) {
				Activity object = Utility.personalExpense(user.username,description);
				if(object==null) {
					break;
				}
				double money = object.totalMoney;
				user.personalexpense+=money;
				user.myActivity.add(object);
				break;
			}
			else if(Utility.back(option)) {
				return;
			}
			else {
				System.out.println("Enter 'Y' or 'N' ");
				System.out.println();
			}
		}
	}
	
	//  Unequall Split
	private static void splitUnequal(HashMap<String, Users> allAccount, Users user, String description) {
		System.out.println();
		
		System.out.println("Enter money spent for yourself ? If not enter zero !");
		System.out.println();
		double mySpent = Utility.getMoney();
		if(mySpent==-1) {
			return;
		}
		user.actualexpense+=mySpent;
		
		ArrayList<String> listOfMembers = new ArrayList<>();
		boolean check = Utility.getMembers(listOfMembers,user.username);
		if(!check) {
			return;
		}
		double spentMoney[] = new double[listOfMembers.size()];
		double totalMoney = Utility.getMembersMoney(spentMoney, listOfMembers);
		totalMoney+=mySpent;
		user.totalexpense+=totalMoney;
		Activity objectActivity = new Activity("expense",Utility.getDate(),description,user.username,totalMoney,spentMoney,
				listOfMembers,mySpent,user.username,"Un Equall Split");
		user.myActivity.add(objectActivity);
		for(int i=0;i<listOfMembers.size();i++) {
			if(allAccount.containsKey(listOfMembers.get(i))) {
				Users friend = allAccount.get(listOfMembers.get(i));
				Utility.isFirstTransaction(user.myAccount,user.username,friend.myAccount,friend.username);
				friend.totalexpense+=spentMoney[i];
				friend.myActivity.add(objectActivity);
				Utility.transaction(user.username,user.myAccount,friend.username, friend.myAccount, spentMoney[i]);
			}
			else {
				
				if(!user.unknownAccount.containsKey(listOfMembers.get(i))) {
					System.out.println("Pop Up !!  : "+listOfMembers.get(i)+" dosent have account, add as an unknown user");
					user.unknownAccount.put(listOfMembers.get(i), 0.0);
				}
					Utility.unknownTransaction(user.unknownAccount, listOfMembers.get(i), spentMoney[i], "addition");
			}			
		}
		System.out.println("Successfully Added");
		
	}


	// Get Members Money
	public static double getMembersMoney(double[] spentMoney, ArrayList<String> listOfMembers) {
		System.out.println();
		double totalMoney = 0.0;
		for( int i=0; i<spentMoney.length; i++ ) {
			System.out.println("Enter money spent for "+listOfMembers.get(i));
			System.out.println();
			spentMoney[i] = Math.round(Utility.getMoney()) ;
			totalMoney+=spentMoney[i];
		}
		return totalMoney;
	}



	// Get list of members
	private static boolean getMembers(ArrayList<String> listOfMembers , String username) {
		System.out.println();
		while(true) {
			System.out.println("Enter friend name ");
			System.out.println("Enter '!' for stop adding");
			String friendName = in.nextLine();
			if(Utility.back(friendName)) {
				return false;
			}
			if(friendName.equals("!")) {
				if(listOfMembers.isEmpty()) {System.out.println("No friends added "+System.lineSeparator()+" Add friends ");}
				else {break ;}
			}
			if(Utility.checkName(friendName)) {
				if(friendName.equals(username)) {
					System.out.println("You should not enter your name here ");
					System.out.println();
				}
				else {
					listOfMembers.add(friendName);
				}	
			}
			else {
				System.out.println("Username not correct");
				System.out.println("Note :- ");
				System.out.println(" Your username must be unique of length 2 to 30 characters(include) ");
				System.out.println(" contains only Alphabets, Numbers, ( _ ) Underscore");
				System.out.println(" first should be an alphabet");
			}
			
		}
		return true;
	}

	// Split Expense Equally
	private static void splitEqual(HashMap<String, Users> allAccount, Users user, String description) {
		System.out.println();
		double money = Utility.getMoney();
		if(money==-1) {
			return;
		}
		
		ArrayList<String> listOfMembers = new ArrayList<>();
		
		boolean check = Utility.getMembers(listOfMembers,user.username);
		if(!check) {
			return;
		}
		double individualExpense = Math.round(Utility.getIndividualSpent(money, listOfMembers)) ;
		user.totalexpense+=money;
		user.actualexpense+=individualExpense;
		double spentMoney[] = new double[listOfMembers.size()];
		Arrays.fill(spentMoney, individualExpense);
		Activity objectActivity = new Activity("expense",Utility.getDate(),description,user.username,money,spentMoney,
				listOfMembers,individualExpense,user.username,"Equally Split");
		user.myActivity.add(objectActivity);
		
		for(String friendName : listOfMembers) {
			if(allAccount.containsKey(friendName)) {
				Users friend = allAccount.get(friendName);
				friend.myActivity.add(objectActivity);
				Utility.isFirstTransaction(user.myAccount,user.username,friend.myAccount,friendName);
				friend.totalexpense+=individualExpense;
				Utility.transaction(user.username,user.myAccount,friendName, friend.myAccount, individualExpense);
			}
			else {
				
				if(!user.unknownAccount.containsKey(friendName)) {
					user.unknownAccount.put(friendName, 0.0);
					System.out.println("Pop Up !!  : "+friendName+" dosent have account, add as an unknown user");
				}
				Utility.unknownTransaction(user.unknownAccount,friendName,individualExpense,"addition");
			}
		}
		
		System.out.println("Successfully Added");
		
		
	}

	// Done Unknown Transaction
	public static void unknownTransaction(HashMap<String, Double> unknownAccount, String friendName,
			double individualExpense, String type) {
		System.out.println();
		if(type.equals("addition")) {
			unknownAccount.put(friendName, unknownAccount.get(friendName)+individualExpense);
		}
		else if(type.equals("reduction")){
			unknownAccount.put(friendName, unknownAccount.get(friendName)-individualExpense);
		}
	}



	// Check For First Transaction
	public static void isFirstTransaction(HashMap<String,Double> user, String username , HashMap<String,Double> friend, String friendname) {
		System.out.println();
		if(!user.containsKey(friendname)) {
			user.put(friendname, 0.0);
			friend.put(username, 0.0);
		}
		
	}


	// Done Transaction
	public static void transaction(String payername, HashMap<String, Double> payerMap, String receivername, HashMap<String, Double> receiverMap,
			double Money) {
		System.out.println();
		payerMap.put(receivername, payerMap.get(receivername) - Money);
		receiverMap.put(payername, receiverMap.get(payername)+Money);
		
	}



	// Get Individual Spent
	public static double getIndividualSpent(double money, ArrayList<String> listOfMembers) {
		System.out.println();
		return money / (listOfMembers.size()+1);
	}



	// Total Money
	public static double getMoney(){
		System.out.println();
		double money = 0.0;
		while(true) {
			
			System.out.println("Enter  money ");
			System.out.println();
			String s = in.nextLine();
			try {
				if(Utility.back(s)) {
					return -1;
				}
				money = Double.parseDouble(s);
				if(money>=0) {
					return money;
				}
				System.out.println("Enter a positive number");
				System.out.println();
			}
			catch(Exception e) {
				System.out.println("Enter correct amount");
				System.out.println();
			}
			
		}
	}


	// Personal Expense
	public static Activity personalExpense(String username, String description) {
		System.out.println();
		
		
		double money = Math.round(Utility.getMoney())  ;
		if(money==-1) {
			return null;
		}
		
		
		Activity objectActivity = new Activity("personalExpense",Utility.getDate(),description,username,money);
		
		System.out.println("Successfully added added as personal expense");
		return objectActivity;
	}



	// Lend Details
	public static boolean lendDetails(HashMap<String, Double> myAccount, HashMap<String, Double> unknownAccount) {
		System.out.println();
		int checking=0;
		if(myAccount.isEmpty() && unknownAccount.isEmpty()) {
			System.out.println("No transaction done yet !!");
			System.out.println();
			return false;
		}
		System.out.println(" Lend Details");
		System.out.println();
		if(!myAccount.isEmpty()) {
			System.out.println(" Existing Users Balance");
			System.out.println();
			int check=0;
			for(Map.Entry balance : myAccount.entrySet()) {
				if((double)balance.getValue()<0) {
					check++;
					System.out.printf("%2s %5s %10s %3s", "You lend ",(-1*(double)balance.getValue())+""," Rs. from ",balance.getKey());
					System.out.println();
				}
			}
			if(check==0) {System.out.println(" No Balance"); System.out.println(); checking++;}
		}
		
		if(!unknownAccount.isEmpty()) {
			System.out.println(" Unknown Users Balance");
			System.out.println();
			int check=0;
			for(Map.Entry balance : unknownAccount.entrySet()) {
				if((double)balance.getValue()>0) {
					check++;
					//System.out.println("You lend "+balance.getValue()+" Rs. from "+balance.getKey());
					System.out.printf("%2s %8s %10s %3s", "You lend ",(double)balance.getValue()+""," Rs. from ",balance.getKey());
					System.out.println();
				}
			}
			if(check==0) {System.out.println(" No Balance"); System.out.println(); checking++;}
		}
		if(checking==2) {return false;}
		return true;
	}

	// Lend Deatils Group
	public static boolean lendDeatils(HashMap<String,Double> Account) {
		System.out.println();
		if(Account.isEmpty()) {
			System.out.println("No transaction yet");
			System.out.println();
			return false;
		}
		System.out.println("Lend Details");
		int check = 0;
		for(Map.Entry balance : Account.entrySet()) {
			if((double)balance.getValue()<0) {
				check++;
				System.out.println("You Lend "+(-1*(double)balance.getValue())+" Rs. from "+balance.getKey());
				System.out.println();
			}
		}
		if(check==0) {
			System.out.println("No balance");
			return false;
		}
		return true;
		
	}

	// Borrow Details
	public static boolean borrowDetails(HashMap<String, Double> myAccount) {
		System.out.println();
			if(myAccount.isEmpty()) {
				System.out.println(" No transaction done yet");
				System.out.println();
				return false;
			}
			int check = 0;
			System.out.println(" Borrow Details");
			System.out.println();
			for(Map.Entry balance : myAccount.entrySet()) {
				if((double)balance.getValue()>0) {
					check++;
					System.out.println("You Borrowed "+balance.getValue()+" Rs. from "+balance.getKey());
					System.out.println();
				}
			}
			if(check==0) {
				System.out.println(" No Balance");
				System.out.println();
				return false;
			}
			return true;
	}

	// Settle up your balance
	public static void settleUp(Users user, HashMap<String, Users> allAccount) {
		System.out.println();
		if(user.myAccount.isEmpty()) {
			System.out.println("Nothing to settleup !!");
			System.out.println();
			return;
			
		}
		String friendName=null;
		while(true) {
			boolean check = Utility.borrowDetails(user.myAccount);
			if(!check) {
				return;
			}
			
			while(true) {
				System.out.println("Enter name from above list ");
				 friendName = in.nextLine();
				 if(friendName.equals("*")) {
					 return;
				 }
				 if(!Utility.checkName(friendName)) {
					 System.out.println("Invalid username ");
				 }
				 else {
					 break;
				 }
			}
			 
			if(user.myAccount.containsKey(friendName) && user.myAccount.get(friendName) >0) {
				break;
			}
			else {
				System.out.println("Name is incorrect");
			}
		}
		Utility.settleUp2(user,allAccount,friendName);
		
	}
	
	// settle up 2
	public static void settleUp2(Users user, HashMap<String, Users> allAccount, String friendName) {
		double money = Utility.getMoney();
		if(money == -1) {
			return;
		}
		Users friend = allAccount.get(friendName);
		Utility.transaction(user.username, user.myAccount, friendName, friend.myAccount, money);
		Activity objectActivity = new Activity("settlement", Utility.getDate(), "Settle Up", user.username, user.username, money, friendName);
		user.myActivity.add(objectActivity);
		friend.myActivity.add(objectActivity);
		user.actualexpense+=money;
		System.out.println("Successfull ");
		System.out.println();
	}

	// Settled up friends balance
	public static void settledUp(Users user, HashMap<String, Users> allAccount) {
		System.out.println();
		if(user.myAccount.isEmpty()&&user.unknownAccount.isEmpty()) {
			System.out.println("Nothing to settled up");
			System.out.println();
			return;
		}
		String friendName=null;
		while(true) {
			boolean check = Utility.lendDetails(user.myAccount, user.unknownAccount);
			if(!check) {
				return;
			}
			
			while(true) {
				System.out.println("Enter name from above list ");
				 friendName = in.nextLine();
				 if(friendName.equals("*")) {
					 return;
				 }
				 if(!Utility.checkName(friendName)) {
					 System.out.println("Invalid username ");
				 }
				 else {
					 break;
				 }
			}
			if( (user.myAccount.containsKey(friendName) && user.myAccount.get(friendName)<0  ) || ( user.unknownAccount.containsKey(friendName) && user.unknownAccount.get(friendName)>0) ) 
			{
				break;
			}
			else {System.out.println("Name is incorrect");}
		}
		
		Utility.settledUp2(user,allAccount,friendName);
		
	}
	
	// Settled Up 2
	public static void settledUp2(Users user, HashMap<String, Users> allAccount, String friendName) {
		
		double money = Utility.getMoney();
		if(money == -1) {
			return;
		}
		if(user.myAccount.containsKey(friendName)) {
			Users friend = allAccount.get(friendName);
			Utility.transaction(friendName, friend.myAccount, user.username, user.myAccount, money);
			friend.actualexpense+=money;
			Activity objectActivity = new Activity("settlement", Utility.getDate(), "Settle Up", user.username,friendName,money,user.username);
			user.myActivity.add(objectActivity);
			friend.myActivity.add(objectActivity);
			System.out.println("Successfull");
			System.out.println();
		}
		else if(user.unknownAccount.containsKey(friendName)) {
			if(user.unknownAccount.get(friendName)>=money) {
				Utility.unknownTransaction(user.unknownAccount, friendName, money,"reduction");
				Activity objectActivity = new Activity("settlement", Utility.getDate(), "Settle Up",user.username, friendName, money, user.username);
				user.myActivity.add(objectActivity);
				System.out.println("Successfull");
				System.out.println();
			}
			
			else {
				System.out.println("Entered amount is higher than the balance");
				System.out.println();
			}
		}
		
	}

	// Add group
	public static void addGroup(Users user, HashMap<String, Users> allAccount) {
		System.out.println();
		System.out.println("Enter group name");
		System.out.println();
		String groupName = in.nextLine();
		if(Utility.back(groupName)) {
			return;
		}
		
		HashMap<String,Users> groupMembers = new HashMap<>();
		groupMembers.put(user.username, user);
		while(true) {
			System.out.println("Enter frined username "+System.lineSeparator()+"! - to stop adding");
			String friendName = in.nextLine();
			if(friendName.equals("!")) {
				break;
			}
			if(Utility.back(friendName)) {
				return;
			}
			if(!Utility.checkName(friendName)) {
				System.out.println("Invalid username");
				continue;
			}
			if(allAccount.containsKey(friendName)) {
				if(groupMembers.containsKey(friendName)) {
					System.out.println("Already in group");
					System.out.println();
				}
				else {
					groupMembers.put(friendName, allAccount.get(friendName));
					
				}
			}
			else {
				System.out.println("User name not found");
				System.out.println();
			}
			
		}
		if(groupMembers.size()<2) {
			System.out.println("Atleast two members needed to create a group");
			System.out.println("Group is not created");
			System.out.println();
			return;
		}
		Group objectGroup = new Group(groupName, groupMembers, user.username);
		for(Map.Entry members : groupMembers.entrySet()) {
			Users memberAccount = (Users)members.getValue();
			memberAccount.myGroups.put(objectGroup.getGroupId(), objectGroup);
		}
		System.out.println("Group created !!!");
		System.out.println();
		System.out.println();
		new GroupHome(objectGroup,user.username);
		
	}

	// Activity
	public static Activity getActivity(ArrayList<Activity> myActivity) {
		if(myActivity.isEmpty()) {
			System.out.println("Activity Empty");
			System.out.println();
			return null;
		}
		int i=1;
		for(Activity objectActivity : myActivity) {
			if(objectActivity.getType()=="expense") {
				System.out.println(i+"]  "+objectActivity.date+"   "+objectActivity.payer+" paid Rs."+objectActivity.totalMoney+" for "+objectActivity.description+" Friends "+objectActivity.friends);
			}
			else if (objectActivity.getType()=="personalExpense") {
				System.out.println(i+"]  "+objectActivity.date+"   "+objectActivity.payer+" paid Rs. "+objectActivity.totalMoney+" for "+objectActivity.description+" (Personal Expense)");
			}
			else if (objectActivity.getType().equals("settlement")) {
				System.out.println(i+"]  "+objectActivity.date+"  "+objectActivity.payer+"  settle Rs. "+objectActivity.totalMoney+" to "+objectActivity.receiver);
			}
			else if (objectActivity.getType().equals("others")) {
				
			}
			i++;
		}
		int sno=0;
		while(true){
			System.out.println("Enter Serial Number for Detailed Activity");
			 sno = Utility.checkNumber();
			 if(sno == -1) {
				 break ;
			 }
			if(0<sno && sno<=myActivity.size()) {
				break;
			}
			System.out.println("Enter correct serial number");
		}
		if(sno == -1) {return null;}
		Activity object = myActivity.get(sno-1);
		object.details();
		System.out.println();
		while(true) {
			System.out.println("Enter 0 to delete activity or * to go back");
			int option = Utility.checkNumber();
			if(option == -1) {
				break;
			}
			else if(option == 0){
				return object;
			}
			else {
				System.out.println("Enter correct option");
			}
		}
		return null;
	}

	public static void tranfer(String requestUserName, Users user, HashMap<String, Users> allAccount, String unknownName) {
		Users requestUser = allAccount.get(requestUserName);
		double balance = requestUser.unknownAccount.get(unknownName);
		balance = balance;
		requestUser.unknownAccount.remove(unknownName);
		Utility.isFirstTransaction(user.myAccount, user.username, requestUser.myAccount, requestUserName);
		user.myAccount.put(requestUserName, user.myAccount.get(requestUserName)+balance);
		requestUser.myAccount.put(user.username, -1*user.myAccount.get(requestUserName));
		System.out.println();
		for(Activity object : requestUser.myActivity) {
			if(object.friends!=null && object.friends.contains(unknownName)) {
				
				int index = object.friends.indexOf(unknownName);
				object.friends.remove(index);
				object.friends.add(index, user.username);
				user.myActivity.add(object);
				
			}
			else if(object.receiver!=null && object.receiver.equals(unknownName)) {
				
				object.receiver = user.username;
				user.myActivity.add(object);
				
			}
			else if(object.payer!=null && object.payer.equals(unknownName)) {
				
				object.payer = user.username;
				user.myActivity.add(object);
				
			}
		}
		
		
		System.out.println("Sucessfully all Balance and Activities are transfer");
		requestUser.notifications.add("You transfer account request to "+user.username+" is accpected and account transfered");
		
		return;
		
	}

	public static void notification(LinkedList<String> notifications) {
		for(String s: notifications) {
			System.out.println(s);
			System.out.println();
		}
	}

	public static void xxx(String friendName, Users user, HashMap<String,Users> allAccount) {
		if(Utility.back(friendName)) {
			return;
		}
		if((user.myAccount.containsKey(friendName) && user.myAccount.get(friendName)<0) || (user.unknownAccount.containsKey(friendName) && user.unknownAccount.get(friendName)>0)) {
			Utility.settledUp2(user, allAccount, friendName);
			return;
		}
		else if(user.myAccount.containsKey(friendName)&&user.myAccount.get(friendName)>0) {
			Utility.settleUp2(user, allAccount, friendName);
			return;
		}
		else {
			System.out.println("Wrong name");
			System.out.println();
		}
	}
	


	
	
}
