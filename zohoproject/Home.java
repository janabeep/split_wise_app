package zohoproject;
import java.util.*;

public class Home {
	Scanner in = new Scanner(System.in);
	public Home(int id,List<Signup> all_account, Signup my_account) {
		boolean f= true;
		while(f) {
		System.out.println("                                                                                                          "+my_account.username);
		System.out.println();
		System.out.println("1.   Add Your Expense");
		System.out.println("2.   View Your Activities");
		System.out.println("3.   View Money lends Details");
		System.out.println("4.   View Money Borrowed Details By Enter");
		System.out.println("5.   View Recent Activity");
		System.out.println("6.   Number Of Friend requested  = "+my_account.friendnotify);
		System.out.println("7.   Add Your Friend");
		System.out.println("8.   Show My Friends List");
		System.out.println("9.   Settle Up Your Balance");
		System.out.println("10.  Quit");
		System.out.println("11.  Settled Up Your Friends Balance");
		System.out.println("12.  Add Groups");
		System.out.println("13.  My Groups");
		System.out.println("14.  My Account Info");
		System.out.println();
		int num = 0;
		while(true) {
		try {
			System.out.println("Enter a number ");
			String s = in.nextLine();
             num = Integer.parseInt(s);
             break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a number ( 1 - 11 )");
        }
		}
		switch(num) {
		case 1:
			Add_expense obj2 =new Add_expense();
			obj2.addexpense(all_account,my_account);
			break;
		case 2:
			if(my_account.my_accounts_details.isEmpty()) {
				System.out.println("No Activities Found");
			}
			else {
			for(String ar[] : my_account.my_accounts_details) {
				if(ar[0].equals("0")) {
					System.out.println(ar[1]+"   You paid  "+ar[3]+".Rs   for   "+ar[2]+"   friends: "+ar[4]);
				}
				else if(ar[0].equals("1")){
					System.out.println(ar[1]+"   "+ar[2]+"   "+ar[4]+"  paid  "+ar[3]+".Rs   ");
				}
				else if(ar[0].equals("2")){
					System.out.println(ar[1]+"   "+ar[2]+"  "+ar[3]+"     "+ar[4]);
				}
				else if(ar[0].equals("3")) {
					System.out.println(ar[1]+"   "+ar[4]+"  "+ar[2]+"     "+ar[3]);
				}
			}
			}
			break;
		case 4:

			Borrow_money obj4 = new Borrow_money();
			obj4.borrow(my_account);
			break;
		case 3:

			Lend_money obj3 = new Lend_money();
			obj3.lend(all_account,my_account);
			break;
		case 5:
			if(my_account.my_accounts_details.isEmpty()) {
				System.out.println("No Activities Found");
			}
			else {
				int i=0;
				for(String ar[] : my_account.my_accounts_details) {
					i++;
					if(ar[0].equals("0")) {
						System.out.println(ar[1]+"   "+ar[2]+"   You paid  "+ar[3]+".Rs    "+ar[4]);
					}
					else {
						System.out.println(ar[1]+"   "+ar[2]+"   "+ar[4]+" paid  "+ar[3]+".Rs   ");
					}
					if(i>5) {
						System.out.println("...........................................................");
						break;
					}
				}
			}
			break;
		case 6:
			Accp_friends obj6 = new Accp_friends();
			obj6.accepct(my_account,all_account);
			break;
		case 7:
			Add_friends obj7 = new Add_friends();
			obj7.addfriends(my_account,all_account);
			break;
		case 8:
			if(my_account.friends.isEmpty()) {
				System.out.println("You have no friends ");
				System.out.println();
			}
			else {
				for(user name : my_account.friends) {
					System.out.println(name.username);
					System.out.println();
				}
			}
			break;
		case 9:
			Settle_up obj9 = new Settle_up();
			obj9.settleup(my_account,all_account);
			break;
		case 10:
			f=false;
			break;
		case 11:
			Settled obj11 =new Settled();
			obj11.settle(all_account,my_account);
			break;
		case 12:
//			if(!t.friends.isEmpty()) {
////				Group g = null;
//			System.out.println("Enter group Name :");
//			String grp_name = in.nextLine();
//			int grp_size=0;
//			ArrayList<user> grp_mem = new ArrayList<>();
//			grp_mem.add(t);
//			for(user ss : t.friends ) {
//				System.out.println(ss.username);
//			}
//			System.out.println("Add Friends ");
//			System.out.println("Enter '!' to stop adding");
//			while(true) {
//				System.out.println("Enter name..");
//				String user = in.nextLine();
//				int chk=0;
//				if(user.equals("!")) {break;}
//				for(user ss: t.friends) {
//					if(ss.username.equals(user)) {
//						chk++;
//						if(!grp_mem.isEmpty()) {
//						if(grp_mem.contains(ss)) {
//							System.out.println("Already in group");
//							chk++;
//						}
//						}
//						 if(chk==1) {
////							 ss.myGroups.add(new Group(grp_name,ss.username));
////							grp_mem.add(ss);
////							grp_size++;
//							 
//							 // edited below
//							 if(g == null) {
//								 g = new Group(grp_name,t.username);
//								 g.addMember(ss);
//								 t.myGroups.add(g);
//								 ss.myGroups.add(g);
//							 }
//							 else {
//								 g.addMember(ss);
//								 ss.myGroups.add(g);
//							 }
//							 
//						 }
//						break;
//					}
//				}
//				if(chk==0) {System.out.println("Enter your friend names. Not others");}
//			}
////			grp_size++;
////			System.out.println("Group is succesfully created with "+grp_size+" members");
////			Group obj = new Group(grp_name,t.username);
////			obj.setgrpdet(grp_mem, grp_size);
////			t.myGroups.add(obj);
////			System.out.println();
////			System.out.println("Group Members  ");
////			System.out.println();
////			for(Group u : t.myGroups) {
////				for(user jj: u.members ) {
////					System.out.println(jj.username);
////				}
////			}
////			System.out.println();
////			for(user ut: grp_mem) {
////				for(Group ss : ut.myGroups) {
////					if(ss.grp_name.equals(grp_name)) {
////						ss.setgrpdet(grp_mem, grp_size);
////					}
////				}
////			}
////			} 
////			else {
////				System.out.println("No Friends");
////			}
////			
			if(my_account.friends.isEmpty()) {
				System.out.println("You Dont Have Any Friends");
				System.out.println("Add Friends");
			}
			else {
				boolean flag = true;
				String grp_name = "";
				while(flag) {
					int no=0;
				System.out.println("Enter Group Name");
				 grp_name = in.nextLine();
				qq:for(Signup t : all_account) {
					for(Group u : t.myGroups) {
						if(u.grp_name.equals(grp_name)) {
							System.out.println("Grp_name Already Taken");
							no++;
							break qq;
						}
					}
				}
				if(no==0) {
					flag = false;
				}
			}
				int grp_size=1;
				ArrayList<user> grp_mem = new ArrayList<>();
				grp_mem.add(my_account);
				my_account.grpaccount.put(grp_name, new HashMap<>());
				my_account.grpdetail.put(grp_name, new ArrayList<>());
				for(user name : my_account.friends) {
					System.out.println();
					System.out.println(name.username+" ");
					System.out.println();
				}
				while(true) {
					
				System.out.println("Enter names from above list ");
				System.out.println("Enter '!' to stop adding");
				String name = in.nextLine();
				if(name.equals("!")) {break;}
				
				for(user user : my_account.friends) {
					if(user.username.equals(name)) {
						if(!grp_mem.contains(user)) {
							
						user.grpaccount.put(grp_name, new HashMap<>());
						
						for(Map.Entry tt : my_account.grpaccount.entrySet()) {
							String groupname = (String)tt.getKey();
							if(groupname.equals(grp_name)) {
								HashMap<String,Double> hm= (HashMap<String, Double>) tt.getValue();
								hm.put(user.username, 0.00);
								my_account.grpaccount.put(grp_name, hm);
								break;
							}
						}
						
						for(Map.Entry tt : user.grpaccount.entrySet()) {
							String groupname = (String)tt.getKey();
							if(groupname.equals(grp_name)) {
								HashMap<String,Double> hm= (HashMap<String, Double>) tt.getValue();
								hm.put(my_account.username, 0.00);
								user.grpaccount.put(grp_name, hm);
								break;
							}
						}
						
						user.grpdetail.put(grp_name, new ArrayList<>());
						grp_mem.add(user);
						grp_size++;
						
						break;
						}
						else {
							
							System.out.println("Already in group");
						}
						break;
					}
				}
				
				}
//				Group obj = new Group(grp_name,t.username);
//				obj.setgrpdet(grp_mem,grp_size);
//				t.myGroups.add(obj);
				my_account.myGroups.add(new Group(grp_name,my_account.username,grp_mem,grp_size));
				
			}
			
			break;
		case  13:
//			if(t.myGroups.isEmpty()) {
//				System.out.println("No groups created");
//				break;
//			}
//			HashMap<String,Group> hm = new HashMap<>();
////			for(Map.Entry d : t.myGroups.entrySet()) {
////				Group st = (Group) d.getValue();
////				hm.put(st.grp_name, st);
////				System.out.println("---------------");
////				System.out.println(st.grp_name);
////				System.out.println("---------------");
////			}
//			for(Group d : t.myGroups) {
//				hm.put(d.grp_name, d);
//				System.out.println("---------------");
//				System.out.println(d.grp_name);
//				System.out.println("---------------");
//			}
//			System.out.println("Enter Group Name ");
//			String ss = in.nextLine();
//			if(hm.containsKey(ss)) {
//				opengroup obj = new opengroup();
//				obj.open(hm.get(ss),l,t.myGroups);
//			}
//			else {
//				System.out.println("No group created on the name "+ss);
//			}
			if(my_account.grpaccount.isEmpty()) {
				System.out.println("No Groups Created");
			}
			else {
				ArrayList<String> al = new ArrayList<>();
			for(Map.Entry grpname : my_account.grpaccount.entrySet()) {
				al.add((String)grpname.getKey());
				System.out.println("--------------------------");
				System.out.println(grpname.getKey());
				System.out.println("--------------------------");
			}
			System.out.println("Enter Group Name");
			String groupname = in.nextLine();
			if(al.contains(groupname)) {
				int chk=0;
				for(Signup all_acc : all_account) {
					for(Group grp : all_acc.myGroups) {
						if(grp.grp_name.equals(groupname)) {
							Open_group obj = new Open_group();
							obj.open(all_account,my_account,groupname,all_acc,grp);
							chk++;
							break;
						}
					}
					if(chk>0) {break;}
				}
				
			}
			else {
				System.out.println("Entered name is wrong");
			}
			}
			break;
		case 14:
				System.out.println();
				System.out.println();
				System.out.println(" User Name:   "+my_account.username);
				System.out.println(" Email Id   :   "+my_account.getEmail());
				System.out.println();
				System.out.println();
			break;
		default :
			System.out.println("Given input is wrong");
			break;
		}
		}
	}
	

}
