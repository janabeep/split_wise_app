package group;

import java.util.*;
public class GroupAccount {
	HashMap<String,Double> groupAccount;
	double totalExpense ;
	double personalExpense;
	double actualExpense ;
	double overallExpense ;
	


	public GroupAccount() {
		groupAccount = new HashMap<>();
		totalExpense = 0;
		actualExpense = 0;
		overallExpense = 0;
		personalExpense = 0;
	}

}
