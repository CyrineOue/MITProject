package tn.MITProject.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.Service.ContractService;
import tn.MITProject.Service.PDFGeneratorService;
import tn.MITProject.Service.PaymentService;
import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;
import tn.MITProject.entities.PdfExporter;

@RestController
@RequestMapping("/pdfexport")

public class PDFExportController {
	@Autowired
	 PDFGeneratorService pdfGeneratorService;
	@Autowired
	PaymentService paymentservice;
	public PDFExportController (PDFGeneratorService pdfGeneratorService)
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
		List<Payment> listPayment = paymentservice.retrieveAllPayments();
	       System.out.println(listPayment+"payment") ;
	        
	       PdfExporter exporter = new PdfExporter(listPayment);
	      
	       exporter.export(response);
	}


}