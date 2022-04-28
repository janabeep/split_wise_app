package otp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Activity_dummy {
	public static void main(String[]args) {
		
		
		
		LocalDateTime myDateObj = LocalDateTime.now();  
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		 DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("yyyy MM  dd  HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj); 
		 String d = myDateObj.format(myFormatObj1);
		 
		System.out.println(d);
		System.out.println("------------------");
		System.out.println(formattedDate);
	}
}
