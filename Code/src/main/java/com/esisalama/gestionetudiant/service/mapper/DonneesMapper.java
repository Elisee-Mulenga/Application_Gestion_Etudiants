package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Donnees;
import com.esisalama.gestionetudiant.service.dto.DonneesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Donnees} and its DTO {@link DonneesDTO}.
 */
@Mapper(componentModel = "spring")
public interface DonneesMapper extends EntityMapper<DonneesDTO, Donnees> {}
