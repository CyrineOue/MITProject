package tn.MITProject.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Sinister;
import tn.MITProject.entities.Status;
import tn.MITProject.entities.Type;
import tn.MITProject.repositories.LogRepository;
import tn.MITProject.repositories.SinisterRepository;

@Service
public class SinisterServiceImpl implements SinisterService {

	@Autowired
	SinisterRepository sinisterRepository;
	@Autowired
	LogRepository logRepository;

	@Override
	public List<Sinister> retrieveAllSinisters() {
		return (List<Sinister>) sinisterRepository.findAll();
	}

	@Override
	public Sinister addSinister(Sinister s) {
		s.setDeclarationDate(new Date());
		s.setSinisterStatus(Status.PROCESSING);
		sinisterRepository.save(s);
		return s;
	}

	@Override
	public Sinister retrieveSinisterById(Long id) {
		return  sinisterRepository.findById(id).orElse(null);
	}
	
	@Override
	public void deleteSinister(Long id) {
		sinisterRepository.deleteById(id);	
	}
	
	@Override
	public Sinister updateSinister(Sinister sinister) {
		sinisterRepository.save(sinister);
		return sinister;
	}

	@Override
	public List<Sinister> getSinisterByParticularClient(Long idPClient) {
		Long idLog= logRepository.findClientP(idPClient);
		//return (List<Sinister>) sinisterRepository.findByLogSinister_idLog(idLog);
		return (List<Sinister>) sinisterRepository.retrieveSinisters(idLog); 
	}

	@Override
	public List<Sinister> getSinisterByCompanyClient(Long idCClient) {
		Long idLog= logRepository.findClientC(idCClient);
		//return (List<Sinister>) sinisterRepository.findByLogSinister_idLog(idLog);
		return (List<Sinister>) sinisterRepository.retrieveSinisters(idLog); 
	}

	@Override
	public List<Sinister> getSinisterByExpert(Long idExpert) {
		Long idLog= logRepository.findExpert(idExpert);
		//return (List<Sinister>) sinisterRepository.findByLogSinister_idLog(idLog);
		return (List<Sinister>) sinisterRepository.retrieveSinisters(idLog); 
	}

	@Override
	public List<Sinister> retrieveSinisterByContract(Long contractId) {
		return (List<Sinister>) sinisterRepository.retrieveSinistersByContract(contractId);
	}

	@Override
	public List<Sinister> retrieveSinisterByDeclarationDate(Date from, Date to) {
		return sinisterRepository.retrieveSinisterByDeclarationDate(from, to);
	}

	@Override
	public List<Sinister> retrieveSinisterBySinisterDate(Date from, Date to) {
		return sinisterRepository.retrieveSinisterBySinisterDate(from, to);
	}

	@Override
	public Sinister affectSinisterToContract(Long idContrat, Long idSinister) {
		/*Contract c = sinisterRepository.retrieveContract(idContrat, idSinister);
		Sinister s = sinisterRepository.findById(idSinister).orElse(null) ;
	    sinisterRepository.save(s);
		return s;*/
		return null;
	}

	@Override
	public String checkSinisterDelay(Long sinisterId) {
		double nbrDays  =0;
		String message = null ; 
        Sinister sinister = sinisterRepository.findById(sinisterId).orElse(null)	;
        Date a = sinister.getDeclarationDate() ;
        Integer b =sinister.getDeclarationDelay();
        Date c = sinister.getSinisterDate() ;
        long diff = a.getTime() - c.getTime();
        nbrDays = (diff / (1000*60*60*24));
        if(nbrDays <= b){
        	System.out.println("delai bien vérifié , delai respecté !") ;
        	message = "delai bien vérifié , delai respecté !" ;
        	}
        else {
        	System.out.println("delai expiré , vous n'avez pas le droit d'indemnisation , demande rejetéé !! ");
        	message = "delai expiré , vous n'avez pas le droit d'indemnisation , demande rejetéé !! ";
        	}
        return message ;
	}

