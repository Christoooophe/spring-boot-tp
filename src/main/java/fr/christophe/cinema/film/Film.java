package fr.christophe.cinema.film;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.christophe.cinema.acteur.Acteur;
import fr.christophe.cinema.realisateur.Realisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "film")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Film {
    @Id
    @GeneratedValue //génère des id automatiquement
    private Integer id; //il faut  des Integer pas des int, il faut utiliser des UUID c'est mieux

    // @Column(name = "toto")
    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private LocalDate dateSortie;

    @Column(nullable = false)
    private int duree;

    @Column(length = 500)
    private String synopsis;

    @ManyToOne//one réalisateur, many films
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;

    @ManyToMany
    @JoinTable(
            name = "acteur_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name="acteur_id")
    )
    private List<Acteur> acteurs = new ArrayList<>();
}
