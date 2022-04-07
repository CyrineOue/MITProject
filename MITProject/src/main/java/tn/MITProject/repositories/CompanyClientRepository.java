package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.CompanyClient;

@Repository
public interface CompanyClientRepository extends CrudRepository<CompanyClient, Long> {
	
	@Query("SELECT c FROM CompanyClient c WHERE c.CategoriyCompanyClient(c.idClientC)= :cat ")
	CompanyClient IdealCompanyClient(@Param("cat") int cat); 
	
	@Query("SELECT c FROM CompanyClient c WHERE c.subscriptionDate between :from  and :to  and c.archived=false ")
	List<CompanyClient> retrieveCompanyClientsBySbuscriptionDate(@Param("from") Date from, @Param("to") Date to);
	
	
 
}
