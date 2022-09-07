package iepscf.akimts.produit.models.dto;

import iepscf.akimts.data.entity.Commande;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommandeDTO extends RepresentationModel<CommandeDTO> {

    private String id;
    private String user;
    private String bur;
    private Date date;
    private String nom;
    private String prenom;

    private List<CmdProduitDTO> boissons;
    private List<CmdProduitDTO> platsChauds;
    private List<CmdSandwichDTO> sandwichs  ;

    public static CommandeDTO of(Commande cmd){
        CommandeDTO dto = new CommandeDTO();

        dto.setId( cmd.getId().toHexString() );
        dto.setUser(cmd.getUser());
        dto.setBur( cmd.getBur() );
        dto.setDate( cmd.getDate() );
        dto.setNom( cmd.getNom() );
        dto.setPrenom( cmd.getPrenom() );

        dto.setBoissons( cmd.getBoissons().stream().map(CmdProduitDTO::of).toList() );
        dto.setPlatsChauds( cmd.getPlats().stream().map(CmdProduitDTO::of).toList() );
        dto.setSandwichs( cmd.getSandwich().stream().map(CmdSandwichDTO::of).toList() );

        return dto;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CmdSandwichDTO extends CmdProduitDTO {

        private Collection<String> supplement;
        private Collection<String> moins;

        public CmdSandwichDTO() {
        }

        public CmdSandwichDTO(String nom, int qte, Collection<String> supplement, Collection<String> moins) {
            super(nom, qte);
            this.supplement = supplement;
            this.moins = moins;
        }

        public static CmdSandwichDTO of(Commande.CmdSandwich sandwich){
            return new CmdSandwichDTO(
                    sandwich.getNom(),
                    sandwich.getQte(),
                    sandwich.getSupplement(),
                    sandwich.getMoins()
            );
        }
    }

    @Data
    public static class CmdProduitDTO {
        private String nom;
        private int qte;

        public CmdProduitDTO() {
        }

        public CmdProduitDTO(String nom, int qte) {
            this.nom = nom;
            this.qte = qte;
        }

        public static CmdProduitDTO of(Commande.CmdProduit produit){
            return new CmdProduitDTO(produit.getNom(), produit.getQte());
        }
    }

}
