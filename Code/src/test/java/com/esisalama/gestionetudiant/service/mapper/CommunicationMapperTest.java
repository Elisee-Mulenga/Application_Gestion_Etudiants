package com.esisalama.gestionetudiant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommunicationMapperTest {

    private CommunicationMapper communicationMapper;

    @BeforeEach
    public void setUp() {
        communicationMapper = new CommunicationMapperImpl();
    }
}
