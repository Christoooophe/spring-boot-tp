package fr.christophe.cinema.film;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Film> findByTitre(String titre);

    Optional<Film> findByDateSortieAfter(LocalDate date);

    Optional<List<Film>> findAllByRealisateurId(Integer id);
}
