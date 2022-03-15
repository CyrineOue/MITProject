package tn.MITProject.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
@Autowired
PaymentRepository paymentrepository;
@Autowired
ContractRepository contractrepository;
	@Override
	public List<Payment> retrieveAllPayment() {
		paymentrepository.findAll();
		return null;
	}

	
	@Override
/*	public Payment addPayment(Payment p,Long IDContract) {
		
			Contract contract;
			contract = contractrepository.findById(IDContract).orElse(null);
			p.setContract(contract);
			p.setPaymentDate(new Date());
			p.setStatus(false);
			
			List detailsPayment = p.getDetailPayment();
			Payment pay = addDetailsPayment(p, detailsPayment);
			return paymentrepository.save(pay);

		}
	
	
	
/*	private Payment addDetailsPayment(Payment p) {
		/*Partiemétier
		 * 
		 * 
		 * while (status=False)
		 * {
		 * if (Method=="Yearly") 
		 * {InstallmentsNb=1; RemainingPremium=TTCPremium-RefundAmount;
		 * addPayment(Payment p)}
		 * else if {Method=="Monthly")
		 * {InstallmentsNb=12; RemainingPremium=(TTCPremium-(PaidPremium+RefundAmount));
		 * setPayments()}
		 * status= True
		 * }
		 * */
		
		
	
	
	

	
	public void deletePayment(Long id) {
		paymentrepository.deleteById(id);
		
	}

	@Override
	public Payment updatePayment(Payment p) {
		paymentrepository.save(p);
		return null;
	}

	@Override
	public Payment retrievePayment(Long id) {
	Payment p=paymentrepository.findById(id).orElse(null);
		
		return p;
	}


	@Override
	public Payment addPayment(Payment p) {
		paymentrepository.save(p);
		return p; //ESPECE .save 
	}


	@Override
	public Payment addPayment(Payment p, Long IDContract) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
