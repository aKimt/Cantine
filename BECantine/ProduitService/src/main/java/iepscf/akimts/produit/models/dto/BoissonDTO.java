package iepscf.akimts.produit.models.dto;

import iepscf.akimts.data.entity.Boisson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoissonDTO extends RepresentationModel<BoissonDTO> implements ProduitDTO {
    private ObjectId id;
    private String nom;
    private double prix;

    public static BoissonDTO of(Boisson entity){
        if(entity == null)
            return null;

        BoissonDTO boissonDTO = new BoissonDTO();

        boissonDTO.setId(entity.getId());
        boissonDTO.setNom(entity.getNom());
        boissonDTO.setPrix(entity.getPrix());

        return boissonDTO;
    }

}
