package fr.christophe.cinema.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.christophe.cinema.acteur.Acteur;
import fr.christophe.cinema.acteur.ActeurService;
import fr.christophe.cinema.acteur.dto.ActeurReduitDto;
import fr.christophe.cinema.acteur.dto.ActeurSansFilmDto;
import fr.christophe.cinema.film.dto.FilmCompletDto;
import fr.christophe.cinema.film.dto.FilmIdTitreSortieActeurRealDto;
import fr.christophe.cinema.film.exceptions.BadRequestException;
import fr.christophe.cinema.film.exceptions.FilmNotFoundException;
import fr.christophe.cinema.realisateur.Realisateur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    private final ActeurService acteurService;

    private final ObjectMapper objectMapper;

    public FilmService(FilmRepository filmRepository, ActeurService acteurService, ObjectMapper objectMapper) {
        this.filmRepository = filmRepository;
        this.acteurService = acteurService;
        this.objectMapper = objectMapper;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) {
        verifyFilms(film);
        return filmRepository.save(film);
    }

    private static void verifyFilms(Film film) {
        List<String> erreurs = new ArrayList<>();
        if (film.getTitre() == null) {
            erreurs.add("Le titre est obligatoire");
        }

        if (film.getDateSortie() == null) {
            erreurs.add("La date de sortie est obligatoire");
        }

        if (film.getRealisateur() == null) {
            erreurs.add("Il faut un réalisateur");
        }

        if (!erreurs.isEmpty()) {
            throw new BadRequestException(erreurs);
        }
    }

    // Si on n'a rien, on rejette un throw
    public Film findById(Integer id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new FilmNotFoundException(id)
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

    /**
     * On récupère un id de film et un acteur
     * L'id du film nous permet de récupérer le film en question. On récupère aussi l'acteur ajouté
     * On ajoute l'acteur au film puis on transforme le film en filmDto pour ne pas tout récupérer
     * @param id
     * @param acteur
     * @return FilmIdTitreSortieActeurRealDto
     */
    public FilmIdTitreSortieActeurRealDto addActeurInFilmById(Integer id, Acteur acteur) {
        Acteur acteurAjoute = acteurService.findById(acteur.getId());
        Film film = this.findById(id);
        film.getActeurs().add(acteurAjoute);

        // Ne surtout pas l'oublier !
        this.save(film);

        FilmIdTitreSortieActeurRealDto filmIdTitreSortieActeurRealDto = new FilmIdTitreSortieActeurRealDto();
        filmIdTitreSortieActeurRealDto.setTitre(film.getTitre());
        filmIdTitreSortieActeurRealDto.setRealisateur(film.getRealisateur());
        filmIdTitreSortieActeurRealDto.setDateSortie(film.getDateSortie());
        filmIdTitreSortieActeurRealDto.setId(film.getId());
        filmIdTitreSortieActeurRealDto.setActeurs(film.getActeurs().stream().map(
                acteurListe -> objectMapper.convertValue(acteurListe, ActeurSansFilmDto.class)
        ).toList());
        return filmIdTitreSortieActeurRealDto;
    }
}