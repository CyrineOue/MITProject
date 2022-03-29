package tn.MITProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Expert;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.ExpertRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class ExpertServiceImpl implements ExpertService {

	@Autowired
	ExpertRepository expertrepository;
	@Autowired
	LogRepository logrepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	
	@Override
	public List<Expert> retrieveAllExperts() {
		return(List<Expert>)expertrepository.findAll();
		
	}

	@Transactional
	public Expert addExpert(Expert e) {
		Log log= saveLog(e);
		e.setLogExpert(log);
		expertrepository.save(e);
		return e;
	}
	
	private Log saveLog(Expert e){
		Log log=e.getLogExpert();
		log.setPassword(bCryptPasswordEncoder.encode(e.getLogExpert().getPassword()));
		log.setRole(Role.EXPERT);
		logrepository.save(log);
		return log;
	}

	@Override
	public void deleteExpert(Long id) {
		expertrepository.deleteById(id);
		
	}

	@Transactional
	public Expert updateExpert(Expert e) {
		Log log= updateLog(e);
		e.setLogExpert(log);
		expertrepository.save(e);
		return e;
	}
	
	private Log updateLog(Expert e){
		Log log=e.getLogExpert();
		log.setPassword(bCryptPasswordEncoder.encode(e.getLogExpert().getPassword()));
		log.setRole(Role.EXPERT);
		logrepository.save(log);
		return log;
	}

	@Override
	public Expert retrieveExpert(Long id) {
		return expertrepository.findById(id).orElse(null);
		
	}

}
