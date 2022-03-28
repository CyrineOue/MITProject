package tn.MITProject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Charge;

import tn.MITProject.Service.PaymentService;
import tn.MITProject.Service.StripeClientService;
import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;
	@RestController
	@RequestMapping("/Payment")
	
	public class PaymentController {
		@Autowired
	PaymentService paymentService;
		
		@PostMapping("/add-Payment")
		@ResponseBody
		public Payment addPayment(@RequestBody Payment p)
		{
		Payment Payment = paymentService.addPayment(p);
		return Payment;
		}
		
		
		@GetMapping("/retrieve-all-payment")
		@ResponseBody
		public List<Payment> getPayments() {
		List<Payment> listPayments = paymentService.retrieveAllPayments();
		return listPayments;
		}
		
		
		@GetMapping("/retrieve-Payment/{Payment-id}")
		@ResponseBody
		public Payment retrievePayment(@PathVariable("Payment-id") Long PaymentId) {
		return paymentService.retrievePayment(PaymentId);
		}
		
		
		
		@DeleteMapping("/delete-Payment/{Payment-id}")
		@ResponseBody
		public void deletePayment(@PathVariable("Payment-id") Long PaymentId) {
		paymentService.deletePayment(PaymentId);
		}
		
		@PutMapping("/modify-Payment")
		@ResponseBody
		public Payment modifyPayment(@RequestBody Payment Payment) {
		return paymentService.updatePayment(Payment);
		}
		
		

	    private StripeClientService stripeClient;

	    @Autowired
	    PaymentController(StripeClientService stripeClient) {
	        this.stripeClient = stripeClient;
	    }

	    @PostMapping("/charge")
	    public Charge chargeCard(HttpServletRequest request) throws Exception {
	        String token = request.getHeader("token");
	        Double amount = Double.parseDouble(request.getHeader("amount"));
	        return this.stripeClient.chargeNewCard(token, amount);
	    }
				
	}

