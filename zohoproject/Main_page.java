package zohoproject;



import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main_page {

	public static void main(String[] args) {
		List<Signup> all_account= new ArrayList<>();
		Scanner in = new Scanner(System.in);
		System.out.println("Share you expense....");
		System.out.println();
		String regex3 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  
	    Pattern p3 = Pattern.compile(regex3); 
		while(true){
		System.out.println();
		System.out.println("1.Login");
		System.out.println("2.SignUp");
		System.out.println("3.Quit");
		String s = in.nextLine();
		int num =0;
		try {
             num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Enter a number..");
        }
		
		switch(num) {
		case 1:
			if(all_account.isEmpty()) {
				System.out.println("First Create Account ! ! ");
				break;
			}
			boolean flag1=true;
			while(flag1) {
			System.out.println("Enter your Email");
			int check =0;
			String emails= in.nextLine();
			if(emails.equals("*")) {break;}
			Matcher m3 = p3.matcher(emails);
			if(m3.matches()) {
				
			for(Signup my_account : all_account) {
				if(my_account.getEmail().equals(emails)) {
					System.out.println("Account Found!!");
					System.out.println("Enter your Password");
					if(my_account.getPassword().equals(in.nextLine())) {
						new Home(my_account.userid,all_account,my_account);
						check++;
						flag1=false;
						break;
					}
					check--;
					System.out.println("password doesnt match");
					break;
				}
			}
			if(check==0) {System.out.println("No Account found pls create an account");};
			}
			else {
				System.out.println("Entered email is wrong");
			}
			}
			break;
	   case 2:
			String regex = "^(?=.*[0-9])"
		            + "(?=.*[a-z])(?=.*[A-Z])"
		            + "(?=.*[!@#$%^&+=])"
		            + "(?=\\S+$).{8,20}$";
			Pattern p = Pattern.compile(regex);
			String regex1 = "^[A-Za-z]\\w{1,29}$";
		    Pattern p1 = Pattern.compile(regex1);
		    String regex2 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  
		    Pattern p2 = Pattern.compile(regex2);
		    String username="";
			String duplicatepass = null;
			String email="";
			boolean f1=true;
			while(f1) {
			System.out.println("Enter your name ");
			 username = in.nextLine();
			Matcher m1 = p1.matcher(username);
			if(m1.matches()) {
				int check=0;
				if(!all_account.isEmpty()) {
					for(Signup t: all_account) {
						if(t.username.equals(username)) {
							System.out.println("Name is already taken ");
							check++;
						}
						break;
					}
				}
				if(check==0) {
					f1=false;
				}
			}
			else {
				System.out.println("User Name should Consist of  2 to 30 characters alphanumeric ");
				System.out.println("No special characters only underscore(_)  allowed");
			}
			}
			HashMap<String,Double> hm = new HashMap<>();
			int break_check =0;
			int condition_check1=0;
			for(Signup tt : all_account) {
				if(tt.username!=username && !(tt.unknown_accounts.isEmpty()) && tt.unknown_accounts.containsKey(username)) {
					System.out.println("Do You Know "+tt.username);
					System.out.println(tt.username+" Lend Some Money to Person With Username "+username);
					System.out.println("Is That You ??");
					System.out.println("Enter Options 1 or 2");
					System.out.println(" 1 - Yes I'm That Person");
					System.out.println(" 2 - No I'm Not That Person");
					int n = Integer.parseInt(in.nextLine());
					if(n==1) {
						condition_check1++;
						double val = (double)tt.unknown_accounts.get(username);
						 hm.put(tt.username, val);
						tt.unknown_accounts.remove(username);
						tt.my_accounts.put(username, 0.00);
					}
					else if(n==2) {
						if(condition_check1==0) {
						System.out.println("Ok!! Change Your UserName and Try again ");
						break_check++;
						break ;
						}
					}
					
				}
			}
			if(break_check==1) {break;}
			boolean recursive_bool=true;
			while(recursive_bool) {
			System.out.println("Enter your email");
			email = in.nextLine();
			Matcher m2 = p2.matcher(email);
			if(m2.matches()) {
				int ch=0;
				if(!all_account.isEmpty()) {
					for(Signup signup_object: all_account) {
						if(signup_object.getEmail().equals(email)) {
							System.out.println("Email is already Registered ");
							ch++;
						}
						break;
					}
				}
				if(ch==0) {
					recursive_bool=false;
				}
			}
			else {
				System.out.println("Wrong mail id");
			}
			}
			
			 flag1=true;
			while(flag1) {
			System.out.println("Enter your password");
			duplicatepass = in.nextLine();
			Matcher m = p.matcher(duplicatepass);
			if(m.matches()) {
				boolean ff=true;
				while(ff) {
				System.out.println("Confirm your password");
				String confirmpass = in.nextLine();
				if(confirmpass.equals(duplicatepass) ) {
					ff=false;
					flag1=false;
				}
				else {
					System.out.println("password dosent match");
					System.out.println();
				}
				}
			}
			else {
				System.out.println("It contains 8 - 20 characters");
				System.out.println("atleast one digit, one uppercase, one lowercase");
				System.out.println("atleast one special characters");
				System.out.println();
			}
			}
			all_account.add(new Signup(username,duplicatepass,email));

			if(!(hm.isEmpty())) {
				for(Signup user_account : all_account) {
					if(user_account.username.equals(username)) {
						for(Map.Entry d : hm.entrySet()) {
							user_account.my_accounts.put((String)d.getKey(), (double)d.getValue());
						}
						break;
					}
				}
			}
			for(Signup user_account : all_account) {
				if(user_account.username.equals(username)) {
					System.out.println();
					System.out.println("Enter Home Page");
					System.out.println();
					new Home(user_account.userid,all_account,user_account);
					break;
				}
			}
			break;
		case 3:
			System.out.println("Thank You... Bye...");
			System.exit(0);
		default :
			System.out.println("Enter number (1 or 2 or 3)");
		}
		}
	
	}

}


