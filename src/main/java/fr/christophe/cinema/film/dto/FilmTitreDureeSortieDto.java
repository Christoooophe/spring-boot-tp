package fr.christophe.cinema.film.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmTitreDureeSortieDto {
    private String titre;
    private LocalDate dateSortie;
    private int duree;
}
