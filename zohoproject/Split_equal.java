package zohoproject;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Split_equal {


	 
	public  void eshare(List<Signup> all_account, Signup my_account, double money_spend,String description) {
		Scanner in = new Scanner(System.in);
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  
		 String regex1 = "^[A-Za-z]\\w{1,29}$";
		    Pattern p1 = Pattern.compile(regex1);
		    
		String members=" ";
		ArrayList<String> list_of_member = new ArrayList<>();
		
		aa:while(true) {
			System.out.println("Enter the person username");
			System.out.println("Enter '!' to Stop Adding ");
			String ss ="";
			int noo=0;
			boolean f=true;
			while(f) {
			
				 ss=in.nextLine();
				 if(ss.equals("!")) {
					 break aa;
				 }
				 Matcher m1 = p1.matcher(ss);
			if(m1.matches()) {
				list_of_member.add(ss);
				break;
			}
			else {
				System.out.println("Enter correct username");
			}
			}	
		}
		double individual_spent = money_spend/(list_of_member.size()+1);
		for(int i=0;i<list_of_member.size();i++) {
			int ck=0;
			for(Signup tt : all_account) {
				if(tt.username.equals(list_of_member.get(i))) {
					members = members + list_of_member.get(i)+" ";
					String arrr[] = {"1",formattedDate,description,Double.toString(individual_spent),my_account.username};
					tt.my_accounts_details.add(arrr);

					if(!my_account.my_accounts.containsKey(list_of_member.get(i))) {		
						my_account.my_accounts.put(list_of_member.get(i), 0.00);
						tt.my_accounts.put(my_account.username, individual_spent);
					}
					else {
						
						double balance = (double) my_account.my_accounts.get(tt.username);
						
						if(balance==0.00) {
							double j = (double) tt.my_accounts.get(my_account.username);
							tt.my_accounts.put(my_account.username, individual_spent+j);
						}
						else if(balance>0) {
							double current_amt = balance-individual_spent;
							if(current_amt>=0) {
								my_account.my_accounts.put(tt.username, current_amt);
							}
							else if(current_amt<0) {
								current_amt = current_amt*(-1);
								my_account.my_accounts.put(tt.username, 0.00);
								tt.my_accounts.put(my_account.username, current_amt);
							}
						}
					}
					ck++;
					break;
				}
			}
			if(ck==0) {
				if(my_account.unknown_accounts.containsKey(list_of_member.get(i))) {
					members = members + list_of_member.get(i)+" ";
					double n = (double)my_account.unknown_accounts.get(list_of_member.get(i));
					my_account.unknown_accounts.put(list_of_member.get(i),n+individual_spent);
					String ar[]= {"0",formattedDate,description,Double.toString(money_spend),list_of_member.get(i)};
					my_account.unknown_account_details.add(ar);
				}
				else {
				System.out.println("Notifications !!("+list_of_member.get(i)+" doesnt have account.  )");
				
					members = members + list_of_member.get(i)+" ";
					my_account.unknown_accounts.put(list_of_member.get(i), individual_spent);
					String ar[]= {"0",formattedDate,description,Double.toString(money_spend),list_of_member.get(i)};
					my_account.unknown_account_details.add(ar);
				
			}
		}

		}
 
		String arr[] = {"0",formattedDate,description,Double.toString(money_spend),members};
		
		my_account.my_accounts_details.add(arr);

		
		System.out.println("Added successfully");
	
		
	}

}
