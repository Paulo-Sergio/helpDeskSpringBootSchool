package br.com.paulofranca.Helpdesk.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.paulofranca.Helpdesk.model.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long>{

}
