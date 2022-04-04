package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.Payment;

public interface PaymentService {
	
	Payment addPayment (Payment p);

	void deletePayment (Long id);

	Payment updatePayment (Payment p);

	Payment retrievePayment (Long id);

	List<Payment> retrieveAllPaymentbycontract(Long contractId);

	List<Payment> retrieveAllPayments();

	Payment addPayment(Payment p, Long IDContract);

}
