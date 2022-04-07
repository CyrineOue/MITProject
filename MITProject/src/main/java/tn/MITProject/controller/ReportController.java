package tn.MITProject.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.MITProject.entities.Report;
import tn.MITProject.services.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	ReportService reportService;

	// http://localhost:8081/mit/report/retrieve-all-reports
	@GetMapping("/retrieve-all-reports")
	@ResponseBody
	public List<Report> getReports() {
	List<Report> listReports = reportService.retrieveAllReports();
	return listReports;
	}
	
	// http://localhost:8081/mit/report/retrieve-report/8
	@GetMapping("/retrieve-report/{report-id}")
	@ResponseBody
	public Report retrieveReport(@PathVariable("report-id") Long reportId) {
	return reportService.retrieveReport(reportId);
	}

	// http://localhost:8081/mit/report/add-report
	@PostMapping("/add-report")
	@ResponseBody
	public Report addReport(HttpServletResponse response,@RequestBody Report r) throws IOException, DocumentException
	{
	Report report = reportService.addReport(r);
	return report;
	}
	

	// http://localhost:8081/mit/report/modify-report
	@PutMapping("/modify-report")
	@ResponseBody
	public Report modifyReport(@RequestBody Report report) {
	return reportService.updateReport(report);
	}
	
	// http://localhost:8081/Achat/report/remove-report/{report-id}
	@DeleteMapping("/remove-report/{report-id}")
	@ResponseBody
	public void removeReport(@PathVariable("report-id") Long reportId) {
	reportService.deleteReport(reportId);
	}
	
	// http://localhost:8081/mit/report/assign-report/2/1
	@PutMapping("/assign-report/{report-id}/{admin-id}")
	@ResponseBody
	public void assignReportToAdmin(@PathVariable("report-id") Long reportId, @PathVariable("admin-id") Long adminId)
	{
	   reportService.assignReportToAdmin(reportId, adminId);
	 }
	
	//http://localhost:8081/mit/report/bestCompanyClient/2022-03-10/2022-03-18
		@GetMapping("/bestCompanyClient/{from}/{to}")
		@ResponseBody
		public String  bestCompanyClient(@PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,@PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {    
		String list = reportService.bestCompanyClient( from , to ) ;
		return list ; 
	    }
	


}
