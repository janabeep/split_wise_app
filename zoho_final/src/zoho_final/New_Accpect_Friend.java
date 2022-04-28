package zoho_final;

import java.util.*;


public class New_Accpect_Friend {

	public void accpect(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		Scanner in = new Scanner(System.in);
		if(my_account.waitfriends.isEmpty()) {
			System.out.println("					You Dont Have Any Friends Request");
			return;
		}
		// Wait Friends List
		System.out.println("s.no   username");
		for(int i=0;i<my_account.waitfriends.size();i++) {
			System.out.println((i+1)+"      "+my_account.waitfriends.get(i));
		}
		// 					Get Friend
		int op =0;
		while(true) {
		System.out.println("					Enter 1 to Accpect Friend Request");
		System.out.println("					Enter 2 to Reject Friend Request");
		String opp;
		try {
			opp = in.nextLine();
			if(opp.equals("*")) {return;}
			op = Integer.parseInt(opp);
			
			if(op==1||op==2) {
				break;
			}
			else {
				System.out.println("Enter Number 1 or 2");
			}
		}
		catch(Exception e) {
			System.out.println("					Enter Number");
		}
		}
		
		if(op==1) {
		while(true) {
			int n=0;
			
			while(true) {
			System.out.println("Enter s.no to 'Accpect' friends or Enter 0 to goback");
			String chk =in.nextLine();
			
			try {
				 n = Integer.parseInt(chk);	
				 break;
			}
			catch(Exception e) {
				System.out.println("Enter  A  Number");
			}
			}
			
			if(n==0) {
				break;
			}
			else if(0<n&&n<=my_account.waitfriends.size()) {
				my_account.notify--;
				String name = my_account.waitfriends.get(n-1);
				my_account.waitfriends.remove(name);
				my_account.friends.add(name);
				Account_Details account = all_account2.get(name);
				account.friends.add(my_account.username);
				System.out.println();
				System.out.println(				name + " added  ");
				System.out.println();
				break;
			}
			else {
				System.out.println("Please enter correct number");
				System.out.println();	
			}
			}
		}
		else if(op==2) {
			while(true) {
				int n=0;
				while(true) {
				System.out.println("Enter s.no to 'Reject' friends or Enter 0 to goback");
				String chk =in.nextLine();
				try {
					 n = Integer.parseInt(chk);	
					 break;
				}
				catch(Exception e) {
					System.out.println("Enter  A  Number");
				}
				}
				if(n==0) {break;}
				else if(0<n&&n<=my_account.waitfriends.size()) {
					my_account.notify--;
					my_account.waitfriends.remove(n-1);
					System.out.println(" 							Removed ");
					System.out.println();
					break;
				}
				else {
					System.out.println("                           Enter Correct Number");
					System.out.println();
				}
				
			}
		}
	}

}
