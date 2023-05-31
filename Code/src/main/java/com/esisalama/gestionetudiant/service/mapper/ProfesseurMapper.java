package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.Professeur;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.ProfesseurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Professeur} and its DTO {@link ProfesseurDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfesseurMapper extends EntityMapper<ProfesseurDTO, Professeur> {
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    ProfesseurDTO toDto(Professeur s);

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);
}
