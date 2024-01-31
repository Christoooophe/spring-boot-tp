package fr.christophe.cinema.film;

import fr.christophe.cinema.acteur.Acteur;
import fr.christophe.cinema.acteur.ActeurService;
import fr.christophe.cinema.realisateur.Realisateur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    private final ActeurService acteurService;
    public FilmService(FilmRepository filmRepository, ActeurService acteurService) {
        this.filmRepository = filmRepository;
        this.acteurService = acteurService;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    // Si on n'a rien, on rejette un throw
    public Film findById(Integer id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Film non trouvé"
                )
        );
    }

    public void deleteById(Integer id) {
        Film film = this.findById(id); //Si le film n'existe pas, ça throw, donc on atteint pas le code en dessous
        filmRepository.delete(film);
    }

    public Film update(Film film) {
        return filmRepository.save(film);
    }

    public Film findByTitre(String titre) {
        return filmRepository.findByTitre(titre).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun film avec le titre : " + titre
                )
        );
    }

    public Film findByDateSortieAfter(LocalDate date) {
        return filmRepository.findByDateSortieAfter(date).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun film après cette date : " + date
                )
        );
    }

    public List<Film> findAllByRealisateurId(Integer id) {
        return filmRepository.findAllByRealisateurId(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Il n'y a aucun film"
                )
        );
    }

    public List<Acteur> findAllActorsByFilmId(Integer id) {
        return filmRepository.findById(id).get().getActeurs();
    }

    public Realisateur findRealisateur(Integer id) {
        return filmRepository.findById(id).get().getRealisateur();
    }

//    public Film addActeur(Integer id) {
//        Acteur acteur = acteurService.findById(id);
//        Film
//    }
}
