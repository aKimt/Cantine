package iepscf.akimts.data.repository;

import iepscf.akimts.data.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BaseUserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByLogin(String login);

}
