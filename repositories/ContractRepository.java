package tn.MITProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
	
	@Query("SELECT c FROM Contract c WHERE c.coproduct.IDProduct= :IdProduct")
	List<Contract> getContractByProduct(@Param("IdProduct") Long productid);
	
	
	@Query("SELECT c FROM Contract c ")
	List<Contract> ViewContracts();
	
	@Query("SELECT count(c) FROM Contract c WHERE c.IDClient= : idClient ")
	int CountContracts(@Param("idClient") Long idClient);


	@Query("SELECT count(c) FROM Contract c join c.coproduct p join p.user u join u.particularClient pc WHERE pc.idClientP= :idClientP ")
	int CountParticularClientContracts(@Param("idClientP") Long idClientP);
	@Query("SELECT count(c) FROM Contract c join c.coproduct p join p.user u join u.companyClient cc WHERE cc.idClientC= :idClientC ")
	int CountCompanyClientContracts(@Param("idClientC") Long idClientC);
	
	  @Query ("SELECT SUM(c.CeillingAmount) FROM Contract c join c.coproduct p join p.user u join u.particularClient pc WHERE pc.idClientP= :idClientP ")
	   float TotalParticularCeillingAmount(@Param("idClientP") Long idClientP);

	   @Query ("SELECT SUM(s.refundAmount) FROM Sinister s join s.logSinister l join l.particularClient pc WHERE pc.idClientP= :idClientP ")
	   float TotalParticularRefundAmount(@Param("idClientP") Long idClientP);
	   
	   @Query ("SELECT SUM(c.CeillingAmount) FROM Contract c join c.coproduct p join p.user u join u.companyClient cc WHERE cc.idClientC= :idClientC ")
	   float TotalCompanyCeillingAmount(@Param("idClientC") Long idClientC);

	   @Query ("SELECT SUM(s.refundAmount) FROM Sinister s join s.logSinister l join l.companyClient cc WHERE cc.idClientC= :idClientC ")
	   float TotalCompanyRefundAmount(@Param("idClientC") Long idClientC);

	
}
