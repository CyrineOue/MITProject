package tn.MITProject.Service;

import java.util.List;

import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Payment;
@Repository
public interface PaymentService {
	List<Payment> retrieveAllPayment();

	Payment addPayment (Payment p);

	void deletePayment (Long id);

	Payment updatePayment (Payment p);

	Payment retrievePayment (Long id);

	Payment addPayment(Payment p, Long IDContract);
}
//springscheduler