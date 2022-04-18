package zohoproject;
import java.util.*;
public class Add_friends {
	Scanner in = new Scanner(System.in);
	public void addfriends(Signup myaccount,List<Signup> allaccount) {
		System.out.println("Enter your friend username");
		String name = in.nextLine();
		if(name.equals(myaccount.username)) {
			System.out.println("You Should Not Give Your Username Here !!");
			return;
		}
		System.out.println();
		int fl=0;
		for(Signup tt : allaccount) {
			if(tt.username.equals(name) && !(tt.username.equals(myaccount.username)) ) {
				tt.friendnotify++;
				tt.waitfriends.add(myaccount);
				fl++;
				break;
			}
		}
		if(fl==0) {
			System.out.println("User not found");
			System.out.println();
			System.out.println();
		}
		else {
			System.out.println("Friend request sent");
			System.out.println();
			System.out.println();
			
		}
	}
	

}
