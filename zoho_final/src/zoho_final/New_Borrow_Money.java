package zoho_final;

import java.util.Map;

public class New_Borrow_Money {
	
	public void borrow(Account_Details my_account) {
		System.out.println();
		if(my_account.my_accounts.isEmpty()) {
			System.out.println("				You Didnt Borrowed ");
			return;
		}
		int chk=0;
		for(Map.Entry m : my_account.my_accounts.entrySet()){    
		   double n = (double) m.getValue();
		   if(n>0) {
			   chk++;
			   System.out.println("You  borrowed "+String.format("%.2f", m.getValue())+".Rs from "+m.getKey());
		   }
		   } 
		if(chk==0) {System.out.println("			No money borrow");};
		
	}

}
