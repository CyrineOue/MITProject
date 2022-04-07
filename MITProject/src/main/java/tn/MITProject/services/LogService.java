package tn.MITProject.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Log;
import tn.MITProject.repositories.LogRepository;

@Service
public class LogService implements ILogService {
	
	@Autowired
	private LogRepository logRepository;
	//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	@Override
	public Log findLogByLogEmail(String email) {
		return logRepository.findByEmail(email);
	}
	/*public Log saveLog(Log log) {
		log.setPassword(bCryptPasswordEncoder.encode(log.getPassword()));
		log.setActive(true);
		return logRepository.save(log);
	}*/
	
	@Override
	public void fireAgent(Long id) {
		Long idLog= logRepository.findAgent(id);
		Log agent=logRepository.findById(idLog).orElse(null);
	    agent.setActive(false);	
	    logRepository.save(agent);
	    }
	
	@Override
	public void fireExpert(Long id) {
		Long idLog=logRepository.findExpert(id);
		Log expert=logRepository.findById(idLog).orElse(null);
	    expert.setActive(false);
	    logRepository.save(expert);
	}

}
