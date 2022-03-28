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

	public List<Payment> retrieveAllPaymentbycontract(Long contractId) {
		
		
		Contract contract = contractrepository.findById(contractId).orElse(null);
		
		
		return paymentrepository.getPaymentByContrat(contract);
		}

	
	public Payment addPayment(Set<Payment> pays,Long IDContract) {
		for (Payment p : pays)
		{
			Contract contract;
			contract = contractrepository.findById(IDContract).orElse(null);
			p.setCopayment(contract);
			p.setPaymentDate(new Date());
			p.setStatus(false);
			p.setInstallmentsNB(0);
			p.setMethod(null);
			Payment pay=addDetailsPayment(contract,pays);
		}
			
			return (Payment) paymentrepository.saveAll(pays);
		
		}
	
	
	
	private Payment addDetailsPayment(Contract c, Set<Payment> pays) {
		for (Payment p : pays)
		{
			  while (p.isStatus())
			 {
			 float amountyear=0;
			  if (p.getMethod()=="Yearly") 
			  {p.setInstallmentsNB(1); 
			  
			  amountyear=(float) (c.getTTCPremium()-p.getRefundAmount());
			  p.setRemainingPremium(amountyear);
			  ((PaymentServiceImpl) paymentrepository).addPayment(p,c.getIDContract());}
			  
			  else if 
				  (p.getMethod()=="Monthly")
			{ p.setInstallmentsNB(12); 
			  float amountmonth=0;
			  amountmonth=(c.getTTCPremium()-(p.getPaidPremium()+p.getRefundAmount()));
			  p.setRemainingPremium(amountmonth);
			};
			 }
			 }
		return null;
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
		paymentrepository.save(p);
		return p; //ESPECE .save 
	}


	
	@Override
	public Payment addPayment(Payment p, Long IDContract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> retrieveAllPayments() {
		
		return (List<Payment>) paymentrepository.findAll();
	}



	
}
