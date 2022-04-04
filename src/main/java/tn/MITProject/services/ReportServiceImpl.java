package tn.MITProject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.MITProject.entities.Admin;
import tn.MITProject.entities.CompanyClient;
import tn.MITProject.entities.Contract;
import tn.MITProject.entities.ParticularClient;
import tn.MITProject.entities.Report;
import tn.MITProject.repositories.AdminRepository;
import tn.MITProject.repositories.AgentRepository;
import tn.MITProject.repositories.CompanyClientRepository;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.ExpertRepository;
import tn.MITProject.repositories.ParticularClientRepository;
import tn.MITProject.repositories.PaymentRepository;
import tn.MITProject.repositories.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	ReportRepository reportrepository;
	@Autowired
	ContractRepository contractrepository;
	@Autowired
	PaymentRepository paymentrepository;
	@Autowired
	AdminRepository adminrepository;
	@Autowired
	AgentRepository agentrepository;
	@Autowired
	ExpertRepository expertrepository;
	@Autowired
    SalaryService salaryservice;
	@Autowired
	CompanyClientService companyClientService;
	@Autowired
	ParticularClientService particularClientService;
	@Autowired
	ParticularClientRepository particularClientRepository;
	@Autowired
	CompanyClientRepository companyClientRepository;

	@Override
	public List<Report> retrieveAllReports() {
		return (List<Report>) reportrepository.findAll();
	}

	@Override
	public Report addReport(Report r) {
		r.setTurnover(calculateTurnover(r.getStartDate(), r.getEndDate()));
		r.setNetIncome(calculateNetIncome(r, r.getStartDate(), r.getEndDate()));
		r.setNetIncomeMargin(calculateNetIncomeMargin(r));
		r.setMonthCompanyClient(bestCompanyClient(r.getStartDate(), r.getEndDate()));
		r.setMonthParticularClient(bestParticularClient(r.getStartDate(), r.getEndDate()));
		reportrepository.save(r);
		return r;
	}

	@Override
	public void deleteReport(Long id) {
		reportrepository.deleteById(id);
		
	}

	@Override
	public Report updateReport(Report r) {
		r.setTurnover(calculateNetIncome(r, r.getStartDate(), r.getEndDate()));
		r.setNetIncome(calculateNetIncome(r, r.getStartDate(), r.getEndDate()));
		reportrepository.save(r);
		return r;
	}

	@Override
	public Report retrieveReport(Long id) {
		return reportrepository.findById(id).orElse(null);
	}

	@Override
	public double calculateTurnover(Date startDate,Date endDate) {	
		List<Contract> contract = (List<Contract>) contractrepository.findAll();
		double turnover=0;
		for(Contract c: contract) {
			turnover+=paymentrepository.getPaidPremium(c.getIDContract(), startDate, endDate);		
		}
	    return turnover;
	}

	@Override
	public double calculateNetIncome(Report r, Date startDate,Date endDate) {
		double NetIncome=0;
		//double salaries = adminrepository.getAdminSalaries()+agentrepository.getAgentSalaries()+expertrepository.getExpertSalaries();
		double profit = calculateTurnover(startDate, endDate)-r.getVariableCharges()-r.getFixedCharges()-salaryservice.getSalaries(startDate, endDate);
		if(profit>0){
		double incomeTax = (profit*r.getIncomeTaxRate())/100;
		 NetIncome += profit-incomeTax;
		}
		else {
			NetIncome += profit;
		}
		return NetIncome;
	}
	
	@Override
	public double calculateNetIncomeMargin(Report r) {
		double NetIncomeMargin=(r.getNetIncome()/r.getTurnover())*100;
		return NetIncomeMargin;
	}

	@Override
	public List<Report> retrieveReportByStartDate(Date from, Date to) {
		return reportrepository.retrieveReportByStartDate(from, to);
	}
	
	@Override
	public void assignReportToAdmin(Long idReport, Long idAdmin) {
		Report r=reportrepository.findById(idReport).orElse(null);
		Admin a=adminrepository.findById(idAdmin).orElse(null);
		r.setAdmin(a);
		reportrepository.save(r);
		
	}
	
	@Override
	public String bestParticularClient(Date startDate,Date endDate) {
		float score=0;
		ParticularClient bestClient=null;
		List<ParticularClient> pClients=particularClientService.retrieveAllParticularClients()/*particularClientRepository.retrieveParticularClientsBySbuscriptionDate(startDate, endDate)*/;
		for(ParticularClient pc:pClients ) {
			if(particularClientService.scoreParticularClient(pc.getIdClientP())>score) {
			    bestClient=pc;
				score=particularClientService.scoreParticularClient(pc.getIdClientP());
			}
		}
		return bestClient.getIdClientP()+bestClient.getFirstName()+bestClient.getLastName();
	}
	
	@Override
	public String bestCompanyClient(Date startDate,Date endDate) {
		float score=0;
		CompanyClient bestClient=null;
		List<CompanyClient> cClients=companyClientService.retrieveAllCompanyClients();/*companyClientRepository.retrieveCompanyClientsBySbuscriptionDate(startDate, endDate)*/;
		for(CompanyClient cc:cClients ) {
			if(companyClientService.scoreCompanyClient(cc.getIdClientC())>score) {
			    bestClient=cc;
				score=companyClientService.scoreCompanyClient(cc.getIdClientC());
			}
		}
		return bestClient.getIdClientC()+bestClient.getBrand();
	}


}
