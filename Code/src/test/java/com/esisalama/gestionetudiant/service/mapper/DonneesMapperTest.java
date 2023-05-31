package com.esisalama.gestionetudiant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DonneesMapperTest {

    private DonneesMapper donneesMapper;

    @BeforeEach
    public void setUp() {
        donneesMapper = new DonneesMapperImpl();
    }
}
