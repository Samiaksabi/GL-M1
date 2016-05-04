package mailService;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	Properties mailServerProperties;
	Session mailSession;
	
	private String senderMailAddress;
	private String senderMailPassword;
	
	public EmailSender(String senderMailAddress,String senderMailPassword){
		this.mailServerProperties = new Properties();
		this.mailServerProperties.put("mail.smtp.port", "587");
		this.mailServerProperties.put("mail.smtp.auth", "true");
		this.mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		this.mailSession = Session.getDefaultInstance(mailServerProperties, null);
		
		this.senderMailAddress=senderMailAddress;
		this.senderMailPassword=senderMailPassword;
	}
	
	public void sendMessage(String subject, String message, String receiverAdress) throws AddressException, MessagingException{
		MimeMessage mail=new MimeMessage(this.mailSession);
		mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverAdress));
		mail.setSubject(subject	);
		mail.setText(message);
		
		Transport transport = this.mailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", this.senderMailAddress, this.senderMailPassword);
		transport.sendMessage(mail, mail.getAllRecipients());
		transport.close();
		System.out.println("Email sent");
		
	}
	
}
