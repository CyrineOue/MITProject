package tn.MITProject.services;

import java.util.Date;
import java.util.List;

import tn.MITProject.entities.Report;

public interface ReportService  {
	
	List<Report> retrieveAllReports();
	Report addReport (Report r);
	void deleteReport (Long id);
	Report updateReport (Report r);
	Report retrieveReport (Long id);
	List<Report> retrieveReportByStartDate( Date from, Date to);
	void assignReportToAdmin(Long idReport, Long idAdmin);
	double calculateTurnover(Date startDate,Date endDate);
	double calculateNetIncome(Report r,Date startDate,Date endDate);
	double calculateNetIncomeMargin(Report r);
	String bestCompanyClient(Date startDate,Date endDate);
	String bestParticularClient(Date startDate,Date endDate);

}
