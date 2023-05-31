package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DossiersacademiqueDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DossiersacademiqueDTO.class);
        DossiersacademiqueDTO dossiersacademiqueDTO1 = new DossiersacademiqueDTO();
        dossiersacademiqueDTO1.setId(1L);
        DossiersacademiqueDTO dossiersacademiqueDTO2 = new DossiersacademiqueDTO();
        assertThat(dossiersacademiqueDTO1).isNotEqualTo(dossiersacademiqueDTO2);
        dossiersacademiqueDTO2.setId(dossiersacademiqueDTO1.getId());
        assertThat(dossiersacademiqueDTO1).isEqualTo(dossiersacademiqueDTO2);
        dossiersacademiqueDTO2.setId(2L);
        assertThat(dossiersacademiqueDTO1).isNotEqualTo(dossiersacademiqueDTO2);
        dossiersacademiqueDTO1.setId(null);
        assertThat(dossiersacademiqueDTO1).isNotEqualTo(dossiersacademiqueDTO2);
    }
}
