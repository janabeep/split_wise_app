package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class New_Group_Settle_Up {

	Scanner in = new Scanner(System.in);
	
	public void grpsettleup(Account_Details my_account, HashMap<String, Account_Details> all_account2,
			New_Group group) {
		New_Group_Account my = my_account.Group_Account.get(group.grpid);
		if(my.acc.isEmpty()) {
			System.out.println("				You didnt start any transaction");
			System.out.println();
			return;
		}
		else {
			int chk =0;
			for(Map.Entry m : my.acc.entrySet()) {
				if((double)m.getValue()>0.00) {
					chk++;
					System.out.println("  You  Borrowed  "+String.format("%.2f", m.getValue())+" Rs.  from  "+m.getKey());
					System.out.println();
				}
			}
			if(chk==0) {System.out.println("		All things are settled up"); return;}
			System.out.println();
			while(true) {
			System.out.println("Enter name of the person to settle up amount");
			System.out.println();
			String friend = in.nextLine();
			if(my.acc.containsKey(friend)) {
				
				Account_Details friendacc = all_account2.get(friend);
				New_Group_Account fri = friendacc.Group_Account.get(group.grpid);
					
				LocalDateTime myDateObj = LocalDateTime.now();  
				 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
				 String formattedDate = myDateObj.format(myFormatObj);  
				 
				 double val = (double)my.acc.get(friend);
				 
				double amt =0;
				while(true) {
					System.out.println("Enter amount ");
					try {
						amt = Double.parseDouble(in.nextLine());
						break;
					}
					catch(Exception e) {
						System.out.println("	Enter Correct Amount  ");
					}
				}
				
				String primarydet = "  "+formattedDate+"  "+my_account.username+"  paid  "+String.format("%.2f",amt)+"  to  "+friend;
				group.Detail.add(primarydet);
				fri.det.add(primarydet);
				my.det.add(primarydet);
				if(val>amt) {
					double cur = val-amt;
					my.acc.put(friend, cur);
					fri.acc.put(my_account.username, -1*cur);
				}
				else {
					double cur = amt-val;
					my.acc.put(friend, -1*cur);
					fri.acc.put(my_account.username, cur);
				}
				
				break;
			}
			else {
				System.out.println("Enter name from above list");
			}
			
		}
		}

		
	}

}
