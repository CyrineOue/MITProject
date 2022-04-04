package tn.MITProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {
	
	/*@Query("SELECT SUM(a.salary) FROM Agent a join a.logAgent l where l.active=true")
	double getAgentSalaries();*/
	
	@Query("SELECT a FROM Agent a join a.logAgent l where l.active=true and a.IDAgent =:idUser")
	Agent retrieveAgent(@Param("idUser") Long idUser);

}
