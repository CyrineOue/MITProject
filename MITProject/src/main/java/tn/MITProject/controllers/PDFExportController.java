package tn.MITProject.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.MITProject.Service.ContractService;
import tn.MITProject.Service.PDFGeneratorService;
import tn.MITProject.entities.Contract;

@Controller
public class PDFExportController {
	@Autowired
	 PDFGeneratorService pdfGeneratorService;
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
}