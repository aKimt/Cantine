package iepscf.akimts.produit.models.form;

import iepscf.akimts.data.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterForm {

    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 6)
    private String pwd;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String bureau;

    public User toEntity(){

        User user = new User();

        user.setLogin(login);
        user.setPwd(pwd);
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setRoles(Set.of("USER"));
        user.setBureau(bureau);

        return user;

    }

}
