package tn.MITProject.Service;

import java.util.Date;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import com.stripe.model.Order;

import tn.MITProject.entities.Contract;
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

	float viewremainingamount(Long paymentid);


	void assignPaymentToContract(Long paymentId, Long contractId);

	float pourcentageRemainingAmount(Date startDate, Date endDate);

	List<Payment> retrievePaymentByContract(Long idContract);
}
//springscheduler