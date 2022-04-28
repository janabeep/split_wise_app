package zoho_final;

public class Activities2 {
												// lender   borrower  settle payer 	settle receiver
	int type;									// 0 		|| 1 		 || 2 			 	|| 3 				
	String date;//outer // insider			// date
	String description;//outer // insider	// "Movie"
	String totalmoney;//outer //insider	// "200"
	String individualmoney;//insider		// " 20 30 40 50 60 "
	String addedby;//insider			// "JD" 
	String typeofexpense;//insider	// "equal" || "unequal"
	String payer;//insider		//" JD HARI "
	String receiver;//insider	// " A B C D "
	
	
	String sentence;
	
	
	public Activities2(int type, String date, String description, String totalmoney, 
			String individualmoney,String addedby,String typeofexpense,String payer,String receiver) {
		this.type =type;
		this.date =date;
		this.description =description;
		this.totalmoney =totalmoney;
		this.individualmoney =individualmoney;
		this.addedby =addedby;
		this.typeofexpense =typeofexpense;
		this.payer =payer;
		this.receiver =receiver;
		
		if(type ==0) {
			sentence = "  "+date+"  You  Paid  "+totalmoney+" Rs.  for  "+description+"  Friends:  "+receiver;
			//sentence = "  "+date+"  You  Paid  "+money+" Rs.  for  "+description+"  Friends:  "+names;
		}
		else if(type ==1) {
			sentence = "  "+date+"  "+payer+"  paid  "+totalmoney+" Rs.  for  "+description+"  ";
			
		}
		else if(type ==2) {
			sentence = "  "+date+"  You  Paid  "+totalmoney+" Rs.  to  "+receiver+"  ";
			//sentence = "  "+date+"  You  Paid  "+money+" Rs.  to  "+names+" ";
		}
		else if(type ==3) {
			sentence ="  "+date+"  "+payer+"  paid  "+totalmoney+" Rs.  ";
			//sentence ="  "+date+"  "+names+"  paid  "+money+" Rs.";
		}
		
		
	}
	public Activities2(int type ,String x) {
		this.type = type;
		sentence = x;
	}
	public void details() {
		if(type==0||type==1){
		String[] receiver = this.receiver.trim().split(" ");
		String[] payer = this.payer.trim().split(" ");
		String[] individualmoney = this.individualmoney.trim().split(" ");
		System.out.println();
		System.out.println("	"+description);
		System.out.println("	"+totalmoney+" Rs.  Paid  By  "+addedby);
		System.out.println("	Added  by  "+addedby+"  on  "+date);
		System.out.println("	Expense type :- "+this.typeofexpense);
		System.out.println();
		System.out.println("	Expense  Breakout");
		for(int i=0,j=0,k=0; i<payer.length||j<receiver.length||k<individualmoney.length;) {
			if(i<payer.length) {
				System.out.println(payer[i]+"  owes  "+individualmoney[k]);
				i++;
				k++;
			}
			else if(j<receiver.length) {
				System.out.println(receiver[j]+"  owes  "+individualmoney[k]);
				j++;
				k++;
			}
		}
	}
		else if(type==2||type==3){
		
				System.out.println();
				System.out.println("	"+description);
				System.out.println("	"+totalmoney+" Rs.  Paid  by  "+payer);
				System.out.println("	"+"Added  by  "+addedby+"  on  "+date);
				System.out.println("	Expense type :- "+this.typeofexpense);
				System.out.println();
				System.out.println("	"+payer+"  paids  "+totalmoney+" Rs.  to  "+receiver);
			
		
		}
		else if(type==99) {
			System.out.println(sentence);
		}
	}
}
