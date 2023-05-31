package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Dossiersacademique;
import com.esisalama.gestionetudiant.service.dto.DossiersacademiqueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dossiersacademique} and its DTO {@link DossiersacademiqueDTO}.
 */
@Mapper(componentModel = "spring")
public interface DossiersacademiqueMapper extends EntityMapper<DossiersacademiqueDTO, Dossiersacademique> {}
