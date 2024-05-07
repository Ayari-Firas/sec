package org.sid.Elearning.Repository;

import org.sid.Elearning.entities.AvisFormateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AvisFormateurRepository extends MongoRepository<AvisFormateur,String> {

    List<AvisFormateur> findByFormationId(String formationId);

    List<AvisFormateur> findByUserId(String userId);
}

