package iepscf.akimts.produit.mapper;

import iepscf.akimts.data.entity.Commande;
import iepscf.akimts.produit.models.form.CommandeForm;

import java.time.ZoneId;
import java.util.Date;

public final class
CommandeMapper {

    // no instance for this class
    private CommandeMapper(){}

    /**
     * mappe une form de commande vers la forme entité
     * Ignore:
     * <ul>
     *     <li>nom</li>
     *     <li>prenom</li>
     *     <li>bur</li>
     * </ul>
     *
     * @param form, un CommandeForm représentant les infos nécessaires
     *              à la création d'une commande
     * @return une entité Commande pouvant être placée en persistance
     */
    public static Commande formToEntity(CommandeForm form){
        if(form == null)
            return null;

        Commande cmd = new Commande();

        Date date = Date.from( form.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant() );
        cmd.setDate( date );

        cmd.setBoissons(form.getBoissons());
        cmd.setSandwich(form.getSandwiches());
        cmd.setPlats(form.getPlatChauds());

        return cmd;
    }


}
