package tn.MITProject.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.xml.txw2.Document;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.ParticularClient;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.Product;
import tn.MITProject.entities.Status;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.PaymentRepository;


@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	ContractRepository contractrepository;
	@Autowired
	PaymentRepository paymentrepository;
	@Override
	public List<Contract> retrieveAllContracts() {
		
		return (List<Contract>) contractrepository.findAll();
	}

	@Transactional
	


	public Contract removeContract(Long IDContract) {
		Contract contract=contractrepository.findById(IDContract).get();
		contract.setCostatus(Status.TREATED);
		updateContract(contract);
		return contract;
		
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
	public ArrayList<Contract> searchByClient(int IDClientP) {
		List<Contract> con= new ArrayList<Contract>();
		con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbyclient = new ArrayList<Contract>();
		for(Contract c :con)
		{
			if (c.getIDClient()==IDClientP)
			lcontractbyclient.add(c);
		}
		return (ArrayList<Contract>) lcontractbyclient;
	}
	
	@Override
	public ArrayList<Contract> searchByAgent(String IDAgent) {
		List<Contract> con=(List<Contract>) contractrepository.findAll();
		List<Contract> lcontractbyagent = new ArrayList<Contract>();
		for(Contract c :con)
		{
		
		if (c.getIDAgent()==IDAgent)
			lcontractbyagent.add(c);
		}
		
		
		
		return (ArrayList<Contract>) lcontractbyagent;
	}



	
	@Override
	public Document generatePDFversion(Contract c) {
		com.lowagie.text.Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		String path="D:\\Projets\\Spring\\MITProject"+String.valueOf(c.getIDClient())+".pdf";
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		return document;	}
	
	

@Override	
	public Contract addContract(long IDClient, List<Product> product)  {
		
		ParticularClient client=particularclientrepository.findById(IDClient).get();
		Contract c =new Contract() ;
		String EOY = "2023-01-01";
		String BOY = "2022-01-01";

		
		c.setCreationDate(new Date());
		c.setEndDate(new Date());
		c=calculateNetPrimium(c,BOY,EOY,product);
		c.setIDClient(client);
	   	c.setCategory(product.get(0).getCategory());
		Payment pays;
		c.setPayments(List(pays));
		float tax = (float) (c.getNetPremium()*0.1);
		calculateTotalPremuim(c,tax);
		c.setCostatus(Status.WAITING);
		c.setClient(particularclientRepository.findById(product.get(0).getIDClient()).get());
		
		return contractrepository.save(c);
		}

private void calculateTotalPremuim(Contract c, float tax) {
	// TODO Auto-generated method stub
	
}








	
}
