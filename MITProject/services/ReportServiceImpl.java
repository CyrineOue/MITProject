package tn.MITProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Report;
import tn.MITProject.repositories.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	ReportRepository reportrepository;

	@Override
	public List<Report> retrieveAllReports() {
		return (List<Report>) reportrepository.findAll();
	}

	@Override
	public Report addReport(Report r) {
		reportrepository.save(r);
		return r;
	}

	@Override
	public void deleteReport(Long id) {
		reportrepository.deleteById(id);
		
	}

	@Override
	public Report updateReport(Report r) {
		reportrepository.save(r);
		return r;
	}

	@Override
	public Report retrieveReport(Long id) {
		return reportrepository.findById(id).orElse(null);
	}

}
