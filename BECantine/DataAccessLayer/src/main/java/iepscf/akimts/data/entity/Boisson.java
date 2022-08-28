package iepscf.akimts.data.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("boisson")
public class Boisson {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private ObjectId id;
    private String nom;
    private double prix;

}
