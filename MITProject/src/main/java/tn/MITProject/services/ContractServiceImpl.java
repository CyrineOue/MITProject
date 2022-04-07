package tn.MITProject.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import tn.MITProject.entities.Contract;
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
	
	/*
	@Override
	public ArrayList<Contract> searchByAgent(String IDAgent) {
		List<Contract> con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbyagent = new ArrayList<Contract>();
		for(Contract c :con)
		{
		
		if (productrepository.agentRepository.getID()==IDAgent)
			lcontractbyagent.add(c);
		}
		
		
		
		return (ArrayList<Contract>) lcontractbyagent;
		
	}
*/

	
	/*@Override
	public Document generatePDF(Contract c) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		String path="D:\\Projets\\Spring\\MITProject"+String.valueOf(c.getIDClientP())+".pdf";
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		return document;	}*/
	
	
/*
@Override	
	public Contract addContract(Long IDClient, Long idproduct)  {
		Product p;
		idproduct=p.getIDProduct();
		ParticularClient client=particularclientrepository.findById(IDClient).get();
		Contract c =new Contract() ;
		String EOY = "2023-01-01";
		String BOY = "2022-01-01";
		
		c.setCreationDate(new Date());
		c.setEndDate(new Date());
		c.calculateNetPrimium(c,BOY,EOY,product);
		c.setIDClient(p.getParticularClient());
	   	c.setCategory(product.get(0).getCategory());
		Payment pays;
		c.setPayments(List(pays));
		Double tax = (Double) (c.getNetPremium()*0.1);
		calculateTotalPremuim(c,tax);
		c.setCostatus(Status.WAITING);
		c.setClient(particularclientRepository.findById(product.get(0).getIDClient()).get());
		
		return contractrepository.save(c);
		}
*/
	
	
	
	
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
	//@Transactional(rollbackOn = {NullPointerException.class})
	public Contract addContract(Contract c) {
		c.setCreationDate(new Date());
		c.setCostatus(Status.WAITING);
		if (c.getInstallmentsNB()==1)
		{c.setMethod("YEARLY");}
		else if (c.getInstallmentsNB()==12)
			{c.setMethod("Monthly");}
		
		
		contractrepository.save(c);
		
		return c;
	}
	
	/*private Set<Payment> addPayment(Contract c){	
		Set<Payment> pays = c.getPayments();
	
		paymentrepository.saveAll(pays);
		return (Set<Payment>) pays;
	}*/


	

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

	
	@Override
	public List<Contract> retrieveContractByEndDate(Date from, Date to) {
		return contractrepository.retrieveContractByEndDate(from, to);
	}


	
	@Override
	public Contract ViewContracts() {
		return contractrepository.ViewContracts() ;
	}
	
	
	
	@Override
	public float EvaluateParticularContractsNb(Long idClient) {
		int contractsNb= contractrepository.CountParticularClientContracts(idClient);
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
	public float EvaluateCompanyContractsNb(Long idClient) {
		int contractsNb= contractrepository.CountCompanyClientContracts(idClient);
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
	public float EvaluateParticularClaimsAmount(Long idClient) {
		 
		float rapport =contractrepository.TotalParticularRefundAmount(idClient)/ contractrepository.TotalParticularCeillingAmount(idClient);
		if (rapport<0.25)
			return 1;
		else {
			if (rapport <0.5)
				return 0.5f;
		}
		return 0;
		
	}
	
	@Override
	public float EvaluateCompanyClaimsAmount(Long idClient) {
		
		float rapport =contractrepository.TotalCompanyRefundAmount(idClient)/ contractrepository.TotalCompanyCeillingAmount(idClient);
		if (rapport<0.25)
			return 1;
		else {
			if (rapport <0.5)
				return 0.5f;
		}
		return 0;
		
	}
	






}
