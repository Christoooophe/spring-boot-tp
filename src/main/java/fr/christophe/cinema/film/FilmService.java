package fr.christophe.cinema.film;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
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
}
