package org.sid.Elearning.Repository;

import org.sid.Elearning.entities.Formation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FormationRepo extends MongoRepository<Formation,String > {

}
