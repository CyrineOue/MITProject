package tn.MITProject.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;


//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
//import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import tn.MITProject.entities.Report;





@Component("pdfGeneratorReport")
public class PdfGeneratorReport {
	
		
		/*@Value("${pdfDir}")
		private String pdfDir;
		
		@Value("${reportFileName}")
		private String reportFileName;
		
		@Value("${reportFileNameDateFormat}")
		private String reportFileNameDateFormat;
		
		@Value("${localDateFormat}")
		private String localDateFormat;
		
		@Value("${logoImgPath}")
		private String logoImgPath;
		
		@Value("${logoImgScale}")
		private Float[] logoImgScale;*/
		
		/*@Value("${currencySymbol:}")
		private String currencySymbol;*/
		
		

		public void generatePdfReport(HttpServletResponse response, Report r)throws DocumentException, IOException {
			Document document = new Document(PageSize.A4);
			try {

				PdfWriter.getInstance(document, response.getOutputStream());
				document.open();
				addLogo(document);
				addDocTitle(document);
				getDbData(document,r);
				addFooter(document);
				document.close();
				System.out.println("------------------Your PDF Report is ready!-------------------------");

			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void addLogo(Document document) {
			try {
				Image img = Image.getInstance("C:\\Users\\21623\\Documents\\mitproject.png");
				img.scalePercent(25, 25);
				img.setAlignment(Element.ALIGN_RIGHT);
				document.add(img);
				
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void addDocTitle(Document document) throws DocumentException {
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
			String localDateString = dateFormatter.format(new Date());
			Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			 fontTiltle.setSize(21);
			 fontTiltle.setColor(32, 103, 66);
			 fontTiltle.setStyle("bold");
			Paragraph p1 = new Paragraph();
			p1.add(new Paragraph("Report", fontTiltle));
			p1.setAlignment(Element.ALIGN_CENTER);
			leaveEmptyLine(p1, 2);
			p1.add(new Paragraph("Report generated on " + localDateString, fontTiltle));
			leaveEmptyLine(p1, 3);

			document.add(p1);

		}

		
		private void getDbData(Document document,Report r) throws DocumentException {
	
			
			Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			 fontData.setSize(14);
				Paragraph p2=new Paragraph();
				p2.add(new Paragraph("Report ID: "+"   "+r.getIdReport(), fontData));
				p2.setAlignment(Element.ALIGN_LEFT);
				p2.setIndentationLeft(11);
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Start Date: "+"   "+r.getStartDate().toString(), fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("End Date: "+"   "+r.getEndDate().toString(), fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Number of contracts: "+"   "+r.getNbContracts() , fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Number of claims: "+"   "+r.getNbSinisters(), fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Fixed Charges: "+"   "+r.getFixedCharges()+" "+"DT", fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Variable Charges: "+"   "+r.getVariableCharges()+" "+"DT", fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Income tax rate: "+"   "+r.getIncomeTaxRate()+" "+"%", fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Turnover: "+"   "+r.getTurnover()+" "+"DT", fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Net Income: "+"   "+r.getNetIncome()+" "+"DT", fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Net Income Margin: "+"   "+r.getNetIncomeMargin()+" "+"%", fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Company Client of the month: "+"   "+r.getMonthCompanyClient(), fontData));
				leaveEmptyLine(p2, 1);
				p2.add(new Paragraph("Particular Client of the month: "+"   "+r.getMonthParticularClient(), fontData));
				leaveEmptyLine(p2, 1);
				document.add(p2);
			
			
		}
		
		private void addFooter(Document document) throws DocumentException {
			Font fontFooter = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			 fontFooter.setSize(17);
			 fontFooter.setColor(32, 103, 66);
			 fontFooter.setStyle("bold");
			Paragraph p2 = new Paragraph();
			leaveEmptyLine(p2, 2);
			p2.add(new Paragraph(
					"------------------------End Of Report------------------------", fontFooter));
			p2.setAlignment(Element.ALIGN_CENTER);			
			document.add(p2);
		}

		private static void leaveEmptyLine(Paragraph paragraph, int number) {
			for (int i = 0; i < number; i++) {
				paragraph.add(new Paragraph(" "));
			}
		}
		
}
	


