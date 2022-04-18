package zohoproject;

import java.util.ArrayList;
import java.util.List;

class user extends Group_account{
	String username;
	private String email;
	private String password;
	List<Group> myGroups;
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public user(String username, String pass, String mail) {
		
		this.username = username;
		this.email = mail;
		this.password = pass;
		myGroups = new ArrayList<>();
		
	}
}