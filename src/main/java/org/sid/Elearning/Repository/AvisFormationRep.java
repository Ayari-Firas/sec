package org.sid.Elearning.Repository;

import org.sid.Elearning.entities.AvisFormation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AvisFormationRep extends MongoRepository<AvisFormation,String> {
   /* public AvisFormation saveAvisFormation(AvisFormation avisFormation); // New method
*/
}
