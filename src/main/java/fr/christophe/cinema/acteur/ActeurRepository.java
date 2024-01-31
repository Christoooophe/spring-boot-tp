package fr.christophe.cinema.acteur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActeurRepository extends JpaRepository<Acteur, Integer> {
    Optional<List<Acteur>> findAllByNom(String nom);

    Optional<List<Acteur>> findAllByPrenom(String prenom);
}
