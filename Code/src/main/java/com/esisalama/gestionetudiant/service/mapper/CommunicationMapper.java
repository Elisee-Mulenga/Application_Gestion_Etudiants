package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.Communication;
import com.esisalama.gestionetudiant.domain.Professeur;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.CommunicationDTO;
import com.esisalama.gestionetudiant.service.dto.ProfesseurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Communication} and its DTO {@link CommunicationDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommunicationMapper extends EntityMapper<CommunicationDTO, Communication> {
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    @Mapping(target = "professeur", source = "professeur", qualifiedByName = "professeurId")
    CommunicationDTO toDto(Communication s);

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);

    @Named("professeurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProfesseurDTO toDtoProfesseurId(Professeur professeur);
}
