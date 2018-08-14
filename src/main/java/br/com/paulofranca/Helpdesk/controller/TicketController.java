package br.com.paulofranca.Helpdesk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.Interaction;
import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.service.TicketService;
import br.com.paulofranca.Helpdesk.service.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.ticketService.findAll());
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		
		return "ticket/index";
	}

	@GetMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Ticket ticket = this.ticketService.show(id);
		List<Interaction> interactions = ticket.getInteractions();
		
		model.addAttribute("ticket", ticket);
		model.addAttribute("interaction", new Interaction());
		model.addAttribute("interactions", interactions);

		return "ticket/show";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model = this.ticketService.findAllTechinician(model);
		model.addAttribute("ticket", new Ticket());

		return "ticket/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ticket/create";
		}

		this.ticketService.create(ticket);

		return "redirect:/tickets";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model = this.ticketService.findAllTechinician(model);
		model.addAttribute("ticket", this.ticketService.show(id));

		return "ticket/edit";
	}

	@PutMapping("/edit/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "ticket/edit";
		}
		
		this.ticketService.update(id, ticket);
		
		return "redirect:/tickets";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		this.ticketService.delete(id);
		
		return "redirect:/tickets";
	}
}
