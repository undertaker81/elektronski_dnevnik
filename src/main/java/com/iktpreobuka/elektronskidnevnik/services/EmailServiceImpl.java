package com.iktpreobuka.elektronskidnevnik.services;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronskidnevnik.email.models.EmailObject;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender emailSender;
	
	@Override
	public void sendSimpleEmailMessage(EmailObject emailObject) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailObject.getTo());
		mail.setSubject(emailObject.getSubject());
		mail.setText(emailObject.getText());
		emailSender.send(mail);
		
	}

	@Override
	public void sendTemplateMessage(EmailObject emailObject) throws IOException, MessagingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
