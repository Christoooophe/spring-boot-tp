package fr.christophe.cinema.acteur;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.christophe.cinema.film.Film;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Acteur {
    @Id
    @GeneratedValue
    private Integer id;

    private String nom;

    private String prenom;

    @ManyToMany(mappedBy = "acteurs") //Le acteurs est récupéré dans FILM
    private List<Film> films = new ArrayList<>();
}
