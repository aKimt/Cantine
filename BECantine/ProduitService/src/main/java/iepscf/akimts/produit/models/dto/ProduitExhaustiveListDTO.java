package iepscf.akimts.produit.models.dto;

import iepscf.akimts.data.entity.Boisson;
import iepscf.akimts.data.entity.PlatChaud;
import iepscf.akimts.data.entity.Sandwich;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProduitExhaustiveListDTO extends RepresentationModel<ProduitExhaustiveListDTO> {

    private List<BoissonDTO> boissons;
    private List<PlatChaudDTO> platChauds;
    private List<SandwichDTO> sandwiches;

    public static ProduitExhaustiveListDTO of(
            Collection<Boisson> boissons,
            Collection<PlatChaud> platChauds,
            Collection<Sandwich> sandwiches
    ){

        ProduitExhaustiveListDTO list = new ProduitExhaustiveListDTO();

        if(boissons != null)
            list.setBoissons( boissons.stream().map(BoissonDTO::of).toList() );

        if(platChauds != null)
            list.setPlatChauds( platChauds.stream().map(PlatChaudDTO::of).toList() );

        if(sandwiches != null)
            list.setSandwiches( sandwiches.stream().map(SandwichDTO::of).toList() );

        return list;
    }

}
