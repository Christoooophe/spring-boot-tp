package fr.christophe.cinema.seance;

import fr.christophe.cinema.film.Film;
import fr.christophe.cinema.salle.Salle;
import fr.christophe.cinema.salle.SalleRepository;
import fr.christophe.cinema.salle.SalleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;

    private final SalleService salleService;

    public SeanceService(SeanceRepository seanceRepository, SalleService salleService) {
        this.seanceRepository = seanceRepository;
        this.salleService = salleService;
    }

    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    public Seance save(Seance seance) {
        verificationSeance(seance);
        Salle salle = salleService.findById(seance.getSalle().getId());
        seance.setPlaceDisponibles(salle.getCapacite());
        return seanceRepository.save(seance);
    }

    private static void verificationSeance(Seance seance) {
        if (!(seance.getSalle() instanceof Salle)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aucune salle à cet id"
            );
        }

        if (!(seance.getFilm() instanceof Film)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aucun film à cet id"
            );
        }
        if (seance.getPrix() < 0){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Le prix ne peut être négatif"
            );
        }

        if (seance.getDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
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

}
