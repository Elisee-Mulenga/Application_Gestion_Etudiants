package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Inscription;
import com.esisalama.gestionetudiant.service.dto.InscriptionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inscription} and its DTO {@link InscriptionDTO}.
 */
@Mapper(componentModel = "spring")
public interface InscriptionMapper extends EntityMapper<InscriptionDTO, Inscription> {}
