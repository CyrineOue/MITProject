package tn.MITProject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Admin;
import tn.MITProject.entities.Agent;
import tn.MITProject.entities.Expert;
import tn.MITProject.entities.Role;
import tn.MITProject.entities.Salary;
import tn.MITProject.repositories.AdminRepository;
import tn.MITProject.repositories.AgentRepository;
import tn.MITProject.repositories.ExpertRepository;
import tn.MITProject.repositories.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	SalaryRepository salaryrepository;
	@Autowired
	AdminRepository adminrepository;
	@Autowired
	AgentRepository agentrepository;
	@Autowired
	ExpertRepository expertrepository;
	
	@Override
	public List<Salary> retrieveAllSalaries() {
		return (List<Salary>) salaryrepository.findAll();
	}

	@Override
	public Salary addSalary(Salary s){
		salaryrepository.save(s);
		return s;
	}

	@Override
	public Salary updateSalary(Salary s) {
		salaryrepository.save(s);
		return s;
	}

	@Override
	public Salary retrieveSalary(Long id) {
		return salaryrepository.findById(id).orElse(null);

	}

	@Override
	public void deleteSalary(Long id) {
		salaryrepository.deleteById(id);
	}

	@Override
	public double getSalaries(Date startDate,Date endDate) {
		double salary=0;
		List<Salary> salaries =salaryrepository.retrieveSalaryByMonth(startDate, endDate);
		for(Salary s:salaries) {
		  if(s.getRole()==Role.ADMIN) {
			 Admin admin=adminrepository.retrieveAdmin(s.getIdUser());
			 if(admin!=null) {
			   salary+=s.getSalaryAmount();
			 }
		  }
		  else if(s.getRole()==Role.AGENT) {
			 Agent agent =agentrepository.retrieveAgent(s.getIdUser());
			 if(agent!=null) {
			   salary+=s.getSalaryAmount();
			 }
		  }
		  else if(s.getRole()==Role.EXPERT) {
			 Expert expert=expertrepository.retrieveExpert(s.getIdUser());
			 if(expert!=null) {
			   salary+=s.getSalaryAmount();				 
			 }
		  }
		}
		return salary;
	}

}
