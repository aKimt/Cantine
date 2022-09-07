package iepscf.akimts.produit.service;

import iepscf.akimts.produit.models.dto.CommandeDTO;
import iepscf.akimts.produit.models.form.CommandeForm;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommandeService {

    void createOrder(String username, CommandeForm form);
    List<CommandeDTO> getOrdrerByUsername(String username);
    void deleteById(ObjectId id, String user);

}
