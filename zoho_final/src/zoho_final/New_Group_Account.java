package zoho_final;

import java.util.*;

public class New_Group_Account {

	int groupid;
	String groupname;
	HashMap<String,Double> acc;
	ArrayList<String> det;
	double mygroupspend=0;
	public  New_Group_Account(int n,String name) {
		groupid = n;
		groupname = name;
		acc = new HashMap<>();
		det = new ArrayList<>();
		
	}
}
