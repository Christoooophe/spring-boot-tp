package fr.christophe.cinema.seance;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @OneToOne
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
