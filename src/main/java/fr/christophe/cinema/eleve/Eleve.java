package fr.christophe.cinema.eleve;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Eleve {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne // Pour lui dire de supprimer le carnet de note à la suppression de l'élève
    private CarnetDeNotes carnetDeNotes;
}
