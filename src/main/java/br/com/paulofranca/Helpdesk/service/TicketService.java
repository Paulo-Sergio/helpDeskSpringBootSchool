package br.com.paulofranca.Helpdesk.service;

import java.util.List;

import org.springframework.ui.Model;

import br.com.paulofranca.Helpdesk.model.Ticket;

public interface TicketService {

	public List<Ticket> findAll();
	
	public Ticket create(Ticket ticket);
	
	public Model createTemplate(Model model);
	
	public Boolean delete(Long id);
	
	public Boolean update(Long id, Ticket ticket);
	
	public Ticket show(Long id);
}
