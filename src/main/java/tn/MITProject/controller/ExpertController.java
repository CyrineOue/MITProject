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

import tn.MITProject.entities.Expert;
import tn.MITProject.services.ExpertService;

@RestController
@RequestMapping("/expert")
public class ExpertController {
	
	@Autowired
	ExpertService expertService;

	// http://localhost:8081/mit/expert/retrieve-all-experts
	@GetMapping("/retrieve-all-experts")
	@ResponseBody
	public List<Expert> getExperts() {
	List<Expert> listExperts = expertService.retrieveAllExperts();
	return listExperts;
	}
	
	// http://localhost:8081/mit/expert/retrieve-expert/8
	@GetMapping("/retrieve-expert/{expert-id}")
	@ResponseBody
	public Expert retrieveExpert(@PathVariable("expert-id") Long expertId) {
	return expertService.retrieveExpert(expertId);
	}

	// http://localhost:8081/mit/expert/add-expert
	@PostMapping("/add-expert")
	@ResponseBody
	public Expert addExpert(@RequestBody Expert e)
	{
	Expert expert = expertService.addExpert(e);
	return expert;
	}
	

	// http://localhost:8081/mit/expert/modify-expert
	@PutMapping("/modify-expert")
	@ResponseBody
	public Expert modifyExpert(@RequestBody Expert expert) {
	return expertService.updateExpert(expert);
	}
	
	// http://localhost:8081/Achat/expert/remove-expert/{expert-id}
	@DeleteMapping("/remove-expert/{expert-id}")
	@ResponseBody
	public void removeExpert(@PathVariable("expert-id") Long expertId) {
	expertService.deleteExpert(expertId);
	}

}
