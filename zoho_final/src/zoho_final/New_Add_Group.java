package zoho_final;

import java.util.*;

public class New_Add_Group {

	public void addgrp(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		Scanner in = new Scanner(System.in);
		if(my_account.friends.isEmpty()) {
			System.out.println("								You Dont Have Any Friends");
			System.out.println("								Add Friends");
			return;
		}
		else {
			boolean flag = true;
			String grp_name = "";
			
			System.out.println("								Enter Group Name");
			 grp_name = in.nextLine();
			 if(grp_name.equals("*")) {return;}
			 int grp_size=1;
				ArrayList<Account_Details> grp_mem = new ArrayList<>();
				HashMap<String,Account_Details> member = new HashMap<>();
				member.put(my_account.username,my_account);
				grp_mem.add(my_account);
				for(String name : my_account.friends) {
					System.out.println("							"+name+" ");
					System.out.println();
				}
			 while(true) {
				 System.out.println("Enter names from above list ");
					System.out.println("Enter '!' to stop adding");
					System.out.println();
					String name = in.nextLine();
					if(name.equals("!")) {break;}
					if(all_account2.containsKey(name)) {
					Account_Details account = all_account2.get(name);
					if(my_account.friends.contains(name)) {
						if(!grp_mem.contains(account)) {
							grp_mem.add(account);
							member.put(account.username, account);
							grp_size++;
						}
						else {
							System.out.println("                 Already in group");
							System.out.println();
						}
					}
					else {
						System.out.println("						Enter Name From Above List");
						System.out.println();
					}
					}
					else {
						System.out.println("                    User Name Not Exist");
						System.out.println();
					}
			 }
			 if(grp_mem.size()<=2) {
				 System.out.println("					Atleast 3 members needed to create a group");
				 System.out.println("                   Group is Not Created");
				 return;
			 }
			 New_Group obj1 = new New_Group(grp_name,my_account.username,grp_mem,grp_size,member);

			 for(Account_Details u : grp_mem) {
				 u.my_Groups.put(obj1.grpid, obj1);
				 u.Group_Account.put(obj1.grpid, new New_Group_Account(obj1.grpid,grp_name));
			 }
			New_Group_Home obj = new New_Group_Home();
			obj.opengroup(all_account2,my_account,obj1.grp_name,obj1);
					
			}
	}

}
