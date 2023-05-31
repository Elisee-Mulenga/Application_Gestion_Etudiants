package com.esisalama.gestionetudiant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestionInfosMapperTest {

    private GestionInfosMapper gestionInfosMapper;

    @BeforeEach
    public void setUp() {
        gestionInfosMapper = new GestionInfosMapperImpl();
    }
}
