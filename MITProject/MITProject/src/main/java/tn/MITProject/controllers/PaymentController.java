package tn.MITProject.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.Response;
	@RestController
	@RequestMapping("/payment")
	
	public class PaymentController {
		@Autowired
	PaymentService paymentService;
		
	
	@PostMapping("/add-payment/{contract-id}")
	@ResponseBody
	public Payment addPayment(@RequestBody Payment p,@PathVariable("contract-id") Long contractId)
	{
	Payment payment = paymentService.addPayment(p,contractId);
	return payment;
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
		
		@GetMapping("/remainingamount/{ContractID}")
		@ResponseBody
		public void viewremainingamount (@PathVariable("ContractID") Long ContractID) {
		paymentService.viewremainingamount(ContractID);
		}
		
		
		@GetMapping("/get-pourcentage/{start-date}/{end-date}")
		@ResponseBody
		public float pourcentageRemainingAmount(@PathVariable("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
			float pourcentage = paymentService.pourcentageRemainingAmount(startDate, endDate);
			return pourcentage;
			}
		
		@GetMapping("/getPaymentByContrat/{ContractID}")
		@ResponseBody
		public List<Payment> getPaymentByContrat(@PathVariable("ContractID")Long contractId) {
			return (List<Payment>) paymentService.retrievePaymentByContract(contractId);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}