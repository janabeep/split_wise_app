package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class New_Settled_Up {
	Scanner in = new Scanner(System.in);
	public void settledup(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj); 
		while(true) {
		System.out.println();
		System.out.println("		1. Existing Account ");
		System.out.println("		2. Unknown Account");
		System.out.println();
		
		int n =0;
		String nn="";
		while(true) {
			try {
				System.out.println("Enter Number ");
				nn = in.nextLine();
				if(nn.equals("*")) {return;}
				n = Integer.parseInt(nn);
				
				if(n==1 || n==2) {
					break;
				}
				else {
					System.out.println("Enter 1 or 2");
				}
			}
			catch(Exception e) {
				System.out.println("Enter a number");
			}
		}
		
		if(n==1) {
			while(true) {
			int chkk=0;
			HashMap<String,Double> hm1 = new HashMap<String,Double>();
			if(!my_account.my_accounts.isEmpty()) {
				for(Map.Entry m : my_account.my_accounts.entrySet()) {
					if((double)m.getValue()<0.00) {
							hm1.put((String)m.getKey(),-1*(double)m.getValue() );
							System.out.println("You lend "+String.format("%.2f", -1*(double)m.getValue())+".Rs to "+m.getKey());
					}
				}	
			}
			else {
				System.out.println("You Didnt Start Any Transaction  ");
				break;
			}
			if(hm1.isEmpty()) {
				System.out.println("No Money Lend To Existing User");
				break;
			}
			System.out.println("Enter name from list  ");
			String name = in.nextLine();
			if(name.equals("*")) {return ;}
			if(hm1.containsKey(name)) {
				System.out.println("Enter amount ");
				double amount = Double.parseDouble(in.nextLine());
				//my_account.my_accounts_details2.add(new Activities1(3, formattedDate,"", Double.toString(amount), name));
				
				
				Activities2 obj = new Activities2(3,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
				my_account.my_accounts_details3.add(obj);
				

				
				if(amount>(double)hm1.get(name)) {
					double balance = amount - (double)hm1.get(name);
					my_account.my_accounts.put(name, balance);
					Account_Details account = all_account2.get(name);
					account.my_accounts.put(my_account.username, -1*balance);
					//account.my_accounts_details2.add(new Activities1(2,formattedDate,"",Double.toString(amount),my_account.username));
					
					Activities2 obj1 = new Activities2(2,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
					account.my_accounts_details3.add(obj1);
					
					
					System.out.println("					SUCCESSFULLY  UPDATED   ");
					
					break;
				}
				else {
					double balance = (double)hm1.get(name) - amount;
					Account_Details d = all_account2.get(name) ;
					d.my_accounts.put(my_account.username, balance);
					my_account.my_accounts.put(name, -1*balance);
					//d.my_accounts_details2.add(new Activities1(2,formattedDate,"",Double.toString(amount),my_account.username));
					
					Activities2 obj1 = new Activities2(2,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
					d.my_accounts_details3.add(obj1);
					
					
					System.out.println("					SUCCESSFULLY  UPDATED   ");
					break;
				}
			}
			else {
				System.out.println(" 				Enter Name From List");
			}
		}
			
		}
		
		if(n==2) {
			while(true) {
			int chkk=0;
			if(!my_account.unknown_accounts.isEmpty()) {
				for(Map.Entry m: my_account.unknown_accounts.entrySet()) {
					double val = (double) m.getValue();
					 if(val>0.00) {
						 chkk++;
						   System.out.println("You lend "+String.format("%.2f", m.getValue())+".Rs to "+m.getKey());
					 }
				}
			}
			else {
				System.out.println("You Didnt Start Any Transaction ");
				break;
			}
			if(chkk==0) {System.out.println("No Money Lend To Unknown User");break;}	
			if(chkk>0) {
				System.out.println("Enter name from list  ");
				String name = in.nextLine();
				if(name.equals("*")) {return ;}
				if(my_account.unknown_accounts.containsKey(name)) {
					System.out.println("Enter amount ");
					double amount = Double.parseDouble(in.nextLine());

					if(amount>(double)my_account.unknown_accounts.get(name)) {
						System.out.println("Amount is higher than the balance money");
						System.out.println("Not Updated");
						break;
					}
					else {
						if(amount-my_account.unknown_accounts.get(name)==0) {
							my_account.unknown_accounts.put(name, 0.00);
							//my_account.my_accounts_details2.add(new Activities1(3, formattedDate,"", Double.toString(amount), name));
							
							Activities2 obj = new Activities2(3,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
							my_account.my_accounts_details3.add(obj);
							
							Activities2 obj1 = new Activities2(2,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
							ArrayList<Activities2> al = my_account.unknown_account_details3.get(name);
							al.add(obj1);
							my_account.unknown_account_details3.put(name, al);
							
							System.out.println("					SUCCESSFULLY  UPDATED   ");
							break;
						}
						else {
							my_account.unknown_accounts.put(name,my_account.unknown_accounts.get(name)-amount );
							//my_account.my_accounts_details2.add(new Activities1(3, formattedDate,"", Double.toString(amount), name));

							Activities2 obj = new Activities2(3,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
							my_account.my_accounts_details3.add(obj);
							
							Activities2 obj1 = new Activities2(3,formattedDate,"Settlement",String.format("%.2f", amount)," "+Double.toString(amount)+" ",my_account.username,"Settlement",name," "+my_account.username+" ");
							ArrayList<Activities2> al = my_account.unknown_account_details3.get(name);
							al.add(obj1);
							my_account.unknown_account_details3.put(name,al);
							
							System.out.println("					SUCCESSFULLY  UPDATED   ");
							break;
						}
					}
				}
			}
		}
//			ArrayList<String> al = new ArrayList<>();
//			for(Map.Entry d : my_account.unknown_accounts.entrySet()) {
//				if((double)d.getValue()==0) {
//					al.add((String)d.getKey());
//				}
//			}
//			for(String s : al) {
//				my_account.unknown_accounts.remove(s);
//			}
		}
		}
	}

}
