package fr.christophe.cinema.realisateur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RealisateurRepository extends JpaRepository<Realisateur, Integer> {
    Optional<List<Realisateur>> findAllByNom(String nom);
    Optional<List<Realisateur>> findAllByPrenom(String prenom);
}
