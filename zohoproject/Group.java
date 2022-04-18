package zohoproject;
import java.util.*;

public class Group {
	
		String grp_name;
	 ArrayList<user> members;
	 ArrayList<String> member_name = new ArrayList<>();
	 int grp_size;
	ArrayList<String> group_details;
	
	String usernameg;

	
	
	public  Group(String grp_name, String username,ArrayList<user> grp_mem,int grpsi) {
		this.usernameg = username;
		this.grp_name=grp_name;
		members = new ArrayList<>();	
		for(user u : grp_mem) {
			members.add(u);
		}
		for(user ug : members) {
			member_name.add(ug.username);
		}
		grp_size = grpsi;
		group_details = new ArrayList<>();
	}
	
	// @edited
//	void addMember(user u) {
//		if(members == null) {
//			members = new ArrayList();
//		}
//		members.add(u);
//	}
	
//	public  void setgrpdet(ArrayList<user> grp_mem,int grpsi) {
//		members = new ArrayList<>(grp_mem);	
//		grp_size = grpsi;
//	}
	
}
