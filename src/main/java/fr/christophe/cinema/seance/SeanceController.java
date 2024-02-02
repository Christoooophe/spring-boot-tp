package fr.christophe.cinema.seance;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.christophe.cinema.film.dto.FilmTitreDureeSortieDto;
import fr.christophe.cinema.seance.dto.SeanceAvecTicketsDto;
import fr.christophe.cinema.seance.dto.SeanceFilmReduitDto;
import fr.christophe.cinema.ticket.Ticket;
import fr.christophe.cinema.ticket.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {
    private final SeanceService seanceService;

    private final TicketService ticketService;
    private final ObjectMapper objectMapper;
    public SeanceController(SeanceService seanceService, TicketService ticketService, ObjectMapper objectMapper) {
        this.seanceService = seanceService;
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<SeanceFilmReduitDto> findAll() {
        List<Seance> seances = seanceService.findAll();
        return seances.stream().map(
                seance -> objectMapper.convertValue(seance, SeanceFilmReduitDto.class)
        ).toList();
    }

    /**
     * Méthode qui nous permet de passer d'une séance à une séance avec un film réduit pour que ce soit plus lisible
     */
    @GetMapping("/{id}")
    public SeanceFilmReduitDto findById(@PathVariable Long id) {
        Seance seance = seanceService.findById(id);
        FilmTitreDureeSortieDto filmTitreDureeSortieDto = new FilmTitreDureeSortieDto();
        filmTitreDureeSortieDto.setTitre(seance.getFilm().getTitre());
        filmTitreDureeSortieDto.setDuree(seance.getFilm().getDuree());
        filmTitreDureeSortieDto.setDateSortie(seance.getFilm().getDateSortie());
        SeanceFilmReduitDto seanceFilmReduitDto = new SeanceFilmReduitDto();
        seanceFilmReduitDto.setDate(seance.getDate());
        seanceFilmReduitDto.setPlaceDisponibles(seance.getPlaceDisponibles());
        seanceFilmReduitDto.setSalle(seance.getSalle());
        seanceFilmReduitDto.setId(seance.getId());
        seanceFilmReduitDto.setPrix(seance.getPrix());
        seanceFilmReduitDto.setFilm(filmTitreDureeSortieDto);
        return seanceFilmReduitDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seance save(@RequestBody Seance seance) {
        return seanceService.save(seance);
    }

    @PutMapping
    public Seance update(@RequestBody Seance seance) {
        return seanceService.update(seance);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        seanceService.deleteById(id);
    }

    @PostMapping("/{id}/reserver")
    public Ticket makeReservation(@PathVariable Long id, @RequestBody Ticket ticket){
        Seance seance = seanceService.findById(id);
        ticket.setSeance(seance);
        return ticketService.save(ticket);
    }

    @GetMapping("/{id}/tickets")
    public SeanceAvecTicketsDto findBySeanceId(@PathVariable Long id){
        SeanceAvecTicketsDto seanceAvecTicketsDto = seanceService.findAllBySeanceId(id);
        seanceAvecTicketsDto.setTickets(ticketService.findAllBySeanceId(id));
        return seanceAvecTicketsDto;
    }

}
