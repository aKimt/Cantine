package iepscf.akimts.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Document("user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    private String login;
    private String pwd;
    private String nom;
    private String prenom;
    private String bureau;

    private Set<String> roles = new HashSet<>();


}
