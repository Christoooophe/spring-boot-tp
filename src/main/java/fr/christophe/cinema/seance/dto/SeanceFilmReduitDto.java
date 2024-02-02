package fr.christophe.cinema.seance.dto;

import fr.christophe.cinema.film.dto.FilmTitreDureeSortieDto;
import fr.christophe.cinema.film.dto.FilmTitreIdDateSortieDto;
import fr.christophe.cinema.salle.Salle;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SeanceFilmReduitDto {

    private Long id;

    private LocalDateTime date;

    private int placeDisponibles;

    private float prix;

    private Salle salle;

    private FilmTitreDureeSortieDto film;
}