	@Override
	public String treatSinister(Long sinisterId) throws Exception {
		String message = null ; 
		Sinister s = sinisterRepository.findById(sinisterId).orElse(null);
		if(checkSinisterDelay(sinisterId).contains("delai bien vérifié , delai respecté !")){
			System.out.println(checkSinisterDelay(sinisterId).toString()) ;
			Contract c=sinisterRepository.retrieveContract( sinisterId);
			if(s.getDamageAmount()<=c.getCeillingAmount()){
				Date d =Calendar.getInstance().getTime();
				s.setIndemnisationDate(d);
				s.setRefundAmount(s.getDamageAmount());
			  	message = "votre prestation du sinistre declaré le :"+s.getDeclarationDate()+"est effectué"+"\n"+"indemnisation accéptée montantIndemnisé:"+s.getRefundAmount()+"Date d'indemnisation"+"\n"+s.getIndemnisationDate();
				s.setSinisterStatus(Status.SETTLED);
				sinisterRepository.save(s);
				}
			else if ( s.getDamageAmount() > c.getCeillingAmount() ) {
				Date d =Calendar.getInstance().getTime();
				s.setIndemnisationDate(d);
				s.setRefundAmount(c.getCeillingAmount());
			  	message = "votre prestation du sinistre declaré le :"+s.getDeclarationDate()+"est effectué"+"\n"+"indemnisation accéptée de montantIndemnisé:"+s.getRefundAmount()+"Date d'indemnisation"+"\n"+s.getIndemnisationDate();
				s.setSinisterStatus(Status.SETTLED);
				sinisterRepository.save(s); 				
				}		
				
				System.out.println(retrieveSinisterById(sinisterId)); 
			}
		else{ 
			message = "indemnisation refusée" ;
		    System.out.println("indemnisation refusée") ;
		    s.setSinisterStatus(Status.REJECTED);
		    s.setCauseOfRejection("delai ou instructions non respectés");
		    sinisterRepository.save(s);
		    System.out.println(checkSinisterDelay(sinisterId).toString()) ;
		    }
		return message+"\n"+s;
	}

	@Override
	public void assignSinisterToExpert(Long idSinister, Long idExpert) {
		Sinister sinister = sinisterRepository.findById(idSinister).orElse(null);
		Long idLog = logRepository.findExpert(idExpert);
		Log log = logRepository.findById(idLog).orElse(null);
		sinister.setLogSinister(log);
		sinisterRepository.save(sinister);
		
		
	}

	@Override
	public String sinisterSettlementSpeed(Long sinisterId) {
		
        double sinisterSpeedSettelment = 0;
		Sinister s= sinisterRepository.findById(sinisterId).orElse(null);
		Date d = s.getDeclarationDate();
		Date i = s.getIndemnisationDate();
		long diff = i.getTime() - d.getTime();
		sinisterSpeedSettelment = (diff / (1000*60*60*24));
		System.out.println("Processing days: "+sinisterSpeedSettelment);
		String rslt = sinisterSpeedSettelment +"days" ;
		return  rslt ;
	}

	@Override
	public String mostFrequentSinister() {
		List<Sinister> sinisters = sinisterRepository.findAll();
		int agriculture=0;
		int property=0;
		int credit=0;
		String most = null;
		for(Sinister sinister: sinisters){
			if(sinister.getTypeSinister()== Type.AGRICULTURE) {
				agriculture+=1;		
			}
			else if(sinister.getTypeSinister()==Type.CREDIT) {
				credit+=1;
			}
			else if(sinister.getTypeSinister()==Type.PROPERTY) {
				property+=1;
			}
		}
		if(agriculture>credit && agriculture>property) {
			most="Agriculture";
		}
		else if(credit>agriculture && credit>property) {
			most="Credit";
		}
		else if(property>agriculture && property>credit) {
			most="Property";
		}
		else if(credit==agriculture) {
			most="Agriculture and Credit";
		}
		else if(property==agriculture) {
			most="Agriculture and Property";
		}
		else if(credit==property) {
			most="Property and Credit";
		}
		else if(credit==agriculture && credit==property && agriculture==property) {
			most="Agriculture , Credit and Propety";
		}
		System.out.println(most) ;
		return most;
		
	}

}
