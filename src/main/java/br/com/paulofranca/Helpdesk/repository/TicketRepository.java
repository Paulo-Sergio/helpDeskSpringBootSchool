package br.com.paulofranca.Helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.paulofranca.Helpdesk.model.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

	@Query(value="SELECT ticket.* FROM tickets as ticket WHERE ticket.created >= date(now()) - interval (:day) day", nativeQuery=true)
	public List<Ticket> findAllTicketsByDay(@Param("day") Integer day);
}
