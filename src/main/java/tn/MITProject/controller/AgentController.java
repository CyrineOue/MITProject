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

import tn.MITProject.entities.Agent;
import tn.MITProject.services.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	AgentService agentService;

	// http://localhost:8081/mit/agent/retrieve-all-agents
	@GetMapping("/retrieve-all-agents")
	@ResponseBody
	public List<Agent> getAgents() {
	List<Agent> listAgents = agentService.retrieveAllAgents();
	return listAgents;
	}
	
	// http://localhost:8081/mit/agent/retrieve-agent/8
	@GetMapping("/retrieve-agent/{agent-id}")
	@ResponseBody
	public Agent retrieveAgent(@PathVariable("agent-id") Long agentId) {
	return agentService.retrieveAgent(agentId);
	}

	// http://localhost:8081/mit/agent/add-agent
	@PostMapping("/add-agent")
	@ResponseBody
	public Agent addAgent(@RequestBody Agent a)
	{
	Agent agent = agentService.addAgent(a);
	return agent;
	}
	

	// http://localhost:8081/mit/agent/modify-agent
	@PutMapping("/modify-agent")
	@ResponseBody
	public Agent modifyAgent(@RequestBody Agent agent) {
	return agentService.updateAgent(agent);
	}
	
	// http://localhost:8081/Achat/agent/remove-agent/{agent-id}
	@DeleteMapping("/remove-agent/{agent-id}")
	@ResponseBody
	public void removeAgent(@PathVariable("agent-id") Long agentId) {
	agentService.deleteAgent(agentId);
	}

}
