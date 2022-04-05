package tn.MITProject.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import tn.MITProject.entities.Product;
import tn.MITProject.entities.TwilioConfig;
import tn.MITProject.repositories.ProductRepository;

@Service("twilio")
public class TwilioSmsSender implements SmsSender  {
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
	
	private final TwilioConfig twilioConfig;
	
	@Autowired
	public TwilioSmsSender(TwilioConfig twilioConfig) {
		this.twilioConfig = twilioConfig;
	}
	@Autowired
	ProductService productservice;
	ProductRepository productrepository;
	

	@Override
	public void sendSms(SmsRequest smsRequest, Long idproduct) {
		
		if(isPhoneNumberValid(smsRequest.getPhoneNumber())) {
			
			PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
			
			/*
			List<Product> products= new ArrayList<Product>();
			products=(List<Product>) productrepository.findAll();
			for(Product c :products)
			{
				if(c.getIDProduct()==idproduct) {
					
					String message =("Check our website to know about"+c.getDescription()+"");	
					MessageCreator creator = Message.creator(to, from, message);
					creator.create();	
					
			}
			
				
			}*/
			
			
			String message = fonction(idproduct);
			MessageCreator creator = Message.creator(to, from, message);
			creator.create();	
			LOGGER.info("send sms {}"+smsRequest);
		}else {
			
			throw new IllegalArgumentException(
					"Phone number ["+smsRequest.getPhoneNumber()+"] is invalid"
					);
			
			  }
		
	
	}



	private boolean isPhoneNumberValid(String phoneNumber) {
		return true;
	}
	
	
	/*
	public List<Contract> viewContractsByStatus(Status contractstatus) {
		List<Contract> con=new ArrayList<Contract>();
				con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbystatus = new ArrayList<Contract>();
		for(Contract c :con)
		{   if (c.getCostatus()==contractstatus)
			lcontractbystatus.add(c);
		}
		return lcontractbystatus;}
		*/

	public String fonction (Long idproduct) {
				//List<Product> products= new ArrayList<Product>();
				List<Product> products = productservice.retrieveAllProducts();
				LOGGER.info(""+products);
				for(Product c :products)
				{
					if(c.getIDProduct()==idproduct) 	
				    return "Check our website to know about"+" "+c.getDescription();	
				}
			return "";	
		
	}
	
	
	

}
