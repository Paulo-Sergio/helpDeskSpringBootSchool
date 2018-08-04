package br.com.paulofranca.Helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping
	public String create(Model model) {
		model = this.ticketService.createTemplate(model);

		return "ticket/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "ticket/create";
		}
		
		this.ticketService.create(ticket);
		
		return "redirect:/tickets";
	}
}
