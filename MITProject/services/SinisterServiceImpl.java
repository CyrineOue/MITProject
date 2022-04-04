package tn.MITProject.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Expert;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Sinister;
import tn.MITProject.entities.Status;
import tn.MITProject.entities.Type;
import tn.MITProject.repositories.ExpertRepository;
import tn.MITProject.repositories.LogRepository;
import tn.MITProject.repositories.SinisterRepository;

@Service
public class SinisterServiceImpl implements SinisterService {

	@Autowired
	SinisterRepository sinisterRepository;
	@Autowired
	LogRepository logRepository;
	@Autowired
	ExpertRepository expertRepository;

	@Override
	public List<Sinister> retrieveAllSinisters() {
		return (List<Sinister>) sinisterRepository.findAll();
	}

	@Override
	public Sinister addSinister(Sinister s) {
		s.setDeclarationDate(Calendar.getInstance().getTime());
		s.setSinisterStatus(Status.WAITING);
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
	public String checkSinisterDelay(Long sinisterId) {
		double nbrDays = 0;
		String message = null ; 
        Sinister sinister = sinisterRepository.findById(sinisterId).orElse(null)	;
        Date a = sinister.getDeclarationDate() ;
        int b =sinister.getDeclarationDelay();
        Date c = sinister.getSinisterDate() ;
        long diff = a.getTime() - c.getTime();
        nbrDays = (diff / (1000*60*60*24));
        if(nbrDays <= b){
        	System.out.println("Declaration deadline respected. Your claim for compensation will be treated.") ;
        	message = "Declaration deadline respected. Your claim for compensation will be treated." ;
        	}
        else {
        	System.out.println("Declaration deadline expired. Your claim for compensation will be rejected.");
        	message = "Declaration deadline expired. Your claim for compensation will be rejected.";
        	}
        return message ;
	}

	@Override
	public String treatSinister(Long sinisterId) throws Exception {
		String message = null ; 
		Sinister s = sinisterRepository.findById(sinisterId).orElse(null);
		if(checkSinisterDelay(sinisterId).contains("Declaration deadline respected")){
			System.out.println(checkSinisterDelay(sinisterId).toString()) ;
			Contract c=sinisterRepository.retrieveContract( sinisterId);
			
			if(s.getDamageAmount()<=c.getCeillingAmount()){
				Date d = Calendar.getInstance().getTime();
				s.setIndemnisationDate(d);
				s.setRefundAmount(s.getDamageAmount());
			  	message = "The claim declared on:"+s.getDeclarationDate()+"has been treated."+"\n"+
				"The compensation amount is:"+s.getRefundAmount()+"refunded on:"+"\n"+s.getIndemnisationDate();
				s.setSinisterStatus(Status.TREATED);
				sinisterRepository.save(s);
				}
			else if ( s.getDamageAmount() > c.getCeillingAmount() ) {
				Date d =Calendar.getInstance().getTime();
				s.setIndemnisationDate(d);
				s.setRefundAmount(c.getCeillingAmount());
			  	message = "The claim declared on:"+s.getDeclarationDate()+"has been treated."+"\n"+
				"The compensation amount is:"+s.getRefundAmount()+"refunded on:"+"\n"+s.getIndemnisationDate();
				s.setSinisterStatus(Status.TREATED);
				sinisterRepository.save(s); 				
				}		
				
				System.out.println(retrieveSinisterById(sinisterId)); 
			}
		else{ 
			message = "The claim for compensation declared on: "+ s.getDeclarationDate()+" has been rejected." ;
		    System.out.println("The claim for compensation has been rejected.") ;
		    s.setSinisterStatus(Status.REJECTED);
		    s.setCauseOfRejection("Declaration deadline expired or attempted fraud detected.");
		    sinisterRepository.save(s);
		    System.out.println(checkSinisterDelay(sinisterId).toString()) ;
		    }
		return message+"\n";
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
        
		Sinister s= sinisterRepository.findById(sinisterId).orElse(null);
		Date d = s.getDeclarationDate(); 
		Date i = s.getIndemnisationDate();
		Long diff = i.getTime() - d.getTime();
		int sinisterSpeedSettelment = Math.round ((diff / (1000*60*60*24)));
		System.out.println("Processing days: "+sinisterSpeedSettelment);
		String rslt = " Processing days: "+sinisterSpeedSettelment ;
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
			most="Agriculture & Credit";
		}
		else if(property==agriculture) {
			most="Agriculture & Property";
		}
		else if(credit==property) {
			most="Property & Credit";
		}
		else if(credit==agriculture && credit==property && agriculture==property) {
			most="Agriculture , Credit & Propety";
		}
		System.out.println(most) ;
		return most;
		
	}

	
	@Override
	public String checkSinisterClaim(Long sinisterId) {
	Sinister sinister = sinisterRepository.findById(sinisterId).orElse(null);
		
		if(sinister.getSinisterStatus().equals("REJECTED")){
			
			return "The sinister declared on: "+sinister.getDeclarationDate()+
				   " has been rejected."+"\n"+" Cause of rejection:"+sinister.getCauseOfRejection();
			
		}else if(sinister.getSinisterStatus().equals("PROCESSING")){
		
		    return "The sinister declared on: "+sinister.getDeclarationDate()+" is being processed.";
	}
		else return "The sinister declared on: "+sinister.getDeclarationDate()+ "is still waiting to be treated.";
	}

	@Override
	public String indemnityRejectionRatio(Long idExpert) {
		
		double nbRejectedSinisters =0 ;
		double totalSinisters = 0 ;
		double indemnityRejectionRatio = 0 ;
		
		Long idLog= logRepository.findExpert(idExpert);
		List<Sinister> sinisters = sinisterRepository.retrieveSinisters(idLog); 
		
		for (Sinister s :sinisters){
				 
			if(s.getSinisterStatus().equals("REJECTED")){
					nbRejectedSinisters = nbRejectedSinisters + 1 ;
				}	     
			}
		
		  totalSinisters =	(int) sinisterRepository.count() ;
	      System.out.println("Total sinisters: " +sinisterRepository.count());
	      System.out.println("Total rejected sinisters: " +nbRejectedSinisters);

	      
	      indemnityRejectionRatio = (nbRejectedSinisters/totalSinisters)*100  ;
		
		return "Indemnity rejection ratio:"+indemnityRejectionRatio+"%";
	}
	

}
