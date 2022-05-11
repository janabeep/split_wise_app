package group;

import java.util.*;

import non_group.Activity;
import non_group.Users;
import non_group.Utility;

public class GroupHome {
	
	Scanner in = new Scanner(System.in);
	
	public GroupHome(Group objectGroup, String username) {
		
		System.out.println();
		
		a:while(true) {
			System.out.println("              Welcome to "+objectGroup.groupName+" Group                                                                                Username :  "+objectGroup.groupName);
			System.out.println("                                                                                                  Username :  "+username);
			System.out.println();
			System.out.println("1. Add Expense");
			System.out.println("2. Settle up");
			System.out.println("3. My Balance");
			System.out.println("4. Members");
			System.out.println("5. Group Activity");
			System.out.println("6. "+objectGroup.groupName+" Stats");
			System.out.println("7. My Group Account");
			System.out.println("*  Exit");
			System.out.println();
			System.out.println("Enter number ");
			int option1 = Utility.checkNumber();
			if(option1==-1) {
				System.out.println("Exit group");
				System.out.println();
				return;
			}
			GroupAccount myGroupAccount = objectGroup.allGroupAccount.get(username);
			switch(option1) {
			
			case 4:
				System.out.println("Group Members");
				System.out.println();
				for(String members : objectGroup.getGroupMembers().keySet()) {
					if(myGroupAccount.groupAccount.containsKey(members)) {
						if(myGroupAccount.groupAccount.get(members)>0) {
							System.out.println(members+"     You borrow "+myGroupAccount.groupAccount.get(members)+" Rs");
						}
						else if(myGroupAccount.groupAccount.get(members)<0) {
							System.out.println(members+"     You lend "+(-1)*myGroupAccount.groupAccount.get(members)+" Rs");
						}
						else {
							System.out.println(members+"     "+"All settle up");
						}
					}
					else {
						if(!members.equals(username)) {
							System.out.println(members+"    "+"No expenses");
						}
					}
				}
				break;
			case 2:
				if(myGroupAccount.groupAccount.isEmpty()) {
					System.out.println("Nothing to settle");
					break;
				}
				int checked=0;
				for(Map.Entry friend : myGroupAccount.groupAccount.entrySet()) {
					if((double)friend.getValue()<0) {
						System.out.println(friend.getKey()+"    "+(-1*(double)friend.getValue())+" Rs. (You Get)");
						checked++;
					}
					else if((double)friend.getValue()>0) {
						System.out.println(friend.getKey()+"    "+friend.getValue()+" Rs.(You Pay)");
						checked++;
					}
				}
				if(checked==0) {
					System.out.println("Nothing to settle");
					break;
				}
				System.out.println("Enter name ");
				String name = in.nextLine();
				if(Utility.back(name)) {
					break;
				}
				if(myGroupAccount.groupAccount.containsKey(name)&&myGroupAccount.groupAccount.get(name)>0) {
					GroupUtility.settleUp2(objectGroup, username, name, myGroupAccount);
				}
				else if(myGroupAccount.groupAccount.containsKey(name)&&myGroupAccount.groupAccount.get(name)<0) {
					GroupUtility.settledUp2(objectGroup, username, myGroupAccount, name);
				}
				
//				if(myGroupAccount.groupAccount.isEmpty() ) {
//					System.out.println("No Balance");
//					System.out.println();
//					break;
//				}
//				while(true) {
//					System.out.println("1. Settle up your balance ");
//					System.out.println("2. Settle up your friends balance");
//					
//					int option = Utility.checkNumber();
//					if(option==-1) {
//						break;
//					}
//					if(option==1) {
//						GroupUtility.settleUp(objectGroup, username);
//						break;
//					}
//					else if(option==2) {
//						GroupUtility.settledUp(objectGroup, username);
//						break;
//					}
//					else {
//						System.out.println("Wrong number");
//					}
//				}
				break;
			case 3:
				int checking=0;
				double balance =0;
				if(myGroupAccount.groupAccount.isEmpty()) {
					System.out.println("No Balance");
					System.out.println();
					break;
				}
				int check = 0;
				System.out.println("My Balances");
				System.out.println();
				for(Map.Entry members : myGroupAccount.groupAccount.entrySet()) {
					if((double)members.getValue()>0) {
						System.out.println("You pay "+members.getValue()+" Rs. to "+members.getKey());
						balance-=(double)members.getValue();
						check++;
					}
					else if((double)members.getValue()<0) {
						System.out.println("You get "+(-1*(double)members.getValue())+" Rs. from "+members.getKey());
						balance+=(-1*(double)members.getValue());
						check++;
					}	
				}
				if(check==0) {
					System.out.println("No Balance");
					break;
				}
				System.out.println();
				System.out.println();
				if(balance>0) {
					System.out.println("Balance :- ");
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
				//
				while(true) {
					System.out.println();
					System.out.println("Enter name to settle balance or Enter 'L' to show lend details or Enter 'B' to show borrow details ,,, * - goback ");
					String friendName = in.nextLine();
					if(friendName.equals("L")) {
						boolean flag = Utility.lendDeatils(myGroupAccount.groupAccount);;
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
						boolean flag = Utility.borrowDetails(myGroupAccount.groupAccount);
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
					if((myGroupAccount.groupAccount.containsKey(friendName) && myGroupAccount.groupAccount.get(friendName)<0) ) {
						GroupUtility.settledUp2(objectGroup, username, myGroupAccount, friendName);
						break;
					}
					else if(myGroupAccount.groupAccount.containsKey(friendName) && myGroupAccount.groupAccount.get(friendName)>0) {
						GroupUtility.settleUp2(objectGroup, username, friendName, myGroupAccount);
						break;
					}
					else {
						System.out.println("Wrong name");
						System.out.println();
					}
				}
				//
				break;
			case 1:
				GroupUtility.addExpense(objectGroup, username);
				break;
//			case 2:
//				GroupAccount myGroupObject = objectGroup.allGroupAccount.get(username);
//				Utility.borrowDetails(myGroupObject.groupAccount);
//				break;
//			case 3:
//				GroupAccount myGroupObject1 = objectGroup.allGroupAccount.get(username);
//				Utility.lendDeatils(myGroupObject1.groupAccount);
//				break;
//			case 4:
//				GroupUtility.settleUp(objectGroup, username);
//				break;
			case 5:
				Activity object = Utility.getActivity(objectGroup.groupActivityList);
				if(object != null) {
					if(object.type.equals("expense")) {
						
						double totalMoney = object.totalMoney;
						double[] friendSpent = Arrays.copyOf(object.friendsSpent, object.friendsSpent.length);;
						ArrayList<String> friends = new ArrayList<>(object.friends);
						double payerSpent = object.payerSpent;
						String payer = object.payer;
						GroupAccount payerAccount = objectGroup.allGroupAccount.get(payer);
						payerAccount.totalExpense-=totalMoney;
						payerAccount.actualExpense-=payerSpent;
						int i=0;
						for(String friendName : friends) {
							GroupAccount friend = objectGroup.allGroupAccount.get(friendName);
							friend.totalExpense-=friendSpent[i];
							Utility.transaction(friendName, friend.groupAccount, payer, payerAccount.groupAccount, friendSpent[i]);
							i++;
						}
						objectGroup.groupActivityList.remove(object);
						System.out.println("Successfully deleted");
						System.out.println();
					}
					else if(object.type.equals("personalExpense")) {
						String payer = object.payer;
						double totalMoney = object.totalMoney;
						GroupAccount payerAccount = objectGroup.allGroupAccount.get(payer);
						payerAccount.personalExpense-=totalMoney;
						objectGroup.groupActivityList.remove(object);
						System.out.println("Successfully deleted");
						System.out.println();
					}
					else if(object.type.equals("settlement")) {
						String payer = object.payer;
						double money = object.totalMoney;
						String receiver = object.receiver;
						GroupAccount receiverAccount = objectGroup.allGroupAccount.get(receiver);
						GroupAccount payerAccount = objectGroup.allGroupAccount.get(payer);
						payerAccount.actualExpense-=money;
						Utility.transaction(receiver, receiverAccount.groupAccount, payer, payerAccount.groupAccount, money);
						objectGroup.groupActivityList.remove(object);
						System.out.println("Successfully deleted");
						System.out.println();
					}
					
				}
				break;
			case 6:
				GroupUtility.groupStats(objectGroup);
				break;
			case 7:
				GroupAccount objectGroupAccount = objectGroup.allGroupAccount.get(username);
				System.out.println();
				System.out.println();
				System.out.println("  User Name :   "+username);
				System.out.println();
				System.out.println("  Personal Expense :   "+objectGroupAccount.personalExpense);
				System.out.println();
				System.out.println("  Shared Expense :   "+objectGroupAccount.totalExpense);
				System.out.println();
				System.out.println("  Actual Expense :   "+objectGroupAccount.actualExpense);
				System.out.println();
				System.out.println("  Overall Expense :   "+(objectGroupAccount.personalExpense+objectGroupAccount.actualExpense) );
				System.out.println();
				System.out.println();
				break;
			default:
				
				break;
			}
			
			
		}
		
	}

}
