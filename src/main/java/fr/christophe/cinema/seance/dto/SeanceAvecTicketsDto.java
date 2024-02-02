package fr.christophe.cinema.seance.dto;

import fr.christophe.cinema.ticket.Ticket;
import fr.christophe.cinema.ticket.dto.TicketsReduitDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeanceAvecTicketsDto {
    private Long id;
    private List<Ticket> tickets = new ArrayList<>();
}
