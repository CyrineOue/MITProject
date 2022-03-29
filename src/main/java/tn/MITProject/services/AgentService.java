package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.Agent;

public interface AgentService {
	
	List<Agent> retrieveAllAgents();

	Agent addAgent (Agent a);

	void deleteAgent (Long id);

	Agent updateAgent (Agent a);

	Agent retrieveAgent (Long id);

}
