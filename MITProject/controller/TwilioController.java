package tn.MITProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.services.SmsRequest;
import tn.MITProject.services.TwilioService;

@RestController
@RequestMapping("api/v1")
public class TwilioController {
	
	private final TwilioService twilioService;
	
	@Autowired
	public TwilioController(TwilioService twilioService ) {
		
		this.twilioService = twilioService;
	}
	@PostMapping("/sms/{product}")
	public void sendSms(@RequestBody SmsRequest smsRequest,@PathVariable("product")Long idproduct) {
		twilioService.sendSms(smsRequest,idproduct);
		
		
	}
	
	

}