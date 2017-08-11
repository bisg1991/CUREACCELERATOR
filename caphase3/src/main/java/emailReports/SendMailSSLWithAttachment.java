package emailReports;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.WebDriver;

import com.cure.softweb.Main.TestBase;


public class SendMailSSLWithAttachment extends TestBase {
	
	WebDriver driver;
	public SendMailSSLWithAttachment(WebDriver driver){
		this.driver=driver;
	}

	public void sendTheEmail() throws EmailException {
	
		final String username = "biswajitgqa@gmail.com";
		final String password = "softbis24";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("java.net.preferIPv4Stack", "true");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
		  protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(username, password);
		}
		});

		try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("YourEmail"));
		message.setRecipients(Message.RecipientType.TO,
		InternetAddress.parse("bsoftwebsolutions@gmail.com"));
		message.setSubject("Cure Accelrator Test results report");



		BodyPart messageBodyPart1 = new MimeBodyPart();  
		messageBodyPart1.setText("Please refer the attached reports for the results of the Test run.");  

		//4) create new MimeBodyPart object and set DataHandler object to this object      
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		String file = System.getProperty("user.dir")+"\\test-output\\"+"custom-emailable-report.html";
	    
	
	    /*DataSource source = new FileDataSource(filename);  
		messageBodyPart2.setDataHandler(new DataHandler(source));  
		messageBodyPart2.setFileName(filename); */ 
        
	    DataSource source = new FileDataSource(file);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(file);

		//5) create Multipart object and add MimeBodyPart objects to this object      
		Multipart multipart = new MimeMultipart();  
		multipart.addBodyPart(messageBodyPart1);  
		multipart.addBodyPart(messageBodyPart2);  

		//6) set the multiplart object to the message object  
		message.setContent(multipart);  
		Transport.send(message);

		System.out.println("Mail Sent Successfully");
   
	    }
	      
	    catch (MessagingException e){
		
	    	throw new RuntimeException(e);
		}

	}
}
	

