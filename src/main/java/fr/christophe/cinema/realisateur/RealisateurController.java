package fr.christophe.cinema.realisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.christophe.cinema.acteur.Acteur;
import fr.christophe.cinema.acteur.dto.ActeurSansFilmsEtIdDto;
import fr.christophe.cinema.film.Film;
import fr.christophe.cinema.film.dto.FilmReduitDto;
import fr.christophe.cinema.film.dto.FilmTitreDureeSortieDto;
import fr.christophe.cinema.film.dto.FilmVraimentReduitDto;
import fr.christophe.cinema.realisateur.dto.RealisateurCompletDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {
    private final RealisateurService realisateurService;

    private final ObjectMapper objectMapper;

    public RealisateurController(RealisateurService realisateurService, ObjectMapper objectMapper) {
        this.realisateurService = realisateurService;
        this.objectMapper = objectMapper;
    }

    // Ok
    @PostMapping
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    // Ok
    @GetMapping
    public List<Realisateur> findAll() {
        return realisateurService.findAll();
    }

    // Ok
    @GetMapping("/{id}")
    public RealisateurCompletDto findById(@PathVariable Integer id) {
        return realisateurService.findAllByRealisateurId(id);
    }

    @GetMapping("/{id}/films")
    public List<FilmTitreDureeSortieDto> findAllFilmsByReal(@PathVariable Integer id) {
        return realisateurService.findAllFilmsRealisateur(id).stream().map(
                film -> objectMapper.convertValue(film, FilmTitreDureeSortieDto.class)
        ).toList();
    }

    @PutMapping
    public Realisateur update(@RequestBody Realisateur realisateur) {
        return realisateurService.update(realisateur);
    }

    // Ok
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        realisateurService.deleteById(id);
    }

    // Ok
    @GetMapping("/search")
    public List<Realisateur> findByNom(@RequestParam String nom) {
        return realisateurService.findByNom(nom);
    }

    // Ok
    @GetMapping("/searchprenom")
    public List<Realisateur> findByPrenom(@RequestParam String prenom) {
        return realisateurService.findByPrenom(prenom);
    }
}
