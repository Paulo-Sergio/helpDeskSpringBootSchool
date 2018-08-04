package br.com.paulofranca.Helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.Role;
import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.service.RoleService;
import br.com.paulofranca.Helpdesk.service.TicketService;
import br.com.paulofranca.Helpdesk.service.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	private final Long ROLE_ADMIN = 4l;

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
		
		Role adminRole = this.roleService.findByName("ADMIN");
		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId()));
		// passando o ID da Role direto no parametro
		// model.addAttribute("techs", this.userService.findAllWhereRoleEquals(ROLE_ADMIN));
		
		return "ticket/create";
	}
}
