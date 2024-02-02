package fr.christophe.cinema.seance;

import fr.christophe.cinema.film.Film;
import fr.christophe.cinema.salle.Salle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    private int placeDisponibles;

    private float prix;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
