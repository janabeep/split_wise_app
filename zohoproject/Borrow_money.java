package zohoproject;
import java.util.*;
public class Borrow_money {

	public void borrow(Signup myaccount) {
		if(myaccount.my_accounts.isEmpty()) {
			System.out.println("You didnt start any transaction");
			return;
		}
		int chk=0;
		for(Map.Entry m : myaccount.my_accounts.entrySet()){    
		   double n = (double) m.getValue();
		   if(!(n==0)) {
			   chk++;
			   System.out.println("You  borrowed "+String.format("%.2f", m.getValue())+".Rs from "+m.getKey());
		   }
		   } 
		if(chk==0) {System.out.println("No money borrow");};
		
	}

}
