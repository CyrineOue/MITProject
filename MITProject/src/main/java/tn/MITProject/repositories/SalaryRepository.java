package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Salary;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {
	
	@Query("SELECT s FROM Salary s WHERE s.monthStart =:monthStart and s.monthEnd =:monthEnd ")
	List<Salary> retrieveSalaryByMonth(@Param("monthStart") Date monthStart, @Param("monthEnd") Date monthEnd);

}
