package com.esisalama.gestionetudiant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmploiTempsMapperTest {

    private EmploiTempsMapper emploiTempsMapper;

    @BeforeEach
    public void setUp() {
        emploiTempsMapper = new EmploiTempsMapperImpl();
    }
}
