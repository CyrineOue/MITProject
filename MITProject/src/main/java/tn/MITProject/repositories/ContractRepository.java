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
	
   /* 
   * @Query ("SELECT c FROM Contract c WHERE c.IDClient= : idClient ")

   List<Contract> FindContractsByClientId(@Param("idClient") Long idClient);
   */
	
  /* @Query ("SELECT SUM(c.CeillingAmount) FROM Contract c join c.coproduct p join p.user u join u.particularClient pc WHERE u.idClientP= : IdClientP ")

   float TotalCeillingAmount(@Param("idClientP") Long idClientP);*/

  /* @Query ("SELECT SUM(s.refundAmount) FROM Sinister s join s.logSinister l join l.particularClient pc WHERE pc.idClientP= : idClientP ")
   @Query ("SELECT SUM(s.RefundAmount) FROM Sinister s where id")

   float TotalRefundAmount(@Param("idClientP") Long idClientP);*/

	
}
