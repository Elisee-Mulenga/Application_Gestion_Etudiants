package com.esisalama.gestionetudiant.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CollecteinfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollecteinfoDTO.class);
        CollecteinfoDTO collecteinfoDTO1 = new CollecteinfoDTO();
        collecteinfoDTO1.setId(1L);
        CollecteinfoDTO collecteinfoDTO2 = new CollecteinfoDTO();
        assertThat(collecteinfoDTO1).isNotEqualTo(collecteinfoDTO2);
        collecteinfoDTO2.setId(collecteinfoDTO1.getId());
        assertThat(collecteinfoDTO1).isEqualTo(collecteinfoDTO2);
        collecteinfoDTO2.setId(2L);
        assertThat(collecteinfoDTO1).isNotEqualTo(collecteinfoDTO2);
        collecteinfoDTO1.setId(null);
        assertThat(collecteinfoDTO1).isNotEqualTo(collecteinfoDTO2);
    }
}
