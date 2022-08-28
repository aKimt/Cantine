package iepscf.akimts.produit.models.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
