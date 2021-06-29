package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Skill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillRepository extends MongoRepository<Skill, String> {}
