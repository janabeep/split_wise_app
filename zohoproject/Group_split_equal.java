package zohoproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Group_split_equal {

	public void eshare(List<Signup> all_account,Signup my_account,String group_name,double money_spend,String des,Signup group_admin, Group group){
		Scanner in = new Scanner(System.in);
//		System.out.println("check 4");
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj); 
		 ArrayList<String>list_of_member = new ArrayList<>();
			String members=" ";
		 System.out.println();
		 System.out.println("Group Members");
//		 System.out.println("check 5");
			System.out.println();
			for(String u : group.member_name) {
				System.out.println();
				System.out.println(u);
				System.out.println();
			}
			aa:while(true) {
//				System.out.println("check 6");
				System.out.println("Enter the person username");
				System.out.println("Enter '!' to Stop Adding ");
				String ss ="";
				int noo=0;
				boolean f=true;
				while(f) {
				
					 ss=in.nextLine();
					 if(ss.equals("!")) {
						 break aa;
					 }
					 if(!group.member_name.contains(ss)) {
							System.out.println("Enter Name From The Above List");
						}
					 else {
						 list_of_member.add(ss);
						 members = members + ss+" ";
						 break;
					 }
				}	
			}
//			System.out.println("check 7");
			double individual_spent = money_spend/(list_of_member.size()+1);
			for(int i=0;i<list_of_member.size();i++) {
//				System.out.println("check 8");
				String friendname = list_of_member.get(i);
				for(user users : group.members) {
//					System.out.println("check 9");
//					System.out.println(group.members);
					if(users.username.equals(friendname)) {
//						System.out.println("check 10");
						HashMap<String,Double> hm = users.grpaccount.get(group_name);
						if(!hm.containsKey(my_account.username)) {
//							System.out.println("check 11 i");
							hm.put(my_account.username, individual_spent);
							HashMap<String,Double> hm1 = my_account.grpaccount.get(group_name);
							hm1.put(friendname, 0.00);
							my_account.grpaccount.put(group_name,hm1);
						}
						else {
//							System.out.println("check 11 ii");
							HashMap<String,Double> hm1 = my_account.grpaccount.get(group_name);
							double current = hm1.get(friendname);
							if(current== 0.00) {
//								System.out.println("check 11 i  i");
								double value = hm.get(my_account.username);
								hm.put(my_account.username, value+individual_spent);
							}
							else if(current>0.00) {
//								System.out.println("check 11 i  ii");
								current = current - individual_spent;
								if(current>=0.00) {
									hm1.put(friendname, current);
									my_account.grpaccount.put(group_name, hm1);
								}
								else if(current<0.00) {
//									System.out.println("check 11 i  iii");
									current = current * (-1);
									hm1.put(friendname, 0.00);
									my_account.grpaccount.put(group_name, hm1);
									double value  = hm.get(my_account.username);
									hm.put(my_account.username, value+current);
								}
							}
						}
//						System.out.println("check 12 ");
						users.grpaccount.put(group_name, hm);
						break;
					}
					
				}
			}
//		 for(int i=0;i<list_of_member;i++) {
//			 System.out.println("Enter name");
//			 String nam = in.nextLine();
//			 if(gg.member_name.contains(nam)) {
//				 mem = mem+nam+" ";
//				 for(user ug : gg.members) {
//					 if(ug.username.equals(nam)) {
//						 for(Map.Entry tt : ug.grpaccount.entrySet()) {
//								String sa = (String)tt.getKey();
//								if(sa.equals(name)) {
//									HashMap<String,Double> hm= (HashMap<String, Double>) tt.getValue();
//									if(!hm.containsKey(t.username)) {
//										hm.put(t.username, amt);
//										for(Map.Entry ttt : t.grpaccount.entrySet()) {
//											String saa = (String)ttt.getKey();
//											if(saa.equals(name)) {
//												HashMap<String,Double> hm1= (HashMap<String, Double>) ttt.getValue();
//												hm1.put(ug.username, 0.00);
//												t.grpaccount.put(name, hm1);
//												break;
//											}
//										}
//									
//									}
//									else {
////									for(Map.Entry dd : hm.entrySet()) {
////										if(dd.getKey().equals(t.username)) {
////											double cur = (double)dd.getValue();
////											if(cur==0.00) {
////												hm.put(t.username, amt);
////											}
////											else if(cur>0.00) {
////												cur = cur -amt;
////												if(cur>=0.00) {
////													hm.put(t.username, cur);
////												}
////												else if(cur<0.00){
////													cur = cur*(-1);
////													hm.put(t.username, 0.00);
////													for(Map.Entry ttt : t.grpaccount.entrySet()) {
////														String saa = (String)ttt.getKey();
////														if(saa.equals(name)) {
////															HashMap<String,Double> hm1= (HashMap<String, Double>) ttt.getValue();
////															hm1.put(ug.username, cur);
////															t.grpaccount.put(name, hm1);
////															break;
////														}
////													}
////												}
////											}
////											break;
////										}
////									}
//										for(Map.Entry ttt : t.grpaccount.entrySet()) {
//											String saa = (String)ttt.getKey();
//											if(saa.equals(name)) {
//												HashMap<String,Double> hm1= (HashMap<String, Double>) ttt.getValue();
//												for(Map.Entry d: hm1.entrySet() ) {
//													if(d.getKey().equals(nam)) {
//													 double cur = (double)d.getValue();
//													 if(cur ==0.00) {
//														 double val = hm.get(t.username);
//														 hm.put(t.username, val+amt);
//													 }
//													 else if(cur>0.00) {
//														 cur = cur - amt;
//														 if(cur>=0.00) {
//															 hm1.put(nam, cur);
//															 t.grpaccount.put(name, hm1);
//														 }
//														 else if(cur<0.00) {
//															 cur = cur * (-1);
//															 hm1.put(nam, 0.00);
//															 t.grpaccount.put(name, hm1);
//															 double val = hm.get(t.username);
//															 hm.put(t.username, val+cur);
//														 }
//													 }
//													break;
//													}
//												}
//												break;
//											}
//										}
//								}
//									ug.grpaccount.put(name, hm);
//									break;
//								}
//							}
//						 break;
//					 }
//					
//				 }
//				 
//			 }
//			 else {
//				 System.out.println("Enter name from members list");
//				 i--;
//			 }
//		 }
//			System.out.println("check 13");
		 String ar  = my_account.username+"  paid  Rs."+money_spend+"  for  "+des+"  on  "+formattedDate+"  for  friends :"+members;
		 group.group_details.add(ar);
	}
}
