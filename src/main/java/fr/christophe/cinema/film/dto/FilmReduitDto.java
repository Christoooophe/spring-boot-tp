package fr.christophe.cinema.film.dto;

import lombok.Data;

import java.time.LocalDate;

@Data //On peut l'utiliser vu que ce n'est pas une entité
public class FilmReduitDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private int duree;
    private String synopsis;
}
