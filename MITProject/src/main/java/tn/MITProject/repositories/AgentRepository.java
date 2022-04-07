package tn.MITProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Agent;
import tn.MITProject.entities.Gender;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {
	
	/*@Query("SELECT SUM(a.salary) FROM Agent a join a.logAgent l where l.active=true")
	double getAgentSalaries();*/
	
	@Query("SELECT a FROM Agent a join a.logAgent l where l.active=true and a.IDAgent =:idUser")
	Agent retrieveAgent(@Param("idUser") Long idUser);
	
	@Query(" select count(c) from Agent c where c.genre=:genre")
	int getAgentByGenre(@Param("genre") Gender genre);
	
	@Query("SELECT ag FROM Agent ag WHERE ag.LastName LIKE %:keyword%"
	        + " OR CONCAT(ag.Name, '') LIKE %:keyword%"
	        + " OR ag.genre LIKE %:keyword%")
	List<Agent> search(@Param("keyword") String keyword);

}
