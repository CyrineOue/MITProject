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

import tn.MITProject.Service.CompanyClientService;
import tn.MITProject.entities.CompanyClient;

@RestController
@RequestMapping("/CompanyClient")
//http://localhost:8086/MITMVC/CompanyClient
public class CompanyClientController {
	@Autowired
	CompanyClientService companyClientService;
	
	@PostMapping("/add-companyClient")
	// http://localhost:8086/MITMVC/CompanyClient/add-companyClient
	@ResponseBody
	public CompanyClient addCompanyClient(@RequestBody CompanyClient cc)
	{
		CompanyClient companyClient = companyClientService.addCompanyClient(cc);
	return companyClient;
	}
	
	
	@GetMapping("/retrieve-all-companyClients")
	// http://localhost:8086/MITMVC/CompanyClient/retrieve-all-companyClients
	@ResponseBody
	public List<CompanyClient> getCompanyClients() {
	List<CompanyClient> listCompanyClients = companyClientService.retrieveAllCompanyClients();
	return listCompanyClients;
	}
	
	
	@GetMapping("/retrieve-CompanyClient/{companyClient-id}")
	// http://localhost:8086/MITMVC/CompanyClient/retrieve-CompanyClient/{companyClient-id}
	@ResponseBody
	public CompanyClient retrieveCompanyClient(@PathVariable("companyClient-id") Long companyClientId) {
	return companyClientService.retrieveCompanyClient(companyClientId);
	}
	
	
	@DeleteMapping("/delete-CompanyClient/{companyClient-id}")
	// http://localhost:8086/MITMVC/CompanyClient/delete-CompanyClient/{companyClient-id}
	@ResponseBody
	public void deleteCompanyClient(@PathVariable("companyClient-id") Long companyClientId) {
	companyClientService.removeCompanyClient(companyClientId);
	}
	
	@PutMapping("/modify-companyClient")
	// http://localhost:8086/MITMVC/CompanyClient/modify-companyClient

	@ResponseBody
	public CompanyClient modifyCompanyClient(@RequestBody CompanyClient companyClient) {
		return companyClientService.updateCompanyClient(companyClient);
	}
	
	@GetMapping("/score-companyClient/{companyClient-id}")
	// http://localhost:8086/MITMVC/CompanyClient/score-companyClient/{companyClient-id}
	@ResponseBody
	public float getScoreCompanyClient(@PathVariable("companyClient-id") Long companyClientId) {
	return companyClientService.scoreCompanyClient(companyClientId);
	}
}
