package fr.christophe.cinema.ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}
