package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.Gestioninscrip;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.GestioninscripDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Gestioninscrip} and its DTO {@link GestioninscripDTO}.
 */
@Mapper(componentModel = "spring")
public interface GestioninscripMapper extends EntityMapper<GestioninscripDTO, Gestioninscrip> {
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    GestioninscripDTO toDto(Gestioninscrip s);

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);
}
