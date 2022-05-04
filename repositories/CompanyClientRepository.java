package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.CompanyClient;

@Repository
public interface CompanyClientRepository extends CrudRepository<CompanyClient, Long> {
	/*
	@Query("SELECT count(n) FROM CompanyClient c join c.logClientC l join l.products p join p.contracts n WHERE c.idClientC= :idClientC ")
	int CountCompanyClientContracts(@Param("idClientC") Long idClientC);
	*/


}
