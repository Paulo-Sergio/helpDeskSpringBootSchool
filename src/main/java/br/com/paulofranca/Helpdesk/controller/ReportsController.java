package br.com.paulofranca.Helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.service.TicketService;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/tickets")
	public String ticketReport(@RequestParam(required = false, value = "day") Integer day, Model model) {
		List<Ticket> reportTicketsByDays = this.ticketService.reportTicketsByDays(day);

		model.addAttribute("list", reportTicketsByDays);

		return "reports/ticket";
	}
}
