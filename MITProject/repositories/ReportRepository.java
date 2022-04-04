package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
	
	@Query("SELECT r FROM Report r WHERE r.startDate between :from and :to ")
	List<Report> retrieveReportByStartDate(@Param("from") Date from, @Param("to") Date to);

}
