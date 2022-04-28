package zoho_final;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class New_Split_Amt {

	public void divide(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("For What Purpose You Spent Money");
		String description = in.nextLine();								//Description of expense
		
		if(description.equals("*")) {return ;}
		
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  		// Date Time of the Expense
		
		 ArrayList<String> list_of_member = new ArrayList<>();
		 
		 double total=0.00;															// To Calculate Total Amount
		
		String mon="";
		while(true) {
		System.out.println("Enter money spent for yourself");
		mon = in.nextLine();
		if(mon.equals("*")) {return ;}
		try {
			double d = Double.parseDouble(mon);
			if(d>=0.00) {
			break;
			}
			else {
				System.out.println("Enter positive number");
			}
		}
		catch(Exception e){
			System.out.println("Enter number ");
		}
		}
		my_account.actualexpense+=Double.parseDouble(mon);
		total = total+Double.parseDouble(mon);
		
		String members = " ";													//Add Friends name in one string(space separated)
		String indivimoney = " ";
		indivimoney = indivimoney + String.format("%.2f", Double.parseDouble(mon))+" ";
		
		
		aa:while(true) {
			String regex1 = "^[A-Za-z]\\w{1,29}$";						//Regex for friend name
		    Pattern p1 = Pattern.compile(regex1);
			String friendname="";
			// Get Friends Name *******
			bb:while(true){
				System.out.println("Enter Friend name ");
				System.out.println("Enter '!' to Stop Adding");
				 friendname = in.nextLine();
				 if(friendname.equals("!")) {break aa;}
				
					 Matcher m1 = p1.matcher(friendname);
					if(!m1.matches()) {
					 System.out.println("Enter correct name");
					 continue;
					}
					else {
						friendname=friendname;
					}
				 
				if(friendname.equals(my_account.username)) {System.out.println("You Should Not Enter Your Name Here");}
				
				else {
					break bb;
				}
				}
			
			
			if(all_account2.containsKey(friendname)) {
				// if account exist in username
				Account_Details account = all_account2.get(friendname);
				
				list_of_member.add(friendname);
				
				double amount_spend=0.00;
				
				while(true) { 												//Get Amount Paid for friend
				System.out.println("Enter amount spend for "+friendname); 
				 try {
					 amount_spend = Double.parseDouble(in.nextLine());
					 if(amount_spend>=0) {
						 break;
					 }
					 else {
						 System.out.println("Negative Numbers Not Allowed");
					 }
				 }
				 catch(Exception e) {
					 System.out.println("Enter correct amount");
				 }
				 
				}
				
				members = members + friendname+" ";
				indivimoney = indivimoney + String.format("%.2f", amount_spend)+" ";
				
				account.totalexpense+=amount_spend;
				total = total+ amount_spend;								//Adding Total Amount
//				account.my_accounts_details2.add(new Activities1(1,formattedDate,description,Double.toString(amount_spend),my_account.username));
				account.totalexpense+=amount_spend;
				if(!my_account.my_accounts.containsKey(friendname)) {
					my_account.my_accounts.put(friendname, -1*amount_spend );
					account.my_accounts.put(my_account.username, amount_spend);
				}
				else {
					double balance = (double) my_account.my_accounts.get(account.username);
					if(balance<=0.00) {
						account.my_accounts.put(my_account.username, (double)account.my_accounts.get(my_account.username)+amount_spend);
						my_account.my_accounts.put(account.username, -1*((double)account.my_accounts.get(my_account.username)+amount_spend ));
					}
					else if(balance>0.00) {
						double currentamt = balance-amount_spend;
						if(currentamt>=0) {
							my_account.my_accounts.put(account.username, currentamt);
							account.my_accounts.put(my_account.username, -1*currentamt);
						}
						else if(currentamt<0) {
							currentamt = currentamt*(-1);
							my_account.my_accounts.put(account.username, -1*currentamt);
							account.my_accounts.put(my_account.username, currentamt);
						}
					}
				}
			}
			else {
				// ******* If he doesnt have account*******
				list_of_member.add(friendname);
				
				double spendamt=0.00;
				while(true) {
				System.out.println("Enter amount ");
				 
				 try {
					 spendamt = Double.parseDouble(in.nextLine());
					 if(spendamt>=0) {
						 break;
					 }
					 else {
						 System.out.println("Negative Numbers Not Allowed");
					 }
				 }
				 catch(Exception e) {
					 System.out.println("Enter correct amount");
				 }
				}
				
				total = total + spendamt;
				
				if(my_account.unknown_accounts.containsKey(friendname)) {
					
					members = members + friendname+" ";
					indivimoney = indivimoney + String.format("%.2f", spendamt)+" ";
					
					double n = (double)my_account.unknown_accounts.get(friendname);
					my_account.unknown_accounts.put(friendname,n+spendamt);
					
//					my_account.unknown_account_details2.add(new Activities1(0,formattedDate,description,Double.toString(spendamt),friendname));
				}
				else {
						System.out.println("Notifications !!("+friendname+" doesnt have account .  )");
						
						members = members + friendname+" ";
						indivimoney = indivimoney + String.format("%.2f", spendamt)+" ";						
						my_account.unknown_accounts.put(friendname, spendamt);
						my_account.unknown_account_details3.put(friendname, new ArrayList<Activities2>());
//						my_account.unknown_account_details2.add(new Activities1(0,formattedDate,description,Double.toString(spendamt),friendname));
					}
					
			}
		
		}
		my_account.totalexpense+=total;
//		my_account.my_accounts_details2.add(new Activities1(0,formattedDate,description,Double.toString(total),members));
		
		Activities2 obj = new Activities2(0, formattedDate, description, String.format("%.2f", total), indivimoney, my_account.username, "Unequally", my_account.username, members);
		my_account.my_accounts_details3.add(obj);
		
		for(String s : list_of_member) {
			if(all_account2.containsKey(s)) {
				Activities2 obj2 = new Activities2(1,formattedDate,description,String.format("%.2f", total),indivimoney,my_account.username,"Unequally",my_account.username,members);
				all_account2.get(s).my_accounts_details3.add(obj2);
			}
			else {
				Activities2 obj2 = new Activities2(1,formattedDate,description,String.format("%.2f", total),indivimoney,my_account.username,"Unequally",my_account.username,members);
				ArrayList<Activities2> al = my_account.unknown_account_details3.get(s);
				al.add(obj2);
				my_account.unknown_account_details3.put(s, al);
			}
		}
		
		
		System.out.println("Added successfully...");
		
	
	}

}
