package fr.christophe.cinema.seance;

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
        if (seance == null && seance.getFilm() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aucun film ou aucune séance n'existe"
            );
        }

        if (seance.getPrix() < 0){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Le prix ne peut être négatif" + "si deja"
            );
        }

        if (seance.getDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "La date n'est pas bonne"
            );
        }

        seance.setPlaceDisponibles(salleService.findById(seance.getId()).getCapacite());

        return seanceRepository.save(seance);
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
        return seanceRepository.save(seance);
    }

    public void deleteById(Long id) {
        Seance seance = this.findById(id);
        seanceRepository.delete(seance);
    }

}
