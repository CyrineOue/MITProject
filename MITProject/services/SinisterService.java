package tn.MITProject.services;

import java.util.Date;
import java.util.List;

import tn.MITProject.entities.Sinister;

public interface SinisterService {
	List<Sinister> retrieveAllSinisters();
	Sinister addSinister (Sinister s);
    void deleteSinister (Long id);
	Sinister updateSinister(Sinister sinister );
	Sinister retrieveSinisterById (Long id);
	List<Sinister> getSinisterByParticularClient(Long idPClient);
	List<Sinister> getSinisterByCompanyClient(Long idCClient);
	List<Sinister> getSinisterByExpert(Long idClient);
	List<Sinister> retrieveSinisterByContract(Long contractId);
	List<Sinister> retrieveSinisterByDeclarationDate(Date from , Date to );
	List<Sinister> retrieveSinisterBySinisterDate(Date from , Date to );
	void assignSinisterToExpert(Long idSinister, Long idExpert);
	String checkSinisterDelay(Long sinisterId) ;
	String treatSinister(Long siniterId ) throws Exception;
	String sinisterSettlementSpeed(Long sinisterId);
	String mostFrequentSinister();
	String checkSinisterClaim(Long sinisterId);
	String indemnityRejectionRatio(Long idExpert);

}
