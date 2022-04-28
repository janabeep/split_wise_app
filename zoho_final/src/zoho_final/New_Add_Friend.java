package zoho_final;

import java.util.*;


public class New_Add_Friend {

	public void add(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your friend username");
		String name = in.nextLine();
		if(name.equals("*")) {return ;}
		if(name.equals(my_account.username)) {
			System.out.println("You Should Not Give Your Username Here !!");
			return;
		}
		System.out.println();
		if(all_account2.containsKey(name)) {
			Account_Details tt = all_account2.get(name);
				tt.notify++;
				tt.waitfriends.add(my_account.username);
				System.out.println("Friend Request Sent");
		}
		else {
			System.out.println("User not found");
			System.out.println();
		}
		
	}

}
