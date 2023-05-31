package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DonneesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonneesDTO.class);
        DonneesDTO donneesDTO1 = new DonneesDTO();
        donneesDTO1.setId(1L);
        DonneesDTO donneesDTO2 = new DonneesDTO();
        assertThat(donneesDTO1).isNotEqualTo(donneesDTO2);
        donneesDTO2.setId(donneesDTO1.getId());
        assertThat(donneesDTO1).isEqualTo(donneesDTO2);
        donneesDTO2.setId(2L);
        assertThat(donneesDTO1).isNotEqualTo(donneesDTO2);
        donneesDTO1.setId(null);
        assertThat(donneesDTO1).isNotEqualTo(donneesDTO2);
    }
}
