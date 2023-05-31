package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Resultat;
import com.esisalama.gestionetudiant.service.dto.ResultatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Resultat} and its DTO {@link ResultatDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResultatMapper extends EntityMapper<ResultatDTO, Resultat> {}
