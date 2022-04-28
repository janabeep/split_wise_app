package zoho_final;
import otp.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class New_main {
	public static void main(String[]args) {
		HashMap<String,Account_Details> all_account2 = new HashMap<>(); //edited
		HashMap<String,Account_Details> emailids = new HashMap<>();
		Random r = new Random();
		Scanner in = new Scanner(System.in);//  Scanner object
		// ***************Starting of Project***********************
		System.out.println("						Share you expense....");//  Title  ... 
		System.out.println();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("Enter  '*'   for   go   back   at   any   screen");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//**************Email Regex******************************
		String regex3 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  
	    Pattern p3 = Pattern.compile(regex3); 
	    
	    
		while(true){                 // ********* Loop Condition For Main Page ******
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
		aa:switch(num) {
		//********* Case 1*****
		case 1:
			if(all_account2.isEmpty()) {
				System.out.println("First Create Account ! ! ");//******* Didnt create any account on app**
				break;
			}
			boolean flag1=true;
			while(flag1) {        
				
			System.out.println("Enter your Email   ");
			
			String emails= in.nextLine();                       //******** email
			if(emails.equals("*")) {break;}
			Matcher m3 = p3.matcher(emails);
			if(m3.matches()) {
				if(!emailids.containsKey(emails)) {System.out.println("Email Id Not Registered");
				break;}
				Account_Details my_account = (Account_Details)emailids.get(emails);
				if(my_account.getEmail().equals(emails)) {
					System.out.println("Account Found!! ");
					System.out.println("Enter your Password  ");
					String dummy = in.nextLine();
					if(dummy.equals("*")) {break ;}
					if(my_account.getPassword().equals(dummy)) {
						System.out.println("         WELCOME BACK    "+my_account.username+"    .......");
						new New_home(my_account,all_account2);
						
						flag1=false;
						break;
					}
					else {
					System.out.println("password doesnt match ");
					break;
					}
				}
				
				else{System.out.println("Email ID incorrect ");};
			}
			else {System.out.println("Enter Valid Email Id");}
			}
			
			break;
		case 2:
			String regex = "^(?=.*[0-9])"
		            + "(?=.*[a-z])(?=.*[A-Z])"
		            + "(?=.*[!@#$%^&+=])"
		            + "(?=\\S+$).{8,20}$"; //**************** regex for password
			Pattern p = Pattern.compile(regex);
			String regex1 = "^[A-Za-z]\\w{1,29}$";  //****************** regex for username
		    Pattern p1 = Pattern.compile(regex1);
		    String regex2 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  //*********** regex for email
		    Pattern p2 = Pattern.compile(regex2);
		    String username="";
			String duplicatepass = null;
			String email="";
			/////////////////////////////////////////////////////
			boolean f1=true;
			while(f1) {
			System.out.println("Enter your name ");
			 username = in.nextLine();                   //******** get username
			 if(username.equals("*")) {break aa;}
			Matcher m1 = p1.matcher(username);
			if(m1.matches()) {
				int check=0;
				if(!all_account2.isEmpty()) {
						if(all_account2.containsKey(username)) {
							System.out.println("Name is already taken ");
							check++;
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
			///////////////////////////////////////////////////////
			boolean recursive_bool=true;
			while(recursive_bool) {
			System.out.println("Enter your email");
			email = in.nextLine();                             //************** Get Email........
			if(email.equals("*")) {break aa;}
			Matcher m2 = p2.matcher(email);
			if(m2.matches()) {
				int ch=0;
				if(!all_account2.isEmpty()) {
						if(emailids.containsKey(email)) {
							System.out.println("Email is already Registered ");
							ch++;
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
			//////////////////////////////////////////
			flag1=true;
			while(flag1) {
			System.out.println("Enter your password");
			duplicatepass = in.nextLine();                  ///********** Get password
			if(duplicatepass.equals("*")) {break aa;}
			Matcher m = p.matcher(duplicatepass);
			if(m.matches()) {
				boolean ff=true;
				while(ff) {
				System.out.println("Confirm your password");
				String confirmpass = in.nextLine();                 ///////************** Get Confirm Password
				if(confirmpass.equals("*")) {break aa;}
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
//			int otp =r.nextInt(10000);
//			String otps = Integer.toString(otp);
//			otp objec = new otp(email,otps);
			
			Account_Details ob =new Account_Details(username,duplicatepass,email);
			all_account2.put(username,ob);//******** Adding to hashmap
			emailids.put(email,ob);
			//////////////////////////////////////////////////////////////////////
			//Entering Into Home Page ...................
			Account_Details user_account = all_account2.get(username); 
			System.out.println();
			System.out.println("						Enter Home Page");
			System.out.println();
			System.out.println();
			System.out.println("        				WELCOME  "+user_account.username+"  .......");
			new New_home(user_account,all_account2);
			break;
		case 3:
			System.out.println("Thank You Byee..");
			System.exit(0);
			break;
		default :
			System.out.println("Enter number (1 or 2 or 3)");
			break;
		}
		}
		
		
		}
	}

