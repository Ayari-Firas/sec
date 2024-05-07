package org.sid.Elearning.Repository;

import org.sid.Elearning.entities.admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminrep extends MongoRepository<admin,String> {


}
