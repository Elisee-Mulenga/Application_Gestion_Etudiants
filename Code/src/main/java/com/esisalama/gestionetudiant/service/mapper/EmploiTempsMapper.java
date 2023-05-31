package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.EmploiTemps;
import com.esisalama.gestionetudiant.domain.Etudiant;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.EmploiTempsDTO;
import com.esisalama.gestionetudiant.service.dto.EtudiantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmploiTemps} and its DTO {@link EmploiTempsDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmploiTempsMapper extends EntityMapper<EmploiTempsDTO, EmploiTemps> {
    @Mapping(target = "etudaint", source = "etudaint", qualifiedByName = "etudiantId")
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    EmploiTempsDTO toDto(EmploiTemps s);

    @Named("etudiantId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EtudiantDTO toDtoEtudiantId(Etudiant etudiant);

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);
}
