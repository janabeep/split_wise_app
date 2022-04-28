package zoho_final;

import java.util.*;

public class New_Group_Home {

	public void opengroup(HashMap<String, Account_Details> all_account2, Account_Details my_account, String grp_name, New_Group group) {
		Scanner in = new Scanner(System.in);
		aa:while(true) {
			System.out.println("1. 	Add Expense");
			System.out.println("2. 	Money Borrow");
			System.out.println("3. 	Money Lend");
			System.out.println("4. 	Settle Up Your Balance");
			System.out.println("5. 	Settled Up Your Friends Balance");
			System.out.println("6. 	Activity");
			System.out.println("7. 	Group Expense");
			System.out.println("8. 	Exit");
			System.out.println();
			System.out.println("Enter a number (1-8)");
			System.out.println();
			int n;
			while(true) {
				try {
					String s = in.nextLine();
					if(s.equals("*")) {break aa;}
					 n = Integer.parseInt(s);
					 if(1<=n&&n<=8) {
						 break;
					 }
					 else {
						 System.out.println("Enter a number between 1 and 8");
						 System.out.println();
					 }
				}
				catch(Exception e) {
					System.out.println("Enter a number");
					System.out.println();
				}
			}
			switch(n) {
			//					Add Expense
			case 1:
				int nn =0;
				while(true) {
				try {
					System.out.println("1. Equal Share");
					System.out.println("2. Divide by amount");
					System.out.println();
					String dom = in.nextLine();
					if(dom.equals("*")) {break aa;}
				 nn = Integer.parseInt(dom);
				 if(nn==1||nn==2) {
					 break;
				 }
				 else {
					 System.out.println("Enter Number 1 or 2");
					 System.out.println();
				 }
				}
				catch(Exception e) {
					System.out.println("Enter Number");
					System.out.println();
				}
				}
				
					if(nn==1) {
					double money ;
					while(true) {
						try {
							System.out.println("		Enter Total Money Spent ");
							System.out.println();
							String s = in.nextLine();
							money = Double.parseDouble(s);
							if(money<0.00) {
								System.out.println("Enter a positive number");
							}
							else {
								break;
							}
						}
						catch(Exception e) {
							System.out.println("Enter a Number ");
							System.out.println();
						}
					}
					System.out.println("					Purpose Of Spent Money");
					System.out.println();
					String Description = in.nextLine();
					System.out.println();
					System.out.println("Choose The Type Of The Expense");
					System.out.println();
					System.out.println("1. Accommodation");
					System.out.println("2. Food");
					System.out.println("3. Travel");
					System.out.println("4. Others");
					System.out.println();
					int option =0;
					while(true){
					try {
						option=Integer.parseInt(in.nextLine());
						if(0<option&& option<5) {
							break;
						}
						else {
							System.out.println("Enter Any Number From 1 to 4");
						}
					}
					catch(Exception e) {
						System.out.println("Enter a number 1-4");
					}
					}
					switch(option) {
					case 1:
						group.Accommodation+=money;
						break;
					case 2:
						group.Food+=money;
						break;
					case 3:
						group.Travel+=money;
						break;
					case 4:
						group.Others+=money;
						break;
					}
					group.totalmoney +=money;
					New_Group_Split_Equal spli = new New_Group_Split_Equal();
					spli.splitequal(my_account,all_account2,group,money,Description);
				}
				else if (nn==2) {
					New_Group_Split_Amt spli = new New_Group_Split_Amt();
					spli.splitunequal(my_account,all_account2,group);
					
				}
				break;
			//					Money Borrow
			case 2:
				New_Group_Account my1=  my_account.Group_Account.get(group.grpid) ;
				System.out.println();
				if(my1.acc.isEmpty()) {
					System.out.println("				You Didnt Borrowed ");
					return;
				}
				else {
					int chk=0;
					for(Map.Entry dd : my1.acc.entrySet()) {
						if((double)dd.getValue()>=1) {
							chk++;
							System.out.println("You  borrowed "+String.format("%.2f", dd.getValue())+".Rs from "+dd.getKey());
						}
					}
					if(chk==0) {System.out.println("			All  Settle  up");}
				}
				break;
			// 					Money Lend
			case 3:
				New_Group_Account my2=  my_account.Group_Account.get(group.grpid) ;
				System.out.println();
				if(my2.acc.isEmpty()) {
					System.out.println("				You Didnt Lend ");
					return;
				}
				else {
					int chk=0;
					for(Map.Entry dd : my2.acc.entrySet()) {
						if((double)dd.getValue()<0) {
							chk++;
								System.out.println("You Lent "+String.format("%.2f", (-1*(double)dd.getValue()))+" Rs.  From "+dd.getKey());
						}
					}
					if(chk==0) {
						System.out.println("No  Money  Lend");
					}
				}
				break;
			// 					Settle Up Your Balance
			case 4:
				New_Group_Settle_Up obj4 = new New_Group_Settle_Up();
				obj4.grpsettleup(my_account,all_account2,group);
				
				break;
			//					Settled Up Your Friends Balance
			case 5:
				New_Group_Settled_Up obj5 = new New_Group_Settled_Up();
				obj5.gepsettledup(my_account,all_account2,group);
				
				break;
			//						Activity
			case 6:
				System.out.println();
				for(String det : group.Detail) {
					System.out.println(det);
				}
				System.out.println();
				break;
			//							Group Expense
			case 7:
				
				
				break;
			//							Exit
			case 8:
				
				
				System.out.println("				EXIT  GROUP ");
				System.out.println();
				return;
				
				
			default:
				
				
				System.out.println("Enter Correct Number");
				break;
			}
			
		}
		
	}

	

}
