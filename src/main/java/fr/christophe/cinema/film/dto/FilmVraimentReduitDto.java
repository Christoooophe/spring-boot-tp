package fr.christophe.cinema.film.dto;

import fr.christophe.cinema.realisateur.Realisateur;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmVraimentReduitDto {
    private String titre;
    private LocalDate dateSortie;
}
