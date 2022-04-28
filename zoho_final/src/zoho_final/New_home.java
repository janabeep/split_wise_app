package zoho_final;

import java.util.*;

public class New_home {

	public New_home(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		Scanner in = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		boolean f = true;
		while(f) {
			System.out.println("                                                                                                  Username :  "+my_account.username);
			System.out.println();
			System.out.println();
			System.out.println("1.     Add Your Expense");
			System.out.println("2.     View Money Borrowed Details");
			System.out.println("3.     View Money lends Details");
			System.out.println("4.     Settle Up Your Balance");
			System.out.println("5.     Settled Up Your Friends Balance");
			System.out.println("6.     View Your Activities");
			System.out.println("7.     Notifications = "+my_account.notify);
			System.out.println("8.     Add Your Friend");
			System.out.println("9.     Show My Friends List");
//			System.out.println("10.    Add Groups");
//			System.out.println("11.    My Groups");
			System.out.println("10.    My Account Info");
			System.out.println("11.    Add Group");
			System.out.println("12.    My Groups");
			System.out.println("13.    Transfer Account");
			System.out.println("0.     Quit");
			System.out.println();
			/// Enter Number*****
			int num = 0;
			while(true) {
			try {
				System.out.println("Enter a number ");
				String s = in.nextLine();
	             num = Integer.parseInt(s);
	             break;
	        } catch (NumberFormatException e) {
	            System.out.println("Enter a number ( 1 - 11 )");
	        }
			}
			// Switch Case Starts Here ******************
			switch(num) {
			//			EXIT STATEMENT
			case 0:
				f=false;
				break;
			//								ADD EXPENSE
			case 1:
				New_Add_Expense obj1 =new New_Add_Expense();
				obj1.addexpense(my_account,all_account2);
				break;
			//								BORROW MONEY DETAILS
			case 2:
				New_Borrow_Money obj2 = new New_Borrow_Money();
				obj2.borrow(my_account);
				break;
			//								LEND MONEY DETAILS
			case 3:
				New_Lend_Money obj3 = new New_Lend_Money();
				obj3.lend(my_account,all_account2);
				break;
			// 								SETTLE UP YOUR BALANCE
			case 4:
				New_Settle_Up obj4 = new New_Settle_Up();
				obj4.settleup(my_account,all_account2);
				break;
			//								SETTLE UP YOUR FRIENDS BALANCE
			case 5:
				New_Settled_Up obj5 = new New_Settled_Up();
				obj5.settledup(my_account,all_account2);
				break;
			//								ACTIVITY TRACKER
			case 6:
				if(my_account.my_accounts_details3.isEmpty()) {
					System.out.println("							No Activity Found");
					break;
				}
//				else {
//					for(Activities1 t : my_account.my_accounts_details2) {
//						System.out.println(t.sentence);
//					}
//				}
				System.out.println();
				System.out.println();
				int i=my_account.my_accounts_details3.size();
				HashMap<Integer,Activities2> hm = new HashMap<>();
				for(Activities2 t : my_account.my_accounts_details3) {
					System.out.println("		"+i+")   "+t.sentence);
					System.out.println();
					hm.put(i, t);
					i--;
				}
				while(true) {
				System.out.println("Enter number for detailed activity or enter '*' for go back");
				String sno = in.nextLine();
				if(sno.equals("*")) {break;}
				try {
					int sn = Integer.parseInt(sno);
					if(0<sn&&sn<my_account.my_accounts_details3.size()+1) {
						Activities2 tt = hm.get(sn);
						tt.details();
						break;
					}
					else {
						System.out.println("Enter Correct Number");
						System.out.println();
					}
				}
				catch(Exception e) {
					System.out.println("Enter a number");
					System.out.println();
				}
			}
				break;
			//				FRIENDS REQUEST NOTIFICATION				
			case 7:
				System.out.println("1. Accpect Friends ");
				System.out.println("2. Transfer Account ");
				int op;
				while(true) {
					try {
						System.out.println("Enter 1 or 2");
						op = Integer.parseInt(in.nextLine());
						if(op==1||op==2) {
							break;
						}
						else {
							System.out.println("Enter correct number");
						}
					}
					catch(Exception e) {
						System.out.println("Enter a number");
					}
				}
				if(op==1) {
					New_Accpect_Friend obj7 = new New_Accpect_Friend();
					obj7.accpect(my_account,all_account2);
				}
				else if(op==2) {
					if(my_account.transfer.isEmpty()) {
						System.out.println("No Transfer Request Found");
						break;
					}
					
					System.out.println("User Request to merge");
					
					for(Map.Entry d : my_account.transfer.entrySet()) {
						System.out.println(d.getKey());
					}
					System.out.println("Enter name of the user");
					String user = in.nextLine();
					if(my_account.transfer.containsKey(user)) {
						Transfer_Account ob = new Transfer_Account();
						ob.transfer(all_account2, all_account2.get(user), my_account.username,my_account.transfer.get(user) );
					}
					else {
						System.out.println("User name wrong ");
						System.out.println();
						break;
					}
				}
				break;
			//				ADD FRIENDS
			case 8:
				New_Add_Friend obj8 = new New_Add_Friend();
				obj8.add(my_account,all_account2);
				break;
			//					My Friends List
			case 9:
				if(my_account.friends.isEmpty()) {
					System.out.println("You have no friends ");
					System.out.println();
				}
				else {
					for(String name : my_account.friends) {
						System.out.println(name);
						System.out.println();
					}
				}
				break;
			// 					ADD GROUPS
			case 10:
				System.out.println();
				System.out.println();
				System.out.println(" User Name :   "+my_account.username);
				System.out.println();
				System.out.println(" Email Id :   "+my_account.getEmail());
				System.out.println();
				System.out.println(" Personal Expense :   "+my_account.personalexpense);
				System.out.println();
				System.out.println("  Total Expense :   "+my_account.totalexpense);
				System.out.println();
				System.out.println(" Actual Expense :   "+my_account.actualexpense);
				System.out.println();
				System.out.println("  Overall Expense :   "+(my_account.personalexpense+my_account.actualexpense) );
				System.out.println();
				System.out.println();
				break;
			//  			CREATE GROUP
			case 11:
				New_Add_Group obj11 = new New_Add_Group();
				obj11.addgrp(my_account, all_account2);
				break;
			// 					OPEN GROUPS
			case 12:
				System.out.println("		GroupID		GroupName");
				for(Map.Entry dd : my_account.my_Groups.entrySet()) {
					New_Group g = (New_Group)dd.getValue();
					System.out.println("		"+dd.getKey()+"		"+g.grp_name);
					System.out.println();
				}
				System.out.println("				Enter  GroupId  ");
				int n = Integer.parseInt(in.nextLine());
				New_Group ob = my_account.my_Groups.get(n);
				
				New_Group_Home obj = new New_Group_Home();
				obj.opengroup(all_account2,my_account,ob.grp_name,ob);
				break;
			//					Transfer Account
			case 13:
				System.out.println();
				System.out.println("		UnknownAccounts		Balance");
				for(Map.Entry m : my_account.unknown_accounts.entrySet()) {
					System.out.println("		"+m.getKey()+"		"+String.format("%.2f", m.getValue()));
				}
				System.out.println();
				System.out.println("Enter Name of the unknownuser");
				String unknownname = in.nextLine();
				System.out.println();
				if(!my_account.unknown_accounts.containsKey(unknownname)) {
					System.out.println("Name not in the given list");
					System.out.println();
					break;
				}
				System.out.println("Enter existing account username ");
				String accountname =in.nextLine();
				if(all_account2.containsKey(accountname)) {
					Account_Details acc = all_account2.get(accountname);
					acc.notify++;
					acc.transfer.put(my_account.username,unknownname);
					System.out.println("Reques Sent ! ! ! ! ! ! ! !");
				}
				else {
					System.out.println("Account Not Found");
					System.out.println();
					break;
				}
				break;
			//				DEFAULT CONDITION
			default:
				System.out.println("Given input is wrong");
				break;
			}
			
		}
	}
}
