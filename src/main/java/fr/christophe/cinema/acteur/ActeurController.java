package fr.christophe.cinema.acteur;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {
    private final ActeurService acteurService;

    public ActeurController(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    // Ok
    @PostMapping
    public Acteur save(@RequestBody Acteur acteur) {
        return acteurService.save(acteur);
    }

    // Ok
    @GetMapping
    public List<Acteur> findAll() {
        return acteurService.findAll();
    }

    // Ok
    @GetMapping("/{id}")
    public Acteur findById(@PathVariable Integer id) {
        return acteurService.findById(id);
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
