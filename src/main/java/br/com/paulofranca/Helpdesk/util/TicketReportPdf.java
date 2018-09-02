package br.com.paulofranca.Helpdesk.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.paulofranca.Helpdesk.model.Ticket;

public class TicketReportPdf {

	public static ByteArrayInputStream generate(List<Ticket> tickets) {
		Document document = new Document();
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(3);
			
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 1, 2, 3 });
			
			// THEAD = HEAD
			PdfPCell headerCell;
			
			headerCell = new PdfPCell(new Phrase("ID"));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);
			
			headerCell = new PdfPCell(new Phrase("Nome"));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);
			
			headerCell = new PdfPCell(new Phrase("Descrição"));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);
			
			// TBODY = BODY
			for (Ticket ticket : tickets) {
				PdfPCell bodyCell;
				
				bodyCell = new PdfPCell(new Phrase(ticket.getId().toString()));
				bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(bodyCell);
				
				bodyCell = new PdfPCell(new Phrase(ticket.getName()));
				bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(bodyCell);
				
				bodyCell = new PdfPCell(new Phrase(ticket.getDescription()));
				bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(bodyCell);
			}
			
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();
			
			Paragraph title = new Paragraph(new Phrase("Tickets por dias"));
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(25);
			
			document.add(title);
			document.add(table);
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}
}
