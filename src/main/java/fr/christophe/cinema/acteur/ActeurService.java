package fr.christophe.cinema.acteur;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActeurService {
    private final ActeurRepository acteurRepository;

    public ActeurService(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    public Acteur save(Acteur acteur) {
        return acteurRepository.save(acteur);
    }
    public List<Acteur> findAll() {
        return acteurRepository.findAll();
    }

    public Acteur findById(Integer id) {
        return acteurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Acteur non trouvé"
                )
        );
    }

    public Acteur update(Acteur acteur) {
        return acteurRepository.save(acteur);
    }

    public void deleteById(Integer id) {
        Acteur acteur = this.findById(id);
        acteurRepository.delete(acteur);
    }

    public List<Acteur> findByNom(String nom) {
        return acteurRepository.findAllByNom(nom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun acteur avec le nom : " + nom
                )
        );
    }

    public List<Acteur> findByPrenom(String prenom) {
        return acteurRepository.findAllByPrenom(prenom).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun acteur avec le prenom : " + prenom
                )
        );
    }
}
