package com.esisalama.gestionetudiant.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Communication.
 */
@Entity
@Table(name = "communication")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Communication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "destinataire")
    private String destinataire;

    @Column(name = "expeditaire")
    private String expeditaire;

    @Column(name = "forum")
    private String forum;

    @Column(name = "annonce")
    private String annonce;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "collecteinfos", "gestioninscrips", "etudiants", "professeurs", "gestionInfos", "emploiTemps", "communications" },
        allowSetters = true
    )
    private Administrateur administrateur;

    @ManyToOne
    @JsonIgnoreProperties(value = { "cours", "communications", "administrateur" }, allowSetters = true)
    private Professeur professeur;

    @ManyToMany(mappedBy = "communications")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<Etudiant> etudiants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Communication id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinataire() {
        return this.destinataire;
    }

    public Communication destinataire(String destinataire) {
        this.setDestinataire(destinataire);
        return this;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getExpeditaire() {
        return this.expeditaire;
    }

    public Communication expeditaire(String expeditaire) {
        this.setExpeditaire(expeditaire);
        return this;
    }

    public void setExpeditaire(String expeditaire) {
        this.expeditaire = expeditaire;
    }

    public String getForum() {
        return this.forum;
    }

    public Communication forum(String forum) {
        this.setForum(forum);
        return this;
    }

    public void setForum(String forum) {
        this.forum = forum;
    }

    public String getAnnonce() {
        return this.annonce;
    }

    public Communication annonce(String annonce) {
        this.setAnnonce(annonce);
        return this;
    }

    public void setAnnonce(String annonce) {
        this.annonce = annonce;
    }

    public Administrateur getAdministrateur() {
        return this.administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Communication administrateur(Administrateur administrateur) {
        this.setAdministrateur(administrateur);
        return this;
    }

    public Professeur getProfesseur() {
        return this.professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public Communication professeur(Professeur professeur) {
        this.setProfesseur(professeur);
        return this;
    }

    public Set<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        if (this.etudiants != null) {
            this.etudiants.forEach(i -> i.removeCommunication(this));
        }
        if (etudiants != null) {
            etudiants.forEach(i -> i.addCommunication(this));
        }
        this.etudiants = etudiants;
    }

    public Communication etudiants(Set<Etudiant> etudiants) {
        this.setEtudiants(etudiants);
        return this;
    }

    public Communication addEtudiant(Etudiant etudiant) {
        this.etudiants.add(etudiant);
        etudiant.getCommunications().add(this);
        return this;
    }

    public Communication removeEtudiant(Etudiant etudiant) {
        this.etudiants.remove(etudiant);
        etudiant.getCommunications().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Communication)) {
            return false;
        }
        return id != null && id.equals(((Communication) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Communication{" +
            "id=" + getId() +
            ", destinataire='" + getDestinataire() + "'" +
            ", expeditaire='" + getExpeditaire() + "'" +
            ", forum='" + getForum() + "'" +
            ", annonce='" + getAnnonce() + "'" +
            "}";
    }
}
