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

import tn.MITProject.entities.ParticularClient;
import tn.MITProject.services.ParticularClientService;

@RestController
@RequestMapping("/particularclient")
public class ParticularClientController {
	
	@Autowired
	ParticularClientService particularclientService;

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

}
