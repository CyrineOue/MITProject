package tn.MITProject.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInit {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInit.class);
	private final TwilioConfig twilioConfig;
	
	@Autowired
	public TwilioInit (TwilioConfig twilioConfig) {
		this.twilioConfig = twilioConfig;
		Twilio.init(
				twilioConfig.getAccountSid(),
				twilioConfig.getAuthToken()
				);
		LOGGER.info("Twilio initialized..with account sid {}",twilioConfig.getAccountSid());
	}
}
