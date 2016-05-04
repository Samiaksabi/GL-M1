package test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import mailService.EmailSender;


public class EmailSenderTest {
	public static void main(String args[]) throws AddressException, MessagingException {
		EmailSender s=new EmailSender("gla2016.flightplanification@gmail.com", "alexgla2016");
		s.sendMessage("Test", "Message test","gla2016.flightplanification@gmail.com");
		s.sendMessage("Test", "Message test","gla2016.flightplanification@gmail.com");
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}
}
