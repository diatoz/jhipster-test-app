package com.mycompany.myapp.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Skill.
 */
@Document(collection = "skill")
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("skill")
    private String skill;

    @Field("skill_desc")
    private String skillDesc;

    @Field("icon")
    private String icon;

    @Field("language")
    private String language;

    @Field("type")
    private String type;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Skill id(String id) {
        this.id = id;
        return this;
    }

    public String getSkill() {
        return this.skill;
    }

    public Skill skill(String skill) {
        this.skill = skill;
        return this;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkillDesc() {
        return this.skillDesc;
    }

    public Skill skillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
        return this;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }

    public String getIcon() {
        return this.icon;
    }

    public Skill icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLanguage() {
        return this.language;
    }

    public Skill language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return this.type;
    }

    public Skill type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        return id != null && id.equals(((Skill) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Skill{" +
            "id=" + getId() +
            ", skill='" + getSkill() + "'" +
            ", skillDesc='" + getSkillDesc() + "'" +
            ", icon='" + getIcon() + "'" +
            ", language='" + getLanguage() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
