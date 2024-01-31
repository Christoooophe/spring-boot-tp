package fr.christophe.cinema.realisateur;

import fr.christophe.cinema.acteur.Acteur;
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

    public Realisateur save(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
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

    public Realisateur update(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    public void deleteById(Integer id) {
        Realisateur realisateur = this.findById(id);
        realisateurRepository.delete(realisateur);
    }

    public List<Realisateur> findByNom(String nom) {
        return realisateurRepository.findAllByNom(nom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Realisateur non trouvé avec le nom " + nom
                )
        );
    }

    public List<Realisateur> findByPrenom(String prenom) {
        return realisateurRepository.findAllByPrenom(prenom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Realisateur non trouvé avec le prénom " + prenom
                )
        );
    }
}
