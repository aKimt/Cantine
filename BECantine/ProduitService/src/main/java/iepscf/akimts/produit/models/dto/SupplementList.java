package iepscf.akimts.produit.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplementList extends RepresentationModel<SupplementList> {

    private Set<ComposantDTO> supplements;

    public SupplementList(Set<ComposantDTO> supplements) {
        this.supplements = supplements;
    }
}
