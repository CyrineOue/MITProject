package tn.MITProject.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Area;
import tn.MITProject.entities.ParticularClient;
import tn.MITProject.repositories.ParticularClientRepository;

@Service
public class ParticularClientServiceImpl implements ParticularClientService {
	@Autowired
	ParticularClientRepository particularClientRepository;
	@Autowired
	ContractService contractService;

	@Override
	public List<ParticularClient> retrieveAllParticularClients() {
		
		return ( List<ParticularClient>) particularClientRepository.findAll();
	}

	@Override
	public ParticularClient addParticularClient(ParticularClient pc) {
		
		return particularClientRepository.save(pc);
	}

	@Override
	public void removeParticularClient(Long id) {
		/*
		 * ParticularClient pc =particularClientRepository.findById(id).orElse(null);
		 * pc.setArchived(true);
		 */
		particularClientRepository.deleteById(id);
		
	}

	@Override
	public ParticularClient updateParticularClient (ParticularClient pc  ) {
	
		return particularClientRepository.save(pc) ;
	}

	@Override
	public ParticularClient retrieveParticularClient(Long id) {
		
		return particularClientRepository.findById(id).orElse(null);
	}
	

	

	@Override
	public float EvaluateSeniority(Long idClient) {
		ParticularClient pc =particularClientRepository.findById(idClient).orElse(null);
		LocalDate ld= pc.getSbuscriptionDate();
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
	public float EvaluateArea(Long idClient) {
		ParticularClient pc =particularClientRepository.findById(idClient).orElse(null);
		Area ha =pc.getHomeAddress();
		if (ha==Area.Tunis)
			return 0.75f;
		if (ha==Area.Ariana)
			return 0.72f;
		if (ha==Area.Monastir)
			return 0.71f;
		if (ha==Area.BenArous)
			return 0.7f;
		if (ha==Area.Sousse)
			return 0.67f;
		if (ha==Area.Nabeul)
			return 0.6f;
		if (ha==Area.Sfax)
			return 0.58f;
		if (ha==Area.Manouba)
			return 0.56f;
		if (ha==Area.Bizerte)
			return 0.52f;
		if (ha==Area.Tozeur)
			return 0.5f;
		if (ha==Area.Zaghouan)
			return 0.49f;
		if (ha==Area.Kebili)
			return 0.45f;
		if (ha==Area.Gabès)
			return 0.42f;
		if (ha==Area.Mahdia)
			return 0.39f;
		if (ha==Area.Médenine)
			return 0.39f;
		if (ha==Area.Gafsa)
			return 0.38f;
		if (ha==Area.Béja)
			return 0.33f;
		if (ha==Area.Tataouine)
			return 0.3f;
		if (ha==Area.Kef)
			return 0.29f;
		if (ha==Area.SidiBouzid)
			return 0.27f;
		if (ha==Area.Siliana)
			return 0.26f;
		if (ha==Area.Kairouan)
			return 0.25f;
		if (ha==Area.Kasserine)
			return 0.22f;
		if (ha==Area.Jendouba)
			return 0.22f;
		return 0;	}
	
	
	@Override
	public float scoreParticularClient(Long idClient) {
		
	 
				
		return (float) (0.3* EvaluateArea(idClient) + 0.15 * EvaluateSeniority(idClient) 
		+ 0.3 *contractService.EvaluateClaimsAmount(idClient) +
		0.15 * contractService.EvaluateContractsNb(idClient)
	//   + 0.1 * OnTimePaymentsRate 
				);
	}

	@Override
	public int CategoriyParticularClient(Long idClient) {
		if (scoreParticularClient(idClient)>0.5)
			return 1; 
		else 
			if (scoreParticularClient(idClient)>0.25)
				return 2;
			else 
				return 3;
		
	}
}
