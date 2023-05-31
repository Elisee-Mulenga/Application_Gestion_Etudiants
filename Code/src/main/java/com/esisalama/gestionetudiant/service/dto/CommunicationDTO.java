package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Communication} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CommunicationDTO implements Serializable {

    private Long id;

    private String destinataire;

    private String expeditaire;

    private String forum;

    private String annonce;

    private AdministrateurDTO administrateur;

    private ProfesseurDTO professeur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getExpeditaire() {
        return expeditaire;
    }

    public void setExpeditaire(String expeditaire) {
        this.expeditaire = expeditaire;
    }

    public String getForum() {
        return forum;
    }

    public void setForum(String forum) {
        this.forum = forum;
    }

    public String getAnnonce() {
        return annonce;
    }

    public void setAnnonce(String annonce) {
        this.annonce = annonce;
    }

    public AdministrateurDTO getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(AdministrateurDTO administrateur) {
        this.administrateur = administrateur;
    }

    public ProfesseurDTO getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurDTO professeur) {
        this.professeur = professeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicationDTO)) {
            return false;
        }

        CommunicationDTO communicationDTO = (CommunicationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, communicationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommunicationDTO{" +
            "id=" + getId() +
            ", destinataire='" + getDestinataire() + "'" +
            ", expeditaire='" + getExpeditaire() + "'" +
            ", forum='" + getForum() + "'" +
            ", annonce='" + getAnnonce() + "'" +
            ", administrateur=" + getAdministrateur() +
            ", professeur=" + getProfesseur() +
            "}";
    }
}
