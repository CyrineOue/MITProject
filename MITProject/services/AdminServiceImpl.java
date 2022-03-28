package tn.MITProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Admin;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.AdminRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminrepository;
	@Autowired
	LogRepository logrepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	@Override
	public List<Admin> retrieveAllAdmins() {
		return (List<Admin>) adminrepository.findAll();
		
	}

	@Transactional
	public Admin addAdmin(Admin a) {
		Log log= saveLog(a);
		a.setLogAdmin(log);
		adminrepository.save(a);
		return a;
	}
	
	private Log saveLog(Admin a){
		Log log=a.getLogAdmin();
		log.setPassword(bCryptPasswordEncoder.encode(a.getLogAdmin().getPassword()));
		log.setRole(Role.ADMIN);
		logrepository.save(log);
		return log;
	}

	@Override
	public void deleteAdmin(Long id) {
		adminrepository.deleteById(id);
		
	}

	@Transactional
	public Admin updateAdmin(Admin a) {
		Log log= updateLog(a);
		a.setLogAdmin(log);
		adminrepository.save(a);
		return a;
	}
	
	private Log updateLog(Admin a){
		Log log=a.getLogAdmin();
		log.setPassword(bCryptPasswordEncoder.encode(a.getLogAdmin().getPassword()));
		log.setRole(Role.ADMIN);
		logrepository.save(log);
		return log;
	}

	@Override
	public Admin retrieveAdmin(Long id) {
		return adminrepository.findById(id).orElse(null);
	
	}

}
