package zohoproject;
import java.util.*;
public class Lend_money {
	public void lend(List<Signup> allaccount,Signup myaccount) {
		if(myaccount.my_accounts.isEmpty()&&myaccount.unknown_accounts.isEmpty()) {
			System.out.println("You didnt start any transaction");
			return;
		}
		else {
			int chk=0;
			if(!myaccount.my_accounts.isEmpty()) {
				
				for(Signup account :allaccount) {
		
					for(Map.Entry m : account.my_accounts.entrySet()){    
						double balance = (double) m.getValue();
						String names = (String)m.getKey();
						if(!(balance==0.00) && names.equals(myaccount.username)) {
								chk++;
								System.out.println("You lend "+String.format("%.2f", m.getValue())+".Rs to "+account.username);
						}
					}		 
		
				}
	
			}
			 if(!myaccount.unknown_account_details.isEmpty()) {
				
				for(Map.Entry m: myaccount.unknown_accounts.entrySet()) {
					double balance = (double) m.getValue();
					 if(!(balance==0.00) ) {
						 chk++;
						   System.out.println("You lend "+String.format("%.2f", m.getValue())+".Rs to "+m.getKey());
					 }
				}
			}
			if(chk==0) {System.out.println("No Money Lend");} 
	}
	}
}
