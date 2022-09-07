package iepscf.akimts.produit.exceptions;

import iepscf.akimts.handler.annotation.AdvisorHandled;
import org.springframework.http.HttpStatus;

@AdvisorHandled(HttpStatus.FORBIDDEN)
public class NoOwnershipException extends RuntimeException{

    public NoOwnershipException() {
        super("You have no ownership over this ressource");
    }
}
