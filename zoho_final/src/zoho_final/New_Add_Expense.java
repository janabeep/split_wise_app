package zoho_final;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;


public class New_Add_Expense {

	public void addexpense(Account_Details my_account, HashMap<String, Account_Details> all_account2) {
		Scanner in =new Scanner(System.in);
		System.out.println("Add Your Expense Here");
		System.out.println();
		System.out.println();
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);  	// Current Date and time
		////
		 
		System.out.println("1.  Share With Friends");
		System.out.println("2.  Personal Expense");
		int nu;
		while(true) {
		try {
			nu = Integer.parseInt(in.nextLine());
			if(nu==2||nu==1) {
				break;
			}
			else {
				System.out.println("Enter 1 or 2");
			}
		}
		catch(Exception e) {
			System.out.println("Enter a Number");
		}
		}
		 
		if(nu==2) {
			System.out.println("Enter Total Amount");
			
			double amount_spend=0.00;
			
			while(true) { 												//Get Amount Paid for friend
			System.out.println("Enter amount spend  "); 
			 try {
				 amount_spend = Double.parseDouble(in.nextLine());
				 if(amount_spend>=0) {
					 break;
				 }
				 else {
					 System.out.println("Negative Numbers Not Allowed");
				 }
			 }
			 catch(Exception e) {
				 System.out.println("Enter correct amount");
			 }
			 
			}
			double total= amount_spend;
			System.out.println("For What Purpose You Spend Money ");
			String description = in.nextLine();
			System.out.println("								Successfully Added");
			my_account.personalexpense+=total;
		
			//my_account.my_accounts_details2.add(new Activities1(1,formattedDate,description+" (personal expense)",Double.toString(total),my_account.username));
			Activities2 obj = new Activities2(1,formattedDate,description,String.format("%.2f", total)," "+Double.toString(total)+" ",my_account.username,"Personal",my_account.username," " );
			my_account.my_accounts_details3.add(obj);

			return;
			
		}
//
		else {
		System.out.println();
		System.out.println("Enter '1' to Share Amount Equally With Friends");
		System.out.println("Enter '2' to Share Amount UnEqually With Friends");
		int n =0;																	// Number For Split Option
		while(true) { 																// Validation ...
			try {
				String s = in.nextLine();
				if(s.equals("*")) {return ;}
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
			Double spend_money = 0.00; 											// Total Money Spent ***************......
			while(true) {
			System.out.println("Enter Total Money Spent");
			String s = in.nextLine();
			if(s.equals("*")) {return ;}
			try {																		///Validations..........
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
			my_account.totalexpense+=spend_money;
			New_Split_Equal obj = new New_Split_Equal();						// Split Equal
			obj.eshare( my_account, spend_money, all_account2);
			
			//edited
//			New_Split_Equal_1 obj1 = new New_Split_Equal_1();
//			obj1.eshare(my_account, spend_money, all_account2);
		}
		else if(n==2) {
			New_Split_Amt obj = new New_Split_Amt();						
			obj.divide( my_account,all_account2);									//Split UnEqual
//			New_Split_Amt_1 obj = new New_Split_Amt_1();
//			obj.divide(my_account, all_account2);
		}
		
		}
		
	}

}
