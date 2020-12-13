package br.com.rodrigo.pageable.between.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.pageable.between.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	public abstract Page<Ticket> findAll(final Pageable pageable);
	
	public abstract Page<Ticket> findByOpeningBetween(final LocalDateTime start, final LocalDateTime end, final Pageable pageable);
}
