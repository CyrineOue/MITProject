package tn.MITProject.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lowagie.text.DocumentException;

import tn.MITProject.entities.Report;
import tn.MITProject.repositories.ReportRepository;
import tn.MITProject.services.PdfGeneratorReport;
import tn.MITProject.services.PdfGeneratorService;

@Controller

public class PdfExportController {
	
	@Autowired
	 PdfGeneratorService pdfGeneratorService;
	@Autowired
	 PdfGeneratorReport pdfGeneratorReport;
	@Autowired
	 ReportRepository reportRepository;
	public PdfExportController(PdfGeneratorService pdfGeneratorService)
		{  this.pdfGeneratorService = pdfGeneratorService;
		}
	
	
	@GetMapping("/pdf/generate")
	public void generatePDF(HttpServletResponse response) throws IOException, Exception
	{ response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attatchement; filename=pdf.." + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		this.pdfGeneratorService.export(response);
	}
	/*@GetMapping("/generate-pdf-IDContract={contract-id}")
	@ResponseBody
	public com.lowagie.text.Document generatePDFversion(@PathVariable("contract-id")long id) {
		Contract c;
		try {
			c = ContractService.retrieveContract(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PDFExportController contractService;
		com.lowagie.text.Document d=contractService.generatePDF(c);
		return d;
	}
	*/
	
	@GetMapping("/pdf/generateReport/{report-id}")
	public void generatePDFReport(HttpServletResponse response,@PathVariable("report-id") Long reportId) throws IOException, DocumentException
	{ response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attatchement; filename=Report.." + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		  
        PdfGeneratorReport exporter = new PdfGeneratorReport();
        Report report= reportRepository.findById(reportId).orElse(null);
        exporter.generatePdfReport(response, report);
	}

}
