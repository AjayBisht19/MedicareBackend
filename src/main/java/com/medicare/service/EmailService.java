//package com.medicare.service;
//
////import java.util.Properties;  
////
////import javax.mail.Authenticator;
////import javax.mail.Message;
////import javax.mail.PasswordAuthentication;
////import javax.mail.Session;
////import javax.mail.Transport;
////import javax.mail.internet.InternetAddress;
////import javax.mail.internet.MimeMessage;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailService {
//	@Autowired
//	private JavaMailSender javaMailSender;
//
////	@Async
////	public void sendEmail(String message,String to) {
////		String from="issiewatson10@gmail.com";
////		String subject="OTP for Email verification";
////		String host="smtp.gmail.com";
////		Properties properties = System.getProperties();
////		System.out.println("here are the properties");
////		System.out.println(" properties " + properties);
////	
////		properties.put("mail.smtp.host", host);
////		properties.put("mail.smtp.port", "465");
////		properties.put("mail.smtp.ssl.enable", "true");
////		properties.put("mail.smtp.auth", "true");
////		
////		Session session= Session.getInstance(properties, new Authenticator() {
////		@Override
////		 protected PasswordAuthentication getPasswordAuthentication() {
////			return new PasswordAuthentication("issiewatson10@gmail.com","issie987");
////		}			
////		});
////		
////		session.setDebug(true);
////		
////		MimeMessage m= new MimeMessage(session);
////		try {
////			m.setFrom(from);
////			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
////			m.setSubject(subject);
////			m.setText(message);
////			
////			Transport.send(m);
////			
////			System.out.println("Sent Successfully.....");
////		} catch (Exception e) {
////			// TODO: handle exception
////			System.out.println("Could not send.....");
////			e.printStackTrace();
////		}
////	}
//	
//	
//	@Async
//	public void sendEmail(String message,String to) {
//		
//		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//		simpleMailMessage.setFrom("@gmail.com");
//		simpleMailMessage.setTo(to);
//		simpleMailMessage.setSubject("OTP for Email verification");
//		simpleMailMessage.setText(message);
//		javaMailSender.send(simpleMailMessage);
//	}
//}
