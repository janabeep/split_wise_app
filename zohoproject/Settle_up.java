package zohoproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Settle_up {
	
	Scanner in = new Scanner(System.in);
	public boolean settle(Signup t,HashMap<String,Double> hm,String s,List<Signup> l) {
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  
		double val =hm.get(s);
		
		System.out.println("Enter amount ");
		double amt= Double.parseDouble(in.nextLine());
		if((val-amt)==0 ) {
			t.my_accounts.put(s, 0.00);
			
			for(Signup tt:l) {
				if(tt.username.equals(s)) {
					String ar[] = {"2",formattedDate,"Settled amount",Double.toString(amt),tt.username};
					t.my_accounts_details.add(ar);
					String arr[] = {"3",formattedDate," Settled amount",Double.toString(amt),t.username};
					tt.my_accounts_details.add(arr);
					break;
				}
			}
		}
		else if(val-amt >0) {
			t.my_accounts.put(s, val-amt);
			String ar[] = {"2",formattedDate,"Settled amount",Double.toString(amt),s};
			t.my_accounts_details.add(ar);
			String arr[] = {"3",formattedDate," Settled amount",Double.toString(amt),t.username};
			for(Signup tt:l) {
				if(tt.username.equals(s)) {
					tt.my_accounts_details.add(arr);
					break;
				}
			}
		}
		else if(val -amt <0.00) {
			System.out.println("Entered amount is higher than the borrowed amount");
			System.out.println("amount less than or equal to borrowed amount is accpcted");
			return true;
		}
		return false;
	
	}

	public void settleup(Signup myaccount,List<Signup> allaccount) {
		
		if(myaccount.my_accounts.isEmpty()) {
			System.out.println("You didnt start any transaction");
			return;
		}
		int chk=0;
		HashMap<String,Double> hm = new HashMap<String,Double>();
		for(Map.Entry m : myaccount.my_accounts.entrySet()){    
		   double n =  (double) m.getValue();
		   if(!(n==0.00)) {
			   chk++;
			   System.out.println("You  borrowed "+String.format("%.2f", m.getValue())+".Rs from "+m.getKey());
			   hm.put((String)m.getKey(), (double)m.getValue());
		   }
		   }
		if(chk==0) {System.out.println("No money borrow");
		return;};
		boolean fl=true;
		System.out.println("Enter name of the person to settle up amount");
		String s = in.nextLine();
		while(fl) {
		if(hm.containsKey(s)) {
			fl = settle(myaccount,hm,s,allaccount);
			fl=false;
		}
		else {
			System.out.println("Enter name from given list");
			
		}
		}
	}
}
