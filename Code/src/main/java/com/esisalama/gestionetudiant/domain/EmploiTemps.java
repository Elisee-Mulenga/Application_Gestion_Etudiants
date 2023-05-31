package com.esisalama.gestionetudiant.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EmploiTemps.
 */
@Entity
@Table(name = "emploi_temps")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmploiTemps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "cours")
    private String cours;

    @Column(name = "semestre")
    private String semestre;

    @Column(name = "timestre")
    private String timestre;

    @Column(name = "horairecours")
    private String horairecours;

    @Column(name = "horaireexam")
    private String horaireexam;

    @Column(name = "activite")
    private String activite;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "inscription",
            "dossiersacademique",
            "donnees",
            "resultat",
            "document",
            "progression",
            "cours",
            "emploiTemps",
            "communications",
            "administrateur",
        },
        allowSetters = true
    )
    private Etudiant etudaint;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "collecteinfos", "gestioninscrips", "etudiants", "professeurs", "gestionInfos", "emploiTemps", "communications" },
        allowSetters = true
    )
    private Administrateur administrateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EmploiTemps id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCours() {
        return this.cours;
    }

    public EmploiTemps cours(String cours) {
        this.setCours(cours);
        return this;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getSemestre() {
        return this.semestre;
    }

    public EmploiTemps semestre(String semestre) {
        this.setSemestre(semestre);
        return this;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getTimestre() {
        return this.timestre;
    }

    public EmploiTemps timestre(String timestre) {
        this.setTimestre(timestre);
        return this;
    }

    public void setTimestre(String timestre) {
        this.timestre = timestre;
    }

    public String getHorairecours() {
        return this.horairecours;
    }

    public EmploiTemps horairecours(String horairecours) {
        this.setHorairecours(horairecours);
        return this;
    }

    public void setHorairecours(String horairecours) {
        this.horairecours = horairecours;
    }

    public String getHoraireexam() {
        return this.horaireexam;
    }

    public EmploiTemps horaireexam(String horaireexam) {
        this.setHoraireexam(horaireexam);
        return this;
    }

    public void setHoraireexam(String horaireexam) {
        this.horaireexam = horaireexam;
    }

    public String getActivite() {
        return this.activite;
    }

    public EmploiTemps activite(String activite) {
        this.setActivite(activite);
        return this;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public Etudiant getEtudaint() {
        return this.etudaint;
    }

    public void setEtudaint(Etudiant etudiant) {
        this.etudaint = etudiant;
    }

    public EmploiTemps etudaint(Etudiant etudiant) {
        this.setEtudaint(etudiant);
        return this;
    }

    public Administrateur getAdministrateur() {
        return this.administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public EmploiTemps administrateur(Administrateur administrateur) {
        this.setAdministrateur(administrateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmploiTemps)) {
            return false;
        }
        return id != null && id.equals(((EmploiTemps) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmploiTemps{" +
            "id=" + getId() +
            ", cours='" + getCours() + "'" +
            ", semestre='" + getSemestre() + "'" +
            ", timestre='" + getTimestre() + "'" +
            ", horairecours='" + getHorairecours() + "'" +
            ", horaireexam='" + getHoraireexam() + "'" +
            ", activite='" + getActivite() + "'" +
            "}";
    }
}
