package fr.christophe.cinema.realisateur;

import fr.christophe.cinema.film.Film;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RealisateurService {
    private final RealisateurRepository realisateurRepository;

    public RealisateurService(RealisateurRepository realisateurRepository) {
        this.realisateurRepository = realisateurRepository;
    }

    public Realisateur findByNom(String nom) {
        return realisateurRepository.findByNom(nom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Realisateur non trouvé avec le nom " + nom
                )
        );
    }

    public Realisateur findByPrenom(String prenom) {
        return realisateurRepository.findByPrenom(prenom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Realisateur non trouvé avec le prénom " + prenom
                )
        );
    }

    public List<Realisateur> findAll() {
        return realisateurRepository.findAll();
    }

    public Realisateur findById(Integer id) {
        return realisateurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Realisateur non trouvé"
                )
        );
    }

    public Realisateur save(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    public Realisateur update(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    public void deleteById(Integer id) {
        realisateurRepository.deleteById(id);
    }
}
