package tn.MITProject.services;

import java.util.Date;
import java.util.List;

import tn.MITProject.entities.Payment;

public interface PaymentService {
	
	Payment addPayment(Payment p, Long IDContract);
	void deletePayment (Long id);
	Payment updatePayment (Payment p);
	Payment retrievePayment (Long id);
	//List<Payment> retrieveAllPaymentbycontract(Long contractId);
	List<Payment> retrieveAllPayments();
	void viewremainingamount(Long paymentid);
	void assignPaymentToContract(Long paymentId, Long contractId);
	float pourcentageRemainingAmount(Date startDate, Date endDate);
	List<Payment> retrievePaymentByContract(Long idContract);

}
