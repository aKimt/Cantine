package iepscf.akimts.data.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Sandwich {

    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;
    private String nom;
    private String desc;
    private Set<Composant> composition = Set.of();
    private double prix;

}
