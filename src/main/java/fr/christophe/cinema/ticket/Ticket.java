package fr.christophe.cinema.ticket;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.christophe.cinema.seance.Seance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    private String nomClient;

    private int nombrePlaces;

    @ManyToOne
    @JoinColumn(name = "seance_id")
    private Seance seance;
}
