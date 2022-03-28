package tn.MITProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.ParticularClient;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.ParticularClientRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class ParticularClientServiceImpl implements ParticularClientService {
	
	@Autowired
	ParticularClientRepository particularclientrepository;
	@Autowired
	LogRepository logrepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	@Override
	public List<ParticularClient> retrieveAllParticularClients() {
		return (List<ParticularClient>) particularclientrepository.findAll();
	}

	@Transactional
	public ParticularClient addParticularClient(ParticularClient p) {
		Log log= saveLog(p);
		p.setLogClientP(log);
		particularclientrepository.save(p);
		return p;
	}
	
	private Log saveLog(ParticularClient p){
		Log log=p.getLogClientP();
		log.setPassword(bCryptPasswordEncoder.encode(p.getLogClientP().getPassword()));
		log.setRole(Role.PARTICULARCLIENT);
		logrepository.save(log);
		return log;
	}

	@Override
	public void deleteParticularClient(Long id) {
		particularclientrepository.deleteById(id);

	}

	@Transactional
	public ParticularClient updateParticularClient(ParticularClient p) {
		Log log= updateLog(p);
		p.setLogClientP(log);
		log.setPassword(bCryptPasswordEncoder.encode(p.getLogClientP().getPassword()));
		particularclientrepository.save(p);
		return p;
	}
	
	private Log updateLog(ParticularClient a){
		Log log=a.getLogClientP();
		log.setRole(Role.PARTICULARCLIENT);
		logrepository.save(log);
		return log;
	}

	@Override
	public ParticularClient retrieveParticularClient(Long id) {
		return particularclientrepository.findById(id).orElse(null);
	}

}
