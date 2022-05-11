 package group;

import java.util.*;

import non_group.Activity;
import non_group.Users;
public class Group {

	static int id = 0;
	private int groupId = 0;
	String groupName;
	int groupSize;
	HashMap<String,Users> groupMembers;
	int Accommodation,Food,Travel,Entertainment,Others;
	String groupAdmin;
	ArrayList<Activity> groupActivityList;
	double totalGroupSpent;
	HashMap<String,GroupAccount> allGroupAccount;
	
	public Group( String groupName, HashMap<String, Users> groupMembers, String groupAdmin) {
		
		id++;
		groupId = id;
		this.groupName = groupName;
		this.groupSize = groupMembers.size();
		this.groupMembers = new HashMap<>(groupMembers);
		this.allGroupAccount = new HashMap<>();
		this.groupActivityList = new ArrayList<>();
		for(Map.Entry groupMember : groupMembers.entrySet()) {
			GroupAccount objectGroupAccount = new GroupAccount();
			allGroupAccount.put((String)groupMember.getKey(), objectGroupAccount);
		}
		this.groupAdmin = groupAdmin;
		this.totalGroupSpent = 0;
		this.Accommodation = 0;
		this.Food=0; 
		this.Travel=0;
		this.Entertainment=0;
		this.Others=0;
		
	}

	public int getGroupId() {
		return groupId;
	}
	public String getGroupName() {
		return groupName;
	}

	public HashMap<String,Users> getGroupMembers() {
		return groupMembers;
	}
	
	
	
}
