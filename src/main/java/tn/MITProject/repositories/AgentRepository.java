package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {

}
