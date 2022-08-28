package iepscf.akimts.produit.models.form;


import iepscf.akimts.data.entity.Commande;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Data
public class CommandeForm {

    @Future
    @NotNull
    private LocalDate date;
    private Collection<Commande.CmdProduit> platChauds;
    private Collection<Commande.CmdSandwich> sandwiches;
    private Collection<Commande.CmdProduit> boissons;

}
