package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QuestionMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the QuestionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionMasterRepository extends MongoRepository<QuestionMaster, String> {}
