package tn.MITProject.Service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.Status;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.PaymentRepository;

@Service
public class ContractServiceImpl implements ContractService {

	//private static final int Set = 0;
	@Autowired
	ContractRepository contractrepository;
	@Autowired
	PaymentRepository paymentrepository;
	
	
	
	@Override
	public List<Contract> retrieveAllContracts() {
		
		return (List<Contract>) contractrepository.findAll();
	}

	//@Transactional
	

	@Override
	public void removeContract(Long IDContract) {
		Contract contract=contractrepository.findById(IDContract).orElse(null);
		contract.setCostatus(Status.TREATED);
		//updateContract(contract);
		contractrepository.save(contract);
		
		
	}

	@Override
	public Contract updateContract(Contract c) {
	contractrepository.save(c);
		return c;
	}

	@Override
	public Contract retrieveContract(Long id) {
		return contractrepository.findById(id).orElse(null);
		
	}
	
	public List<Contract> viewContractsByStatus(Status contractstatus) {
		List<Contract> con=new ArrayList<Contract>();
				con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbystatus = new ArrayList<Contract>();
		for(Contract c :con)
		{   if (c.getCostatus()==contractstatus)
			lcontractbystatus.add(c);
		}
		return lcontractbystatus;}
	
	@Override
	public ArrayList<Contract> searchByParticularClient(Long IDClientP) {
		List<Contract> con= new ArrayList<Contract>();
		con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbyclient = new ArrayList<Contract>();
		for(Contract c :con)
		{
			if (c.getIDClientP()==IDClientP)
			lcontractbyclient.add(c);
		}
		return (ArrayList<Contract>) lcontractbyclient;
	}
	
	
	
	@Override
	public ArrayList<Contract> searchByCompanyClient(Long IDClientC) {
		List<Contract> con= new ArrayList<Contract>();
		con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbyclient = new ArrayList<Contract>();
		for(Contract c :con)
		{
			if (c.getIDClientC()==IDClientC)
			lcontractbyclient.add(c);
		}
		return (ArrayList<Contract>) lcontractbyclient;
	}
	

	
	@Override
	public Document generatePDF(Contract c) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		String path="D:\\Projets\\Spring\\MITProject"+String.valueOf(c.getIDClientP())+".pdf";
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		return document;	}
	
	

	@Override
	public float EvaluateContractsNbofCompanyClient(Long idClientC) {
		int contractsNb= contractrepository.CountContractsforClientC(idClientC);
		if (contractsNb >5 ) {
			return 1;
		}
			
		else { 
			if (contractsNb>3)
				return 0.5f;
		}
		return 0;
	}
	
	
	
	
	@Override
	public float EvaluateContractsNbofParticularClient(Long idClientP) {
		int contractsNb= contractrepository.CountContractsforClientP(idClientP);
		if (contractsNb >5 ) {
			return 1;
		}
			
		else { 
			if (contractsNb>3)
				return 0.5f;
		}
		return 0;
	}
	
	
	
	@Override
	@Transactional(rollbackOn = {NullPointerException.class})
	public Contract addContract(Contract c) {
		float tax=0.1f;
		c.setCreationDate(new Date());
		c.setCostatus(Status.WAITING);
		if (c.getInstallmentsNB()==1)
		{c.setMethod("YEARLY");}
		else if (c.getInstallmentsNB()==12)
			{c.setMethod("Monthly");}
		
		c.setTTCPremium(c.getNetPremium()+c.getNetPremium()*tax);
		contractrepository.save(c);
		
		return c;
	}
	
	
	
	private Set<Payment> addPayment(Contract c){	
		Set<Payment> pays = c.getPayments();
	
		paymentrepository.saveAll(pays);
		return (Set<Payment>) pays;
	}


	

/*
	@Override
	public float EvaluateClaimsAmount(Long idClient) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	@Override
	public List<Contract> getContractByProduct(Long IDProduct) {
	return contractrepository.getContractByProduct(IDProduct);
		
	}
//Every January 1st at 12h
	@Scheduled(cron = "0 0 12 1 1 ? " )
	//@Scheduled(fixedDelay = 60000)
	@Override
	public void contractsandpayments() {
		
		for (Contract c : contractrepository.ViewContracts())
		{
			System.out.println("The contract "+ c.getIDContract().toString()+
					"has  " + c.getInstallmentsNB()+" installments for this year");
			
		}
	}
	
	
	
	@Override
	public List<Contract> retrieveContractByEndDate(Date from, Date to) {
		return contractrepository.retrieveContractByEndDate(from, to);
	}


	


}
