package fr.christophe.cinema.film;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping
    public List<Film> findAll(){
        return filmService.findAll();
    }

    // Surtout pas oublier le requestBody
    @PostMapping
    public Film save(@RequestBody Film film) {
        return filmService.save(film);
    }

    @GetMapping("/{id}")
    public Film findById(@PathVariable Integer id) {
        return filmService.findById(id);
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
