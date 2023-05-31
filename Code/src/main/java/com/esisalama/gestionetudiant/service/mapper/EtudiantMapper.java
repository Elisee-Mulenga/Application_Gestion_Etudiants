package com.esisalama.gestionetudiant.service.mapper;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.domain.Communication;
import com.esisalama.gestionetudiant.domain.Document;
import com.esisalama.gestionetudiant.domain.Donnees;
import com.esisalama.gestionetudiant.domain.Dossiersacademique;
import com.esisalama.gestionetudiant.domain.Etudiant;
import com.esisalama.gestionetudiant.domain.Inscription;
import com.esisalama.gestionetudiant.domain.Progression;
import com.esisalama.gestionetudiant.domain.Resultat;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.dto.CommunicationDTO;
import com.esisalama.gestionetudiant.service.dto.DocumentDTO;
import com.esisalama.gestionetudiant.service.dto.DonneesDTO;
import com.esisalama.gestionetudiant.service.dto.DossiersacademiqueDTO;
import com.esisalama.gestionetudiant.service.dto.EtudiantDTO;
import com.esisalama.gestionetudiant.service.dto.InscriptionDTO;
import com.esisalama.gestionetudiant.service.dto.ProgressionDTO;
import com.esisalama.gestionetudiant.service.dto.ResultatDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Etudiant} and its DTO {@link EtudiantDTO}.
 */
@Mapper(componentModel = "spring")
public interface EtudiantMapper extends EntityMapper<EtudiantDTO, Etudiant> {
    @Mapping(target = "inscription", source = "inscription", qualifiedByName = "inscriptionId")
    @Mapping(target = "dossiersacademique", source = "dossiersacademique", qualifiedByName = "dossiersacademiqueId")
    @Mapping(target = "donnees", source = "donnees", qualifiedByName = "donneesId")
    @Mapping(target = "resultat", source = "resultat", qualifiedByName = "resultatId")
    @Mapping(target = "document", source = "document", qualifiedByName = "documentId")
    @Mapping(target = "progression", source = "progression", qualifiedByName = "progressionId")
    @Mapping(target = "communications", source = "communications", qualifiedByName = "communicationIdSet")
    @Mapping(target = "administrateur", source = "administrateur", qualifiedByName = "administrateurId")
    EtudiantDTO toDto(Etudiant s);

    @Mapping(target = "removeCommunication", ignore = true)
    Etudiant toEntity(EtudiantDTO etudiantDTO);

    @Named("inscriptionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    InscriptionDTO toDtoInscriptionId(Inscription inscription);

    @Named("dossiersacademiqueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DossiersacademiqueDTO toDtoDossiersacademiqueId(Dossiersacademique dossiersacademique);

    @Named("donneesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DonneesDTO toDtoDonneesId(Donnees donnees);

    @Named("resultatId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ResultatDTO toDtoResultatId(Resultat resultat);

    @Named("documentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DocumentDTO toDtoDocumentId(Document document);

    @Named("progressionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProgressionDTO toDtoProgressionId(Progression progression);

    @Named("communicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CommunicationDTO toDtoCommunicationId(Communication communication);

    @Named("communicationIdSet")
    default Set<CommunicationDTO> toDtoCommunicationIdSet(Set<Communication> communication) {
        return communication.stream().map(this::toDtoCommunicationId).collect(Collectors.toSet());
    }

    @Named("administrateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdministrateurDTO toDtoAdministrateurId(Administrateur administrateur);
}
