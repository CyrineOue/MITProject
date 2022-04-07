package tn.MITProject.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.ParticularClient;
import tn.MITProject.entities.Area;
import tn.MITProject.entities.CategoryClient;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Role;
import tn.MITProject.repositories.ParticularClientRepository;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.LogRepository;

@Service
public class ParticularClientServiceImpl implements ParticularClientService {
	
	@Autowired
	ParticularClientRepository particularclientrepository;
	@Autowired
	LogRepository logrepository;
	@Autowired
	ContractService contractService;
	@Autowired
	ContractRepository contractRepository;
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
	
	@Override
	public float EvaluateSeniority(Long idClient) {
		ParticularClient pc =particularclientrepository.findById(idClient).orElse(null);
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
		ParticularClient pc =particularclientrepository.findById(idClient).orElse(null);
		Area ha =pc.getHomeAddress();
		if (ha==Area.TUNIS)
			return 0.75f;
		if (ha==Area.ARIANA)
			return 0.72f;
		if (ha==Area.MONASTIR)
			return 0.71f;
		if (ha==Area.BENAROUS)
			return 0.7f;
		if (ha==Area.SOUSSE)
			return 0.67f;
		if (ha==Area.NABEUL)
			return 0.6f;
		if (ha==Area.SFAX)
			return 0.58f;
		if (ha==Area.MANOUBA)
			return 0.56f;
		if (ha==Area.BIZERTE)
			return 0.52f;
		if (ha==Area.TOZEUR)
			return 0.5f;
		if (ha==Area.ZAGHOUAN)
			return 0.49f;
		if (ha==Area.KEBILI)
			return 0.45f;
		if (ha==Area.GABES)
			return 0.42f;
		if (ha==Area.MAHDIA)
			return 0.39f;
		if (ha==Area.MEDENINE)
			return 0.39f;
		if (ha==Area.GAFSA)
			return 0.38f;
		if (ha==Area.BEJA)
			return 0.33f;
		if (ha==Area.TATAOUINE)
			return 0.3f;
		if (ha==Area.KEF)
			return 0.29f;
		if (ha==Area.SIDIBOUZID)
			return 0.27f;
		if (ha==Area.SILIANA)
			return 0.26f;
		if (ha==Area.KAIROUAN)
			return 0.25f;
		if (ha==Area.KASSERINE)
			return 0.22f;
		if (ha==Area.JENDOUBA)
			return 0.22f;
		return 0;	}
	
	
	@Override
	public float scoreParticularClient(Long idClient) {
		
	 
				
		return (float) (0.3* EvaluateArea(idClient) + 0.15 * EvaluateSeniority(idClient) 
		+ 0.3 *contractService.EvaluateParticularClaimsAmount(idClient) +
		0.15 * contractService.EvaluateParticularContractsNb(idClient)
	//   + 0.1 * OnTimePaymentsRate (idClient) 
				);
	}

	@Override
	public float EvaluateClaimsAmount(Long idClient) {
		float rapport =contractRepository.TotalParticularRefundAmount(idClient)/contractRepository.TotalParticularCeillingAmount(idClient);
		if (rapport<0.25f)
			return 1f;
		else {
			if (rapport <0.5f)
				return 0.5f;
		}
		return 0f;
	}

	@Override
	public void categoriseParticularClient() {
		for (ParticularClient c : particularclientrepository.findAll() ) {
			
			if (scoreParticularClient(c.getIdClientP())>0.5) {
				c.setCategoryP(CategoryClient.TOP);
			}
			else 
				if(scoreParticularClient(c.getIdClientP())>0.25) {
					c.setCategoryP(CategoryClient.MEDIUM);
				}
				else
					c.setCategoryP(CategoryClient.LOW);
		}
		
	}

	
	
	

}
