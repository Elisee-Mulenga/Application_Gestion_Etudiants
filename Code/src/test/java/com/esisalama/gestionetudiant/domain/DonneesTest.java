package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DonneesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Donnees.class);
        Donnees donnees1 = new Donnees();
        donnees1.setId(1L);
        Donnees donnees2 = new Donnees();
        donnees2.setId(donnees1.getId());
        assertThat(donnees1).isEqualTo(donnees2);
        donnees2.setId(2L);
        assertThat(donnees1).isNotEqualTo(donnees2);
        donnees1.setId(null);
        assertThat(donnees1).isNotEqualTo(donnees2);
    }
}
