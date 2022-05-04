package tn.MITProject.services;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import tn.MITProject.entities.Area;
import tn.MITProject.entities.CategoryClient;
import tn.MITProject.entities.CompanyClient;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Product;
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
	@Autowired 
	MailService mailService;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	@Override
	public List<CompanyClient> retrieveAllCompanyClients() {
		List<CompanyClient> listC= (List<CompanyClient>) companyclientrepository.findAll(); 
		for (CompanyClient c : listC) {
			c.setScoreC(this.scoreCompanyClient(c.getIdClientC()));
			this.CategoriseCompanyClient(c.getIdClientC());
		}
		return listC;
	}

	@Transactional
	public CompanyClient addCompanyClient(CompanyClient c) {
		Log log= saveLog(c);
		c.setLogClientC(log);
		companyclientrepository.save(c);
		mailService.ConfirmByMail(log.getEmail());
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
		CompanyClient pc = companyclientrepository.findById(id).orElse(null);
		pc.setArchived(true);
		companyclientrepository.save(pc);

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
		// System.out.println("TotalCompanyRefundAmount" + contractRepository.TotalCompanyRefundAmount(id));
		return companyclientrepository.findById(id).orElse(null);
	}
	
	@Override
	public float EvaluateSeniority(Long idClient) {
		if(companyclientrepository.existsById(idClient)) {
		
		CompanyClient c= companyclientrepository.findById(idClient).orElse(null);
		int thisYear = LocalDate.now().getYear();
		int subYear =c.getSbuscriptionDate().getYear();
		int Seniority  =thisYear-subYear;
		if (Seniority>3) {
			return 1; }
		else {
				if (Seniority>1)
					return  0.5f;
			}
		return 0;
		}
		System.out.println("Client introuvable");
			return 0;
	}
	@Override
	public float EvaluateCapital(Long idClient) {
		if(companyclientrepository.existsById(idClient)) {
		CompanyClient cp= companyclientrepository.findById(idClient).orElse(null);
		long capital =cp.getCapital();
		if ( capital >=20000 )
			return 1;
		else 
			if (capital>=5000)
				return 0.5f;
		return 0;
		}
		return 0; 
	}

	@Override
	public float EvaluateEmployeesNb(Long idClient) {
		if(companyclientrepository.existsById(idClient)) {
		CompanyClient cp= companyclientrepository.findById(idClient).orElse(null);
		int EmplNb =cp.getEmployeesNb();
		if ( EmplNb >=30 )
			return 1;
		else 
			if (EmplNb>=9)
				return 0.5f;
		return 0;
		}
		return 0;

	}
	
	@Override
	public float EvaluateArea(Long idClient) {
		if(companyclientrepository.existsById(idClient)) {

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
		return 0;
	}

	@Override
	public float scoreCompanyClient(Long idClient) {
		if(companyclientrepository.existsById(idClient)) {
				return (float) (
						+ 0.1 * EvaluateSeniority(idClient) 
						+0.1*EvaluateCapital(idClient) +0.1* EvaluateEmployeesNb(idClient) 
						+ 0.3* EvaluateArea(idClient) 
						); 		
				//+0.2 *  contractService.EvaluateCompanyContractsNb(idClient) 
				//+0.2 * contractService.EvaluateCompanyClaimsAmount(idClient)

		}
		System.out.println("Le client n'existe pas");
		return 0;
	}

	//@Scheduled(cron = "*/30 * * * * *" )
	// @Scheduled(cron="0 8 1 * * *")
	@Override
	public void CategoriseCompanyClient(Long id) {
		
	if (companyclientrepository.existsById(id)){
		CompanyClient c= companyclientrepository.findById(id).orElse(null);

		if (scoreCompanyClient(c.getIdClientC())>0.5) {
			c.setCategoryC(CategoryClient.TOP);
			System.out.println("Le client compagnie "
					+ c.getIdClientC() +" est de categorie TOP");
		}
		else {
			if(scoreCompanyClient(c.getIdClientC())>0.25) {
				c.setCategoryC(CategoryClient.MEDIUM);
				System.out.println("Le client compagnie "
						+ c.getIdClientC() +" est de categorie MEDIUM");
			}
			else {
				c.setCategoryC(CategoryClient.LOW);
		System.out.println("Le client compagnie "
				+ c.getIdClientC() +" est de categorie LOW");
			}
		
		}
	
	}
	else 
		System.out.println("Client introuvable ");

}
	

	@Override
	public CompanyClient GetIdealCompanyClient() {
	float maxScore=0;
		for ( CompanyClient cc: companyclientrepository.findAll()) {
			if (scoreCompanyClient(cc.getIdClientC())>maxScore) {
				maxScore=scoreCompanyClient(cc.getIdClientC());
				return cc;
			}
			
		}
		return null;
	}

	@Override
	public int countContracts(Long id) {
		CompanyClient cp= companyclientrepository.findById(id).orElse(null);
		
		int resultat =0;
		System.out.println("resultat = "+resultat);
		Log log=cp.getLogClientC();
		
		Set<Product> products = log.getProducts();
		System.out.println("products = "+products);
		for (Product p: products) {
			p.getContracts();
			System.out.println("contracts  = "+p.getContracts());

			resultat+=p.getContracts().size();
			System.out.println("resultat = "+resultat);
		}
		return resultat;
	}
	



}
