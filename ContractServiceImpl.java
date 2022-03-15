package tn.MITProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.MITProject.entities.Contract;
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
	@Override
	public Contract addContract(Contract c) {
		contractrepository.save(c);
		return null;
	/*	 prodrepository.save(c.getProduit());
		 return contractrepository.save(c);*/
		 
	}

	@Override
	public void deleteContract(Long id) {
		contractrepository.deleteById(id);
		
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
	public ArrayList<Contract> searchByClient(String IDClientP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Contract> searchByAgent(String IDAgent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contract search(String IDClientP, String Name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Override
	public Contract searchByClientIdAndProductName(String IDClientP, String Name)  {
		return insertContractData(contractrepository.search(IDClientP, Name));
	}*/
}
