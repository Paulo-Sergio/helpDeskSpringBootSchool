package br.com.paulofranca.Helpdesk.service;

import java.util.Date;
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
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	private final String ROLE_NAME = "ADMIN";

	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>) this.ticketRepository.findAll();
	}

	@Override
	public Ticket create(Ticket ticket) {
		ticket.setUserOpen(this.getUserLogged());

		return this.ticketRepository.save(ticket);
	}

	@Override
	public Boolean delete(Long id) {
		Ticket ticketExists = this.findById(id);

		if (ticketExists != null) {
			this.ticketRepository.delete(ticketExists);
			return true;
		}

		return false;
	}

	@Override
	public Boolean update(Long id, Ticket ticket) {
		Ticket ticketExists = this.findById(id);

		if (ticketExists != null) {
			ticketExists.setId(ticket.getId());
			ticketExists.setName(ticket.getName());
			ticketExists.setDescription(ticket.getDescription());
			ticketExists.setFinished(ticket.getFinished());
			ticketExists.setTechnician(ticket.getTechnician());

			if (ticketExists.getFinished()) {
				ticketExists.setClosed(new Date());
			}

			this.ticketRepository.save(ticketExists);

			return true;
		}

		return false;
	}

	@Override
	public Ticket show(Long id) {
		return this.findById(id);
	}

	@Override
	public Model findAllTechinician(Model model) {
		Role adminRole = this.roleService.findByName(ROLE_NAME);

		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId(), this.getUserLogged().getId()));

		return model;
	}
	
	@Override
	public List<Ticket> reportTicketsByDays(Integer day) {
		return this.ticketRepository.findAllTicketsByDay(day);
	}

	private Ticket findById(Long id) {
		return this.ticketRepository.findOne(id);
	}

	private User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();

		return this.userRepository.findByEmail(userName);
	}
}
