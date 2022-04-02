package tn.MITProject.services;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import tn.MITProject.entities.Area;
import tn.MITProject.entities.CategoryClient;
import tn.MITProject.entities.Product;
import tn.MITProject.entities.CompanyClient;
import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.CompanyClientRepository;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class CompanyClientServiceImpl implements CompanyClientService {
	
	@Autowired
	CompanyClientRepository companyclientrepository;
	@Autowired
	LogRepository logrepository;
	@Autowired
	ContractRepository contractRepository; 
	@Autowired
	ContractService contractService;
	@Autowired
	PaymentService paymentService;
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
	
	@Override
	public float EvaluateSeniority(Long idClient) {
		CompanyClient c= companyclientrepository.findById(idClient).orElse(null);
		LocalDate ld= c.getSbuscriptionDate();
		long Seniority = ld.until(LocalDate.now(),ChronoUnit.YEARS);
		if (Seniority>5) {
			return 1; }
		else {
				if (Seniority>3)
					return  0.5f;
			}
			
			return 0;
	}
	@Override
	public float EvaluateCapital(Long idClient) {
		CompanyClient cp= companyclientrepository.findById(idClient).orElse(null);
		long capital =cp.getCapital();
		if ( capital >=20000 )
			return 1;
		else 
			if (capital>=5000)
				return 0.5f;
		return 0;
		
	}

	@Override
	public float EvaluateEmployeesNb(Long idClient) {
		CompanyClient cp= companyclientrepository.findById(idClient).orElse(null);
		int EmplNb =cp.getEmployeesNb();
		if ( EmplNb >=30 )
			return 1;
		else 
			if (EmplNb>=9)
				return 0.5f;
		return 0;
	
	}
	
	@Override
	public float EvaluateArea(Long idClient) {
		CompanyClient cp= companyclientrepository.findById(idClient).orElse(null);
		Area ae =cp.getActivityArea();
		if (ae==Area.TUNIS)
			return 0.75f;
		if (ae==Area.ARIANA)
			return 0.72f;
		if (ae==Area.MONASTIR)
			return 0.71f;
		if (ae==Area.BENAROUS)
			return 0.7f;
		if (ae==Area.SOUSSE)
			return 0.67f;
		if (ae==Area.NABEUL)
			return 0.6f;
		if (ae==Area.SFAX)
			return 0.58f;
		if (ae==Area.MANOUBA)
			return 0.56f;
		if (ae==Area.BIZERTE)
			return 0.52f;
		if (ae==Area.TOZEUR)
			return 0.5f;
		if (ae==Area.ZAGHOUAN)
			return 0.49f;
		if (ae==Area.KEBILI)
			return 0.45f;
		if (ae==Area.GABES)
			return 0.42f;
		if (ae==Area.MAHDIA)
			return 0.39f;
		if (ae==Area.MEDENINE)
			return 0.39f;
		if (ae==Area.GAFSA)
			return 0.38f;
		if (ae==Area.BEJA)
			return 0.33f;
		if (ae==Area.TATAOUINE)
			return 0.3f;
		if (ae==Area.KEF)
			return 0.29f;
		if (ae==Area.SIDIBOUZID)
			return 0.27f;
		if (ae==Area.SILIANA)
			return 0.26f;
		if (ae==Area.KAIROUAN)
			return 0.25f;
		if (ae==Area.KASSERINE)
			return 0.22f;
		if (ae==Area.JENDOUBA)
			return 0.22f;
		return 0;
	}
	@Override
	public float TotalCeillingAmount(Long idClient) {
		CompanyClient cc=companyclientrepository.findById(idClient).orElse(null);
		float rst =0f;
		for (Product p: cc.getLogClientC().getProducts()) {
			for (Contract c: p.getContracts()) {
				rst+=c.getCeillingAmount();
			}
		}
		return rst;
	}
	@Override
	public float TotalRefundAmount(Long idClient) {
		CompanyClient cc=companyclientrepository.findById(idClient).orElse(null);
		float rst =0f;
		for (Product p: cc.getLogClientC().getProducts()) {
			for (Contract c: p.getContracts()) {
				for (Payment pay:c.getPayments()) {
					rst+=pay.getRefundAmount();
				}
			}
		}
		return rst;
	}
	@Override
	public float EvaluateClaimsAmount(Long idClient) {
		 
		float rapport =TotalRefundAmount(idClient)/TotalCeillingAmount(idClient);
		if (rapport<0.25)
			return 1;
		else {
			if (rapport <0.5)
				return 0.5f;
		}
		return 0;
		
	}

	@Override
	public float scoreCompanyClient(Long idClient) {
		
				return (float) (0.2 *  contractService.EvaluateContractsNb(idClient) 
						+ 0.2 * EvaluateClaimsAmount(idClient)+ 0.1 * EvaluateSeniority(idClient) 
						+0.1*EvaluateCapital(idClient) +0.1* EvaluateEmployeesNb(idClient) 
						+ 0.3* EvaluateArea(idClient) 
						); 		 	
	}



	@Override
	public CategoryClient CategoriseCompanyClient(Long idClient) {
		CompanyClient cc=companyclientrepository.findById(idClient).orElse(null);
		if (scoreCompanyClient(idClient)>0.5) {
			cc.setCategoryC(CategoryClient.TOP);
		}
		else 
			if(scoreCompanyClient(idClient)>0.25) {
				cc.setCategoryC(CategoryClient.MEDIUM);
			}
			else
				cc.setCategoryC(CategoryClient.LOW);
		return cc.getCategoryC();
	}



}
