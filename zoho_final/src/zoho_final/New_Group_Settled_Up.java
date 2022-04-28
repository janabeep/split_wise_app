package zoho_final;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class New_Group_Settled_Up {

	public void gepsettledup(Account_Details my_account, HashMap<String, Account_Details> all_account2,
			New_Group group) {
		Scanner in = new Scanner(System.in);
		
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj); 
		 
		 while(true) {
			 
			 HashMap<String,Double> hm  = new HashMap<>();
				New_Group_Account my = my_account.Group_Account.get(group.grpid);
				if(my.acc.isEmpty()) {
					System.out.println("You Didnt Start Any Transaction");
					break;
				}
				else {
					for(Map.Entry d : my.acc.entrySet()) {
						if((double)d.getValue()<=0.00) {
								hm.put((String)d.getKey(), -1*(double)d.getValue());
								System.out.println("You  Lend  "+String.format("%.2f", -1*(double)d.getValue())+" Rs.  to  "+d.getKey() );
						}
					
					}
				}
				if(hm.isEmpty()) {
					System.out.println("No Money Lend To Any User ");
					break;
				}
				else {
					String friendname;
					while(true) {
					System.out.println("Enter Name Of Your Friend From The Above List or Enter * to go back");
					 friendname = in.nextLine();
					 if(friendname.equals("*")) {return;}
					if(hm.containsKey(friendname)) {
						break;
					}
					else {
						System.out.println("Enter  Correct  Name");
					}
				}
					
					double amt;
					while(true) {
						try {
							System.out.println("Enter Amount ");
							amt = Double.parseDouble(in.nextLine());
							if(amt>0.00) {
								break;
							}
							else {
								System.out.println("Enter  a  positive number ");
							}
						}
						catch(Exception e) {
							System.out.println("Enter a number");
						}
					}
					String primarydet = "  "+formattedDate+"  "+friendname+"  paid  "+String.format("%.2f", amt)+" Rs.  to  "+my_account.username;
					group.Detail.add(primarydet);
					if(amt>=hm.get(friendname)) {
						double balance = amt - (double)hm.get(friendname);
						my.acc.put(friendname, balance);
						Account_Details friend = all_account2.get(friendname);
						New_Group_Account fri= friend.Group_Account.get(group.grpid);
						fri.acc.put(my_account.username, -1*balance);
						my.det.add(primarydet);
						fri.det.add(primarydet);
						System.out.println("				SUCCESSFULLY ADDED");
						
					}
					else {
						double balance = (double)hm.get(friendname) - amt;
						Account_Details friend = all_account2.get(friendname);
						New_Group_Account fri= friend.Group_Account.get(group.grpid);
						fri.acc.put(my_account.username, balance);
						my.acc.put(friendname,-1*balance);
						my.det.add(primarydet);
						fri.det.add(primarydet);
						System.out.println("				SUCCESSFULLY ADDED");
						
					}
					
				}
		 }
		
	}

}
