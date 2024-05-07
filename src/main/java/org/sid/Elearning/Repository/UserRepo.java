package org.sid.Elearning.Repository;

import org.sid.Elearning.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    List<User> findByFullnameRegex(String Fullname);

    List<User> findByEmail(String email);
    List<User> findByDiplome(String dipome);
    Optional<User> findByFullname(String Fullname);


}
