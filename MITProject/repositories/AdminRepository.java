package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {


}
