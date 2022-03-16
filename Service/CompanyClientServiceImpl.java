package tn.MITProject.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Area;
import tn.MITProject.entities.CompanyClient;
import tn.MITProject.repositories.CompanyClientRepository;
import tn.MITProject.repositories.ContractRepository;

@Service
public class CompanyClientServiceImpl implements CompanyClientService
{
	@Autowired
	CompanyClientRepository companyClientRepository;
	@Autowired
	ContractRepository contractRepository; 
	@Autowired
	ContractService contractService;
	
	@Override
	public List<CompanyClient> retrieveAllCompanyClients() {
		
		return (List<CompanyClient>) companyClientRepository.findAll();
	}

	@Override
	public CompanyClient addCompanyClient(CompanyClient cc) {
		return companyClientRepository.save(cc);
	}

	@Override
	public void removeCompanyClient(Long id) {
		companyClientRepository.deleteById(id);
		
		  CompanyClient cc= companyClientRepository.findById(id).orElse(null);
		  
		cc.setArchived(true);	
	
	}

	@Override
	public CompanyClient updateCompanyClient(CompanyClient cc) {
		
		return companyClientRepository.save(cc);
	}

	@Override
	public CompanyClient retrieveCompanyClient(Long id) {
		
		return companyClientRepository.findById(id).orElse(null);
	}
	
	@Override
	public float EvaluateSeniority(Long idClient) {
		CompanyClient c= companyClientRepository.findById(idClient).orElse(null);
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
		CompanyClient cp= companyClientRepository.findById(idClient).orElse(null);
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
		CompanyClient cp= companyClientRepository.findById(idClient).orElse(null);
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
		CompanyClient cp= companyClientRepository.findById(idClient).orElse(null);
		Area ae =cp.getArea();
		if (ae==Area.Tunis)
			return 0.75f;
		if (ae==Area.Ariana)
			return 0.72f;
		if (ae==Area.Monastir)
			return 0.71f;
		if (ae==Area.BenArous)
			return 0.7f;
		if (ae==Area.Sousse)
			return 0.67f;
		if (ae==Area.Nabeul)
			return 0.6f;
		if (ae==Area.Sfax)
			return 0.58f;
		if (ae==Area.Manouba)
			return 0.56f;
		if (ae==Area.Bizerte)
			return 0.52f;
		if (ae==Area.Tozeur)
			return 0.5f;
		if (ae==Area.Zaghouan)
			return 0.49f;
		if (ae==Area.Kebili)
			return 0.45f;
		if (ae==Area.Gabès)
			return 0.42f;
		if (ae==Area.Mahdia)
			return 0.39f;
		if (ae==Area.Médenine)
			return 0.39f;
		if (ae==Area.Gafsa)
			return 0.38f;
		if (ae==Area.Béja)
			return 0.33f;
		if (ae==Area.Tataouine)
			return 0.3f;
		if (ae==Area.Kef)
			return 0.29f;
		if (ae==Area.SidiBouzid)
			return 0.27f;
		if (ae==Area.Siliana)
			return 0.26f;
		if (ae==Area.Kairouan)
			return 0.25f;
		if (ae==Area.Kasserine)
			return 0.22f;
		if (ae==Area.Jendouba)
			return 0.22f;
		return 0;
	}
	
	@Override
	public float scoreCompanyClient(Long idClient) {
				return (float) (0.2 *  contractService.EvaluateContractsNb(idClient) 
						+ 0.2 * contractService.EvaluateClaimsAmount(idClient)+ 0.1 * EvaluateSeniority(idClient) 
						+0.05*EvaluateCapital(idClient) +0.05* EvaluateEmployeesNb(idClient) 
						+ 0.3* EvaluateArea(idClient)
						); 
				//		 + 0.1 *OnTimePaymentsRate 
			
	}

	@Override
	public int CategoriyCompanyClient(Long idClient) {
		if (scoreCompanyClient(idClient)>0.5) {
			//System.out.println("Ce client fait partie de la meilleure catégorie : 1");
			return 1; 
		}
		else 
			if (scoreCompanyClient(idClient)>0.25) {
				//System.out.println("Ce client fait partie de la catégorie moyenne: 2");
				return 2; }
			else {
				//System.out.println("Ce client fait partie de la faible catégorie : 3");
				return 3; }
		
	}

	
	

	
	
}
