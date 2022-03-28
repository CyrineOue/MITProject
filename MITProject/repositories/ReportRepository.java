package tn.MITProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

}
