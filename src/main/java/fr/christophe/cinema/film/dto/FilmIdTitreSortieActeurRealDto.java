package fr.christophe.cinema.film.dto;

import fr.christophe.cinema.acteur.dto.ActeurSansFilmDto;
import fr.christophe.cinema.realisateur.Realisateur;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilmIdTitreSortieActeurRealDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private Realisateur realisateur;
    private List<ActeurSansFilmDto> acteurs = new ArrayList<>();
}
