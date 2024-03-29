package fr.christophe.cinema.salle;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalleService {
    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public List<Salle> findAll() {
        return salleRepository.findAll();
    }

    public Salle save(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle findById(Long id) {
        return salleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucune salle à cet id"
                )
        );
    }

    public Salle update(Salle salle) {
        return salleRepository.save(salle);
    }

    public void deleteById(Long id) {
        Salle salle = this.findById(id);
        salleRepository.delete(salle);
    }
}
