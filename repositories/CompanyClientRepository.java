package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.CompanyClient;


@Repository
public interface CompanyClientRepository extends CrudRepository<CompanyClient, Long> {
	@Query("SELECT c FROM CompanyClient c WHERE c.CategoriyCompanyClient(c.idClientC)= :cat ")
	CompanyClient IdealCompanyClient(@Param("cat") int cat); 
}
