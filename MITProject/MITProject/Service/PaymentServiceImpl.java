package tn.MITProject.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Order;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.Sinister;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.PaymentRepository;





@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final String TEST_STRIPE_SECRET_KEY ="sk_test_51KcqqdHzwWmhnMrQxhRNLyx8zVtY74sEOP7wZZIIkGlfQOk3Appr3gPM45qa0snFLiH4xcDYAWD0C2q6MM1mgM6200xaH18vuM";
@Autowired
PaymentRepository paymentrepository;
@Autowired
ContractRepository contractrepository;
@Autowired 
StripeService stripeservice;

@Override

	public List<Payment> retrieveAllPaymentbycontract(Long contractId) {
		Contract contract = contractrepository.findById(contractId).orElse(null);
		return paymentrepository.retrievePaymentByContract(contractId);
		}



@Transactional
public void chooseMethod(Payment p) {
	
	if (p.getCopayment().getMethod()=="CASH") {
		
		paymentrepository.save(p);
	}
	else if (p.getCopayment().getMethod()=="DEBITCARD") {
		addPayment(p, p.getCopayment().getIDContract());
		//stripeservice.createCharge("sirineoue1999@gmail.com", TEST_STRIPE_SECRET_KEY, p.getCopayment().getTTCPremium(), p);
	}
}
	@Scheduled(fixedDelay = 60000)
	public Payment addPayment(Payment p,Long IDContract) {
		int i=0;
		
		List<Payment> pays = paymentrepository.retrievePaymentByContract(IDContract);
		Contract contract = contractrepository.findById(IDContract).orElse(null);
		float premium = contract.getTTCPremium();
		float tranche= (float)(premium/12);
		float remainingpremium = premium;
		for (Payment pay : pays)
		{
			remainingpremium=remainingpremium-(pay.getPaidPremium()+pay.getRefundAmount());
		}
		if  (contract.getInstallmentsNB()==1)
		{ 		
				p.setCopayment(contract);
				p.setRemainingPremium(0);
				p.setStatus(true);
				paymentrepository.save(p);
		}
		if (contract.getInstallmentsNB()==12)
			{
			
			for (i=1;i<=12;i++)
			{	
				Payment p1 = new Payment();
				p1.setCopayment(contract);
				p1.setRemainingPremium((remainingpremium-tranche*i));
				paymentrepository.save(p1);
				
			}
			}
			  //p.setCopayment(contract);
			 // p.setRemainingPremium((remainingpremium-p.getPaidPremium())/12);
			  //p.setStatus(false);
			  
			
		return p;
		
		}
	

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
		Contract c = new Contract();
	//	if (p.getCopayment().getInstallmentsNB()==1)
		//{	stripeservice.createCharge(TEST_STRIPE_SECRET_KEY, TEST_STRIPE_SECRET_KEY, 0, p);
		p.setCopayment(c);
		paymentrepository.save(p);
		p.setPaymentDate(new Date());
		p.setStatus(true);
		
		p.setRemainingPremium(c.getTTCPremium()-p.getPaidPremium());
		
	//		}
	
		//else if (p.getCopayment().getInstallmentsNB()==12)
	/*	{ List<Payment> pays= retrieveAllPayments();
			for (Payment pay : pays)
		{
			stripeservice.createSubscription(TEST_STRIPE_SECRET_KEY, TEST_STRIPE_SECRET_KEY, TEST_STRIPE_SECRET_KEY);
			paymentrepository.save(pay);
		
		}
	}*/
		return p; //ESPECE .save 
	}
	
	
	@Override
	public List<Payment> retrievePaymentByContract(Long idContract) {
		return paymentrepository.retrievePaymentByContract(idContract);
	}
	
@Override
	public float viewremainingamount (Long idcontract) {
	float remainingamount=0;
		List <Payment> pay = paymentrepository.retrievePaymentByContract(idcontract);
		Contract c = new Contract();
		
		for(Payment p:pay) {
		  remainingamount=(float) ((c.getTTCPremium()-p.getPaidPremium()/12));
		  p.setRemainingPremium(remainingamount);  
		}
		System.out.println("Remaining amount");
		return remainingamount;
		}
	




@Override
public float pourcentageRemainingAmount(Date startDate, Date endDate) {

	float TotalPremium=contractrepository.TotalPremium(startDate, endDate);
	float TotalPayment=paymentrepository.RemainigAmountbetweentwodates(startDate, endDate);
	float pourcentage=(TotalPayment/TotalPremium)*100;
	return pourcentage;
}

	@Override
	public List<Payment> retrieveAllPayments() {
		return (List<Payment>) paymentrepository.findAll();
	}
	
	@Override
	public void assignPaymentToContract(Long paymentId, Long contractId) {
	Contract c;
	c = contractrepository.findById(contractId).orElse(null);
	Payment p=paymentrepository.findById(paymentId).orElse(null);
	p.setCopayment(c);
	}
	

	}

	



	

