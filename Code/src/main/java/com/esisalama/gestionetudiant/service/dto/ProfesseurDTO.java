package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Professeur} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProfesseurDTO implements Serializable {

    private Long id;

    private String nom;

    private String postnom;

    private String prenom;

    private String nomcours;

    private String adresse;

    private String mail;

    private AdministrateurDTO administrateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomcours() {
        return nomcours;
    }

    public void setNomcours(String nomcours) {
        this.nomcours = nomcours;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public AdministrateurDTO getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(AdministrateurDTO administrateur) {
        this.administrateur = administrateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfesseurDTO)) {
            return false;
        }

        ProfesseurDTO professeurDTO = (ProfesseurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, professeurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfesseurDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", postnom='" + getPostnom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nomcours='" + getNomcours() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", mail='" + getMail() + "'" +
            ", administrateur=" + getAdministrateur() +
            "}";
    }
}
