package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmploiTempsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmploiTempsDTO.class);
        EmploiTempsDTO emploiTempsDTO1 = new EmploiTempsDTO();
        emploiTempsDTO1.setId(1L);
        EmploiTempsDTO emploiTempsDTO2 = new EmploiTempsDTO();
        assertThat(emploiTempsDTO1).isNotEqualTo(emploiTempsDTO2);
        emploiTempsDTO2.setId(emploiTempsDTO1.getId());
        assertThat(emploiTempsDTO1).isEqualTo(emploiTempsDTO2);
        emploiTempsDTO2.setId(2L);
        assertThat(emploiTempsDTO1).isNotEqualTo(emploiTempsDTO2);
        emploiTempsDTO1.setId(null);
        assertThat(emploiTempsDTO1).isNotEqualTo(emploiTempsDTO2);
    }
}
