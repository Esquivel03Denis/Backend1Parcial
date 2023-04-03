package py.com.progweb.prueba.ejb;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.ejb.Stateless;
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

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;    

@Stateless
public class enviaCorreo {
    public static void mail (Clientes cliente,BolsaPuntos bolsaPuntos) {
		
		String to = cliente.getEmail(); // to address. It can be any like gmail, hotmail etc.
		final String from = "robertocarlos2022is2@gmail.com"; // from address. As this is using Gmail SMTP.
		final String password = "ezjchcjbcgxswmdm"; // password for from mail address. 
	
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(from, password);
		}
		});
	
		try {
	
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject("Message from Java Simplifying Tech");
		
		String msg = "Se utilizo: "+bolsaPuntos.getPuntajeUtilizado()+"\n"+"Saldo: " + bolsaPuntos.getSaldoPuntos();
		
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		message.setContent(multipart);
	
		Transport.send(message);
	
		System.out.println("Mensaje enviado!");
	
		} catch (MessagingException e) {
		e.printStackTrace();
		}
	}
}
