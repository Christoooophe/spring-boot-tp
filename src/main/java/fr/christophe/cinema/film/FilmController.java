package fr.christophe.cinema.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.christophe.cinema.acteur.Acteur;
import fr.christophe.cinema.acteur.dto.ActeurSansFilmsEtIdDto;
import fr.christophe.cinema.film.dto.FilmCompletDto;
import fr.christophe.cinema.film.dto.FilmIdTitreSortieActeurRealDto;
import fr.christophe.cinema.film.dto.FilmReduitDto;
import fr.christophe.cinema.realisateur.Realisateur;
import fr.christophe.cinema.realisateur.dto.RealisateurCompletDto;
import fr.christophe.cinema.realisateur.dto.RealisateurReduitDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    private final ObjectMapper objectMapper;

    public FilmController(FilmService filmService, ObjectMapper objectMapper) {
        this.filmService = filmService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}/acteurs")
    public List<ActeurSansFilmsEtIdDto> findAllActors(@PathVariable Integer id) {
        List<Acteur> film = filmService.findAllActorsByFilmId(id);
        return film.stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurSansFilmsEtIdDto.class)
        ).toList();
    }

    @GetMapping("/{id}/realisateur")
    public RealisateurReduitDto findRealisateur(@PathVariable Integer id) {
        Realisateur realisateur = filmService.findRealisateur(id);
        return objectMapper.convertValue(realisateur, RealisateurReduitDto.class);
    }


    @GetMapping
    public List<FilmReduitDto> findAll(){
        return filmService.findAll().stream().map(
                film -> objectMapper.convertValue(film, FilmReduitDto.class)
        ).toList();
    }

    @PostMapping("/{id}/acteurs")
    public FilmIdTitreSortieActeurRealDto addActeurs(@PathVariable Integer id, @RequestBody Acteur acteur){
        return filmService.addActeurInFilmById(id, acteur);
    }

    // Surtout pas oublier le requestBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film save(@RequestBody Film film) {
        return filmService.save(film);
    }

    @GetMapping("/{id}")
    public FilmCompletDto findById(@PathVariable Integer id) {
        Film film = filmService.findById(id);
        return objectMapper.convertValue(film, FilmCompletDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        filmService.deleteById(id);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    @GetMapping("/search")
    public Film findByTitre(@RequestParam String titre) {
        return filmService.findByTitre(titre);
    }

    public Film findByDateSortieAfter(LocalDate date) {
        return filmService.findByDateSortieAfter(date);
    }
}
