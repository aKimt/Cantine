package iepscf.akimts.produit.service;

import iepscf.akimts.produit.models.dto.AuthDTO;
import iepscf.akimts.produit.models.form.RegisterForm;

public interface UserService {

    AuthDTO register(RegisterForm form);

}
