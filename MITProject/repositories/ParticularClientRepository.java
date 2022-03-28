package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.ParticularClient;

@Repository
public interface ParticularClientRepository extends CrudRepository<ParticularClient, Long> {

}
