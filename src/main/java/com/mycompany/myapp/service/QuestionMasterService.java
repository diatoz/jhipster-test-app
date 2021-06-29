package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.QuestionMaster;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link QuestionMaster}.
 */
public interface QuestionMasterService {
    /**
     * Save a questionMaster.
     *
     * @param questionMaster the entity to save.
     * @return the persisted entity.
     */
    QuestionMaster save(QuestionMaster questionMaster);

    /**
     * Partially updates a questionMaster.
     *
     * @param questionMaster the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionMaster> partialUpdate(QuestionMaster questionMaster);

    /**
     * Get all the questionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionMaster> findAll(Pageable pageable);

    /**
     * Get the "id" questionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionMaster> findOne(String id);

    /**
     * Delete the "id" questionMaster.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
