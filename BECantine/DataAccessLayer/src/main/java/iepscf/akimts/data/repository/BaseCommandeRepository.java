package iepscf.akimts.data.repository;

import iepscf.akimts.data.entity.Commande;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BaseCommandeRepository extends MongoRepository<Commande, ObjectId> {
}
