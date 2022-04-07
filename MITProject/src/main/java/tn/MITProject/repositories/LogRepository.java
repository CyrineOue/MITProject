package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
	
	@Query("SELECT l FROM Log l WHERE l.email= :email")
	Log findByEmail(String email);
	
	@Query("SELECT idLog FROM Log l WHERE l.agent.IDAgent= :IDAgent")
	Long findAgent(@Param("IDAgent") Long IDAgent);
	@Query("SELECT idLog FROM Log l WHERE l.particularClient.idClientP= :idClientP")
	Long findClientP(@Param("idClientP") Long idClientP);
	@Query("SELECT idLog FROM Log l WHERE l.companyClient.idClientC= :idClientC")
	Long findClientC(@Param("idClientC") Long idClientC);
	@Query("SELECT idLog FROM Log l WHERE l.expert.idExpert= :idExpert")
	Long findExpert(@Param("idExpert") Long idExpert);
	
	
	
	

}
