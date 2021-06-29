package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.QuestionMaster;
import com.mycompany.myapp.domain.enumeration.Category;
import com.mycompany.myapp.domain.enumeration.Complexity;
import com.mycompany.myapp.domain.enumeration.QuestionType;
import com.mycompany.myapp.repository.QuestionMasterRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link QuestionMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionMasterResourceIT {

    private static final String DEFAULT_QUESTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final QuestionType DEFAULT_TYPE = QuestionType.MCQ;
    private static final QuestionType UPDATED_TYPE = QuestionType.VIDEO;

    private static final Complexity DEFAULT_COMPLEXITY = Complexity.ENTRY;
    private static final Complexity UPDATED_COMPLEXITY = Complexity.JUNIOR;

    private static final Integer DEFAULT_EXPERIENCE_FROM = 1;
    private static final Integer UPDATED_EXPERIENCE_FROM = 2;

    private static final Integer DEFAULT_EXPERIENCE_TO = 1;
    private static final Integer UPDATED_EXPERIENCE_TO = 2;

    private static final Category DEFAULT_CATEGORY = Category.GENERAL;
    private static final Category UPDATED_CATEGORY = Category.TECHNICAL;

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_AUDIOLINK = "AAAAAAAAAA";
    private static final String UPDATED_AUDIOLINK = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_ID = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/question-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private QuestionMasterRepository questionMasterRepository;

    @Autowired
    private MockMvc restQuestionMasterMockMvc;

    private QuestionMaster questionMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionMaster createEntity() {
        QuestionMaster questionMaster = new QuestionMaster()
            .questionId(DEFAULT_QUESTION_ID)
            .question(DEFAULT_QUESTION)
            .type(DEFAULT_TYPE)
            .complexity(DEFAULT_COMPLEXITY)
            .experienceFrom(DEFAULT_EXPERIENCE_FROM)
            .experienceTo(DEFAULT_EXPERIENCE_TO)
            .category(DEFAULT_CATEGORY)
            .reference(DEFAULT_REFERENCE)
            .audiolink(DEFAULT_AUDIOLINK)
            .skillId(DEFAULT_SKILL_ID);
        return questionMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionMaster createUpdatedEntity() {
        QuestionMaster questionMaster = new QuestionMaster()
            .questionId(UPDATED_QUESTION_ID)
            .question(UPDATED_QUESTION)
            .type(UPDATED_TYPE)
            .complexity(UPDATED_COMPLEXITY)
            .experienceFrom(UPDATED_EXPERIENCE_FROM)
            .experienceTo(UPDATED_EXPERIENCE_TO)
            .category(UPDATED_CATEGORY)
            .reference(UPDATED_REFERENCE)
            .audiolink(UPDATED_AUDIOLINK)
            .skillId(UPDATED_SKILL_ID);
        return questionMaster;
    }

    @BeforeEach
    public void initTest() {
        questionMasterRepository.deleteAll();
        questionMaster = createEntity();
    }

    @Test
    void createQuestionMaster() throws Exception {
        int databaseSizeBeforeCreate = questionMasterRepository.findAll().size();
        // Create the QuestionMaster
        restQuestionMasterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isCreated());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionMaster testQuestionMaster = questionMasterList.get(questionMasterList.size() - 1);
        assertThat(testQuestionMaster.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
        assertThat(testQuestionMaster.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testQuestionMaster.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testQuestionMaster.getComplexity()).isEqualTo(DEFAULT_COMPLEXITY);
        assertThat(testQuestionMaster.getExperienceFrom()).isEqualTo(DEFAULT_EXPERIENCE_FROM);
        assertThat(testQuestionMaster.getExperienceTo()).isEqualTo(DEFAULT_EXPERIENCE_TO);
        assertThat(testQuestionMaster.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testQuestionMaster.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testQuestionMaster.getAudiolink()).isEqualTo(DEFAULT_AUDIOLINK);
        assertThat(testQuestionMaster.getSkillId()).isEqualTo(DEFAULT_SKILL_ID);
    }

    @Test
    void createQuestionMasterWithExistingId() throws Exception {
        // Create the QuestionMaster with an existing ID
        questionMaster.setId("existing_id");

        int databaseSizeBeforeCreate = questionMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMasterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllQuestionMasters() throws Exception {
        // Initialize the database
        questionMasterRepository.save(questionMaster);

        // Get all the questionMasterList
        restQuestionMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionMaster.getId())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID)))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].complexity").value(hasItem(DEFAULT_COMPLEXITY.toString())))
            .andExpect(jsonPath("$.[*].experienceFrom").value(hasItem(DEFAULT_EXPERIENCE_FROM)))
            .andExpect(jsonPath("$.[*].experienceTo").value(hasItem(DEFAULT_EXPERIENCE_TO)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].audiolink").value(hasItem(DEFAULT_AUDIOLINK)))
            .andExpect(jsonPath("$.[*].skillId").value(hasItem(DEFAULT_SKILL_ID)));
    }

    @Test
    void getQuestionMaster() throws Exception {
        // Initialize the database
        questionMasterRepository.save(questionMaster);

        // Get the questionMaster
        restQuestionMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, questionMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionMaster.getId()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.complexity").value(DEFAULT_COMPLEXITY.toString()))
            .andExpect(jsonPath("$.experienceFrom").value(DEFAULT_EXPERIENCE_FROM))
            .andExpect(jsonPath("$.experienceTo").value(DEFAULT_EXPERIENCE_TO))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.audiolink").value(DEFAULT_AUDIOLINK))
            .andExpect(jsonPath("$.skillId").value(DEFAULT_SKILL_ID));
    }

    @Test
    void getNonExistingQuestionMaster() throws Exception {
        // Get the questionMaster
        restQuestionMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewQuestionMaster() throws Exception {
        // Initialize the database
        questionMasterRepository.save(questionMaster);

        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();

        // Update the questionMaster
        QuestionMaster updatedQuestionMaster = questionMasterRepository.findById(questionMaster.getId()).get();
        updatedQuestionMaster
            .questionId(UPDATED_QUESTION_ID)
            .question(UPDATED_QUESTION)
            .type(UPDATED_TYPE)
            .complexity(UPDATED_COMPLEXITY)
            .experienceFrom(UPDATED_EXPERIENCE_FROM)
            .experienceTo(UPDATED_EXPERIENCE_TO)
            .category(UPDATED_CATEGORY)
            .reference(UPDATED_REFERENCE)
            .audiolink(UPDATED_AUDIOLINK)
            .skillId(UPDATED_SKILL_ID);

        restQuestionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQuestionMaster.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedQuestionMaster))
            )
            .andExpect(status().isOk());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
        QuestionMaster testQuestionMaster = questionMasterList.get(questionMasterList.size() - 1);
        assertThat(testQuestionMaster.getQuestionId()).isEqualTo(UPDATED_QUESTION_ID);
        assertThat(testQuestionMaster.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testQuestionMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQuestionMaster.getComplexity()).isEqualTo(UPDATED_COMPLEXITY);
        assertThat(testQuestionMaster.getExperienceFrom()).isEqualTo(UPDATED_EXPERIENCE_FROM);
        assertThat(testQuestionMaster.getExperienceTo()).isEqualTo(UPDATED_EXPERIENCE_TO);
        assertThat(testQuestionMaster.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testQuestionMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testQuestionMaster.getAudiolink()).isEqualTo(UPDATED_AUDIOLINK);
        assertThat(testQuestionMaster.getSkillId()).isEqualTo(UPDATED_SKILL_ID);
    }

    @Test
    void putNonExistingQuestionMaster() throws Exception {
        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();
        questionMaster.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionMaster.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchQuestionMaster() throws Exception {
        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();
        questionMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamQuestionMaster() throws Exception {
        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();
        questionMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMasterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionMaster)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateQuestionMasterWithPatch() throws Exception {
        // Initialize the database
        questionMasterRepository.save(questionMaster);

        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();

        // Update the questionMaster using partial update
        QuestionMaster partialUpdatedQuestionMaster = new QuestionMaster();
        partialUpdatedQuestionMaster.setId(questionMaster.getId());

        partialUpdatedQuestionMaster
            .type(UPDATED_TYPE)
            .complexity(UPDATED_COMPLEXITY)
            .experienceTo(UPDATED_EXPERIENCE_TO)
            .category(UPDATED_CATEGORY)
            .reference(UPDATED_REFERENCE)
            .skillId(UPDATED_SKILL_ID);

        restQuestionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionMaster))
            )
            .andExpect(status().isOk());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
        QuestionMaster testQuestionMaster = questionMasterList.get(questionMasterList.size() - 1);
        assertThat(testQuestionMaster.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
        assertThat(testQuestionMaster.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testQuestionMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQuestionMaster.getComplexity()).isEqualTo(UPDATED_COMPLEXITY);
        assertThat(testQuestionMaster.getExperienceFrom()).isEqualTo(DEFAULT_EXPERIENCE_FROM);
        assertThat(testQuestionMaster.getExperienceTo()).isEqualTo(UPDATED_EXPERIENCE_TO);
        assertThat(testQuestionMaster.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testQuestionMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testQuestionMaster.getAudiolink()).isEqualTo(DEFAULT_AUDIOLINK);
        assertThat(testQuestionMaster.getSkillId()).isEqualTo(UPDATED_SKILL_ID);
    }

    @Test
    void fullUpdateQuestionMasterWithPatch() throws Exception {
        // Initialize the database
        questionMasterRepository.save(questionMaster);

        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();

        // Update the questionMaster using partial update
        QuestionMaster partialUpdatedQuestionMaster = new QuestionMaster();
        partialUpdatedQuestionMaster.setId(questionMaster.getId());

        partialUpdatedQuestionMaster
            .questionId(UPDATED_QUESTION_ID)
            .question(UPDATED_QUESTION)
            .type(UPDATED_TYPE)
            .complexity(UPDATED_COMPLEXITY)
            .experienceFrom(UPDATED_EXPERIENCE_FROM)
            .experienceTo(UPDATED_EXPERIENCE_TO)
            .category(UPDATED_CATEGORY)
            .reference(UPDATED_REFERENCE)
            .audiolink(UPDATED_AUDIOLINK)
            .skillId(UPDATED_SKILL_ID);

        restQuestionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionMaster))
            )
            .andExpect(status().isOk());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
        QuestionMaster testQuestionMaster = questionMasterList.get(questionMasterList.size() - 1);
        assertThat(testQuestionMaster.getQuestionId()).isEqualTo(UPDATED_QUESTION_ID);
        assertThat(testQuestionMaster.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testQuestionMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQuestionMaster.getComplexity()).isEqualTo(UPDATED_COMPLEXITY);
        assertThat(testQuestionMaster.getExperienceFrom()).isEqualTo(UPDATED_EXPERIENCE_FROM);
        assertThat(testQuestionMaster.getExperienceTo()).isEqualTo(UPDATED_EXPERIENCE_TO);
        assertThat(testQuestionMaster.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testQuestionMaster.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testQuestionMaster.getAudiolink()).isEqualTo(UPDATED_AUDIOLINK);
        assertThat(testQuestionMaster.getSkillId()).isEqualTo(UPDATED_SKILL_ID);
    }

    @Test
    void patchNonExistingQuestionMaster() throws Exception {
        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();
        questionMaster.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchQuestionMaster() throws Exception {
        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();
        questionMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamQuestionMaster() throws Exception {
        int databaseSizeBeforeUpdate = questionMasterRepository.findAll().size();
        questionMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionMasterMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(questionMaster))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionMaster in the database
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteQuestionMaster() throws Exception {
        // Initialize the database
        questionMasterRepository.save(questionMaster);

        int databaseSizeBeforeDelete = questionMasterRepository.findAll().size();

        // Delete the questionMaster
        restQuestionMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, questionMaster.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionMaster> questionMasterList = questionMasterRepository.findAll();
        assertThat(questionMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
