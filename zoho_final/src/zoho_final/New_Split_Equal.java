package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class New_Split_Equal {


	public void eshare(Account_Details my_account, Double money_spend, HashMap<String, Account_Details> all_account2) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("For What Purpose You Spend Money");
		String description = in.nextLine();									// Description For Expense
		if(description.equals("*")) {return;}
		
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  	// Current Date and time
		 
		 String regex1 = "^[A-Za-z]\\w{1,29}$";
		    Pattern p1 = Pattern.compile(regex1);
		    
		String members=" ";  													///Adding friend names as a one string (space separate)
		String indivimoney=" ";
		
		ArrayList<String> list_of_member = new ArrayList<>();			// List of friends name
		
		aa:while(true) {														//Loop Condition For Adding friends
			System.out.println("Enter the friend name");
			System.out.println("Enter '!' to Stop Adding ");
			String ss ="";
			
			boolean f=true;
			while(f) {
			
				 ss=in.nextLine();
				 if(ss.equals("*")) {return ;}
				 if(ss.equals("!")) {
					 if(list_of_member.isEmpty()) {
						 System.out.println("No Friends Added");
						 System.out.println("Add Friends");
					 }
					 else {
						 break aa;
					 }
				 }
				 if(ss.equals(my_account.username)) {System.out.println("You Should Not Enter Your User Name Here");
				 break;}
				 Matcher m1 = p1.matcher(ss);
			if(m1.matches()) {
				list_of_member.add(ss);
				break;
			}
			else {
				System.out.println("Enter correct username");
			}
			}	
		}
		
		double individual_spent = money_spend/(list_of_member.size()+1);  			//Individual Spent........
		indivimoney = indivimoney + String.format("%.2f", individual_spent) +" ";
		my_account.actualexpense+=individual_spent;
		for(int i=0;i<list_of_member.size();i++) {
			if(all_account2.containsKey(list_of_member.get(i))) {  					///Checking Wheather Given Name has having Account or Not 
				Account_Details tt = all_account2.get(list_of_member.get(i));	// Friend Account

						members = members + list_of_member.get(i)+" ";
						indivimoney = indivimoney + String.format("%.2f", individual_spent) +" ";
					//	tt.my_accounts_details2.add(new Activities1(1,formattedDate,description,Double.toString(individual_spent),my_account.username));
																							
						if(!my_account.my_accounts.containsKey(list_of_member.get(i))) {		//Not done Any Transaction Before
							my_account.my_accounts.put(list_of_member.get(i), (-1)*individual_spent );
							tt.my_accounts.put(my_account.username, individual_spent);
							tt.totalexpense+=individual_spent;
						}
						else {																				//Known him before
							double balance = (double) my_account.my_accounts.get(tt.username);
							if(balance<=0.00) {															//if balance is zero
								double j = (double) tt.my_accounts.get(my_account.username);
								tt.my_accounts.put(my_account.username, individual_spent+j);
								tt.totalexpense+=individual_spent;
								my_account.my_accounts.put(tt.username,(-1)*(individual_spent+j) );
							}
							else if(balance>0) {															// if i borrow some money
								double current_amt = balance-individual_spent;
								if(current_amt>=0) {													//Still some balance left or become zero
									my_account.my_accounts.put(tt.username, current_amt);
									tt.my_accounts.put(my_account.username, (-1)*current_amt);
									tt.totalexpense+=individual_spent;
								}
								else if(current_amt<0) {												//Money exceeds the balance
									current_amt = current_amt*(-1);
									my_account.my_accounts.put(tt.username, (-1)*current_amt);
									tt.my_accounts.put(my_account.username, current_amt);
									tt.totalexpense+=individual_spent;
								}
							}
						}	
				}
			else {																								//If They Dont Have Account
				if(my_account.unknown_accounts.containsKey(list_of_member.get(i))) {				//Dont Have Account But Done Transaction Before
					members = members + list_of_member.get(i)+" ";
					indivimoney = indivimoney + String.format("%.2f", individual_spent) +" ";
					double n = (double)my_account.unknown_accounts.get(list_of_member.get(i));
					my_account.unknown_accounts.put(list_of_member.get(i),n+individual_spent);
				
				}
				else {																								//Dont Have Account also not done any transaction before
				System.out.println("Notifications !!("+list_of_member.get(i)+" doesnt have account.  )");
					members = members + list_of_member.get(i)+" ";
					indivimoney = indivimoney + String.format("%.2f", individual_spent) +" ";
					my_account.unknown_accounts.put(list_of_member.get(i), individual_spent);
					my_account.unknown_account_details3.put(list_of_member.get(i), new ArrayList<Activities2>());
			}
		}
			
		}
	//	my_account.my_accounts_details2.add(new Activities1(0,formattedDate,description,Double.toString(money_spend),members));
		Activities2 obj = new Activities2(0, formattedDate, description, String.format("%.2f", money_spend), indivimoney, my_account.username, "Equally", my_account.username, members);
		my_account.my_accounts_details3.add(obj);
		for(int i=0;i<list_of_member.size();i++) {
			if(all_account2.containsKey(list_of_member.get(i))) {
				Activities2 obj1 = new Activities2(1, formattedDate, description, String.format("%.2f", money_spend), indivimoney, my_account.username, "Equally", my_account.username, members);
				all_account2.get(list_of_member.get(i)).my_accounts_details3.add(obj1);
			}
			else {
				Activities2 obj1 = new Activities2(1, formattedDate, description, String.format("%.2f", money_spend), indivimoney, my_account.username, "Equally", my_account.username, members);
				ArrayList<Activities2> al = my_account.unknown_account_details3.get(list_of_member.get(i));
				al.add(obj1);
				my_account.unknown_account_details3.put(list_of_member.get(i), al);
				
			}
		}
		System.out.println("Added successfully");
		
	}

}
