package group;
import java.util.*;

import non_group.Activity;
import non_group.Utility;

public final class GroupUtility {

	static Scanner in = new Scanner(System.in);
	private GroupUtility() {
	}

	
	// Group Add Expense
	public static void addExpense(Group objectGroup, String username) {
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
							GroupUtility.splitEqual(objectGroup, username,description);
							break;
						}
						else if(splitOption==2) {
							GroupUtility.splitUnequal(objectGroup, username, description);
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
				 Activity object = Utility.personalExpense(username, description);
				if(object == null) {
					break;
				}
				double money = object.totalMoney;
				GroupAccount objectGroupUser = objectGroup.allGroupAccount.get(username);
				objectGroupUser.personalExpense+=money;
				objectGroup.groupActivityList.add(object);
					
				break;
			}
			else {
				System.out.println("Enter Y/N");
				System.out.println();
			}
			
		}
		
	}

	// Personal expense 
//	public static void personalExpense(Group objectGroup, String description, String username) {
//		System.out.println();
//		double money = Math.round(Utility.getMoney())  ;
//		if(money==-1) {
//			return;
//		}
//		GroupAccount objectGroupUser = objectGroup.allGroupAccount.get(username);
//		objectGroupUser.personalExpense+=money;
//		Activity objectActivity = new Activity("personalExpense",Utility.getDate(),description,username,money);
//		objectGroup.groupActivityList.add(objectActivity);
//		
//		System.out.println("Successfully added added as personal account");
//		
//	}

	// Split unequally
	public static void splitUnequal(Group objectGroup, String username, String description) {
		System.out.println();
		System.out.println("Enter money spent for yourself ? If not enter zero !");
		System.out.println();
		double mySpent = Utility.getMoney();
		if(mySpent==-1) {
			return;
		}
		GroupAccount objectGroupAccountUser = objectGroup.allGroupAccount.get(username);
		objectGroupAccountUser.actualExpense+=mySpent;
		ArrayList<String> listOfMembers = new ArrayList<>();
		boolean check = GroupUtility.getMembers(listOfMembers, objectGroup, username);
		if(!check) {
			return;
		}
		double spentMoney[] = new double[listOfMembers.size()];
		double totalMoney = Utility.getMembersMoney(spentMoney, listOfMembers);
		totalMoney+=mySpent;
		GroupUtility.typeOfExpense(totalMoney,objectGroup);
		objectGroupAccountUser.totalExpense+=totalMoney;
		Activity objectActivity = new Activity("expense",Utility.getDate(),description,username,totalMoney,spentMoney,
				listOfMembers,mySpent,username,"Un Equall Split");
		objectGroup.groupActivityList.add(objectActivity);
		for(int i=0;i<spentMoney.length;i++) {
			GroupAccount objectGroupAccountFriend = objectGroup.allGroupAccount.get(listOfMembers.get(i));
			objectGroupAccountFriend.totalExpense+=spentMoney[i];
			Utility.isFirstTransaction(objectGroupAccountUser.groupAccount, username, objectGroupAccountFriend.groupAccount, listOfMembers.get(i));
			Utility.transaction(username, objectGroupAccountUser.groupAccount, listOfMembers.get(i), objectGroupAccountFriend.groupAccount, spentMoney[i]);
		}
		System.out.println("Successfully Added");
	}

	// Split Equally
	public static void splitEqual(Group objectGroup, String username, String description) {
		System.out.println();
		
		double money = Utility.getMoney();
		
		if(money==-1) {
			
			return;
		}
		GroupUtility.typeOfExpense(money,objectGroup);

		ArrayList<String> listOfMembers = new ArrayList<>();

		boolean check = GroupUtility.getMembers(listOfMembers, objectGroup, username);

		if(!check) {

			return;
		}

		double individualExpense = Math.round(Utility.getIndividualSpent(money, listOfMembers)) ;

		GroupAccount objectGroupAccountUser = objectGroup.allGroupAccount.get(username);

		objectGroupAccountUser.totalExpense+=money;

		objectGroupAccountUser.actualExpense+=individualExpense;

		double spentMoney[] = new double[listOfMembers.size()];

		Arrays.fill(spentMoney, individualExpense);

		Activity objectActivity = new Activity("expense",Utility.getDate(),description,username,money,spentMoney,
				listOfMembers,individualExpense,username,"Equally Split");

			objectGroup.groupActivityList.add(objectActivity);

		for(String friendName : listOfMembers) {

			GroupAccount objectGroupAccountFriend = objectGroup.allGroupAccount.get(friendName);

			objectGroupAccountFriend.totalExpense+=individualExpense;

				Utility.isFirstTransaction(objectGroupAccountUser.groupAccount, username, objectGroupAccountFriend.groupAccount, friendName);

				Utility.transaction(username, objectGroupAccountUser.groupAccount, friendName, objectGroupAccountFriend.groupAccount, individualExpense);

			
			
		}

	
		System.out.println("Successfully Added");
		
	}

	// Get type of expense
	public static void typeOfExpense(double money, Group objectGroup) {
		a:while(true) {
			System.out.println();
			System.out.println("Choose The Type Of The Expense");
			System.out.println();
			System.out.println("1. Accommodation");
			System.out.println("2. Food");
			System.out.println("3. Travel");
			System.out.println("4. Entertainment");
			System.out.println("5. Others");
			System.out.println();
			System.out.println("Enter options");
			int option = Utility.checkNumber();
			switch(option) {
				case 1:
					objectGroup.Accommodation+=money;
					break a;
				case 2:
					objectGroup.Food+=money;
					break a;
				case 3:
					objectGroup.Travel+=money;
					break a;
				case 4:
					objectGroup.Entertainment+=money;
					break a;
				case 5:
					objectGroup.Others+=money;
					break a;
				default:
					System.out.println("Enter correct number");
					break;
			}
	}
		
	}

	// Get Members 
	public static boolean getMembers(ArrayList<String> listOfMembers, Group objectGroup, String username) {
		System.out.println();
		if(objectGroup.groupSize==2) {
			return GroupUtility.addOneMember(listOfMembers, objectGroup, username);
		}
		while(true) {
			
			System.out.println("Enter friend name ");
			System.out.println("Enter '!' for stop adding");
			String friendName = in.nextLine();
			if(Utility.back(friendName)) {
				return false;
			}
			if(friendName.equals("!")) {
				if(listOfMembers.isEmpty()) {System.out.println("No friends added "+System.lineSeparator()+" Add friends ");}
				else {return true ;}
			}
			if(Utility.checkName(friendName)) {
				if(friendName.equals(username)) {
					System.out.println("You should not enter your name here ");
					System.out.println();
				}
				else if(!objectGroup.getGroupMembers().containsKey(friendName)) {
					System.out.println("Entered username is not in group ");
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
		
	}
	
	// Only 2 members in group
	public static boolean addOneMember(ArrayList<String> listOfMembers, Group objectGroup, String username) {
		for(String name : objectGroup.getGroupMembers().keySet()) {
			if(!name.equals(username)) {listOfMembers.add(name);return true;}
		}
		return false;
	}


	// Group Statistic
	public static void groupStats(Group objectGroup) {
		int total = objectGroup.Accommodation+objectGroup.Travel+objectGroup.Food+objectGroup.Entertainment+objectGroup.Others;
		if(total==0) {
			System.out.println("Empty statistics");
			return;
		}
		int Accomopercent = ((objectGroup.Accommodation*100/total))/10;
		int Travelpercent = ((objectGroup.Travel*100/total))/10;
		int Foodpercent = ((objectGroup.Food*100/total))/10;
		int Entertainpercent = ((objectGroup.Entertainment*100/total))/10;
		int Otherpercent = ((objectGroup.Others*100/total))/10;
		
		System.out.println();
		System.out.print(" Accommodation :- ");
		for(int i=0;i<Accomopercent;i++) {
			System.out.print("*");
		}
		System.out.println("  Accommodation "+Accomopercent+"%");
		System.out.println();
		
		System.out.print(" Travel :- ");
		for(int i=0;i<Travelpercent;i++) {
			System.out.print("*");
		}
		System.out.println("  Travel "+Travelpercent+"%");
		System.out.println();

		System.out.print(" Food :- ");
		for(int i=0;i<Foodpercent;i++) {
			System.out.print("*");
		}
		System.out.println("  Food "+Foodpercent+"%");
		System.out.println();
		
		System.out.print(" Entertainment :- ");
		for(int i=0;i<Entertainpercent;i++) {
			System.out.print("*");
		}
		System.out.println("  Entertainment "+Entertainpercent+"%");
		System.out.println();
		
		System.out.print(" Others :- ");
		for(int i=0;i<Otherpercent;i++) {
			System.out.print("*");
		}
		System.out.println("  Others "+Otherpercent+"%");
		System.out.println();
		
		System.out.println("	Group Spent ");
		System.out.println("Accommodation :- "+objectGroup.Accommodation+" Rs.");
		System.out.println("Travel :- "+objectGroup.Travel+" Rs.");
		System.out.println("Food :- "+objectGroup.Food+" Rs.");
		System.out.println("Entertainment :- "+objectGroup.Entertainment+" Rs.");
		System.out.println("Others :- "+objectGroup.Others+" Rs.");
		System.out.println();
		String s;
		while(true) {
			System.out.println("Enter 0 to see group member stats or enter * to go back");
			System.out.println();
			s = in.nextLine();
		if(s.equals("*")) {
			return;
		}
		else if (s.equals("0")) {
		break;	
		}
		else {
			System.out.println("Wrong");
			System.out.println();
		}
		}
		System.out.println();
		
		double groupTotalSpent = 0.0;
		
		System.out.println("Group members contribute");
		
		for(Map.Entry account : objectGroup.allGroupAccount.entrySet()) {
			GroupAccount acc = (GroupAccount) account.getValue();
			System.out.println();
			System.out.println((String)account.getKey()+" contribute "+String.format("%.2f",acc.actualExpense+acc.personalExpense)+" Rs. ");
			groupTotalSpent+=acc.actualExpense+acc.personalExpense;
		}
		System.out.println();
		System.out.println("Group total spent :- "+groupTotalSpent);

	}
	
	
	// Settle Up
	public static void settleUp(Group objectGroup,String username) {
		GroupAccount myGroupObject2 = objectGroup.allGroupAccount.get(username);
		if(myGroupObject2.groupAccount.isEmpty()) {
			System.out.println("Nothing to settle up");
			return;
		}
		while(true) {
			boolean check =Utility.borrowDetails(myGroupObject2.groupAccount);
			if(!check) {
				break;
			}
			
			System.out.println();
			System.out.println("Enter a name from the list");
			String friendName = in.nextLine();
			if(Utility.back(friendName)) {
				break;
			}
			if(myGroupObject2.groupAccount.containsKey(friendName) && myGroupObject2.groupAccount.get(friendName)>0) {
				GroupUtility.settleUp2(objectGroup,username,friendName,myGroupObject2);
			}
			else {
				System.out.println("Entered name is wrong ");
			}
		}
	}
	
	// SettleUp2 
	public  static void settleUp2(Group objectGroup,String username, String friendName, GroupAccount myGroupObject) {

		double money = Utility.getMoney();
		if(money==-1) {
			return;
		}
		GroupAccount friendGroupObject = objectGroup.allGroupAccount.get(friendName);
		Utility.transaction(username, myGroupObject.groupAccount, friendName, friendGroupObject.groupAccount, money);
		Activity objectActivity = new Activity("settlement", Utility.getDate(), "Settle Up", username, username, money, friendName);
		objectGroup.groupActivityList.add(objectActivity);
		friendGroupObject.actualExpense+=money;
		System.out.println("Successfully Done");
		System.out.println();
	
	}
	
	// Settled Up
	public static void settledUp(Group objectGroup, String username) {
		GroupAccount myGroupObject3 = objectGroup.allGroupAccount.get(username);
		if(myGroupObject3.groupAccount.isEmpty()) {
			System.out.println("Nothing to settled up");
			return;
		}
		while(true) {
			boolean check2 = Utility.lendDeatils(myGroupObject3.groupAccount);
			if(!check2) {
				break;
			}
			System.out.println();
			System.out.println("Enter a name from the list");
			String friendName = in.nextLine();
			if(Utility.back(friendName)) {
				break;
			}
			if(myGroupObject3.groupAccount.containsKey(friendName) && myGroupObject3.groupAccount.get(friendName)<0) {
				GroupUtility.settledUp2( objectGroup,  username, myGroupObject3, friendName);
			}
			else {
				System.out.println("Entered name is wrong ");
			}
			
		}
	}
	
	// Settled Up2
	public static void settledUp2(Group objectGroup, String username, GroupAccount myGroupObject, String friendName) {

		double money = Utility.getMoney();
		if(money==-1) {
			return;
		}
		GroupAccount friendGroupObject = objectGroup.allGroupAccount.get(friendName);
		myGroupObject.actualExpense+=money;
		Activity objectActivity = new Activity("settlement", Utility.getDate(), "Settle Up", username,friendName,money,username);
		objectGroup.groupActivityList.add(objectActivity);
		Utility.transaction(friendName, friendGroupObject.groupAccount, username, myGroupObject.groupAccount, money);
		System.out.println("Sucessfully done");
		System.out.println();
	
	}


	public static double xxx(Group ele, String name1, String username) {
		
		double  amount = 0;
			GroupAccount g = ele.allGroupAccount.get(username);
			if(g.groupAccount.containsKey(name1)) {
				if(g.groupAccount.get(name1)>0) {
					System.out.println("You Borrow "+(double)g.groupAccount.get(name1)+" Rs. ");
					amount = g.groupAccount.get(name1);
				}
				else if(g.groupAccount.get(name1)<0){
					System.out.println("You Lend "+(-1*g.groupAccount.get(name1))+" Rs. ");
					amount = g.groupAccount.get(name1);
				}
				else {
					System.out.println("Settle up");
				}
			}
			else {
				System.out.println("No transactions made");
			}
			System.out.println();
			return amount;
	}
}
	

