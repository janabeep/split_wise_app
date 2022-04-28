package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class New_Group_Split_Equal {

	public void splitequal(Account_Details my_account, HashMap<String, Account_Details> all_account2, New_Group group,
			double money, String description) {
			Scanner in = new Scanner(System.in);
			
			LocalDateTime myDateObj = LocalDateTime.now();  
			 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
			 String formattedDate = myDateObj.format(myFormatObj); 
			 
			 ArrayList<String>list_of_member = new ArrayList<>();
				String members=" ";
				 System.out.println();
				 System.out.println("					Group Members");
				System.out.println();
				aa:while(true) {
					
				for(String u : group.member_name) {
					if(list_of_member.isEmpty()) {
					if(!u.equals(my_account.username)) {
						System.out.println("						"+u);
						System.out.println();
					}
					}
					else {
						if(!u.equals(my_account.username) && !list_of_member.contains(u)) {
							System.out.println("						"+u);
							System.out.println();
						}
					}
				}
				

					System.out.println("							Enter  Friend Username");
					System.out.println("							Enter '!' to Stop Adding ");
					System.out.println();
					String ss ="";
					int noo=0;
					boolean f=true;
					while(f) {
						 ss=in.nextLine();
						 if(ss.equals("*")) {return;}
						 if(ss.equals("!")) {
							 break aa;
						 }
						 if(!group.member_name.contains(ss)) {
								System.out.println("					Enter Name From The Above List");
								System.out.println();
							}
						 else if(!list_of_member.contains(ss)) {
							 list_of_member.add(ss);
							 members = members + ss+" ";
							 break;
						 }
						 else {
							 System.out.println("			Already Added");
							 break;
						 }
					}	
				}
				double individual_spent = money/(list_of_member.size()+1);
				String primarydet ="  "+formattedDate+"  "+my_account.username+"  paid  "+String.format("%.2f", money)+" Rs.  for  "+description+"  Friends:  "+members;
				String det1="  "+formattedDate+"  "+my_account.username+"  paid  "+String.format("%.2f", individual_spent)+" Rs.  for  "+description;
				group.Detail.add(primarydet);
				for(int i=0;i<list_of_member.size();i++) {
					String friendname = list_of_member.get(i);
					Account_Details friend = all_account2.get(friendname);
					New_Group_Account my = my_account.Group_Account.get(group.grpid);
					New_Group_Account fri = friend.Group_Account.get(group.grpid);
					if(i==0) {
					my.det.add(primarydet);
					}
					fri.det.add(det1);
					if(!fri.acc.containsKey(my_account.username)) {
						fri.acc.put(my_account.username, individual_spent);
						my.acc.put(friendname, -1*individual_spent);
					}
					else {
						double current = my.acc.get(friendname);
						if(current <=0.00) {
							double val = fri.acc.get(my_account.username);
							fri.acc.put(my_account.username, val+individual_spent);
							friend.Group_Account.put(group.grpid, fri);
							my.acc.put(friendname, -1* (val+individual_spent) );
							//my_account.Group_Account.put(group.grpid, my);
						}
						else if(current >0.00) {
							current = current- individual_spent;
							if(current>=0.00) {
								my.acc.put(friendname, current);
								fri.acc.put(my_account.username, -1*current);
							//my_account.Group_Account.put(group.grpid, my);
								//friend.Group_Account.put(group.grpid, fri);
							}
							else if(current<0){
								current = current*(-1);
								my.acc.put(friendname, -1*current);
								fri.acc.put(my_account.username, current);
								//friend.Group_Account.put(group.grpid, fri);
								//my_account.Group_Account.put(group.grpid, my);
							}
						}
					}
					
				}
				System.out.println("					SUCCESSFULLY  ADDED");
				

	}

}
