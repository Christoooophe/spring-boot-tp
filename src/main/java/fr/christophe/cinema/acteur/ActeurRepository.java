package fr.christophe.cinema.acteur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActeurRepository extends JpaRepository<Acteur, Integer> {
    Optional<Acteur> findByNom(String nom);

    Optional<Acteur> findByPrenom(String prenom);
}
