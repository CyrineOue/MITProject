package tn.MITProject.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Status;

public interface ContractService {
	
	List<Contract> viewContractsByStatus(Status contractstatus);
	List<Contract> retrieveAllContracts();
	void removeContract(Long IDContract);
	List <Contract> getContractByProduct(Long IDProduct);
	Contract updateContract (Contract c);
	Contract retrieveContract (Long id);
	//float EvaluateClaimsAmount (Long idClient);
	public Contract addContract(Contract c);
	//Document generatePDF(Contract c) throws DocumentException, IOException;
	ArrayList<Contract> searchByParticularClient(Long IDClientP);
	ArrayList<Contract> searchByCompanyClient(Long IDClientC);
	float EvaluateContractsNbofParticularClient(Long idClientP);
	float EvaluateContractsNbofCompanyClient(Long idClientC);
	List<Contract> retrieveContractByEndDate(Date from, Date to);
	Contract ViewContracts();
	float EvaluateParticularContractsNb(Long idClient);
    float EvaluateCompanyContractsNb(Long idClient);
    float EvaluateParticularClaimsAmount(Long idClient);
    float EvaluateCompanyClaimsAmount(Long idClient);

}
