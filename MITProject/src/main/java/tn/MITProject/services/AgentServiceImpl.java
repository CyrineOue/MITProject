package tn.MITProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Agent;
import tn.MITProject.entities.Gender;
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
	
	@Override
	public void updateAgent(Long IDAgent ,Agent A) {
		// TODO Auto-generated method stub
		Agent aa = agentrepository.findById(IDAgent).get();
		Log log= updateLog(aa);
		aa.setLogAgent(log);
		aa.setLastName(A.getLastName());
		aa.setName(A.getName());
		aa.setContractsNb(A.getContractsNb());
		aa.setPhoneNb(A.getPhoneNb());

		agentrepository.save(aa);
		
	}
	
	@Override
	public int getAgentByGenre(Gender genre) {
		return agentrepository.getAgentByGenre(genre);
	}
	
	@Override
	public List<Agent> search(String keyword) {
		if (keyword != null) {
            return agentrepository.search(keyword);
        }
        return (List<Agent>) agentrepository.findAll();
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


