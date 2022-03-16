package tn.MITProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.Service.ContractService;
import tn.MITProject.entities.Contract;

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
	@GetMapping("/retrieve-Contract/{contract-id}")
	@ResponseBody
	public Contract retrieveContract(@PathVariable("contract-id") Long contractId) {
	return contractService.retrieveContract(contractId);
	}
	@DeleteMapping("/delete-Contract/{contract-id}")
	@ResponseBody
	public void deleteContract(@PathVariable("contract-id") Long contractId) {
	contractService.deleteContract(contractId);
	}
	
	@PutMapping("/modify-contract")
	@ResponseBody
	public Contract modifyContract(@RequestBody Contract contract) {
	return contractService.updateContract(contract);
	}
}
