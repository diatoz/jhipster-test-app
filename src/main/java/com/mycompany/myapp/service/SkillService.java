package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Skill;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Skill}.
 */
public interface SkillService {
    /**
     * Save a skill.
     *
     * @param skill the entity to save.
     * @return the persisted entity.
     */
    Skill save(Skill skill);

    /**
     * Partially updates a skill.
     *
     * @param skill the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Skill> partialUpdate(Skill skill);

    /**
     * Get all the skills.
     *
     * @return the list of entities.
     */
    List<Skill> findAll();

    /**
     * Get the "id" skill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Skill> findOne(String id);

    /**
     * Delete the "id" skill.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
