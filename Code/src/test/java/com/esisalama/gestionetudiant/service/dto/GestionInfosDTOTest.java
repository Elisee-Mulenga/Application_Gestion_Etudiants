package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GestionInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GestionInfosDTO.class);
        GestionInfosDTO gestionInfosDTO1 = new GestionInfosDTO();
        gestionInfosDTO1.setId(1L);
        GestionInfosDTO gestionInfosDTO2 = new GestionInfosDTO();
        assertThat(gestionInfosDTO1).isNotEqualTo(gestionInfosDTO2);
        gestionInfosDTO2.setId(gestionInfosDTO1.getId());
        assertThat(gestionInfosDTO1).isEqualTo(gestionInfosDTO2);
        gestionInfosDTO2.setId(2L);
        assertThat(gestionInfosDTO1).isNotEqualTo(gestionInfosDTO2);
        gestionInfosDTO1.setId(null);
        assertThat(gestionInfosDTO1).isNotEqualTo(gestionInfosDTO2);
    }
}
