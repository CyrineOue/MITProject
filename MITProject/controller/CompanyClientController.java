package tn.MITProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.entities.CategoryClient;
import tn.MITProject.entities.CompanyClient;
import tn.MITProject.repositories.CompanyClientRepository;
import tn.MITProject.services.CompanyClientService;
import tn.MITProject.services.MailService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/companyclient")
public class CompanyClientController {
	
	@Autowired
	CompanyClientService companyclientService;
	@Autowired 
	MailService mailService;
	@Autowired 
	CompanyClientRepository companyClientRepository;
	
	@GetMapping("/CountContracts/{id}")
	@ResponseBody
	public int CountContracts(@PathVariable Long id) {
		return companyclientService.countContracts(id);
	}

	@GetMapping("/EvaluateSeniority/{id}")
	@ResponseBody
	public float EvaluateSeniority(@PathVariable Long id) {
		return companyclientService.EvaluateSeniority(id);
	}
	
	@GetMapping("/EvaluateCapital/{id}")
	@ResponseBody
	public float Evaluate(@PathVariable Long id) {
		return companyclientService.EvaluateCapital(id);
	}
	@GetMapping("/EvaluateEmployeesNB/{id}")
	@ResponseBody
	public float EvaluateEmployeesNB(@PathVariable Long id) {
		return companyclientService.EvaluateEmployeesNb(id);
	}
	@GetMapping("/EvaluateArea/{id}")
	@ResponseBody
	public float EvaluateArea(@PathVariable Long id) {
		return companyclientService.EvaluateArea(id);
	}
	
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
	// http://localhost:8081/companyclient/archive-companyclient/{companyclient-id}
	@DeleteMapping("/archive-companyclient/{companyclient-id}")
	@ResponseBody
	public void archiveCompanyClient(@PathVariable("companyclient-id") Long companyclientId) {
	companyclientService.deleteCompanyClient(companyclientId);
	}
	
	@GetMapping("/score-companyClient/{companyClient-id}")
	@ResponseBody
	public float getScoreCompanyClient(@PathVariable("companyClient-id") Long companyClientId) {
	return companyclientService.scoreCompanyClient(companyClientId);
	}
	@GetMapping("/categorise-companyClient/{id}")
	@ResponseBody
	public CategoryClient categoriseCompanyClient(@PathVariable("id") Long id) {
		if (companyClientRepository.existsById(id)) {
		companyclientService.CategoriseCompanyClient(id);
		return companyClientRepository.findById(id).orElse(null).getCategoryC();
		}
		return null;
		
	}
	
	@GetMapping("/GetIdealCompanyClient")
	@ResponseBody
	public CompanyClient GetIdealCompanyClient() {
		return companyclientService.GetIdealCompanyClient();
	}
	
	

}
