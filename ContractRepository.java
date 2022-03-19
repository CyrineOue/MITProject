package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>  {
	/*@Query("SELECT c FROM Contract c WHERE c.Produit.IDProduct= :IdProduct and c.EndDate= :31-12-2021")
	List<Contract> getContractByProduct(@Param("IdProduct") Long productid, Date EndDate);*/
	@Query("SELECT c FROM Contract c ")
	List<Contract> ViewContracts();
}
