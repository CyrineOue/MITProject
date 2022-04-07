package tn.MITProject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Status;
import tn.MITProject.services.ContractService;

@RestController
@RequestMapping("/Contract")
public class ContractController {
	
	@Autowired
	ContractService contractService;
	

	@PostMapping("/add-contract")
	@ResponseBody
	public Contract addContract(@RequestBody Contract c)
	{
	Contract contract = contractService.addContract(c);
	
	return contract;
	}
	
	@GetMapping("/retrieve-all-contracts")
	@ResponseBody
	public List<Contract> getContracts() {
	List<Contract> listContracts = contractService.retrieveAllContracts();
	return listContracts;
	}
	
	@GetMapping(value = "/viewContractsByStatus={contractstatus}")
	@ResponseBody
	List<Contract> viewContractsByStatus(@PathVariable("contractstatus")int stat){
		List<Contract> lcontractbystatus=new ArrayList<>();
		switch(stat){
		case 1:
			lcontractbystatus=contractService.viewContractsByStatus(Status.TREATED);
			break;
		case 2:
			lcontractbystatus=contractService.viewContractsByStatus(Status.PROCESSING);
			break;
		case 3:
			lcontractbystatus=contractService.viewContractsByStatus(Status.WAITING);
			break;
		
		
		}
		
		return lcontractbystatus;	
	};
	
	
	@GetMapping("/retrieve-Contract/{contract-id}")
	@ResponseBody
	public Contract retrieveContract(@PathVariable("contract-id") Long contractId) {
	return contractService.retrieveContract(contractId);
	}
	
	//http://localhost:8087/MITMVC/Contract/remove-Contract/123457
	@DeleteMapping("/remove-Contract/{contract-id}")
	@ResponseBody
	public void deleteContract(@PathVariable("contract-id") Long contractId) {
	contractService.removeContract(contractId);
	}
	
	//http://localhost:8087/MITMVC/Contract/modify-contract
	@PutMapping("/modify-contract")
	@ResponseBody
	public Contract modifyContract(@RequestBody Contract contract) {
	return contractService.updateContract(contract);
	}
	
	
	//http://localhost:8087/MITMVC/Contract/getContractByProduct/1
	@GetMapping("/getContractByProduct/{id-product}")
	@ResponseBody
	public List<Contract> getContractByProducts(@PathVariable("id-product") Long IDProduct){
		List<Contract> lc = contractService.getContractByProduct(IDProduct);
		return lc;
	}
	
	//http://localhost:8087/MITMVC/Contract/retrieveContractByEndDate/2022-01-01/2022-12-31
	@GetMapping("/retrieveContractByEndDate/{from}/{to}")
	@ResponseBody
	public List<Contract> retrieveContractByEndDate(@PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,@PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {    
	List<Contract> lc = contractService.retrieveContractByEndDate( from , to ) ;
	return lc ; 
    }

}
