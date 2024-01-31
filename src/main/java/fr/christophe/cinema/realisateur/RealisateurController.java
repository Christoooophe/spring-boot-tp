package fr.christophe.cinema.realisateur;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateur")
public class RealisateurController {
    private final RealisateurService realisateurService;

    public RealisateurController(RealisateurService realisateurService) {
        this.realisateurService = realisateurService;
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
    public Realisateur findById(@PathVariable Integer id) {
        return realisateurService.findById(id);
    }

    // Ok
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
