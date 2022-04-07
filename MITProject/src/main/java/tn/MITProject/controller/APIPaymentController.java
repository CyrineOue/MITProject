package tn.MITProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Coupon;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;

import tn.MITProject.entities.ChargeRequest;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.Response;
import tn.MITProject.services.StripeService;


@RestController
@RequestMapping("/APIPayment")

public class APIPaymentController {
	
	@Value("${stripe.key.public}")
	private String API_PUBLIC_KEY;
	@Value("${stripe.key.secret}")
	private String API_SECET_KEY;
	private StripeService stripeService;

	public APIPaymentController(StripeService stripeService) {
		this.stripeService = stripeService;
	}

	@GetMapping("/")
	public String homepage() {
		return "homepage";
	}

	@GetMapping("/subscription")
	public String subscriptionPage(Model model) {
		model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
		return "subscription";
	}

	@GetMapping("/charge")
	public String chargePage(Model model) {
		model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
		return "charge";
	}

	
	
	@PostMapping("/create-subscription")
	public @ResponseBody Response createSubscription(String email, String token, String plan, String coupon) {
		Stripe.apiKey = API_SECET_KEY;
		if (token == null || plan.isEmpty()) {
			return new Response(false, "Stripe payment token is missing. Please try again later.");
		}

		String customerId = stripeService.createCustomer(email, token);

		if (customerId == null) {
			return new Response(false, "An error accurred while trying to create customer");
		}

		String subscriptionId = stripeService.createSubscription(customerId, plan, coupon);

		if (subscriptionId == null) {
			return new Response(false, "An error accurred while trying to create subscription");
		}

		return new Response(true, "Success! your subscription id is " + subscriptionId);
	}

	
	
	
	@PostMapping("/cancel-subscription")
	public @ResponseBody Response cancelSubscription(String subscriptionId) {

		boolean subscriptionStatus = stripeService.cancelSubscription(subscriptionId);

		if (!subscriptionStatus) {
			return new Response(false, "Faild to cancel subscription. Please try again later");
		}

		return new Response(true, "Subscription cancelled successfully");
	}

	@PostMapping("/coupon-validator")
	public @ResponseBody Response couponValidator(String code) {

		Coupon coupon = stripeService.retriveCoupon(code);

		if (coupon != null && coupon.getValid()) {
			String details = (coupon.getPercentOff() == null ? "$" + (coupon.getAmountOff() / 100)
					: coupon.getPercentOff() + "%") + "OFF" + coupon.getDuration();
			return new Response(true, details);
		}
		return new Response(false, "This coupon code is not available. This may be because it has expired or has "
				+ "already been applied to your account.");
	}

	@PostMapping("/create-charge")
	public @ResponseBody Response createCharge(String email, String token, Payment p) {
		Stripe.apiKey = API_SECET_KEY;
		if (token == null) {
			return new Response(false, "Stripe payment token is missing. please try again later.");
		}

		String chargeId = stripeService.createCharge(email, token, 999,p);// 9.99 usd

		if (chargeId == null) {
			return new Response(false, "An error accurred while trying to charge.");
		}

		// You may want to store charge id along with order information

		return new Response(true, "Success your charge id is " + chargeId);
		
		
	}
	@PostMapping("/createCard-Token")
	public @ResponseBody Token createaCardToken(Customer c) throws StripeException {
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

	
	
	

@RequestMapping("/createCustomer")
public ChargeRequest index(@RequestBody ChargeRequest data) throws StripeException {
	Stripe.apiKey = API_SECET_KEY;
   Map<String, Object> params = new HashMap<>();
    params.put("name", data.getName());
    params.put("email", data.getEmail());
   Customer customer = Customer.create(params);
    data.setCustomerId(customer.getId());
    return data;
}
	
@RequestMapping("/createPayment")
public void paymentIntent(@RequestBody ChargeRequest data) throws StripeException {	
Stripe.apiKey = "sk_test_51KcqqdHzwWmhnMrQxhRNLyx8zVtY74sEOP7wZZIIkGlfQOk3Appr3gPM45qa0snFLiH4xcDYAWD0C2q6MM1mgM6200xaH18vuM";

List<Object> paymentMethodTypes =new ArrayList<>();
paymentMethodTypes.add("card");
Map<String, Object> params = new HashMap<>();
params.put("amount", 2000);
params.put("currency", "eur");
params.put("payment_method_types", paymentMethodTypes
);

PaymentIntent paymentIntent =PaymentIntent.create(params);
}

}
