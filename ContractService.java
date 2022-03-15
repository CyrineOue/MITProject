package tn.MITProject.Service;

import java.util.ArrayList;
import java.util.List;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Status;

public interface ContractService {
	List<Contract> viewContractsByStatus(Status contractstatus);
	List<Contract> retrieveAllContracts();

	Contract addContract (Contract c);

	void deleteContract (Long id);

	Contract updateContract (Contract c);

	Contract retrieveContract (Long id);
	 ArrayList<Contract> searchByClient(String IDClientP);
	 ArrayList<Contract> searchByAgent(String IDAgent);
	 Contract search(String IDClientP, String Name);
}
