package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CollecteinfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Collecteinfo.class);
        Collecteinfo collecteinfo1 = new Collecteinfo();
        collecteinfo1.setId(1L);
        Collecteinfo collecteinfo2 = new Collecteinfo();
        collecteinfo2.setId(collecteinfo1.getId());
        assertThat(collecteinfo1).isEqualTo(collecteinfo2);
        collecteinfo2.setId(2L);
        assertThat(collecteinfo1).isNotEqualTo(collecteinfo2);
        collecteinfo1.setId(null);
        assertThat(collecteinfo1).isNotEqualTo(collecteinfo2);
    }
}
