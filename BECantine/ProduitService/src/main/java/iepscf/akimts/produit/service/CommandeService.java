package iepscf.akimts.produit.service;

import iepscf.akimts.produit.models.form.CommandeForm;

public interface CommandeService {

    void createOrder(String username, CommandeForm form);

}
