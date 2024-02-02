package fr.christophe.cinema.seance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
    Optional<List<Seance>> findByDateAfter(LocalDateTime date);
}
