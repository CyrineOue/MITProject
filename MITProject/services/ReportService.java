package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.Report;

public interface ReportService  {
	
	List<Report> retrieveAllReports();

	Report addReport (Report r);

	void deleteReport (Long id);

	Report updateReport (Report r);

	Report retrieveReport (Long id);

}
