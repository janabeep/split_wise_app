package zohoproject;

import java.util.*;

public class Add_expense {

	public void addexpense(List<Signup> all_account, Signup my_account) {
		Scanner in =new Scanner(System.in);
		System.out.println("Add Your Expense Here");
		System.out.println();
		System.out.println();
		String Description="";
		System.out.println("Enter Description");
		Description = in.nextLine();
		System.out.println();
		System.out.println("Enter '1' to Share Amount Equally With Friends");
		System.out.println("Enter '2' to Give Each Spent Amount");
		int n =0;
		while(true) {
		try {
			String s = in.nextLine();
			 n = Integer.parseInt(s);
			if(n==1||n==2) {
				break;
			}
			else {
				System.out.println("Enter Number 1 or 2");
			}
		}
		catch(Exception e) {
			System.out.println("Enter Number 1 or 2");
		}
		}
		if(n==1) {
			Double spend_money = 0.00;
			while(true) {
			System.out.println("Enter Total Money Spent");
			String s = in.nextLine();
			try {
				spend_money = Double.parseDouble(s);
				if(spend_money>0.00) {
				 break;
				}
				else {
					System.out.println("Enter Positive Number");
				}
			}
			catch(Exception e) {
				System.out.println("Enter A Number");
			}
			}
			Split_equal obj = new Split_equal();
			obj.eshare(all_account, my_account, spend_money, Description);
		}
		else if(n==2) {
			Split_amt obj = new Split_amt();
			obj.divide(all_account, my_account, Description);
		}
	}
}
