package com.mycompany.myapp.domain;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AnswerOptionMaster.
 */
public class AnswerOptionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("answer_option_id")
    private String answerOptionId;

    @Field("answer_option")
    private String answerOption;

    @Field("answer_option_order")
    private Integer answerOptionOrder;

    @Field("correct_answer")
    private Boolean correctAnswer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getAnswerOptionId() {
        return this.answerOptionId;
    }

    public AnswerOptionMaster answerOptionId(String answerOptionId) {
        this.answerOptionId = answerOptionId;
        return this;
    }

    public void setAnswerOptionId(String answerOptionId) {
        this.answerOptionId = answerOptionId;
    }

    public String getAnswerOption() {
        return this.answerOption;
    }

    public AnswerOptionMaster answerOption(String answerOption) {
        this.answerOption = answerOption;
        return this;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public Integer getAnswerOptionOrder() {
        return this.answerOptionOrder;
    }

    public AnswerOptionMaster answerOptionOrder(Integer answerOptionOrder) {
        this.answerOptionOrder = answerOptionOrder;
        return this;
    }

    public void setAnswerOptionOrder(Integer answerOptionOrder) {
        this.answerOptionOrder = answerOptionOrder;
    }

    public Boolean getCorrectAnswer() {
        return this.correctAnswer;
    }

    public AnswerOptionMaster correctAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerOptionMaster)) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerOptionMaster{" +
            ", answerOptionId='" + getAnswerOptionId() + "'" +
            ", answerOption='" + getAnswerOption() + "'" +
            ", answerOptionOrder=" + getAnswerOptionOrder() +
            ", correctAnswer='" + getCorrectAnswer() + "'" +
            "}";
    }
}
