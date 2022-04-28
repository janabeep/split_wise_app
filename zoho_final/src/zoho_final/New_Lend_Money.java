package zoho_final;

import java.util.HashMap;
import java.util.Map;


public class New_Lend_Money {

	public void lend(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		System.out.println();
		if(my_account.my_accounts.isEmpty() && my_account.unknown_accounts.isEmpty()) {
			System.out.println("					You didnt start any transaction");
			return;
		}
		else {
			
			int chk=0;
			System.out.println("								EXISTING  ACCOUNTS ");
			System.out.println();
			if(!my_account.my_accounts.isEmpty()) {
				HashMap<String,Double> MyMap = my_account.my_accounts;
//				for(Map.Entry m : MyMap.entrySet()) {
//					if((double)m.getValue()==0.00) {
//						Account_Details account = all_account2.get((String)m.getKey());
//						if(account.my_accounts.containsKey(my_account.username) && account.my_accounts.get(my_account.username)!=0.00) {
//							chk++;
//							System.out.println("		You lend "+String.format("%.2f", account.my_accounts.get(my_account.username))+".Rs to "+account.username);
//						}
//					}
//				}
				for(Map.Entry m : MyMap.entrySet()) {
					if((double)m.getValue()<0.00) {
						chk++;
						System.out.println("		You  Lend  "+String.format("%.2f", (-1)*(double)m.getValue() )+" .Rs  to  "+m.getKey() );
					}
				}
			}
			if(chk==0) {
				System.out.println("			No Money Lend");
			}
		 chk=0;
		 System.out.println();
		 System.out.println("								UNKNOWN ACCOUNTS");
		 System.out.println();
		 if(!my_account.unknown_accounts.isEmpty()) {
				for(Map.Entry m: my_account.unknown_accounts.entrySet()) {
					double balance = (double) m.getValue();
					 if(!(balance==0.00) ) {
						 	chk++;
						   System.out.println("			You lend "+String.format("%.2f", m.getValue())+".Rs to "+m.getKey());
					 }
				}
			}
		 if(chk==0) {System.out.println("					No Money Lend");}
		 
		}
		
	}

}
