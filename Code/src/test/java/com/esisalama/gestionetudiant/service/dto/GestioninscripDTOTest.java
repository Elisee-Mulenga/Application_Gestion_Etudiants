package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GestioninscripDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GestioninscripDTO.class);
        GestioninscripDTO gestioninscripDTO1 = new GestioninscripDTO();
        gestioninscripDTO1.setId(1L);
        GestioninscripDTO gestioninscripDTO2 = new GestioninscripDTO();
        assertThat(gestioninscripDTO1).isNotEqualTo(gestioninscripDTO2);
        gestioninscripDTO2.setId(gestioninscripDTO1.getId());
        assertThat(gestioninscripDTO1).isEqualTo(gestioninscripDTO2);
        gestioninscripDTO2.setId(2L);
        assertThat(gestioninscripDTO1).isNotEqualTo(gestioninscripDTO2);
        gestioninscripDTO1.setId(null);
        assertThat(gestioninscripDTO1).isNotEqualTo(gestioninscripDTO2);
    }
}
