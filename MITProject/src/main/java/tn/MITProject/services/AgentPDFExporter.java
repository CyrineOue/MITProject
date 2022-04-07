package tn.MITProject.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.MITProject.entities.Agent;

 
@Service
public class AgentPDFExporter {
    private List<Agent> listAgent;
     
    public AgentPDFExporter(List<Agent> listAgent) {
        this.listAgent = listAgent;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Agent Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Agent LastName", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Genre", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("tel number", font));
        table.addCell(cell);
         
    }
     
    private void writeTableData(PdfPTable table) {
        for (Agent agent : listAgent) {
            table.addCell(String.valueOf(agent.getName()));
            table.addCell(String.valueOf(agent.getLastName()));
            table.addCell(String.valueOf(agent.getGenre()));
            table.addCell(String.valueOf(agent.getPhoneNb()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Agents", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}