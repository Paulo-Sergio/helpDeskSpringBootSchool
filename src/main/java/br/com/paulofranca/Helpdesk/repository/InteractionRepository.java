package br.com.paulofranca.Helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulofranca.Helpdesk.model.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
