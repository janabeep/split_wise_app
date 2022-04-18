package zohoproject;

import java.util.*;
public class Accp_friends {
Scanner in = new Scanner(System.in);
	public void accepct(Signup myaccount,List<Signup> all_account) {
		if(myaccount.waitfriends.isEmpty()) {
			System.out.println("You Dont Have Any Friends Request");
			return;
		}
		HashMap<Integer,user> hm = new HashMap<>();
		System.out.println("s.no   username");
		for(int i=0;i<myaccount.waitfriends.size();i++) {
			System.out.println((i+1)+"      "+myaccount.waitfriends.get(i).username);
			hm.put(i+1,myaccount.waitfriends.get(i));
		}
		while(true) {
		int n=0;
		
		while(true) {
		System.out.println("Enter s.no to accpect friends or Enter 0 to goback");
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
		else if(hm.containsKey(n)) {
			myaccount.friendnotify--;
			user name = hm.get(n);
			myaccount.waitfriends.remove(name);
			myaccount.friends.add(name);
			for(Signup account : all_account) {
				if(account.username.equals(name.username)) {
					account.friends.add(myaccount);
					break;
				}
				
			}
			System.out.println();
			System.out.println(name.username + " added  ");
			System.out.println();
			break;
		}
		else {
			System.out.println("Please enter correct number");
			System.out.println();	
		}
		}
	}
}
