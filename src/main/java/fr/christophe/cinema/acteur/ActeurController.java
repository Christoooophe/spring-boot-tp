package fr.christophe.cinema.acteur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.christophe.cinema.acteur.dto.ActeurReduitDto;
import fr.christophe.cinema.acteur.dto.ActeurSansFilmDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {
    private final ActeurService acteurService;

    private final ObjectMapper objectMapper;

    public ActeurController(ActeurService acteurService, ObjectMapper objectMapper) {
        this.acteurService = acteurService;
        this.objectMapper = objectMapper;
    }

    // Ok
    @PostMapping
    public Acteur save(@RequestBody Acteur acteur) {
        return acteurService.save(acteur);
    }

    // Ok
    @GetMapping
    public List<ActeurSansFilmDto> findAll() {
        return acteurService.findAll().stream().map(
                acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)
        ).toList();
    }

    // Ok
    @GetMapping("/{id}")
    public ActeurReduitDto findById(@PathVariable Integer id) {
        Acteur acteur = acteurService.findById(id);
        return objectMapper.convertValue(acteur, ActeurReduitDto.class);
    }

    // Ok
    @PutMapping
    public Acteur update(@RequestBody Acteur acteur) {
        return acteurService.update(acteur);
    }

    // Ok
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        acteurService.deleteById(id);
    }

    // Ok
    @GetMapping("/search")
    public List<Acteur> findByNom(@RequestParam String nom) {
        return acteurService.findByNom(nom);
    }

    // Ok
    @GetMapping("/searchPrenom")
    public List<Acteur> findByPrenom(@RequestParam String prenom) {
        return acteurService.findByPrenom(prenom);
    }
}
