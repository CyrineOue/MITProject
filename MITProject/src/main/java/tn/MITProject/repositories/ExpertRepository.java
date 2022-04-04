package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Expert;

@Repository
public interface ExpertRepository extends CrudRepository<Expert, Long> {
	
	/*@Query("SELECT SUM(e.salary) FROM Expert e join e.logExpert l where l.active=true")
	double getExpertSalaries();*/
	
	@Query("SELECT e FROM Expert e join e.logExpert l where l.active=true and e.idExpert =:idUser")
	Expert retrieveExpert(@Param("idUser") Long idUser);

}
