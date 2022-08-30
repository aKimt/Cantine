package iepscf.akimts.produit.exceptions;

import iepscf.akimts.handler.annotation.AdvisorHandled;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AdvisorHandled(HttpStatus.NOT_FOUND)
public class NoRessourseFoundException extends NotFoundException{

    private final String searchedBy;
    private final String searchWith;

    public NoRessourseFoundException(String searchedBy, String searchWith) {
        super("Element couldn't be found by {"+ searchedBy +"} with value {"+ searchWith + "}");
        this.searchedBy = searchedBy;
        this.searchWith = searchWith;
    }
}
