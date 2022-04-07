package tn.MITProject.repositories;

import java.util.Date;
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
	Contract ViewContracts();
	
	@Query("SELECT count(c) FROM Contract c WHERE c.IDClientP= : idClientP ")
	int CountContractsforClientP(@Param("idClientP") Long idClientP);
	
	
	@Query("SELECT count(c) FROM Contract c WHERE c.IDClientC= : idClientC ")
	int CountContractsforClientC(@Param("idClientC") Long idClientC);


	@Query("SELECT c FROM Contract c WHERE c.EndDate between :from and :to ")
	List<Contract> retrieveContractByEndDate(@Param("from") Date from, @Param("to") Date to);

	@Query("SELECT TTCPremium FROM Contract c WHERE c.StartDate between :startDate and :endDate ")
	float TotalPremium(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	
	@Query("SELECT count(c) FROM Contract c WHERE c.CreationDate between :from and :to ")
	Long countContractsNbr(@Param("from") Date from, @Param("to") Date to);

	
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
