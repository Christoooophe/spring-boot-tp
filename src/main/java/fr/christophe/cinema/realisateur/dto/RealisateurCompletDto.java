package fr.christophe.cinema.realisateur.dto;

import fr.christophe.cinema.film.dto.FilmVraimentReduitDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RealisateurCompletDto {
    private String nom;
    private String prenom;
    private List<FilmVraimentReduitDto> films = new ArrayList<>();
}
