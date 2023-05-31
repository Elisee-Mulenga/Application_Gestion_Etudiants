package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Progression;
import com.esisalama.gestionetudiant.service.dto.ProgressionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Progression} and its DTO {@link ProgressionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProgressionMapper extends EntityMapper<ProgressionDTO, Progression> {}
