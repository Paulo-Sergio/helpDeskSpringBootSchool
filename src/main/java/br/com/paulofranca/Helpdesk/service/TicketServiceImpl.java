package br.com.paulofranca.Helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.paulofranca.Helpdesk.model.Role;
import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.model.User;
import br.com.paulofranca.Helpdesk.repository.TicketRepository;
import br.com.paulofranca.Helpdesk.repository.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	private final String ROLE_NAME = "ADMIN";

	@Override
	public List<Ticket> findAll() {
		return null;
	}

	@Override
	public Ticket create(Ticket ticket) {
		ticket.setUserOpen(this.getUserLogged());

		return this.repository.save(ticket);
	}

	@Override
	public Boolean delete(Long id) {
		return null;
	}

	@Override
	public Boolean update(Long id, Ticket ticket) {
		return null;
	}

	@Override
	public Ticket show(Long id) {
		return null;
	}

	@Override
	public Model createTemplate(Model model) {
		model.addAttribute("ticket", new Ticket());

		Role adminRole = this.roleService.findByName(ROLE_NAME);

		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId(), this.getUserLogged().getId()));

		return model;
	}

	private User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();

		return this.userRepository.findByEmail(userName);
	}

}
