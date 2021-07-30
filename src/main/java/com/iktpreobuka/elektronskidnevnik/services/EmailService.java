package com.iktpreobuka.elektronskidnevnik.services;

import java.io.IOException;

import javax.mail.MessagingException;

import com.iktpreobuka.elektronskidnevnik.email.models.EmailObject;

public interface EmailService {

	public void sendSimpleEmailMessage(EmailObject emailObject);
	
	public void sendTemplateMessage(EmailObject emailObject) throws
	java.io.IOException, MessagingException;
	
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws IOException;
}
