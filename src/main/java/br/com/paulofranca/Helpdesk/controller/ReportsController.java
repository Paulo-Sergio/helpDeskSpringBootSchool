package br.com.paulofranca.Helpdesk.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.service.TicketService;
import br.com.paulofranca.Helpdesk.util.TicketReportPdf;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/tickets")
	public String ticketReport(@RequestParam(required = false, value = "day") Integer day, Model model) {
		List<Ticket> reportTicketsByDays = this.ticketService.reportTicketByDays(day);

		model.addAttribute("list", reportTicketsByDays);

		return "reports/ticket";
	}
	
	@GetMapping("/tickets/pdfgen")
	public String ticketReportPdfForm(Model model) { 
		return "reports/ticket_pdf";
	}

	@GetMapping(value = "/tickets/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pdfTicketReport(@RequestParam(required = false, value = "day") Integer day, Model model) {
		List<Ticket> tickets = this.ticketService.reportTicketByDays(day);
		
		ByteArrayInputStream pdf = TicketReportPdf.generate(tickets);

		return ResponseEntity
				.ok()
				.header("Content-Disposition", "inline; filename=report.pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdf));
	}

}
