package fr.christophe.cinema.seance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}
