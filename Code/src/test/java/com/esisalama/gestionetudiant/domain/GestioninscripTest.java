package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GestioninscripTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gestioninscrip.class);
        Gestioninscrip gestioninscrip1 = new Gestioninscrip();
        gestioninscrip1.setId(1L);
        Gestioninscrip gestioninscrip2 = new Gestioninscrip();
        gestioninscrip2.setId(gestioninscrip1.getId());
        assertThat(gestioninscrip1).isEqualTo(gestioninscrip2);
        gestioninscrip2.setId(2L);
        assertThat(gestioninscrip1).isNotEqualTo(gestioninscrip2);
        gestioninscrip1.setId(null);
        assertThat(gestioninscrip1).isNotEqualTo(gestioninscrip2);
    }
}
