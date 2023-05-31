package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GestionInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GestionInfos.class);
        GestionInfos gestionInfos1 = new GestionInfos();
        gestionInfos1.setId(1L);
        GestionInfos gestionInfos2 = new GestionInfos();
        gestionInfos2.setId(gestionInfos1.getId());
        assertThat(gestionInfos1).isEqualTo(gestionInfos2);
        gestionInfos2.setId(2L);
        assertThat(gestionInfos1).isNotEqualTo(gestionInfos2);
        gestionInfos1.setId(null);
        assertThat(gestionInfos1).isNotEqualTo(gestionInfos2);
    }
}
