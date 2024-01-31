package fr.christophe.cinema.acteur.dto;

import fr.christophe.cinema.film.dto.FilmReduitSansActeursDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActeurReduitDto {
    private String nom;
    private String prenom;
    private List<FilmReduitSansActeursDto> films = new ArrayList<>();
}
