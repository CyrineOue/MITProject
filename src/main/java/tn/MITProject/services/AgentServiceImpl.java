package tn.MITProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Agent;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.AgentRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class AgentServiceImpl implements AgentService {
	
	@Autowired
	AgentRepository agentrepository;
	@Autowired
	LogRepository logrepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	@Override
	public List<Agent> retrieveAllAgents() {
		return (List<Agent>) agentrepository.findAll();
	}

	@Transactional
	public Agent addAgent(Agent a) {
		Log log= saveLog(a);
		a.setLogAgent(log);
		agentrepository.save(a);
		return a;
	}
	
	private Log saveLog(Agent a){
		Log log=a.getLogAgent();
		log.setPassword(bCryptPasswordEncoder.encode(a.getLogAgent().getPassword()));
		log.setRole(Role.AGENT);
		logrepository.save(log);
		return log;
	}

	@Override
	public void deleteAgent(Long id) {
		agentrepository.deleteById(id);
	}

	@Transactional
	public Agent updateAgent(Agent a) {
		Log log= updateLog(a);
		a.setLogAgent(log);
		agentrepository.save(a);
		return a;
	}
	
	private Log updateLog(Agent a){
		Log log=a.getLogAgent();
		log.setPassword(bCryptPasswordEncoder.encode(a.getLogAgent().getPassword()));
		log.setRole(Role.AGENT);
		logrepository.save(log);
		return log;
	}

	@Override
	public Agent retrieveAgent(Long id) {
		return agentrepository.findById(id).orElse(null);
	}

}
