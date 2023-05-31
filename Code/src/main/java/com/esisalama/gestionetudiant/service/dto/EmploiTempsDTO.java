package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.EmploiTemps} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmploiTempsDTO implements Serializable {

    private Long id;

    private String cours;

    private String semestre;

    private String timestre;

    private String horairecours;

    private String horaireexam;

    private String activite;

    private EtudiantDTO etudaint;

    private AdministrateurDTO administrateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getTimestre() {
        return timestre;
    }

    public void setTimestre(String timestre) {
        this.timestre = timestre;
    }

    public String getHorairecours() {
        return horairecours;
    }

    public void setHorairecours(String horairecours) {
        this.horairecours = horairecours;
    }

    public String getHoraireexam() {
        return horaireexam;
    }

    public void setHoraireexam(String horaireexam) {
        this.horaireexam = horaireexam;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public EtudiantDTO getEtudaint() {
        return etudaint;
    }

    public void setEtudaint(EtudiantDTO etudaint) {
        this.etudaint = etudaint;
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
        if (!(o instanceof EmploiTempsDTO)) {
            return false;
        }

        EmploiTempsDTO emploiTempsDTO = (EmploiTempsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, emploiTempsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmploiTempsDTO{" +
            "id=" + getId() +
            ", cours='" + getCours() + "'" +
            ", semestre='" + getSemestre() + "'" +
            ", timestre='" + getTimestre() + "'" +
            ", horairecours='" + getHorairecours() + "'" +
            ", horaireexam='" + getHoraireexam() + "'" +
            ", activite='" + getActivite() + "'" +
            ", etudaint=" + getEtudaint() +
            ", administrateur=" + getAdministrateur() +
            "}";
    }
}
