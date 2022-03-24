package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
	
	@Query("SELECT l FROM Log l WHERE l.email= :email")
	Log findByEmail(String email);

}
