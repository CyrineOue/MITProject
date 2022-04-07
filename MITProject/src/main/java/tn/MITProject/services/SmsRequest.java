package tn.MITProject.services;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

import tn.MITProject.entities.Product;
import tn.MITProject.repositories.ProductRepository;
public class SmsRequest {
	
	@Autowired
	ProductService productservice;
	@Autowired
	ProductRepository productrepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
	
	
	
	private final String phoneNumber; //destination
	private final String message;
	
	
	public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
					  @JsonProperty("message") String message) {
		
		this.phoneNumber = phoneNumber;
		this.message = message;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public String getMessage(Long idproduct) {
		//List<Product> products= new ArrayList<Product>();
		List<Product> products=productrepository.findAll();
		LOGGER.info(""+products);
		/*for(Product c :products)
		{
			if(c.getIDProduct()==idproduct) {
				
		    return "Check our website to know about"+c.getDescription();	
		}
		
		}*/
		return "mit";
	}


	@Override
	public String toString() {
		return "SmsRequest [phoneNumber=" + 
				phoneNumber + ", message=" + message +
				"]";
	} 
	

}
