package tn.MITProject.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Service;

import com.stripe.Stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
	import com.stripe.model.Coupon;
	import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Subscription;
import com.stripe.model.Token;


import tn.MITProject.entities.ChargeRequest;
import tn.MITProject.entities.Payment;
import tn.MITProject.repositories.PaymentRepository;

@Service
public class StripeService {
	
	@Autowired
	PaymentRepository paymentrepository;
	
	@Value("${stripe.key.secret}")
	private String API_SECET_KEY;

	public StripeService() {

	}

	public String createCustomer(String email, String token) {

		String id = null;

		try {
			Stripe.apiKey = "sk_test_51KcqqdHzwWmhnMrQxhRNLyx8zVtY74sEOP7wZZIIkGlfQOk3Appr3gPM45qa0snFLiH4xcDYAWD0C2q6MM1mgM6200xaH18vuM";
			Map<String, Object> customerParams = new HashMap<>();
			customerParams.put("description", "Customer for " + email);
			customerParams.put("email", email);
			// obtained with stripe.js
			
			customerParams.put("source", token);

			Customer customer = Customer.create(customerParams);
			id = customer.getId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String createSubscription(String customerId, String plan, String coupon) {

		String subscriptionId = null;

		try {
			Stripe.apiKey = API_SECET_KEY;

			Map<String, Object> item = new HashMap<>();
			item.put("plan", plan);

			Map<String, Object> items = new HashMap<>();
			items.put("0", item);

			Map<String, Object> params = new HashMap<>();
			params.put("customer", customerId);
			params.put("items", items);

			if (!coupon.isEmpty()) {
				params.put("coupon", coupon);
			}

			Subscription subscription = Subscription.create(params);

			subscriptionId = subscription.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscriptionId;
	}
	
	public boolean cancelSubscription(String subscriptionId) {
		
		boolean subscriptionStatus;
		
		try {
			Subscription subscription = Subscription.retrieve(subscriptionId);
			subscription.cancel();
			subscriptionStatus = true;	
		} catch (Exception e) {
			e.printStackTrace();
			subscriptionStatus = false;
		}
		return subscriptionStatus;
	}
	
	public Coupon retriveCoupon(String code) {
		try {
			Stripe.apiKey = API_SECET_KEY;
			return Coupon.retrieve(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void paymentIntent(ChargeRequest data) throws StripeException {	
		Stripe.apiKey = "sk_test_51KcqqdHzwWmhnMrQxhRNLyx8zVtY74sEOP7wZZIIkGlfQOk3Appr3gPM45qa0snFLiH4xcDYAWD0C2q6MM1mgM6200xaH18vuM";

		List<Object> paymentMethodTypes =new ArrayList<>();
		paymentMethodTypes.add("card");
		Map<String, Object> params = new HashMap<>();
		params.put("amount", 2000);
		params.put("currency", "eur");
		params.put("payment_method_types", paymentMethodTypes
		);

		PaymentIntent paymentIntent =PaymentIntent.create(params);
		//Payment  p = new Payment ();
		//paymentrepository.save(p);
		}
	
	
	public String createCharge(String email, String token, float f, Payment p) {
		
		String chargeId = null;
		
		try {
			Stripe.apiKey = API_SECET_KEY;
			
			Map<String, Object> chargeParams = new HashMap<>();
			chargeParams.put("description","Charge for "+email);
			chargeParams.put("currency","usd");
			chargeParams.put("amount",f);
			chargeParams.put("source",token);
			
			Charge charge = Charge.create(chargeParams);
			 System.out.println(charge);
		    chargeId = charge.getId();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		paymentrepository.save(p);
		return chargeId;
	}
	
	
/*	public String generatePDFQuote() {
	Stripe.apiKey = API_SECET_KEY;
	
	Quote qt = Quote.retrieve("qt_1KicS8HzwWmhnMrQTGtM3mOH");
	InputStream stream = qt.pdf(QuotePdfParams.builder().build());
	File file = new File("/tmp/tmp.pdf");
	OutputStream outStream = new FileOutputStream(file);
	int c;
	while ((c = stream.read()) != -1) {
	   outStream.write(c);
	}
	stream.close();
	outStream.close();
*/
	public Token createaCardToken(Customer c) throws StripeException {
	Stripe.apiKey = API_SECET_KEY;

	Map<String, Object> card = new HashMap<>();
	card.put("number", "4242424242424242");
	card.put("exp_month", 3);
	card.put("exp_year", 2023);
	card.put("cvc", "314");
	Map<String, Object> params = new HashMap<>();
	params.put("card", card);

	Token token = Token.create(params);
	return token;
}
}
