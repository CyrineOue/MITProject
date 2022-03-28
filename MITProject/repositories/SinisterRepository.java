package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Sinister;

@Repository
public interface SinisterRepository extends JpaRepository<Sinister, Long> {
	
	@Query("SELECT s FROM Sinister s join Log l where l.idLog= :idLog")
	List<Sinister> retrieveSinisters(@Param("idLog") Long idLog);
	//List<Sinister> findByLogSinister_idLog(Long idLog);
	
	@Query("SELECT s FROM Sinister s join s.logSinister l join l.products p join p.contracts c "
			+ "where c.IDContract= :iDContract")
	List<Sinister> retrieveSinistersByContract(@Param("iDContract") Long iDContract);
	
	@Query("SELECT s FROM Sinister s WHERE s.declarationDate between :from and :to ")
	List<Sinister> retrieveSinisterByDeclarationDate(@Param("from") Date from, @Param("to") Date to);
	
	@Query("SELECT s FROM Sinister s WHERE s.sinisterDate between :from and :to ")
	List<Sinister> retrieveSinisterBySinisterDate(@Param("from") Date from, @Param("to") Date to);
	
	@Query("SELECT c FROM Contract c join c.coproduct p join p.user u join u.sinisters s "
			+ "where s.idSinister= :idSinister")
	Contract retrieveContract(@Param("idSinister") Long idSinister);

}
