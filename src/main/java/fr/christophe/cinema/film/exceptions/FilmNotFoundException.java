package fr.christophe.cinema.film.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends RuntimeException{
    public FilmNotFoundException(Integer id){
        super("Film non trouvé à l'id : " + id + " !");
    }

}
