package tn.MITProject.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>  {
	//@Query("SELECT c FROM Contract c WHERE c.Produit.IDProduct= :IdProduct and c.EndDate=31/12/2021")
	//List<Contract> getContractByProduct(@Param("IdProduct") Long productid);
	
	
	
	@Query("SELECT count(c) FROM Contract c WHERE c.IDClient= : idClient ")
		int CountContracts(@Param("idClient") Long idClient);
	
	/* 
	 * @Query ("SELECT c FROM Contract c WHERE c.IDClient= : idClient ")
	
	List<Contract> FindContractsByClientId(@Param("idClient") Long idClient);
	 */
	@Query ("SELECT count (c.CellingAmount) FROM Contract c WHERE c.IDClient= : idClient ")
	
	float TotalCeillingAmount(@Param("idClient") Long idClient);
	
	@Query ("SELECT count (c.payment.RefundAmount) FROM Contract c WHERE c.IDClient= : idClient ")
	
	float TotalRefundAmount(@Param("idClient") Long idClient);
	
	
	
	
}
