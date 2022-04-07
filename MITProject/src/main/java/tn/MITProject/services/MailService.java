package tn.MITProject.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.MITProject.repositories.CompanyClientRepository;
import tn.MITProject.repositories.ParticularClientRepository;

@Service
public class MailService {
	private JavaMailSender javaMailSender;
	@Autowired
	
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	CompanyClientRepository companyClientRepository;
	@Autowired
	ParticularClientRepository particularClientRepository;
	public void ConfirmByMail(String email) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		
			mail.setTo(email);
			mail.setSubject("MIT");
			mail.setText("Dear Client, This is a mail confirming your subscription in MIT. "
					+ "Have a good day "
	);
			javaMailSender.send(mail);
	}
	
	public void sendEmailWithAttachment(String email) throws MailException, MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(email);
		helper.setSubject("MIT");
		helper.setText("Please find the attached document below.");

		
		ClassPathResource classPathResource = new ClassPathResource("MITLogo.png");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(message);
	}


}