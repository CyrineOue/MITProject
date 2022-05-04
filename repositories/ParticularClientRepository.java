package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.ParticularClient;

@Repository
public interface ParticularClientRepository extends CrudRepository<ParticularClient, Long> {
	/*
	@Query("SELECT p FROM ParticularClient p WHERE p.CategoriyParticularClient(p.idClientP)= :cat ")
	ParticularClient IdealParticularClient(@Param("cat") int cat);
*/
}
