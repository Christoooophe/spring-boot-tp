package fr.christophe.cinema.seance;

import fr.christophe.cinema.film.Film;
import fr.christophe.cinema.film.FilmService;
import fr.christophe.cinema.salle.Salle;
import fr.christophe.cinema.salle.SalleService;
import fr.christophe.cinema.seance.dto.SeanceAvecTicketsDto;
import fr.christophe.cinema.seance.dto.SeanceFilmReduitDto;
import fr.christophe.cinema.ticket.Ticket;
import fr.christophe.cinema.ticket.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;

    private final SalleService salleService;
    private final FilmService filmService;

    public SeanceService(SeanceRepository seanceRepository, SalleService salleService, FilmService filmService) {
        this.seanceRepository = seanceRepository;
        this.salleService = salleService;
        this.filmService = filmService;
    }

    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    public Seance save(Seance seance) {
        verificationSeance(seance);
        Salle salle = salleService.findById(seance.getSalle().getId());
        Film film = filmService.findById(seance.getFilm().getId());
        seance.setPlaceDisponibles(salle.getCapacite());
        Seance createdSeance = seanceRepository.save(seance);
        return this.findById(createdSeance.getId());
    }

    private static void verificationSeance(Seance seance) {
        if (seance.getPrix() < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le prix ne peut être négatif"
            );
        }

        if (seance.getDate().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La date n'est pas bonne"
            );
        }
    }

    public Seance findById(Long id) {
        return seanceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucune séance à cet id"
                )
        );
    }

    public Seance update(Seance seance) {
        verificationSeance(seance);
        return seanceRepository.save(seance);
    }

    public void deleteById(Long id) {
        Seance seance = this.findById(id);
        seanceRepository.delete(seance);
    }

    public SeanceAvecTicketsDto findAllBySeanceId(Long id){
        Seance seance = this.findById(id);
        SeanceAvecTicketsDto seanceAvecTicketsDto = new SeanceAvecTicketsDto();
        seanceAvecTicketsDto.setId(seance.getId());
        return seanceAvecTicketsDto;
    }

    public List<Seance> findAllAfterDate(LocalDateTime date){
        return seanceRepository.findByDateAfter(date).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucune séance après cette date"
                )
        );
    }
}
