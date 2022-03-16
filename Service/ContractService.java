package tn.MITProject.Service;

import java.util.List;

import tn.MITProject.entities.Contract;

public interface ContractService {
	List<Contract> retrieveAllContracts();

	Contract addContract (Contract c);

	void deleteContract (Long id);

	Contract updateContract (Contract c);

	Contract retrieveContract (Long id);
	
	float EvaluateContractsNb (Long idClient );
	
	float EvaluateClaimsAmount (Long idClient);
	
	
}
