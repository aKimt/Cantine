package iepscf.akimts.produit.models.dto;

import iepscf.akimts.data.entity.Composant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComposantDTO {

    private String ingredient;
    private double qte;
    private String unite;

    public static ComposantDTO of(Composant entity){
        if(entity == null)
            return null;

        ComposantDTO composantDTO = new ComposantDTO();

        composantDTO.setIngredient(entity.getIngredient());
        composantDTO.setQte(entity.getQte());
        composantDTO.setUnite(entity.getUnite());

        return composantDTO;
    }

}
