package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.Category;
import com.mycompany.myapp.domain.enumeration.Complexity;
import com.mycompany.myapp.domain.enumeration.QuestionType;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A QuestionMaster.
 */
@Document(collection = "question_master")
public class QuestionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("question_id")
    private String questionId;

    @Field("question")
    private String question;

    @Field("type")
    private QuestionType type;

    @Field("complexity")
    private Complexity complexity;

    @Field("experience_from")
    private Integer experienceFrom;

    @Field("experience_to")
    private Integer experienceTo;

    @Field("category")
    private Category category;

    @Field("reference")
    private String reference;

    @Field("audiolink")
    private String audiolink;

    @Field("skill_id")
    private String skillId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionMaster id(String id) {
        this.id = id;
        return this;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public QuestionMaster questionId(String questionId) {
        this.questionId = questionId;
        return this;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return this.question;
    }

    public QuestionMaster question(String question) {
        this.question = question;
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return this.type;
    }

    public QuestionMaster type(QuestionType type) {
        this.type = type;
        return this;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Complexity getComplexity() {
        return this.complexity;
    }

    public QuestionMaster complexity(Complexity complexity) {
        this.complexity = complexity;
        return this;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public Integer getExperienceFrom() {
        return this.experienceFrom;
    }

    public QuestionMaster experienceFrom(Integer experienceFrom) {
        this.experienceFrom = experienceFrom;
        return this;
    }

    public void setExperienceFrom(Integer experienceFrom) {
        this.experienceFrom = experienceFrom;
    }

    public Integer getExperienceTo() {
        return this.experienceTo;
    }

    public QuestionMaster experienceTo(Integer experienceTo) {
        this.experienceTo = experienceTo;
        return this;
    }

    public void setExperienceTo(Integer experienceTo) {
        this.experienceTo = experienceTo;
    }

    public Category getCategory() {
        return this.category;
    }

    public QuestionMaster category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getReference() {
        return this.reference;
    }

    public QuestionMaster reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAudiolink() {
        return this.audiolink;
    }

    public QuestionMaster audiolink(String audiolink) {
        this.audiolink = audiolink;
        return this;
    }

    public void setAudiolink(String audiolink) {
        this.audiolink = audiolink;
    }

    public String getSkillId() {
        return this.skillId;
    }

    public QuestionMaster skillId(String skillId) {
        this.skillId = skillId;
        return this;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionMaster)) {
            return false;
        }
        return id != null && id.equals(((QuestionMaster) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionMaster{" +
            "id=" + getId() +
            ", questionId='" + getQuestionId() + "'" +
            ", question='" + getQuestion() + "'" +
            ", type='" + getType() + "'" +
            ", complexity='" + getComplexity() + "'" +
            ", experienceFrom=" + getExperienceFrom() +
            ", experienceTo=" + getExperienceTo() +
            ", category='" + getCategory() + "'" +
            ", reference='" + getReference() + "'" +
            ", audiolink='" + getAudiolink() + "'" +
            ", skillId='" + getSkillId() + "'" +
            "}";
    }
}
