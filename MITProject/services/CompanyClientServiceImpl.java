package tn.MITProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.CompanyClient;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.CompanyClientRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class CompanyClientServiceImpl implements CompanyClientService {
	
	@Autowired
	CompanyClientRepository companyclientrepository;
	@Autowired
	LogRepository logrepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	@Override
	public List<CompanyClient> retrieveAllCompanyClients() {
		return (List<CompanyClient>) companyclientrepository.findAll();
	}

	@Transactional
	public CompanyClient addCompanyClient(CompanyClient c) {
		Log log= saveLog(c);
		c.setLogClientC(log);
		companyclientrepository.save(c);
		return c;
	}
	
	private Log saveLog(CompanyClient c){
		Log log=c.getLogClientC();
		log.setPassword(bCryptPasswordEncoder.encode(c.getLogClientC().getPassword()));
		log.setRole(Role.COMPANYCLIENT);
		logrepository.save(log);
		return log;
	}


	@Override
	public void deleteCompanyClient(Long id) {
		companyclientrepository.deleteById(id);

	}

	@Transactional
	public CompanyClient updateCompanyClient(CompanyClient c) {
		Log log= updateLog(c);
		c.setLogClientC(log);
		companyclientrepository.save(c);
		return c;
	}
	
	private Log updateLog(CompanyClient c){
		Log log=c.getLogClientC();
		log.setPassword(bCryptPasswordEncoder.encode(c.getLogClientC().getPassword()));
		log.setRole(Role.COMPANYCLIENT);
		logrepository.save(log);
		return log;
	}

	@Override
	public CompanyClient retrieveCompanyClient(Long id) {
		return companyclientrepository.findById(id).orElse(null);
	}

}
