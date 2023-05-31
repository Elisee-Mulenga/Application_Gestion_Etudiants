package com.esisalama.gestionetudiant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgressionMapperTest {

    private ProgressionMapper progressionMapper;

    @BeforeEach
    public void setUp() {
        progressionMapper = new ProgressionMapperImpl();
    }
}
