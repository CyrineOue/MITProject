package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.Agent;
import tn.MITProject.entities.Gender;

public interface AgentService {
	
	List<Agent> retrieveAllAgents();

	Agent addAgent (Agent a);

	void deleteAgent (Long id);

	Agent retrieveAgent (Long id);
	
	public void updateAgent(Long IDAgent ,Agent A);
	public int getAgentByGenre(Gender genre);
	public List<Agent> search(String keyword);
	

}
