package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progression.class);
        Progression progression1 = new Progression();
        progression1.setId(1L);
        Progression progression2 = new Progression();
        progression2.setId(progression1.getId());
        assertThat(progression1).isEqualTo(progression2);
        progression2.setId(2L);
        assertThat(progression1).isNotEqualTo(progression2);
        progression1.setId(null);
        assertThat(progression1).isNotEqualTo(progression2);
    }
}
