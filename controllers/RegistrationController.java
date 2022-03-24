package tn.MITProject.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.Service.MailService;
import tn.MITProject.entities.User;

@RestController
@RequestMapping("/APIMail")
// http://localhost:8086/MITMVC/APIMail
public class RegistrationController {

	@Autowired
	private MailService notificationService;

	@Autowired
	private User user;
	
	public RegistrationController(MailService notificationService) {
		this.notificationService=notificationService;
	}
	
	@PostMapping("/send-mail")
	public String send() {
		SimpleMailMessage message =new SimpleMailMessage();
		message.setTo("aya.hassine@esprit.tn");
		message.setSubject("Test Simple Email");
		message.setText("Hello, Im testing Simple Email");
		
		
		this.notificationService.sendEmail(user);
		/*
		 * user.setEmailAddress("aya.hassine@esprit.tn");
		
		 
		notificationService.sendEmail(user);
		  */    
		
		return "Congratulations Ayaya ! Your mail has been send to the user.";
	}
	 
	
	
	
	@RequestMapping("send-mail-attachment")
	public String sendWithAttachment() throws MessagingException {
		user.setEmailAddress("aya.hassine@esprit.tn");
		 
			notificationService.sendEmailWithAttachment(user);
		 
		return "Congratulations! Your mail has been send to the user.";
	}
	 

		
	}


