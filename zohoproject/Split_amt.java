package zohoproject;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Split_amt {

	public void divide(List<Signup> all_account, Signup my_account, String description) {
		Scanner in = new Scanner(System.in);
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  
		double total=0.00;
		HashMap<String,Double> hm = new HashMap<>();
		String mon="";
		while(true) {
		System.out.println("Enter money spent for yourself");
		mon = in.nextLine();
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
		total = total+Double.parseDouble(mon);
		String members = " ";

		System.out.println();
		aa:while(true) {
			String regex1 = "^[A-Za-z]\\w{1,29}$";
		    Pattern p1 = Pattern.compile(regex1);
			String friendname="";
			
			bb:while(true){
			System.out.println("Enter Friends name ");
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
//			al.add(s);
			int ck=0;
			for(Signup account :all_account) {
				if(account.username.equals(friendname)) {
					members = members + friendname+" ";
					double amount_spend=0.00;
					while(true) {
					System.out.println("Enter amount ");
					 
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
					total = total+ amount_spend;
					String arrr[] = {"1",formattedDate,description,Double.toString(amount_spend),my_account.username};
					account.my_accounts_details.add(arrr);
					if(!my_account.my_accounts.containsKey(friendname)) {
						my_account.my_accounts.put(friendname, 0.00);
						account.my_accounts.put(my_account.username, amount_spend);
					}
					else {
						double balance = (double) my_account.my_accounts.get(account.username);
						if(balance==0.00) {
							account.my_accounts.put(my_account.username, (double)account.my_accounts.get(my_account.username)+amount_spend);
						}
						else if(balance>0.00) {
							double currentamt = balance-amount_spend;
							if(currentamt>=0) {
								my_account.my_accounts.put(account.username, currentamt);
							}
							else if(currentamt<0) {
								currentamt = currentamt*(-1);
								my_account.my_accounts.put(account.username, 0.0);
								account.my_accounts.put(my_account.username, currentamt);
							}
						}
					}
					ck++;
					break;
				}
			}
			if(ck==0) {
//				System.out.println("Enter amount ");
//				double amt = Double.parseDouble(in.nextLine());
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
					double n = (double)my_account.unknown_accounts.get(friendname);
					my_account.unknown_accounts.put(friendname,n+spendamt);
					String ar[]= {"0",formattedDate,description,Double.toString(spendamt),friendname};
					my_account.unknown_account_details.add(ar);
				}
				else {
						System.out.println("Notifications !!("+friendname+" doesnt have account .  )");
						members = members + friendname+" ";
						my_account.unknown_accounts.put(friendname, spendamt);
						String ar[]= {"0",formattedDate,description,Double.toString(spendamt),friendname};
						my_account.unknown_account_details.add(ar);
					}
					
				}
			}

		

		 
		 String arr[] = {"0",formattedDate,description,Double.toString(total),members};
			
			my_account.my_accounts_details.add(arr);

			System.out.println("Added successfully...");
	}

}
