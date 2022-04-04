package tn.MITProject.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.entities.MailUser;
import tn.MITProject.services.MailService;

@RestController
public class MailController {
	@Autowired
	private MailService notificationService;

	@Autowired
	private MailUser user;
	
	@RequestMapping("send-mail")
	public String send() {
		user.setFirstName("Ayaa");
		user.setLastName("hss");
		user.setEmailAddress("aya.hassine@esprit.tn"); //Receiver's email address

		try {
			notificationService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
	@RequestMapping("send-mail-attachment")
	public String sendWithAttachment() throws MessagingException {
		user.setFirstName("Mukul");
		user.setLastName("Jaiswal");
		user.setEmailAddress("aya.hassine@esprit.tn"); //Receiver's email address
		try {
			notificationService.sendEmailWithAttachment(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}