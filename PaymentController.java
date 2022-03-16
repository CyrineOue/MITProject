package tn.MITProject.controllers;

import java.util.List;


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


import tn.MITProject.Service.PaymentService;
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
		List<Payment> listPayments = paymentService.retrieveAllPayment();
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
		
				
	}

