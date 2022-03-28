package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Expert;

@Repository
public interface ExpertRepository extends CrudRepository<Expert, Long> {

}
