package non_group;

import java.util.*;


import group.Group;
import group.GroupHome;
import group.GroupUtility;


public class Home {
	Scanner in = new Scanner(System.in);
	public Home(HashMap<String,Users>allAccount, Users user ) {
		System.out.println();
		a:while(true) {
			
			System.out.println("                                                                                                  Username :  "+user.username);
			System.out.println();
			
//			if(user.transferNotify>0) {
//				if(user.transfer!=null && !user.transfer.isEmpty()) {
//					System.out.println("99.     Transfer Request ! ! !     "+user.transfer.size());
//					System.out.println();
//				}
//			}
//			if(user.notify>0) {
//				if(user.notifications!=null && !user.notifications.isEmpty()) {
//					System.out.println("98.     Notifications ! ! !    "+user.notifications.size());
//					System.out.println();
//				}
//			}
			
			System.out.println("1.     Add Your Expense");
			System.out.println("2.     My Balances");
			System.out.println("3.     Friends");
			System.out.println("4.     Settle up");
			System.out.println("5.     View Your Activities");
			System.out.println("6.     My Account Info");
			System.out.println("7.     Add Group");
			System.out.println("8.     My Groups");
			System.out.println("9.     Transfer Account");
			System.out.println("10.    Transfer Request ! ! !     - "+user.transfer.size());
			System.out.println("11.    Notifications ! ! !    - "+user.notifications.size());
			System.out.println("*.      LogOut");
			
			System.out.println();
			System.out.println("Enter number ");
			int option1 = Utility.checkNumber();
			if(option1==-1) {
				System.out.println("LogOut ......");
				System.out.println();
				break a;
			}
			switch(option1){
			case 4:
				int checked=0;
				for(Map.Entry friend : user.myAccount.entrySet()) {
					if((double)friend.getValue()<0) {
						System.out.println(friend.getKey()+"    "+(-1*(double)friend.getValue())+" Rs. (You Get)");
						checked++;
					}
					else if((double)friend.getValue()>0) {
						System.out.println(friend.getKey()+"    "+friend.getValue()+" Rs.(You Pay)");
						checked++;
					}
				}
				for(Map.Entry unknownfriend : user.unknownAccount.entrySet()) {
					if((double)unknownfriend.getValue()>0) {
						System.out.println(unknownfriend.getKey()+"    "+unknownfriend.getValue()+" Rs. (You get)");
						checked++;
					}
				}
				if(checked==0) {
					System.out.println("Nothing to settle");
					break;
				}
				System.out.println("Enter name ");
				String name = in.nextLine();
				Utility.xxx(name, user, allAccount);
				break;
				
				
			case 11:
				if(!user.notifications.isEmpty()) {
				Utility.notification(user.notifications);
				user.notifications.clear();
				break;
				}
				else {
					System.out.println("No notifications");
					break;
				}

			case 10:
				if(user.transfer.isEmpty()) {
					System.out.println("No request ");
					break;
				}
			
					ArrayList<String> al = new ArrayList<>();
					int i=1;
					for(Map.Entry request : user.transfer.entrySet()) {
						al.add((String)request.getKey());
						System.out.println(i+") Transfer request from "+request.getKey());
						i++;
					}
					System.out.println("Enter number ");
					int option;
					while(true) {
						 option = Utility.checkNumber();
						if(option ==-1) {
							break;
						}
						if(!(0<option&&option<=al.size())) {
							System.out.println("Enter correct number");
						}
						else {
							break;
						}
					}
					if(option==-1) {
						break;
					}
					String requestUserName = al.get(option-1);
					String[] ar = user.transfer.get(requestUserName).split(" ");
					
						System.out.println("    "+requestUserName+" user wants to add his unknown user to your account");
						System.out.println();
						System.out.println("    Unknown user name "+ar[0]);
						System.out.println("    The current balance amount he has to add is "+ar[1]);
						System.out.println();
						System.out.println("Do you know "+requestUserName+" ??  1- Yes, 2 - No");
						int options ;
						while(true) {
							 options = Utility.checkNumber();
							if(options ==-1) {
								break;
							}
							if(options == 1) {
								
								Utility.tranfer(requestUserName,user,allAccount,ar[0]);
								user.transfer.remove(requestUserName);
								break;
							}
							else if(options ==2) {
								Users requester = allAccount.get(requestUserName);
								requester.notifications.add(" Sorry, your transfer account request to "+user.username+" is not accpected ! !");
								
								user.transfer.remove(requestUserName);
								break;
							}
							else {
								System.out.println("Enter correct number");
							}
						}
						if(options==-1) {
							break;
						}
						
					
				
				
				break;
				
			case 3:
				System.out.println("Friends");
				System.out.println();
				if(user.myAccount.isEmpty()&&user.unknownAccount.isEmpty()) {
					System.out.println("No Friends");
					System.out.println();
					break;
				}
				ArrayList<String> al1 = new ArrayList<>();
				int ii=1;
				if(!user.myAccount.isEmpty()) {
					System.out.println("Existing User");
					System.out.println();
					for(Map.Entry Friends : user.myAccount.entrySet()) {
						System.out.println();
						if((double)Friends.getValue()>0) {
							System.out.println(ii+")  "+Friends.getKey()+"     "+Friends.getValue()+" Rs. you pay");
							al1.add((String) Friends.getKey());
						}
						else if((double)Friends.getValue()<0) {
							System.out.println(ii+")  "+Friends.getKey()+"     "+(-1*(double)Friends.getValue())+" Rs. you get");
							al1.add((String) Friends.getKey());
						}
						else if((double)Friends.getValue()==0) {
							System.out.println(ii+")  "+Friends.getKey()+"     "+"Settle up");
							al1.add((String) Friends.getKey());
						}
						ii++;	
					}
					
				}
				if(!user.unknownAccount.isEmpty()) {
					System.out.println("Unknown User");
					System.out.println();
					for(Map.Entry Friends : user.unknownAccount.entrySet()) {
						System.out.println();
						if((double)Friends.getValue()>0) {
							System.out.println(Friends.getKey()+"     "+Friends.getValue()+" Rs. you get");
						}
						else {
							System.out.println(Friends.getKey()+"     "+"Settle up");
						}
					}
				}
				System.out.println();
				System.out.println("Enter number for person breakout ");
				int number = Utility.checkNumber();
				if(number==-1) {
					break;
				}
				if( !(0<number && number<=al1.size()) ) {
					System.out.println("Wrong number");
					break;
				}
				String name1 = al1.get(number-1);
				int f=0;
				double balances = 0;
				for(Map.Entry grp : user.myGroups.entrySet()) {
					Group ele = (Group)grp.getValue();
					if(ele.getGroupMembers().containsKey(name1)) {
						System.out.println(ele.getGroupName()+" :-");
						balances+=GroupUtility.xxx(ele,name1,user.username);
						f++;
					}
				}
				if(f==0) {
					System.out.println("No Group Expense found!");
					break;
				}
				if(balances!=0) {
					balances+=user.myAccount.get(name1);
					if(balances>0) {
						System.out.println("Finally you paid "+balances+" Rs. to settle amount");
					}
					else if(balances<0) {
						System.out.println("Finally you get "+(-1*balances)+" Rs. to settle amount");
					}
					break;
				}
				else {
					if(user.myAccount.get(name1)>0) {
						System.out.println("Finally you paid "+user.myAccount.get(name1)+" Rs. to settle amount");
					}
					else if(user.myAccount.get(name1)<0) {
						System.out.println("Finally you get "+(-1*user.myAccount.get(name1))+" Rs. to settle amount");
					}
				}
				break;
			case 12:
				if(user.myAccount.isEmpty()&&user.unknownAccount.isEmpty()) {
					System.out.println("No Balance");
					System.out.println();
					break;
				}
				System.out.println("1. Settle up your balance ");
				System.out.println("2. Settle up your friends balance");
				while(true) {
					int options1 = Utility.checkNumber();
					if(options1==-1) {
						break;
					}
					if(options1==1) {
						Utility.settleUp(user,allAccount);
						break;
					}
					else if(options1==2) {
						Utility.settledUp(user,allAccount);
						break;
					}
					else {
						System.out.println("Wrong number");
					}
				}
				break;
			
			case 2:
				int checking=0;
				double balance =0;
				if(user.myAccount.isEmpty()&&user.unknownAccount.isEmpty()) {
					System.out.println("No Balance");
					System.out.println();
					break;
				}
				System.out.println("My Balances");
				System.out.println();
				System.out.println();
				System.out.println("Existing User");
				System.out.println();
				if(!user.myAccount.isEmpty()) {
					int check = 0;
					for(Map.Entry Friends : user.myAccount.entrySet()) {
						if((double)Friends.getValue()>0) {
							check++;
							System.out.println("You pay "+Friends.getValue()+" Rs. to "+Friends.getKey());
							balance-=(double)Friends.getValue();
						}
						else if((double)Friends.getValue()<0) {
							check++;
							System.out.println("You get "+(-1*(double)Friends.getValue())+" Rs. from "+Friends.getKey());
							balance+=(-1*(double)Friends.getValue());
						}
						
					}
					if(check==0) {
						System.out.println("No Balance");
						checking++;
					}
				}
				System.out.println();
				System.out.println("Unknown User");
				System.out.println();
				if(!user.unknownAccount.isEmpty()) {
					int check=0;
					for(Map.Entry Friends : user.unknownAccount.entrySet()) {
						if((double)Friends.getValue()>0) {
							check++;
							System.out.println("You get "+Friends.getValue()+" Rs. from "+Friends.getKey());
							balance+=(double)Friends.getValue();
						}
					}
					if(check==0) {
						System.out.println("No Balance");
						checking++;
					}
				}
				System.out.println();
				if(checking!=2) {
					System.out.println();
					if(balance>0) {
						System.out.println("Balance");
						System.out.println();
						System.out.println("You getback "+balance+" Rs.");
					}
					else if(balance<0) {
						System.out.println("Balance");
						System.out.println();
						System.out.println("You pay "+(-1*balance)+" Rs.");
					}
					else {
						System.out.println("No balance amount");
					}
					while(true) {
						System.out.println();
						System.out.println("Enter name to settle balance or Enter 'L' to show lend details or Enter 'B' to show borrow details ,,, * - goback ");
						String friendName = in.nextLine();
						if(friendName.equals("L")) {
							boolean flag = Utility.lendDetails(user.myAccount,user.unknownAccount);
							if(flag) {
								System.out.println("Enter name to settle balance  ,,, * - goback ");
								System.out.println();
								friendName = in.nextLine();
							}
							else {
								break;
							}

						}
						else if(friendName.equals("B")) {
							boolean flag = Utility.borrowDetails(user.myAccount);
							if(flag) {
								System.out.println("Enter name to settle balance  ,,, * - goback ");
								System.out.println();
								friendName = in.nextLine();
							}
							else {
								break;
							}
						}
						if(Utility.back(friendName)) {
							break;
						}
						if((user.myAccount.containsKey(friendName) && user.myAccount.get(friendName)<0) || (user.unknownAccount.containsKey(friendName) && user.unknownAccount.get(friendName)>0)) {
							Utility.settledUp2(user, allAccount, friendName);
							break;
						}
						else if(user.myAccount.containsKey(friendName)&&user.myAccount.get(friendName)>0) {
							Utility.settleUp2(user, allAccount, friendName);
							break;
						}
						else {
							System.out.println("Wrong name");
							System.out.println();
						}
					}
				}
				break;
			case 1:
				Utility.addExpense(allAccount,user);
				break;
				
//			case 2:
//				Utility.borrowDetails(user.myAccount);
//				break;
				
//			case 3:
//				Utility.lendDetails(user.myAccount,user.unknownAccount);
//				break;
//				
//			case 4:
//				Utility.settleUp(user,allAccount);
//				break;
				
//			case 5:
//				Utility.settledUp(user,allAccount);
//				
//				break;
				
			case 5:
				Activity object = Utility.getActivity(user.myActivity);
				if(object != null) {

					if(object.type.equals("expense")) {
						
						double totalMoney = object.totalMoney;
						double[] friendSpent = Arrays.copyOf(object.friendsSpent, object.friendsSpent.length);;
						ArrayList<String> friends = new ArrayList<>(object.friends);
						double payerSpent = object.payerSpent;
						String payer = object.payer;
						Users payerAccount = allAccount.get(payer);
						payerAccount.totalexpense-=totalMoney;
						payerAccount.actualexpense-=payerSpent;
						int i1=0;
						for(String friendName : friends) {
							if(allAccount.containsKey(friendName)) {
								Users friend = allAccount.get(friendName);
								friend.totalexpense-=friendSpent[i1];
								friend.myActivity.remove(object);
								Utility.transaction(friendName, friend.myAccount, payer, payerAccount.myAccount, friendSpent[i1]);
								i1++;
								
								friend.notifications.add("  "+user.username+"  delete a expense '"+object.description+"'  added on "+object.date);
							}
							else {
								Utility.unknownTransaction(payerAccount.unknownAccount, friendName, friendSpent[i1], "reduction");
								i1++;
							}
						}
						
						payerAccount.notifications.add("  "+user.username+"  delete a expense '"+object.description+"'  added on "+object.date);
					
						payerAccount.myActivity.remove(object);
						System.out.println("Sucessfully deleted ! !");
						System.out.println();
					}
					else if(object.type.equals("personalExpense")) {
						
						String payer = object.payer;
						double totalMoney = object.totalMoney;
						Users payerAccount = allAccount.get(payer);
						payerAccount.personalexpense-=totalMoney;
						payerAccount.myActivity.remove(object);
						System.out.println("Successfully deleted ! !");
						System.out.println();
						
						payerAccount.notifications.add("  "+user.username+"  delete a expense '"+object.description+"'  added on "+object.date);
					
					}
					else if(object.type.equals("settlement")) {
						
						String payer = object.payer;
						double money = object.totalMoney;
						String receiver = object.receiver;
						Users receiverAccount = allAccount.get(receiver);
						if(allAccount.containsKey(payer)) {
							Users payerAccount = allAccount.get(payer);
							payerAccount.actualexpense-=money;
							Utility.transaction(receiver, receiverAccount.myAccount, payer, payerAccount.myAccount, money);
							payerAccount.myActivity.remove(object);
							receiverAccount.myActivity.remove(object);
							System.out.println("Sucessfully deleted ! !");
							System.out.println();
							
							payerAccount.notifications.add("  "+user.username+"  delete a expense '"+object.description+"'  added on "+object.date);
						
							
							receiverAccount.notifications.add("  "+user.username+"  delete a expense '"+object.description+"'  added on "+object.date);
						
							
						}
						else {
							Utility.unknownTransaction(receiverAccount.unknownAccount, payer, money, "addition");
							receiverAccount.myActivity.remove(object);
							System.out.println("Sucessfully deleted ! !");
							System.out.println();
						}
						
						
					}		
					
				}
				
				break;
				
				
			case 6:
				System.out.println();
				System.out.println();
				System.out.println(" User Name :   "+user.username);
				System.out.println();
				System.out.println(" Email Id :   "+user.getEmail());
				System.out.println();
				System.out.println(" Personal Expense :   "+user.personalexpense);
				System.out.println();
				System.out.println("  Total Expense :   "+user.totalexpense);
				System.out.println();
				System.out.println(" Actual Expense :   "+user.actualexpense);
				System.out.println();
				System.out.println("  Overall Expense :   "+(user.personalexpense+user.actualexpense) );
				System.out.println();
				System.out.println();
				break;
				
			case 7:
				Utility.addGroup(user,allAccount);
				
				break;
				
			case 8:
				if(user.myGroups.isEmpty()) {
					System.out.println("No Groups Created");
					break;
				}
				System.out.println("Group Name        Group Id");
				System.out.println();
				for(Map.Entry myGroup : user.myGroups.entrySet()) {
					int groupId =(Integer)myGroup.getKey();
					Group objectGrp = (Group)myGroup.getValue();
					System.out.println(groupId+"        "+objectGrp.getGroupName());
				}
				System.out.println();
				System.out.println("Enter group id");
				while(true) {
					int n = Utility.checkNumber();
					if(n==-1) {
						break;
					}
					if(user.myGroups.containsKey(n)) {
						Group objectGroup = user.myGroups.get(n);
						System.out.println("Group opened");
						System.out.println();
						new GroupHome(objectGroup,user.username);
						break;
					}
					else {
						System.out.println("Enter correct number");
					}
				}
				break;
				
			case 9:
				if(user.unknownAccount.isEmpty()) {
					System.out.println("No unknown account ");
					break;
				}
				
				System.out.println();
				System.out.println("    UnknownAccounts    Balance");
				ArrayList<String> unknownNames = new ArrayList<>();
				int i1=1;
				for(Map.Entry m : user.unknownAccount.entrySet()) {
					System.out.println(i1+"]	    "+m.getKey()+"    "+String.format("%.2f", m.getValue()));
					unknownNames.add((String)m.getKey());
					i1++;
				}
				System.out.println();
				System.out.println("Enter number of the unknownuser");
				int unknownNumber = Utility.checkNumber();
				if(unknownNumber==-1) {break;}
				String unknownname = unknownNames.get(unknownNumber-1);
				double Balance = user.unknownAccount.get(unknownname);
				System.out.println();
				if(!user.unknownAccount.containsKey(unknownname)) {
					System.out.println("Name not in the given list");
					System.out.println();
					break;
				}
				if(!user.myAccount.isEmpty()) {
					System.out.println(user.myAccount.keySet());
				}
				System.out.println();
				System.out.println("Enter existing account username ");
				String accountName =in.nextLine();
				if(allAccount.containsKey(accountName)) {
					Users account = allAccount.get(accountName);
					
					account.transfer.put(user.username,unknownname+" "+String.format("%.2f", Balance));
					System.out.println();
					System.out.println("Request Sent ! ! !");
				}
				else {
					System.out.println("Account Not Found");
					System.out.println();
					break;
				}
				
				break;
			default:
				System.out.println("Enter correct number");
				System.out.println();
				break;	
			}
			
		}
		
	}
	

}
