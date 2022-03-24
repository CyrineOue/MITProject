package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Sinister;

@Repository
public interface SinisterRepository extends CrudRepository<Sinister, Long> {

}
