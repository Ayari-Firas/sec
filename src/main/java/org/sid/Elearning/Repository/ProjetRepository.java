package org.sid.Elearning.Repository;

import org.sid.Elearning.entities.Projet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjetRepository extends MongoRepository<Projet,String> {
    @Query("{ 'user': ?0 }")
    List<Projet> findByUserId(String userId);
}

