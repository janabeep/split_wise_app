package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class New_Group_Split_Amt {

	public void splitunequal(Account_Details my_account, HashMap<String, Account_Details> all_account2, New_Group group) {
		Scanner in = new Scanner(System.in);
		
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj); 
		 
		System.out.println("What Purpose You Spent Money For ?");
		String description = in.nextLine();										// Description for expense
		
		double total=0.00;															// To Calculate Total Amount
		
		String mon="";
		while(true) {
		System.out.println("Enter money spent for yourself ? .. .  If not enter 0 ..");
		mon = in.nextLine();
		if(mon.equals("*")) {return ;}
		try {
			double d = Double.parseDouble(mon);
			if(d>=0) {
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
		total += Double.parseDouble(mon);
		String member=" ";													//Add Friends name in one string(space separated)
		
		ArrayList<String> al =	new ArrayList<>();
		aa:while(true) {
			int chk=0;
			System.out.println("				Group Members");
			for(String u : group.member_name) {
				if(al.isEmpty()&& !u.equals(my_account.username)) {
					System.out.println();
					System.out.println("			"+u);
					chk++;
					System.out.println();
				}
				else {
					if(!u.equals(my_account.username)&&!al.contains(u)) {
						System.out.println();
						System.out.println("			"+u);
						chk++;
						System.out.println();
					}
				}
			}
			if(chk==0) {break;}
			System.out.println("Enter Friend Name From Above List ");
			System.out.println("	Enter '!' to Stop Adding ");
			String friend = in.nextLine();
			if(friend.equals("!")) {break;}
			System.out.println();
			if(group.member_name.contains(friend) && !friend.equals(my_account.username)) {
				if(!al.contains(friend)) {
					al.add(friend);
					member=member+" "+friend+" ";
					double money ;
					while(true) {
						try {
							System.out.println("Enter Money Spent for "+friend);
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
					total+=money;
					Account_Details friends = all_account2.get(friend);
					New_Group_Account my = my_account.Group_Account.get(group.grpid);
					New_Group_Account fri = friends.Group_Account.get(group.grpid);
					String det1="  "+formattedDate+"  "+my_account.username+"  paid  "+String.format("%.2f", money)+" Rs.  for  "+description;
					fri.det.add(det1);
					
					if(!fri.acc.containsKey(my_account.username)) {
						fri.acc.put(my_account.username, money);
						my.acc.put(friend, -1*money);
					}
					else {
						double current = my.acc.get(friend);
						if(current <=0.00) {
							double val = fri.acc.get(my_account.username);
							fri.acc.put(my_account.username, val+money);
							friends.Group_Account.put(group.grpid, fri);
							my.acc.put(friend, -1* (money+val) );
							//my_account.Group_Account.put(group.grpid, my);
						}
						else if(current>0.00) {
							current = current - money;
							if(current>=0.00) {
								my.acc.put(friend, current);
								fri.acc.put(my_account.username, -1*current);
							}
							else if(current <0.00) {
								current = current*(-1);
								my.acc.put(friend, -1*current);
								fri.acc.put(my_account.username, current);
							}
						}
					}
					
					
				}
				else {
					System.out.println("Already Added");
					System.out.println();
				}
			}
			else {
				System.out.println(" Invalid username");
				System.out.println();
			}
		}
		
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
			group.Accommodation+=total;
			break;
		case 2:
			group.Food+=total;
			break;
		case 3:
			group.Travel+=total;
			break;
		case 4:
			group.Others+=total;
			break;
		}
		
		group.totalmoney +=total;
		String primarydet ="  "+formattedDate+"  "+my_account.username+"  paid  "+String.format("%.2f", total)+" Rs.  for  "+description+"  Friends:  "+member;
		group.Detail.add(primarydet);
		New_Group_Account my = my_account.Group_Account.get(group.grpid);
		my.det.add(primarydet);
		
		System.out.println("					SUCCESSFULLY  ADDED");

	}

}
