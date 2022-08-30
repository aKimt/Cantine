package iepscf.akimts.data.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Set;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("platchaud")
public class PlatChaud {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private ObjectId id;

    private String nom;
    private String desc;
    private Set<Composant> compisition = Set.of();
    private double prix;
    private Set<LocalDate> dates = Set.of();

}
