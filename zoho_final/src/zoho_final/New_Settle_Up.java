package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class New_Settle_Up {

	Scanner in = new Scanner(System.in);
	public void settleup(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		System.out.println();
		if(my_account.my_accounts.isEmpty()) {
			System.out.println("				You didnt start any transaction");
			return;
		}
		int chk=0;
		for(Map.Entry m : my_account.my_accounts.entrySet()){    
			   double n =  (double) m.getValue();
			   if(n>0.00) {
				   chk++;
				   System.out.println("You  borrowed "+String.format("%.2f", m.getValue())+".Rs from "+m.getKey());
			   }
		}
			if(chk==0) {System.out.println("				No money borrow");
			return;}
			
			boolean fl=true;
			System.out.println();
			System.out.println("Enter name of the person to settle up amount");
			System.out.println();
			String s = in.nextLine();
			if(s.equals("*")) {return ;}
			while(fl) {
			if(my_account.my_accounts.containsKey(s)) {
				settle(my_account,s,all_account2);
				fl=false;
			}
			else {
				System.out.println("Enter name from given list");
				
			}
		}
		
	}
	private void settle(Account_Details my_account, String s, HashMap<String, Account_Details> all_account2) {
		System.out.println();
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  
		 
		 double val = (double)my_account.my_accounts.get(s);
		 
			double amt= 0.00;
			String amt1 ="";
			while(true) {
				System.out.println("Enter amount ");
				try {
					amt1 = in.nextLine();
					amt = Double.parseDouble(amt1);
					break;
				}
				catch(Exception e) {
					System.out.println("		Enter Correct Amount  ");
				}
			}
			if((val-amt)>=0.00) {
				my_account.my_accounts.put(s, val-amt);
				
					Account_Details tt = all_account2.get(s);
						tt.my_accounts.put(my_account.username, -1*(val-amt));
					//my_account.my_accounts_details2.add(new Activities1(2,formattedDate,"Settled amount",Double.toString(amt),tt.username));
					
					Activities2 obj = new Activities2(2,formattedDate,"Settlement",String.format("%.2f", amt)," "+Double.toString(amt)+" ",my_account.username,"Settlement"," "+my_account.username+" ",tt.username);
					my_account.my_accounts_details3.add(obj);
					
					
					
					//tt.my_accounts_details2.add(new Activities1(3,formattedDate," Settled amount",Double.toString(amt),my_account.username));
					
					Activities2 obj1 = new Activities2(2,formattedDate,"Settlement",String.format("%.2f", amt)," "+Double.toString(amt)+" ",my_account.username,"Settlement"," "+my_account.username+" ",tt.username);
					tt.my_accounts_details3.add(obj1);
				
			}
			else if((val-amt)<=0.00 ) {
				System.out.println();
				System.out.println("Entered amount is higher than the borrowed amount");
				System.out.println("amount less than or equal to borrowed amount is accpcted");
			}
			
	}

}
