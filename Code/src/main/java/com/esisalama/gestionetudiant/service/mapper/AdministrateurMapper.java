package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Administrateur} and its DTO {@link AdministrateurDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdministrateurMapper extends EntityMapper<AdministrateurDTO, Administrateur> {}
