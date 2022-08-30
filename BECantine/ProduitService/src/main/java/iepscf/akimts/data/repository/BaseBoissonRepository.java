package iepscf.akimts.data.repository;

import iepscf.akimts.data.entity.Boisson;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BaseBoissonRepository extends MongoRepository<Boisson, ObjectId> {

    Optional<Boisson> findByNom(String nom);

}
