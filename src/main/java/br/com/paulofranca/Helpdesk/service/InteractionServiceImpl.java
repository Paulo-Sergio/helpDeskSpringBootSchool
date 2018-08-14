package br.com.paulofranca.Helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.paulofranca.Helpdesk.model.Interaction;
import br.com.paulofranca.Helpdesk.model.Ticket;
import br.com.paulofranca.Helpdesk.model.User;
import br.com.paulofranca.Helpdesk.repository.InteractionRepository;
import br.com.paulofranca.Helpdesk.repository.TicketRepository;
import br.com.paulofranca.Helpdesk.repository.UserRepository;

@Service
public class InteractionServiceImpl implements InteractionService {

	@Autowired
	private InteractionRepository interactionRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Interaction create(Interaction interaction, Long tickedId) {
		Ticket ticket = this.ticketRepository.findOne(tickedId);
		User userLogged = this.getUserLogged();
		
		interaction.setTicket(ticket);
		interaction.setUserInteraction(userLogged);
		
		return this.interactionRepository.save(interaction);
	}

	@Override
	public Boolean delete(Long id, Long ticketId) {
		Interaction interaction = this.interactionRepository.findOne(id);
		
		if(interaction != null) {
			this.interactionRepository.delete(interaction);
			return true;
		}
		
		return false;
	}
	
	private User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();

		return this.userRepository.findByEmail(userName);
	}

}
