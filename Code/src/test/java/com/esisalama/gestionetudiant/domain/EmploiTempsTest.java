package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmploiTempsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmploiTemps.class);
        EmploiTemps emploiTemps1 = new EmploiTemps();
        emploiTemps1.setId(1L);
        EmploiTemps emploiTemps2 = new EmploiTemps();
        emploiTemps2.setId(emploiTemps1.getId());
        assertThat(emploiTemps1).isEqualTo(emploiTemps2);
        emploiTemps2.setId(2L);
        assertThat(emploiTemps1).isNotEqualTo(emploiTemps2);
        emploiTemps1.setId(null);
        assertThat(emploiTemps1).isNotEqualTo(emploiTemps2);
    }
}
