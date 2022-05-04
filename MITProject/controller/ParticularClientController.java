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
import tn.MITProject.entities.ParticularClient;
import tn.MITProject.services.MailService;
import tn.MITProject.services.ParticularClientService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/particularclient")
public class ParticularClientController {
	//EvaluateSeniority
	@Autowired
	ParticularClientService particularclientService;
	@Autowired
	MailService mailService;
	
	@GetMapping("/EvaluateClaimsAmount/{id}")
	@ResponseBody
	public float EvaluateClaimsAmount(@PathVariable Long id ){
	return particularclientService.EvaluateClaimsAmount(id);
	} 
	@GetMapping("/EvaluateArea/{id}")
	@ResponseBody
	public float EvaluateArea(@PathVariable Long id ){
	return particularclientService.EvaluateArea(id);
	}
	@GetMapping("/EvaluateSeniority/{id}")
	@ResponseBody
	public float EvaluateSeniority(@PathVariable Long id ){
	return particularclientService.EvaluateSeniority(id);
	}
	// http://localhost:8081/mit/particularclient/retrieve-all-particularclients
	
	
	@GetMapping("/retrieve-all-particularclients")
	@ResponseBody
	public List<ParticularClient> getParticularClients() {
	List<ParticularClient> listParticularClients = particularclientService.retrieveAllParticularClients();
	return listParticularClients;
	}
	
	// http://localhost:8081/mit/particularclient/retrieve-particularclient/8
	@GetMapping("/retrieve-particularclient/{particularclient-id}")
	@ResponseBody
	public ParticularClient retrieveParticularClient(@PathVariable("particularclient-id") Long particularclientId) {
	return particularclientService.retrieveParticularClient(particularclientId);
	}

	// http://localhost:8081/mit/particularclient/add-particularclient
	@PostMapping("/add-particularclient")
	@ResponseBody
	public ParticularClient addParticularClient(@RequestBody ParticularClient p)
	{
	ParticularClient particularclient = particularclientService.addParticularClient(p);
	mailService.ConfirmByMail(p.getLogClientP().getEmail());
	return particularclient;
	}
	

	// http://localhost:8081/mit/particularclient/modify-particularclient
	@PutMapping("/modify-particularclient")
	@ResponseBody
	public ParticularClient modifyParticularClient(@RequestBody ParticularClient particularclient) {
	return particularclientService.updateParticularClient(particularclient);
	}
	
	// http://localhost:8081/Achat/particularclient/remove-particularclient/{particularclient-id}
	@DeleteMapping("/remove-particularclient/{particularclient-id}")
	@ResponseBody
	public void removeParticularClient(@PathVariable("particularclient-id") Long particularclientId) {
	particularclientService.deleteParticularClient(particularclientId);
	}
	
	@GetMapping("/score-particularClient/{particularClient-id}")
	@ResponseBody
	public float getScoreParticularClient(@PathVariable("particularClient-id") Long particularClientId) {
	return particularclientService.scoreParticularClient(particularClientId);
	}

	@PutMapping("/categorise-particularclient/{particularclient-id}")
	@ResponseBody
	public void categoriseParticularClient(@PathVariable Long id ) {
	particularclientService.categoriseParticularClient(id);
	}
	
	@GetMapping("/GetIdealParticularClient")
	@ResponseBody
	public ParticularClient GetIdealParticularClient() {
		return particularclientService.GetIdealParticularClient();
	}
}
