package fr.christophe.cinema.film.dto;

import fr.christophe.cinema.realisateur.Realisateur;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmReduitSansActeursDto {
    private String titre;
    private LocalDate dateSortie;
    private Realisateur realisateur;
}
