package non_group;

import java.util.*;





public class Main {
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);//  Scanner object
		
		//		Stored all user Account class object
		HashMap<String,Users> allAccount = new HashMap<>();
		
		//		Stored all user emailid 
		HashMap<String,Users> allEmailIds = new HashMap<>();
		
		System.out.println("			Splitwise");
		
		int c=0;
		while(true) {
			System.out.println();
			System.out.println("1. Login ");
			System.out.println("2. Signup ");
			System.out.println("3. Quit ");
			System.out.println();
			System.out.println("Enter the options (1-3)");
			System.out.println();
			int option1;
			if(c==1) {
				option1 = 2;
				c=0;
			}
			else {
				 option1 = Utility.checkNumber();
			}
				a: switch(option1){
					case 1:
						if(allAccount.isEmpty()) {
							System.out.println(" Create Account ");
							System.out.println();
							break a;
						}
						
						b:while(true) {
							
							System.out.println("Enter email address");
							System.out.println();
							String email = in.nextLine();
							
							if(Utility.back(email)) {
								break a;
							}
							if(Utility.checkEmail(email)) {
								
								if(allEmailIds.containsKey(email)) {
									while(true) {
										System.out.println("Enter password");
										System.out.println();
										String password = in.nextLine();
										if(Utility.back(password)) {
											break a;
										}
										Users user = allEmailIds.get(email);
										if(Users.checkPassword(password,user)) {
											System.out.println("		Welcome back "+user.username+" ........");
											System.out.println();
											new Home(allAccount,user);
											break b;
										}
										else {
											System.out.println("Entered password is wrong");
											System.out.println();
										}
									}
								}
								else {
									System.out.println("Entered email address not registered");
									System.out.println("Enter 1 to signup or 0 to try again ");
									try {
										int option = Integer.parseInt(in.nextLine());
										if(option==1) {
											c++;
											break;
										}
									}
									catch(Exception e) {
										System.out.println("try again..");
									}
									System.out.println();
								}
							}
							else {
								System.out.println("Enter valid email address");
								System.out.println();
							}
						}
						
						break;
					case 2:
						String username = null;
						String email = null;
						String password = null;
						
						// Username Validation.
						while(true) {
							System.out.println("Enter Your UserName");
							System.out.println("Note :- ");
							System.out.println(" Your username must be unique of length 2 to 30 characters(include) ");
							System.out.println(" contains only Alphabets, Numbers, ( _ ) Underscore");
							System.out.println(" first should be an alphabet");
							System.out.println();
							username = in.nextLine();
							if(Utility.back(username)) {
								break a;
							}
							if(Utility.checkName(username)) {
								if(!allAccount.isEmpty() && allAccount.containsKey(username)) {
									System.out.println("Username already taken");
									System.out.println();
								}
								else {
									break;
								}
							}
							else {
								System.out.println("Invalid username");
								System.out.println();
							}
							
						}
						
						// Email Validation
						while(true) {
							System.out.println("Enter Your Email Address");
							System.out.println();
							email= in.nextLine();
							if(Utility.back(email)) {
								break a;
							}
							if(Utility.checkEmail(email)) {
								if(!allEmailIds.isEmpty() &&  allEmailIds.containsKey(email)) {
									System.out.println("Email address already registered");
									System.out.println();
								}
								else {
									break;
								}
							}
							else {
								System.out.println("Enter valid email address");
								System.out.println();
							}
						}
						
						// Password Validation 
						while(true) {
							System.out.println("Enter password");
							System.out.println("Note :- ");
							System.out.println(" Password of length 8 - 20 characters(include)");
							System.out.println(" It should contains atleast one Upper case, Lower case,"
													+ " Special characters, Numbers");
							System.out.println();
							password = in.nextLine();
							if(Utility.back(password)) {
								break a;
							}
							
							if(Utility.checkPassword(password)) {
								
								// Confirm Password
								while(true) {
									
									System.out.println("Re enter your password to confirm");
									String duplicatePassword = in.nextLine();
									if(Utility.back(duplicatePassword)) {
										break a;
									}
									if(password.equals(duplicatePassword)) {
										break;
									}
									else {
										System.out.println("Password not match");
										System.out.println();
									}
									
								}
								
								break;
							}
							
						}
						
						Users userObject = new Users(username,email,password);
						allAccount.put(username, userObject);
						allEmailIds.put(email, userObject);
						
						System.out.println("		Welcome "+username+" .....");
						System.out.println();
						new Home(allAccount,userObject);
						
						break;
					case 3:
						System.out.println("Thank You.... ");
						System.exit(0);
						break;
					default:
						System.out.println("Enter a number (1-3) ");
						System.out.println();
						break;
				}
		}
		
	}

}
