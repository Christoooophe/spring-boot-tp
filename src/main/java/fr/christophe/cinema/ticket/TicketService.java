package fr.christophe.cinema.ticket;

import fr.christophe.cinema.seance.SeanceService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    private final SeanceService seanceService;

    public TicketService(TicketRepository ticketRepository, SeanceService seanceService) {
        this.ticketRepository = ticketRepository;
        this.seanceService = seanceService;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket) {
        if (ticket.getSeance() == null) {
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ce ticket n'est pas relié à une séance"
            );
        }

        if (ticket.getNombrePlaces() > ticket.getSeance().getPlaceDisponibles()) {
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Plus assez de place"
            );
        }

        if (ticket.getNombrePlaces() < 0) {
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Impossible de commander moins de 0 tickets"
            );
        }

        if (ticket.getNomClient() == null) {
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Impossible de commander un ticket sans nom"
            );
        }
        return ticketRepository.save(ticket);
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun ticket à cet id"
                )
        );
    }

    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {
        Ticket ticket = this.findById(id);
        ticketRepository.delete(ticket);
    }
}
