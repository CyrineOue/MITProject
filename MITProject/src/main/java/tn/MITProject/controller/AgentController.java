package tn.MITProject.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.MITProject.entities.Agent;
import tn.MITProject.entities.Gender;
import tn.MITProject.services.AgentPDFExporter;
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
	@PutMapping("/update-agent/{id}")
	@ResponseBody
	 void  updateAgent(@PathVariable("id")Long IDAgent,@RequestBody tn.MITProject.entities.Agent agent) {
	 agentService.updateAgent(IDAgent,agent);
	}
	
	@GetMapping("/liste-AgentParGenre/{g}")
	int listeDeClientsParCategorie(@PathVariable("g") Gender genre){
		return agentService.getAgentByGenre(genre);
	}
	
	@GetMapping("/search-detail-facture")
	public List<Agent> search(@Param("keyword") String keyword) {
		List<Agent> ll = agentService.search(keyword);
		return ll;
	}
	
	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Agent_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Agent> listAgents = agentService.retrieveAllAgents();

		AgentPDFExporter exporter = new AgentPDFExporter(listAgents);
		exporter.export(response);

	}
	// http://localhost:8081/Achat/agent/remove-agent/{agent-id}
	@DeleteMapping("/remove-agent/{agent-id}")
	@ResponseBody
	public void removeAgent(@PathVariable("agent-id") Long agentId) {
	agentService.deleteAgent(agentId);
	}

}
