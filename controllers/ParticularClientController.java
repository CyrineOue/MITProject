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

import tn.MITProject.Service.ParticularClientService;
import tn.MITProject.entities.ParticularClient;

@RestController
@RequestMapping("/ParticularClient")
//http://localhost:8086/MITMVC/ParticularClient
public class ParticularClientController {
	@Autowired
	ParticularClientService particularClientService;
	
	@PostMapping("/add-particularClient")
	// http://localhost:8086/MITMVC/ParticularClient/add-particularClient
	@ResponseBody
	public ParticularClient addParticularClient(@RequestBody ParticularClient cc)
	{
	ParticularClient particularClient = particularClientService.addParticularClient(cc);
	return particularClient;
	}
	
	
	@GetMapping("/retrieve-all-particularClients")
	// http://localhost:8086/MITMVC/ParticularClient/retrieve-all-particularClients
	@ResponseBody
	public List<ParticularClient> getParticularClients() {
	List<ParticularClient> listParticularClients = particularClientService.retrieveAllParticularClients();
	return listParticularClients;
	}
	
	
	@GetMapping("/retrieve-ParticularClient/{particularClient-id}")
	// http://localhost:8086/MITMVC/ParticularClient/retrieve-ParticularClient/{particularClient-id}
	@ResponseBody
	public ParticularClient retrieveParticularClient(@PathVariable("particularClient-id") Long particularClientId) {
	return particularClientService.retrieveParticularClient(particularClientId);
	}
	
	
	@DeleteMapping("/delete-ParticularClient/{particularClient-id}")
	// http://localhost:8086/MITMVC/ParticularClient/delete-ParticularClient/{particularClient-id}
	@ResponseBody
	public void deleteParticularClient(@PathVariable("particularClient-id") Long particularClientId) {
	particularClientService.removeParticularClient(particularClientId);
	}
	
	@PutMapping("/modify-particularClient")
	// http://localhost:8086/MITMVC/ParticularClient/modify-particularClient
	@ResponseBody
	public ParticularClient modifyParticularClient(@RequestBody ParticularClient particularClient ) {
		
		return particularClientService.updateParticularClient(particularClient) ;
	}
}
