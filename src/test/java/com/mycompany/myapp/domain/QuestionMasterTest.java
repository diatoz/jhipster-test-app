package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionMaster.class);
        QuestionMaster questionMaster1 = new QuestionMaster();
        questionMaster1.setId("id1");
        QuestionMaster questionMaster2 = new QuestionMaster();
        questionMaster2.setId(questionMaster1.getId());
        assertThat(questionMaster1).isEqualTo(questionMaster2);
        questionMaster2.setId("id2");
        assertThat(questionMaster1).isNotEqualTo(questionMaster2);
        questionMaster1.setId(null);
        assertThat(questionMaster1).isNotEqualTo(questionMaster2);
    }
}
