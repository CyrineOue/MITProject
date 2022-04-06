package tn.MITProject.services;

import java.util.ArrayList;
import java.util.List;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Status;

public interface ContractService {
	
	List<Contract> viewContractsByStatus(Status contractstatus);
	List<Contract> retrieveAllContracts();
	void removeContract(Long IDContract);
	Contract updateContract (Contract c);
	Contract retrieveContract (Long id);
	ArrayList<Contract> searchByClient(Long IDClientP);
    float EvaluateContractsNb (Long idClient );	
	float EvaluateCompanyClaimsAmount(Long idClient);
	float EvaluateCompanyContractsNb(Long idClient);
	float EvaluateParticularContractsNb(Long idClient);
		
}
