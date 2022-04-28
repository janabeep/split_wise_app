package zoho_final;

import java.util.*;


public class New_Group {

	static int id=0;
	int grpid;
	String grp_name;
	ArrayList<Account_Details> members;
	ArrayList<String> member_name ;
	HashMap<String,Account_Details> member;
	int grp_size;
	//ArrayList<String> group_details;
	int Accommodation,Food,Travel,Others;
	String usernameg;
	ArrayList<String> Detail;
	double totalmoney;
	
	public  New_Group(String grp_name, String username,ArrayList<Account_Details> grp_mem,int grpsi,HashMap<String,Account_Details> member) {
		id = id+1;//edited
		grpid=id;
		Accommodation=0;
		Food=0;
		Travel=0;
		Others=0;
		totalmoney=0;
		member_name = new ArrayList<>();
		Detail = new ArrayList<>();
		this.usernameg = username;
		this.grp_name=grp_name;
		members = new ArrayList<>(grp_mem);	
		member = new HashMap<>();
		this.member = new HashMap<>(member);
		for(Account_Details ug : members) {
			member_name.add(ug.username);
		}
		grp_size = grpsi;
		//group_details = new ArrayList<>();
	}
}
