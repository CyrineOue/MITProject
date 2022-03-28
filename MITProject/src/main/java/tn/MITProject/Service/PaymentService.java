package tn.MITProject.Service;

import java.util.List;

import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Payment;
@Repository
public interface PaymentService {

	Payment addPayment (Payment p);

	void deletePayment (Long id);

	Payment updatePayment (Payment p);

	Payment retrievePayment (Long id);

	List<Payment> retrieveAllPaymentbycontract(Long contractId);

	List<Payment> retrieveAllPayments();

	Payment addPayment(Payment p, Long IDContract);
}
//springscheduler