package com.esisalama.gestionetudiant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestioninscripMapperTest {

    private GestioninscripMapper gestioninscripMapper;

    @BeforeEach
    public void setUp() {
        gestioninscripMapper = new GestioninscripMapperImpl();
    }
}
