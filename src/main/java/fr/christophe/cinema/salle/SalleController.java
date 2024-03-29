package fr.christophe.cinema.salle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public List<Salle> findAll() {
        return salleService.findAll();
    }

    @GetMapping("/{id}")
    public Salle findById(@PathVariable Long id) {
        return salleService.findById(id);
    }

    @PostMapping
    public Salle save(@RequestBody Salle salle) {
        return salleService.save(salle);
    }

    @PutMapping
    public Salle update(@RequestBody Salle salle) {
        return salleService.update(salle);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        salleService.deleteById(id);
    }
}
