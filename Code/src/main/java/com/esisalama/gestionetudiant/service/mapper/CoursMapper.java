package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Cours;
import com.esisalama.gestionetudiant.domain.Etudiant;
import com.esisalama.gestionetudiant.domain.Professeur;
import com.esisalama.gestionetudiant.service.dto.CoursDTO;
import com.esisalama.gestionetudiant.service.dto.EtudiantDTO;
import com.esisalama.gestionetudiant.service.dto.ProfesseurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cours} and its DTO {@link CoursDTO}.
 */
@Mapper(componentModel = "spring")
public interface CoursMapper extends EntityMapper<CoursDTO, Cours> {
    @Mapping(target = "etudiant", source = "etudiant", qualifiedByName = "etudiantId")
    @Mapping(target = "professeur", source = "professeur", qualifiedByName = "professeurId")
    CoursDTO toDto(Cours s);

    @Named("etudiantId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EtudiantDTO toDtoEtudiantId(Etudiant etudiant);

    @Named("professeurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProfesseurDTO toDtoProfesseurId(Professeur professeur);
}
