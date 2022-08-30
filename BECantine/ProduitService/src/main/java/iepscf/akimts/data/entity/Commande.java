package iepscf.akimts.data.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("commande")
public class Commande {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private ObjectId id;

    private String user;
    private String bur;
    private LocalDate date;
    private String nom;
    private String prenom;

    private Collection<CmdProduit> plats = new HashSet<>();
    private Collection<CmdSandwich> sandwich = new HashSet<>();
    private Collection<CmdProduit> boissons = new HashSet<>();


    @ToString
    @Getter @Setter
    public static class CmdSandwich extends CmdProduit {

        private Collection<String> supplement;
        private Collection<String> moins;

    }

    @ToString
    @Getter @Setter
    public static class CmdProduit {

        private String nom;
        private int qte;

    }

}
