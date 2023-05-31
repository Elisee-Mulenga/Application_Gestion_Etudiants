package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgressionDTO.class);
        ProgressionDTO progressionDTO1 = new ProgressionDTO();
        progressionDTO1.setId(1L);
        ProgressionDTO progressionDTO2 = new ProgressionDTO();
        assertThat(progressionDTO1).isNotEqualTo(progressionDTO2);
        progressionDTO2.setId(progressionDTO1.getId());
        assertThat(progressionDTO1).isEqualTo(progressionDTO2);
        progressionDTO2.setId(2L);
        assertThat(progressionDTO1).isNotEqualTo(progressionDTO2);
        progressionDTO1.setId(null);
        assertThat(progressionDTO1).isNotEqualTo(progressionDTO2);
    }
}
