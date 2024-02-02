package fr.christophe.cinema.seance.dto;

import fr.christophe.cinema.film.dto.FilmTitreIdDateSortieDto;
import fr.christophe.cinema.salle.Salle;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SeanceFilmReduitDto {

    private Long id;

    private LocalDate date;

    private int placeDisponibles;

    private float prix;

    private Salle salle;

    private FilmTitreIdDateSortieDto film;
}
