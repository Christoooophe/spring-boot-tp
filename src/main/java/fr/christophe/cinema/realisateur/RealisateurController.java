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

    public Realisateur findByNom(String nom) {
        return realisateurService.findByNom(nom);
    }

    public Realisateur findByPrenom(String prenom) {
        return realisateurService.findByPrenom(prenom);
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
    @PostMapping
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    // Ok
    public Realisateur update(Realisateur realisateur) {
        return realisateurService.update(realisateur);
    }

    public void deleteById(Integer id) {
        realisateurService.deleteById(id);
    }
}
