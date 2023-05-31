package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.Collecteinfo;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.CollecteinfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Collecteinfo} and its DTO {@link CollecteinfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface CollecteinfoMapper extends EntityMapper<CollecteinfoDTO, Collecteinfo> {
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    CollecteinfoDTO toDto(Collecteinfo s);

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);
}
