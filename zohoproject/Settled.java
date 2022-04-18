package zohoproject;

import java.util.*;

import java.util.Map;

public class Settled {
	Scanner in = new Scanner(System.in);
	
	public void settle(List<Signup> allaccount, Signup myaccount) {
		if(myaccount.my_accounts.isEmpty()&&myaccount.unknown_accounts.isEmpty()) {
			System.out.println("You didnt start any transaction");
			return;
		}
		else {
			HashMap<String,Double> hm1 = new HashMap<String,Double>();
			HashMap<String,Double> hm2 = new HashMap<String,Double>();
			if(!myaccount.my_accounts.isEmpty()) {
	int chkk=0;
	for(Signup account :allaccount) {
		
		for(Map.Entry m : account.my_accounts.entrySet()){    
		   double n = (double) m.getValue();
		   String st = (String)m.getKey();
		   if(!(n==0) && st.equals(myaccount.username)) {
			   chkk++;
			   hm1.put(account.username,n);
			   System.out.println("You lend "+String.format("%.2f", m.getValue())+".Rs to "+account.username);
		   }
		   } 
		
	}
	if(chkk==0) {System.out.println("No money lend");};
		}
				if(!myaccount.unknown_account_details.isEmpty()) {
				
				for(Map.Entry m: myaccount.unknown_accounts.entrySet()) {
					double n = (double) m.getValue();
					
					 if(!(n==0) ) {
						 hm2.put((String)m.getKey(), n);
						   System.out.println("You lend "+String.format("%.2f", m.getValue())+".Rs to "+m.getKey());
					 }
				}
			}
			System.out.println("Enter name from list  ");
			String name = in.nextLine();
			if(hm1.containsKey(name)) {
				System.out.println("Enter amount ");
				double amount = Double.parseDouble(in.nextLine());
				if(amount>(double)hm1.get(name)) {
					double balance = amount - (double)hm1.get(name);
					myaccount.my_accounts.put(name, balance);
					for(Signup account : allaccount) {
						if(account.username.equals(name) ){
							account.my_accounts.put(myaccount.username, 0.00);
							break;
						}
					}
				}
				else {
					double balance = (double)hm1.get(name) - amount;
					for(Signup d : allaccount) {
						if(d.username.equals(name) ){
							d.my_accounts.put(myaccount.username, balance);
							break;
						}
					}
				}
			}
			else if(hm2.containsKey(name)) {
				System.out.println("Enter amount");
				double amount = Double.parseDouble(in.nextLine());
				if(amount>(double)hm2.get(name)) {
					System.out.println("Sorry givem amount is larger than balance");
				}
				else {
					double balance = (double)hm2.get(name) - amount;
					myaccount.unknown_accounts.put(name, balance);
				}
			}
			else {
				System.out.println("Wrong name");
			}
	}
	
		
	}
}
