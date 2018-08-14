package br.com.paulofranca.Helpdesk.service;

import br.com.paulofranca.Helpdesk.model.Interaction;

public interface InteractionService {

	public Interaction create(Interaction interaction, Long tickedId);

	public Boolean delete(Long id, Long ticketId);
}
