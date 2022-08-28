package iepscf.akimts.produit.exceptions;

import iepscf.akimts.exceptions.annotation.AdvisorHandled;
import org.springframework.http.HttpStatus;

@AdvisorHandled(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("ressource not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
