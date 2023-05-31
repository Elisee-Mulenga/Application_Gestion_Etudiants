package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.GestionInfos;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.GestionInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GestionInfos} and its DTO {@link GestionInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface GestionInfosMapper extends EntityMapper<GestionInfosDTO, GestionInfos> {
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    GestionInfosDTO toDto(GestionInfos s);

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);
}
