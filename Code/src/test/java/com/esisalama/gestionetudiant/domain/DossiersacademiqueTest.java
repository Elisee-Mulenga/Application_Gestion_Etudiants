package com.esisalama.gestionetudiant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esisalama.gestionetudiant.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DossiersacademiqueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dossiersacademique.class);
        Dossiersacademique dossiersacademique1 = new Dossiersacademique();
        dossiersacademique1.setId(1L);
        Dossiersacademique dossiersacademique2 = new Dossiersacademique();
        dossiersacademique2.setId(dossiersacademique1.getId());
        assertThat(dossiersacademique1).isEqualTo(dossiersacademique2);
        dossiersacademique2.setId(2L);
        assertThat(dossiersacademique1).isNotEqualTo(dossiersacademique2);
        dossiersacademique1.setId(null);
        assertThat(dossiersacademique1).isNotEqualTo(dossiersacademique2);
    }
}
