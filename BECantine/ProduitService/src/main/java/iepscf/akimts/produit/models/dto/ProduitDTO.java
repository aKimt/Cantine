package iepscf.akimts.produit.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

public interface ProduitDTO {

    ObjectId getId();
    String getNom();
    double getPrix();

}
