package non_group;

import java.util.*;

public class Activity {

	static int id =0;
	int transactionId;
	public String type;
	public String date;
	public String description;
	public String addedBy;
	public double totalMoney;
	public double friendsSpent[];
	public ArrayList<String> friends;
	public String receiver;
	public double payerSpent;
	public String payer;
	public String typeOfExpense;
	
	public Activity(String type, String date, String description, String addedBy, double totalMoney, double[] friendsSpent, 
			ArrayList<String> friends, double payerSpent, String payer, String typeOfExpense) {// Expense
		id++;
		this.transactionId=id;
		this.type=type;
		this.date=date;
		this.description=description;
		this.addedBy = addedBy;
		this.totalMoney=totalMoney;
		this.friendsSpent = Arrays.copyOf(friendsSpent, friendsSpent.length);
		this.friends = new ArrayList<>(friends);
		this.payerSpent = payerSpent;
		this.payer = payer;
		this.typeOfExpense = typeOfExpense;
		
	}
	
	public Activity(String type, String date, String description, String payer, double money) {//Personal
		id++;
		this.transactionId=id;
		this.type=type;
		this.date=date;
		this.description=description;
		this.payer = payer;
		this.totalMoney = money;
		this.addedBy = payer;
		this.typeOfExpense="Personal";
		
	}
	
	public Activity(String type, String date, String description,String addBy, String payer, double money, String receiver) {//Settlement
		id++;
		this.transactionId=id;
		this.type=type;
		this.date=date;
		this.description=description;
		this.addedBy=addBy;
		this.payer=payer;
		this.totalMoney=money;
		this.receiver = receiver;
		this.typeOfExpense="Settlement";
	}

	public void details() {
		if(getType().equals("expense")) {
			System.out.println(description);
			System.out.println();
			System.out.println("Total Money :- "+totalMoney+" Rs.  Paid by "+payer );
			System.out.println();
			System.out.println("Added by "+addedBy+" on "+date);
			System.out.println();
			System.out.println("Expense type :- "+typeOfExpense);
			System.out.println();
			System.out.println("Expense breakout :-");
			System.out.println();
			System.out.println("Payer :-   "+payer+" owes  "+payerSpent+" Rs.");
			for(int i=0;i<friendsSpent.length;i++) {
				System.out.println("Friends :- "+friends.get(i)+" owes "+friendsSpent[i]+" Rs.");
			}
			
		}
		else if (getType().equals("settlement")) {
			System.out.println(description);
			System.out.println();
			System.out.println("Total Money :- "+totalMoney+" Rs. Paid by "+payer);
			System.out.println();
			System.out.println("Added by "+addedBy+" on "+date);
			System.out.println();
			System.out.println("Expense type :- "+typeOfExpense);
			System.out.println();
			System.out.println(payer+" settled amount "+totalMoney+" Rs. to "+receiver);
			
		}
		else if (getType().equals("personalExpense")) {
			System.out.println(description);
			System.out.println();
			System.out.println("Total Money :- "+totalMoney+" Rs.  Paid by "+payer );
			System.out.println();
			System.out.println("Added by "+addedBy+" on "+date);
			System.out.println();
			System.out.println("Expense type :- "+typeOfExpense);
			System.out.println();
			System.out.println("Expense breakout :- ");
			System.out.println();
			System.out.println("Payer :-   "+payer+"  paid  "+totalMoney+" Rs.");
		}
		else if(getType().equals("others")) {
			
		}
	}

	public String getType() {
		return type;
	}


	
}
