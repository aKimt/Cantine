package iepscf.akimts.data.repository;

import iepscf.akimts.data.entity.Boisson;
import iepscf.akimts.data.entity.PlatChaud;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BasePlatChaudRepository extends MongoRepository<PlatChaud, ObjectId> {
    Optional<PlatChaud> findByNom(String nom);
}
