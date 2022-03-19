package tn.MITProject.Service;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.txw2.Document;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Product;
import tn.MITProject.entities.Status;

public interface ContractService {
		List<Contract> viewContractsByStatus(Status contractstatus);
		List<Contract> retrieveAllContracts();
		Contract deleteContract (Long id);
		Contract updateContract (Contract c);
		Contract retrieveContract (Long id);
		ArrayList<Contract> searchByClient(String IDClientP);
		ArrayList<Contract> searchByAgent(String IDAgent);
		Contract search(String IDClientP, String Name);
		ArrayList<Contract> searchByClient(int IDClientP);
		Document generatePDFversion(Contract c);
		Contract generateContract(long IDClient, List<tn.MITProject.Service.Product> product);
		Contract addContract(long IDClient, List<Product> product);
		Contract deletedContract(Long IDContract);
}
