package iepscf.akimts.produit.models.dto;

import iepscf.akimts.data.entity.PlatChaud;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatChaudDTO extends RepresentationModel<PlatChaudDTO> implements ProduitDTO {

    private ObjectId id;
    private String nom;
    private double prix;
    private String description;
    private Set<ComposantDTO> composants;
    private Set<LocalDate> dates;

    public static PlatChaudDTO of(PlatChaud entity){

        if(entity == null)
            return null;

        PlatChaudDTO platChaudDTO = new PlatChaudDTO();

        platChaudDTO.setId(entity.getId());
        platChaudDTO.setDates(entity.getDates());
        platChaudDTO.setNom(entity.getNom());
        platChaudDTO.setDescription(entity.getDesc());
        platChaudDTO.setPrix(entity.getPrix());

        platChaudDTO.setComposants(
                entity.getCompisition().stream()
                        .map(ComposantDTO::of)
                        .collect(Collectors.toSet())
        );

        return platChaudDTO;

    }

}
