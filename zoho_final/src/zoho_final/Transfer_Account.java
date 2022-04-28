package zoho_final;

import java.util.*;

public class Transfer_Account {
	public void transfer(HashMap<String,Account_Details> all_account2, Account_Details my_account, String name,String unknownname ) {
		
		double balance = my_account.unknown_accounts.get(unknownname);
		my_account.unknown_accounts.remove(unknownname);
		Account_Details newuser =	all_account2.get(name);
	//	newuser.my_accounts.put(my_account.username, balance);
		if(newuser.my_accounts.containsKey(my_account.username)) {
			double val = newuser.my_accounts.get(my_account.username);
			if(val<=0.00) {
				val = val+ balance;
				newuser.my_accounts.put(my_account.username, val);
				my_account.my_accounts.put(newuser.username, -1*val);
			}
			else if(val>0.00) {
				val = val + balance;
				newuser.my_accounts.put(my_account.username, val);
				my_account.my_accounts.put(newuser.username, -1*val);
			}
		}
		else {
			newuser.my_accounts.put(my_account.username, balance);
			my_account.my_accounts.put(newuser.username, (-1)*balance);
		}
		
		ArrayList<Activities2> al = my_account.unknown_account_details3.get(unknownname);
		my_account.unknown_account_details3.remove(unknownname);
		newuser.my_accounts_details3.addAll(al);
		newuser.notify--;
		newuser.transfer.remove(my_account.username);
		my_account.my_accounts_details3.add(new Activities2(99," "+unknownname+"  moved  to  account  "+name));
		newuser.my_accounts_details3.add(new Activities2(99," "+my_account.username+"  moved  a  unknownaccount  "+unknownname+"  to  you"));
		System.out.println("Successfully Transfereds");
	}

}
