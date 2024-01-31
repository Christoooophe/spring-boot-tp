package fr.christophe.cinema.realisateur;

import fr.christophe.cinema.film.Film;
import fr.christophe.cinema.film.dto.FilmVraimentReduitDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Realisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nom;

    private String prenom;

}
