package zohoproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class Group_split_amt {

	public void divideshare(List<Signup> all_account, Signup my_account, String group_name, Signup group_admin, Group group) {
		Scanner in = new Scanner(System.in);
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  
		String description; 
		System.out.println("Enter Description  ");
		description = in.nextLine();
		double total=0.00;
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
		System.out.println("Group Members");
		System.out.println();
		for(String u : group.member_name) {
			System.out.println();
			System.out.println(u);
			System.out.println();
		}
		total = total+Double.parseDouble(mon);
		String members = " ";
		aa:while(true) {
			String regex1 = "^[A-Za-z]\\w{1,29}$";
		    Pattern p1 = Pattern.compile(regex1);
			String friendname="";
			bb:while(true) {
				System.out.println("Enter Friend name");
				System.out.println("Enter '!' to stop Adding");
				friendname = in.nextLine();
				if(friendname.equals("!")) {break aa;}
				if(!group.member_name.contains(friendname)) {
					System.out.println("Enter Name From The Above List");
				}
				else {
					break bb;
				}
			}
			members = members+friendname+" ";
			double amount_spend=0.00;
			while(true) {
				System.out.println("Enter Money Spent For Him "+friendname);
				 
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
			for(user users : group.members) {
				if(users.username.equals(friendname)) {
					HashMap<String,Double> hm = users.grpaccount.get(group_name);
					if(!hm.containsKey(my_account.username)) {
						hm.put(my_account.username, amount_spend);
						HashMap<String,Double> hm1 = my_account.grpaccount.get(group_name);
						hm1.put(friendname, 0.00);
						my_account.grpaccount.put(group_name,hm1);
					}
					else {
						HashMap<String,Double> hm1 = my_account.grpaccount.get(group_name);
						double current = hm1.get(friendname);
						if(current== 0.00) {
							double value = hm.get(my_account.username);
							hm.put(my_account.username, value+amount_spend);
						}
						else if(current>0.00){
							current = current - amount_spend;
							if(current>=0.00) {
								hm1.put(friendname, current);
								my_account.grpaccount.put(group_name, hm1);
							}
							else if(current<0.00) {
								current = current * (-1);
								hm1.put(friendname, 0.00);
								my_account.grpaccount.put(group_name, hm1);
								double value  = hm.get(my_account.username);
								hm.put(my_account.username, value+current);
							}
						}
						
					}
					users.grpaccount.put(group_name, hm);
				}
				//break; 
			}
			
		}
		String ar = my_account.username+"  paid  Rs."+total+"  for  "+description+"  on  "+formattedDate+"  for  friends  :"+members;
		group.group_details.add(ar);
		
		
		
	}
}
