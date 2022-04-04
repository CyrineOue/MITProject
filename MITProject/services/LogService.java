package tn.MITProject.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Log;
import tn.MITProject.repositories.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	public Log findLogByLogEmail(String email) {
		return logRepository.findByEmail(email);
	}
	/*public Log saveLog(Log log) {
		log.setPassword(bCryptPasswordEncoder.encode(log.getPassword()));
		log.setActive(true);
		return logRepository.save(log);
	}*/

}
