package fr.christophe.cinema.realisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.christophe.cinema.film.Film;
import fr.christophe.cinema.film.FilmService;
import fr.christophe.cinema.film.dto.FilmVraimentReduitDto;
import fr.christophe.cinema.realisateur.dto.RealisateurCompletDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RealisateurService {
    private final RealisateurRepository realisateurRepository;

    private final ObjectMapper objectMapper;

    private final FilmService filmService;
    public RealisateurService(RealisateurRepository realisateurRepository, ObjectMapper objectMapper, FilmService filmService) {
        this.realisateurRepository = realisateurRepository;
        this.objectMapper = objectMapper;
        this.filmService = filmService;
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
        List<Film> filmsAvecCeRealisateur = filmService.findAllByRealisateurId(id);
        filmsAvecCeRealisateur.forEach(
                film -> {
                    film.setRealisateur(null);
                    filmService.save(film);
                }
        );
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

    public RealisateurCompletDto findAllByRealisateurId(Integer id) {
        Realisateur realisateur = this.findById(id);
        List<Film> films = filmService.findAllByRealisateurId(id);
        RealisateurCompletDto realisateurCompletDto = new RealisateurCompletDto();
        realisateurCompletDto.setNom(realisateur.getNom());
        realisateurCompletDto.setPrenom(realisateur.getPrenom());
        realisateurCompletDto.setFilms(films.stream().map(
                film -> objectMapper.convertValue(film, FilmVraimentReduitDto.class)
        ).toList());

        return realisateurCompletDto;
    }

    public List<Film> findAllFilmsRealisateur(Integer id){
        return filmService.findAllByRealisateurId(id);
    }

}
