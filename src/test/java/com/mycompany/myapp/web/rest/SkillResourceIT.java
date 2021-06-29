package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Skill;
import com.mycompany.myapp.repository.SkillRepository;
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
 * Integration tests for the {@link SkillResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SkillResourceIT {

    private static final String DEFAULT_SKILL = "AAAAAAAAAA";
    private static final String UPDATED_SKILL = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/skills";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private MockMvc restSkillMockMvc;

    private Skill skill;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Skill createEntity() {
        Skill skill = new Skill()
            .skill(DEFAULT_SKILL)
            .skillDesc(DEFAULT_SKILL_DESC)
            .icon(DEFAULT_ICON)
            .language(DEFAULT_LANGUAGE)
            .type(DEFAULT_TYPE);
        return skill;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Skill createUpdatedEntity() {
        Skill skill = new Skill()
            .skill(UPDATED_SKILL)
            .skillDesc(UPDATED_SKILL_DESC)
            .icon(UPDATED_ICON)
            .language(UPDATED_LANGUAGE)
            .type(UPDATED_TYPE);
        return skill;
    }

    @BeforeEach
    public void initTest() {
        skillRepository.deleteAll();
        skill = createEntity();
    }

    @Test
    void createSkill() throws Exception {
        int databaseSizeBeforeCreate = skillRepository.findAll().size();
        // Create the Skill
        restSkillMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isCreated());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeCreate + 1);
        Skill testSkill = skillList.get(skillList.size() - 1);
        assertThat(testSkill.getSkill()).isEqualTo(DEFAULT_SKILL);
        assertThat(testSkill.getSkillDesc()).isEqualTo(DEFAULT_SKILL_DESC);
        assertThat(testSkill.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testSkill.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testSkill.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    void createSkillWithExistingId() throws Exception {
        // Create the Skill with an existing ID
        skill.setId("existing_id");

        int databaseSizeBeforeCreate = skillRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSkills() throws Exception {
        // Initialize the database
        skillRepository.save(skill);

        // Get all the skillList
        restSkillMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skill.getId())))
            .andExpect(jsonPath("$.[*].skill").value(hasItem(DEFAULT_SKILL)))
            .andExpect(jsonPath("$.[*].skillDesc").value(hasItem(DEFAULT_SKILL_DESC)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }

    @Test
    void getSkill() throws Exception {
        // Initialize the database
        skillRepository.save(skill);

        // Get the skill
        restSkillMockMvc
            .perform(get(ENTITY_API_URL_ID, skill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(skill.getId()))
            .andExpect(jsonPath("$.skill").value(DEFAULT_SKILL))
            .andExpect(jsonPath("$.skillDesc").value(DEFAULT_SKILL_DESC))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    void getNonExistingSkill() throws Exception {
        // Get the skill
        restSkillMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewSkill() throws Exception {
        // Initialize the database
        skillRepository.save(skill);

        int databaseSizeBeforeUpdate = skillRepository.findAll().size();

        // Update the skill
        Skill updatedSkill = skillRepository.findById(skill.getId()).get();
        updatedSkill.skill(UPDATED_SKILL).skillDesc(UPDATED_SKILL_DESC).icon(UPDATED_ICON).language(UPDATED_LANGUAGE).type(UPDATED_TYPE);

        restSkillMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSkill.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSkill))
            )
            .andExpect(status().isOk());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
        Skill testSkill = skillList.get(skillList.size() - 1);
        assertThat(testSkill.getSkill()).isEqualTo(UPDATED_SKILL);
        assertThat(testSkill.getSkillDesc()).isEqualTo(UPDATED_SKILL_DESC);
        assertThat(testSkill.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testSkill.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testSkill.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    void putNonExistingSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();
        skill.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillMockMvc
            .perform(
                put(ENTITY_API_URL_ID, skill.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(skill))
            )
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();
        skill.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSkillMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(skill))
            )
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();
        skill.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSkillMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSkillWithPatch() throws Exception {
        // Initialize the database
        skillRepository.save(skill);

        int databaseSizeBeforeUpdate = skillRepository.findAll().size();

        // Update the skill using partial update
        Skill partialUpdatedSkill = new Skill();
        partialUpdatedSkill.setId(skill.getId());

        partialUpdatedSkill.skillDesc(UPDATED_SKILL_DESC).language(UPDATED_LANGUAGE).type(UPDATED_TYPE);

        restSkillMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSkill.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSkill))
            )
            .andExpect(status().isOk());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
        Skill testSkill = skillList.get(skillList.size() - 1);
        assertThat(testSkill.getSkill()).isEqualTo(DEFAULT_SKILL);
        assertThat(testSkill.getSkillDesc()).isEqualTo(UPDATED_SKILL_DESC);
        assertThat(testSkill.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testSkill.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testSkill.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    void fullUpdateSkillWithPatch() throws Exception {
        // Initialize the database
        skillRepository.save(skill);

        int databaseSizeBeforeUpdate = skillRepository.findAll().size();

        // Update the skill using partial update
        Skill partialUpdatedSkill = new Skill();
        partialUpdatedSkill.setId(skill.getId());

        partialUpdatedSkill
            .skill(UPDATED_SKILL)
            .skillDesc(UPDATED_SKILL_DESC)
            .icon(UPDATED_ICON)
            .language(UPDATED_LANGUAGE)
            .type(UPDATED_TYPE);

        restSkillMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSkill.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSkill))
            )
            .andExpect(status().isOk());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
        Skill testSkill = skillList.get(skillList.size() - 1);
        assertThat(testSkill.getSkill()).isEqualTo(UPDATED_SKILL);
        assertThat(testSkill.getSkillDesc()).isEqualTo(UPDATED_SKILL_DESC);
        assertThat(testSkill.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testSkill.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testSkill.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    void patchNonExistingSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();
        skill.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, skill.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(skill))
            )
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();
        skill.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSkillMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(skill))
            )
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();
        skill.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSkillMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSkill() throws Exception {
        // Initialize the database
        skillRepository.save(skill);

        int databaseSizeBeforeDelete = skillRepository.findAll().size();

        // Delete the skill
        restSkillMockMvc
            .perform(delete(ENTITY_API_URL_ID, skill.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
