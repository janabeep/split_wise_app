package otp;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;
import javax.activation.*;

public class otp{


public   otp(String tomail,String msg){

		final String username = "jananiedharansankar@gmail.com";
		final String password = "j9361413655";

                final String from = "jananiedharansankar@gmail.com";
                final String to = tomail;

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");



                Authenticator a=new Authenticator()
                {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }

                };

                Session session = Session.getInstance(props, a);

  //2) compose message
  try{
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
    message.setSubject("OTP ");

    //3) create MimeBodyPart object and set your message content
    BodyPart messageBodyPart1 = new MimeBodyPart();
    messageBodyPart1.setText("Your OTP is "+msg);

    //4) create new MimeBodyPart object and set DataHandler object to this object
 


    //5) create Multipart object and add MimeBodyPart objects to this object
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart1);

    //6) set the multiplart object to the message object
    message.setContent(multipart);

    //7) send message
    Transport.send(message);

   System.out.println("message sent....");
   }catch (MessagingException ex) {
       
       ex.printStackTrace();
   }


 }
}
