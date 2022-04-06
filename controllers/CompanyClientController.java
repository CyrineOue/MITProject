package tn.MITProject.controller;

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

import tn.MITProject.entities.CompanyClient;
import tn.MITProject.services.CompanyClientService;
import tn.MITProject.services.MailService;

@RestController
@RequestMapping("/companyclient")
public class CompanyClientController {
	
	@Autowired
	CompanyClientService companyclientService;
	@Autowired 
	MailService mailService;

	// http://localhost:8081/mit/companyclient/retrieve-all-companyclients
	@GetMapping("/retrieve-all-companyclients")
	@ResponseBody
	public List<CompanyClient> getCompanyClients() {
	List<CompanyClient> listCompanyClients = companyclientService.retrieveAllCompanyClients();
	return listCompanyClients;
	}
	
	// http://localhost:8081/mit/companyclient/retrieve-companyclient/8
	@GetMapping("/retrieve-companyclient/{companyclient-id}")
	@ResponseBody
	public CompanyClient retrieveCompanyClient(@PathVariable("companyclient-id") Long companyclientId) {
	return companyclientService.retrieveCompanyClient(companyclientId);
	}

	// http://localhost:8081/mit/companyclient/add-companyclient
	@PostMapping("/add-companyclient")
	@ResponseBody
	public CompanyClient addCompanyClient(@RequestBody CompanyClient c)
	{
	CompanyClient companyclient = companyclientService.addCompanyClient(c);
	
	return companyclient;
	}
	

	// http://localhost:8081/mit/companyclient/modify-companyclient
	@PutMapping("/modify-companyclient")
	@ResponseBody
	public CompanyClient modifyCompanyClient(@RequestBody CompanyClient companyclient) {
	return companyclientService.updateCompanyClient(companyclient);
	}
	
	// http://localhost:8081/Achat/companyclient/remove-companyclient/{companyclient-id}
	@DeleteMapping("/archive-companyclient/{companyclient-id}")
	@ResponseBody
	public void archiveCompanyClient(@PathVariable("companyclient-id") Long companyclientId) {
	companyclientService.deleteCompanyClient(companyclientId);
	}
	
	@GetMapping("/score-companyClient/{companyclient-id}")
	@ResponseBody
	public float getScoreCompanyClient(@PathVariable("companyClient-id") Long companyClientId) {
	return companyclientService.scoreCompanyClient(companyClientId);
	}

}
