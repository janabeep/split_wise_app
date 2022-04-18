package zohoproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Open_group {
	public void activities(Group u) {
	
		System.out.println("                              Activities                                      ");
		if(u.group_details.isEmpty()) {
			System.out.println("------------------------------------------------");
			System.out.println("No Activities");
			System.out.println("------------------------------------------------");
		}
		else {
		System.out.println("------------------------------------------------");
		for(String s : u.group_details) {
			System.out.println(s);
			System.out.println("------------------------------------------------");
		}
		}
		System.out.println("         Group Members                   ");
		for(String s : u.member_name) {
			System.out.println(s);
		}
	}

	public void open(List<Signup> all_account,Signup my_account,String group_name, Signup group_admin, Group group) {
		Scanner in = new Scanner(System.in);
		boolean f = true;
		
		aa:while(true) {
			activities(group);
		System.out.println("1. Add Expense");
		System.out.println("2. Money Lend");
		System.out.println("3. Money Borrow");
		System.out.println("4. Settle Up Your Balance");
		System.out.println("5. Settled Up Your Friends Balance");
		//System.out.println("6. Statistics");
//		System.out.println("7. Group Expense");
		System.out.println("8. Exit");
		System.out.println();
		System.out.println("Enter a number (1-8)");
		int n = Integer.parseInt(in.nextLine());
		switch (n) {
		case 1:

			System.out.println("1. Equal Share");
			System.out.println("2. Divide by amount");
			int nn = Integer.parseInt(in.nextLine());
			if(nn==1) {
//				System.out.println("check 1");
				System.out.println("Enter Description ");
				String description = in.nextLine();
				double money=0.00;
				String mon="";
				while(true) {
//					System.out.println("check 2");
				System.out.println("Enter Total money ");
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
				money = Double.parseDouble(mon);
			Group_split_equal obj = new Group_split_equal();
//			System.out.println("check 3");
			obj.eshare( all_account, my_account, group_name,money,description, group_admin,  group);
			}
			else if(nn==2) {
				Group_split_amt obj = new Group_split_amt();
				obj.divideshare(all_account,my_account,group_name,group_admin,group);
			}
			break;
		case 2:
			
			int check1 =0;
			for(user u : group.members) {
				if(u.username!=my_account.username) {
					HashMap<String,Double> hm = u.grpaccount.get(group_name);
					for(Map.Entry d : hm.entrySet()) {
						if(d.getKey().equals(my_account.username) && (double)d.getValue()>0) {
							check1++;
							System.out.println("You Lend Rs."+String.format("%.2f", d.getValue())+" to "+u.username);
						}
					}
				}
			}
			if(check1==0) {System.out.println("You Did'nt Lend Money to Anyone");}
			break;
		case 3:
			int check2=0;
			HashMap<String,Double> hm = my_account.grpaccount.get(group_name);
			
			for(Map.Entry d : hm.entrySet()) {
				
				if((double)d.getValue()>0) {
					check2++;
				System.out.println(" You  Borrowed  Rs."+String.format("%.2f", d.getValue())+"  from  "+(String)d.getKey());
				}
			}
			if(check2==0) {System.out.println("You Did'nt Borrowed Money From Anyone");}
			break;
		case 4:
			HashMap<String,Double> map = new HashMap<>();
			HashMap<String,Double> mymap = my_account.grpaccount.get(group_name);
			if(mymap.isEmpty()) {
				System.out.println("You Didnt start any transaction");
				break;
			}
			int check=0;
			for(Map.Entry d: mymap.entrySet()) {
				double balance = (double)d.getValue();
				if(!(balance==0.00)) {
					check++;
					System.out.println("You  Borrowed  "+String.format("%.2f", balance)+".Rs  from "+d.getKey());
					map.put((String)d.getKey(), balance);
				}
			}
			if(check==0) {System.out.println("No  Money  Borrow");
			break;}
			System.out.println("Enter  name of the  person to  settle up amount");
			String ss = in.nextLine();
			boolean flag = true;
			while(flag) {
				if(map.containsKey(ss)) {
					flag = false;
					
					 double value = map.get(ss);
					 System.out.println("Enter Amount");
					 double amount = Double.parseDouble(in.nextLine());
					 if((value-amount)==0 ) {
						 mymap.put(ss, 0.00);
						 my_account.grpaccount.put(group_name, mymap);
					 }
					 else if((value-amount)>0) {
						 mymap.put(ss, value-amount);
						 my_account.grpaccount.put(group_name, mymap);
					 }
					 else if((value-amount)<0){
						 System.out.println("Entered amount is higher than the borrowed amount");
							System.out.println("amount less than or equal to borrowed amount is accpcted");
					 }
				}
				else {
					System.out.println("Enter  Name  From  List");
				}
			}
			break;
		case 5:
			HashMap<String,Double> mymap1 = my_account.grpaccount.get(group_name);
			if(mymap1.isEmpty()) {
				System.out.println("You  Didnt  Start  Any  Transaction");
				break;
			}
			else {
				HashMap<String,Double> hash = new HashMap<>();
//				ArrayList<String> al = new ArrayList<>();
				int check3=0;
				for(user u :group.members) {
					if(!(u.username.equals(my_account.username))) {
						HashMap<String,Double> ham = u.grpaccount.get(group_name);
						for(Map.Entry d : ham.entrySet()) {
							if(d.getKey().equals(my_account.username) && (double)d.getValue()>0) {
								check3++;
								hash.put(u.username,(double)d.getValue());
//								al.add(u.username);
//								mymap1.put(u.username,(double)d.getValue());
								System.out.println("You Lend Rs."+String.format("%.2f", d.getValue())+" to "+u.username);
								break;
							}
						}
					}
				}
				if(check3==0) {
					System.out.println("No Money Lend");
					break;
				}
				while(true) {
				System.out.println("Enter Name Of The Person ");
				String s = in.nextLine();
				if(hash.containsKey(s)) {
					System.out.println("Enter Amount");
					double amt = Double.parseDouble(in.nextLine());
					if(amt>(double)hash.get(s)) {
						System.out.println("Sorry givem amount is larger than balance");
						
					}
					else {
						double bal = (double)mymap1.get(s) - amt;
						for(user uu : group.members) {
							if(uu.username.equals(s)) {
								HashMap<String,Double> mp = uu.grpaccount.get(group_name);
								mp.put(my_account.username, bal);
								uu.grpaccount.put(group_name, mp);
								break;
							}
						}
					}
					break;
				}
				else {
					System.out.println("Enter Correct Name");
				}
				}
			}
			break;
		case 6:
			break;
		case 7:
			
			break;
		case 8:
			break aa;
			
		}
	}
//		int n =0;
//		while(true) {
//		try {
//			System.out.println("Enter a number");
//			n = Integer.parseInt(in.nextLine());
//			break;
//		}
//		catch(Exception e){
//			
//		}
//		}
//		switch (n) {
//			case 1:
//				double money ;
//				int no = 0;
//				String description;
//				System.out.println("Enter * to return main page at any step..");
//				System.out.println("Enter Amount Spend ");
//				boolean f1=true;
//				double num = 0;
//				while(f1) {
//				String s = in.nextLine();
//				if(s.equals("*")) {
//					return;
//				}
//				num =0.00;
//				try {
//		             num = Double.parseDouble(s);
//		             f1=false;
//		        } catch (NumberFormatException e) {
//		            System.out.println("Enter a number..");
//		        }
//				}
//				f1=true;
//				money = num;
//				
//				while(f1) {
//					System.out.println("Enter Total number of person (includes you)");
//					String s = in.nextLine();
//					if(s.equals("*")) {
//						return;
//					}
//					no =0;
//					try {
//							no = Integer.parseInt(s);
//							if(no<=1) {
//								System.out.println("Enter number greater than 1");
//							}
//							else {
//								f1=false;
//							}
//			             
//			        } catch (NumberFormatException e) {
//			            System.out.println("Enter a number..");
//			        }
//				}
//				f1=true;
//				System.out.println("Enter description ");
//				description = in.nextLine();
//				if(description.equals("*")){
//					return;
//				}
//		        System.out.println();		
//				System.out.println("1. Equal share");
//				System.out.println("2. Divide by amount");
//				int noo=0;
//				while(f1) {
//					String s = in.nextLine();
//					if(s.equals("*")) {
//						return;
//					}
//					noo =0;
//					try {
//			             noo = Integer.parseInt(s);
//			             f1=false;
//			        } catch (NumberFormatException e) {
//			            System.out.println("Enter a number..");
//			        }
//					
//				switch (noo){
//				case 1:
////				   equalshare obj1 = new equalshare();
////					obj1.eshare(l,t,no , money,description);
//					grp_eshare obj1 = new grp_eshare();
//					obj1.eshare(l,no,money,description,ll,g);
//					break;
//				case 2:
////					divideamt obj2 = new divideamt();
////					obj2.divide(l,t,no,money,description);
//					
//					
//					break;
//				default:
//					System.out.println("Enter correct number");
//					break;
//				}
//				}
//				break;
//			case 2:
//				System.out.println("enter 1");
//				for(user t : l.members) {
//					System.out.println("enter 2");
//					if(!(t.username.equals(l.usernameg)) ) {
//						System.out.println("enter 3");
//						for(Group d : t.myGroups) {
//							System.out.println("enter 4");
//							if(d.grp_name.equals(l.grp_name)) {
//								System.out.println("enter 5");
//							for(Map.Entry dd : d.moneyg.entrySet()) {
//								System.out.println("enter 6");
//								if(dd.getKey().equals(l.usernameg)) {
//									System.out.println("enter 7");
//									System.out.println();
//									System.out.println("You  Lend  Amount  "+String.format("%2f",dd.getValue())+"Rs  to  "+dd.getKey());
//									System.out.println();
//									break;
//								}
//							}
//						}
//						}
//					}
//				}
//				break;
//			case 3:
//				System.out.println(l.moneyg);
//				System.out.println("join 1");
//				for(Map.Entry dd : l.moneyg.entrySet()) {
//					System.out.println("join 2");
//					System.out.println();
//					System.out.println("You Borrowed Money  "+String.format("%2f",dd.getValue())+"Rs  from  "+dd.getKey());
//					System.out.println();
//				}
//
//
//
//				break;
//			case 4:
//				break;
//			case 5:
//				break;
//			case 6:
//				break;
//			case 7:
//				break;
//			default:
//					System.out.println("Enter correct number(1 - 7)");
//				break;
//		}
	
	}

	
}
