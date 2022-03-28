package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.CompanyClient;

@Repository
public interface CompanyClientRepository extends CrudRepository<CompanyClient, Long> {

}
