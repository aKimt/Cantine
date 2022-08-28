package iepscf.akimts.produit.exceptions;

import iepscf.akimts.exceptions.annotation.AdvisorHandled;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoRessourseFoundException extends NotFoundException{

    private final String searchedBy;
    private final String searchWith;

    public NoRessourseFoundException(String searchedBy, String searchWith) {
        super("Element couldn't be found by {"+ searchedBy +"} with value {"+ searchWith + "}");
        this.searchedBy = searchedBy;
        this.searchWith = searchWith;
    }
}
