package iepscf.akimts.produit.models.dto;

import iepscf.akimts.data.entity.Sandwich;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SandwichDTO extends RepresentationModel<SandwichDTO> implements ProduitDTO {
    private ObjectId id;
    private String nom;
    private double prix;
    private String description;
    private Set<ComposantDTO> composants;

    public static SandwichDTO of(Sandwich sandwich){

        if(sandwich == null)
            return null;

        SandwichDTO sandwichDTO = new SandwichDTO();

        sandwichDTO.setId(sandwich.getId());
        sandwichDTO.setNom(sandwich.getNom());
        sandwichDTO.setPrix(sandwich.getPrix());
        sandwichDTO.setDescription(sandwich.getDesc());

        sandwichDTO.setComposants(
                sandwich.getComposition().stream()
                        .map(ComposantDTO::of)
                        .collect(Collectors.toSet())
        );

        return sandwichDTO;

    }

}
