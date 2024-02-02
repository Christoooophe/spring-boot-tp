package fr.christophe.cinema.ticket;

import fr.christophe.cinema.seance.Seance;
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
        Seance seance = seanceService.findById(ticket.getSeance().getId());
        verify(ticket);

        if (ticket.getNombrePlaces() > seance.getPlaceDisponibles()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Impossible de commander plus de billets que de places dans la séance"
            );
        }

        seance.setPlaceDisponibles(seance.getPlaceDisponibles() - ticket.getNombrePlaces());
        return ticketRepository.save(ticket);
    }

    private static void verify(Ticket ticket) {

        if (ticket.getNombrePlaces() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Impossible de commander moins de 0 tickets"
            );
        }

        if (ticket.getNomClient().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Impossible de commander un ticket sans nom"
            );
        }
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
        verify(ticket);
        return ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {
        Ticket ticket = this.findById(id);
        ticketRepository.delete(ticket);
    }
}
