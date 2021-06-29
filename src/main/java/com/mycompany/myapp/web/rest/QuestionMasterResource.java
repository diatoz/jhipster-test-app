package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.QuestionMaster;
import com.mycompany.myapp.repository.QuestionMasterRepository;
import com.mycompany.myapp.service.QuestionMasterService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.QuestionMaster}.
 */
@RestController
@RequestMapping("/api")
public class QuestionMasterResource {

    private final Logger log = LoggerFactory.getLogger(QuestionMasterResource.class);

    private static final String ENTITY_NAME = "questionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionMasterService questionMasterService;

    private final QuestionMasterRepository questionMasterRepository;

    public QuestionMasterResource(QuestionMasterService questionMasterService, QuestionMasterRepository questionMasterRepository) {
        this.questionMasterService = questionMasterService;
        this.questionMasterRepository = questionMasterRepository;
    }

    /**
     * {@code POST  /question-masters} : Create a new questionMaster.
     *
     * @param questionMaster the questionMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionMaster, or with status {@code 400 (Bad Request)} if the questionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-masters")
    public ResponseEntity<QuestionMaster> createQuestionMaster(@RequestBody QuestionMaster questionMaster) throws URISyntaxException {
        log.debug("REST request to save QuestionMaster : {}", questionMaster);
        if (questionMaster.getId() != null) {
            throw new BadRequestAlertException("A new questionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionMaster result = questionMasterService.save(questionMaster);
        return ResponseEntity
            .created(new URI("/api/question-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /question-masters/:id} : Updates an existing questionMaster.
     *
     * @param id the id of the questionMaster to save.
     * @param questionMaster the questionMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionMaster,
     * or with status {@code 400 (Bad Request)} if the questionMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-masters/{id}")
    public ResponseEntity<QuestionMaster> updateQuestionMaster(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QuestionMaster questionMaster
    ) throws URISyntaxException {
        log.debug("REST request to update QuestionMaster : {}, {}", id, questionMaster);
        if (questionMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionMaster result = questionMasterService.save(questionMaster);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionMaster.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /question-masters/:id} : Partial updates given fields of an existing questionMaster, field will ignore if it is null
     *
     * @param id the id of the questionMaster to save.
     * @param questionMaster the questionMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionMaster,
     * or with status {@code 400 (Bad Request)} if the questionMaster is not valid,
     * or with status {@code 404 (Not Found)} if the questionMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-masters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<QuestionMaster> partialUpdateQuestionMaster(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody QuestionMaster questionMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update QuestionMaster partially : {}, {}", id, questionMaster);
        if (questionMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionMaster> result = questionMasterService.partialUpdate(questionMaster);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionMaster.getId())
        );
    }

    /**
     * {@code GET  /question-masters} : get all the questionMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionMasters in body.
     */
    @GetMapping("/question-masters")
    public ResponseEntity<List<QuestionMaster>> getAllQuestionMasters(Pageable pageable) {
        log.debug("REST request to get a page of QuestionMasters");
        Page<QuestionMaster> page = questionMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /question-masters/:id} : get the "id" questionMaster.
     *
     * @param id the id of the questionMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-masters/{id}")
    public ResponseEntity<QuestionMaster> getQuestionMaster(@PathVariable String id) {
        log.debug("REST request to get QuestionMaster : {}", id);
        Optional<QuestionMaster> questionMaster = questionMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionMaster);
    }

    /**
     * {@code DELETE  /question-masters/:id} : delete the "id" questionMaster.
     *
     * @param id the id of the questionMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-masters/{id}")
    public ResponseEntity<Void> deleteQuestionMaster(@PathVariable String id) {
        log.debug("REST request to delete QuestionMaster : {}", id);
        questionMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
