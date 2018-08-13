package br.com.paulofranca.Helpdesk.service;

import br.com.paulofranca.Helpdesk.model.Interaction;

public interface InteractionService {

	public Interaction create(Interaction interaction);

	public Boolean delete(Long id);
}
