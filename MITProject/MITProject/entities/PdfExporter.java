package tn.MITProject.entities;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.MITProject.repositories.PaymentRepository;

public class PdfExporter {

	
	
	 private List<Payment> listPayments;
    @Autowired
	 PaymentRepository paymentrepository;
	    public PdfExporter(List<Payment> listPayments) {
	        this.listPayments = listPayments;
	    }
	    
	    
	    
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	      
	         
	        cell.setPhrase(new Phrase("paymentMethods", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("PaymentDate", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("PaidPremium", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("RemainingPremium", font));
	        table.addCell(cell);    
	       
	        cell.setPhrase(new Phrase("RefundAmount", font));
	        table.addCell(cell);       
	    }
	    
	  
	    private void writeTableData(PdfPTable table) {
	 
	    	for (Payment payment : listPayments) {
	           
	         
	            table.addCell(payment.getPaymentDate().toString());
	            table.addCell(String.valueOf(payment.getPaidPremium()));
	            table.addCell(String.valueOf(payment.getRemainingPremium()));
	            table.addCell(String.valueOf(payment.getRefundAmount()));
	        }
	      //  table.addCell(String.valueOf(paymentrepository.RemainigAmountbetweentwodates(new Date(), new Date())));  
	    
}


		public void export(HttpServletResponse response) throws DocumentException, IOException  {
	     
	         
	    	  com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
	          PdfWriter.getInstance(document, response.getOutputStream());
	           
	          document.open();
	          com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	          font.setSize(10);
	          font.setColor(Color.BLUE);
	           
	          Paragraph p = new Paragraph("List of Payments", font);
	          p.setAlignment(Paragraph.ALIGN_CENTER);
	           
	          document.add(p);
	           
	          PdfPTable table = new PdfPTable(5);
	          table.setWidthPercentage(100f);
	          table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	          table.setSpacingBefore(10);
	           
	          writeTableHeader(table);
	          writeTableData(table);
	           
	          document.add(table);
	           
	          document.close();
	           
	      }


}
