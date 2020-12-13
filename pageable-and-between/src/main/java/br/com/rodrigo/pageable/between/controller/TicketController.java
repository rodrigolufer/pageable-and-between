package br.com.rodrigo.pageable.between.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.pageable.between.model.Ticket;
import br.com.rodrigo.pageable.between.repository.TicketRepository;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping
	public Page<Ticket> list(@PageableDefault final Pageable pageable) {
		return ticketRepository.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public Optional<Ticket> byId(@PathVariable final Long id) {
		return ticketRepository.findById(id);
	}
	
	@GetMapping("/between")
	public Page<Ticket> between(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate, 
								@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate,
								@PageableDefault final Pageable pageable) {
		
		LocalDateTime start	= startDate.atStartOfDay();
		LocalDateTime end	= LocalDateTime.of(endDate, LocalTime.MAX);
		
		return ticketRepository.findByOpeningBetween(start, end, pageable);
	}
}