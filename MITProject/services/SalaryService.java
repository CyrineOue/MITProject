package tn.MITProject.services;

import java.util.Date;
import java.util.List;

import tn.MITProject.entities.Salary;

public interface SalaryService {
	
	List<Salary> retrieveAllSalaries();

	Salary addSalary (Salary a);

	void deleteSalary (Long id);

	Salary updateSalary (Salary a);

	Salary retrieveSalary (Long id);
	
	double getSalaries(Date startDate,Date endDate);

}
