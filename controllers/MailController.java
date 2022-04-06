package tn.MITProject.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.MITProject.services.MailService;

@RestController
public class MailController {
	@Autowired
	private MailService notificationService;
	
	@RequestMapping("send-mail/{email}")
	public String send(@PathVariable("email") String email) {
		
		
		try {
			notificationService.ConfirmByMail(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Confirmation mail sent to client ";
	}
	@RequestMapping("send-mail-attachment/{email}")
	public String sendWithAttachment(@PathVariable("email") String email) throws MessagingException {
		
		try {
			notificationService.sendEmailWithAttachment(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the Client.";
	}
}