package iepscf.akimts.produit.repository;

import iepscf.akimts.data.entity.Commande;
import iepscf.akimts.data.repository.BaseCommandeRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends BaseCommandeRepository {

    List<Commande> findByUser(String username);

    void deleteById(ObjectId id);
}
