package com.esisalama.gestionetudiant.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Administrateur.
 */
@Entity
@Table(name = "administrateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Administrateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "postnom")
    private String postnom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "mail")
    private String mail;

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "administrateur" }, allowSetters = true)
    private Set<Collecteinfo> collecteinfos = new HashSet<>();

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "administrateur" }, allowSetters = true)
    private Set<Gestioninscrip> gestioninscrips = new HashSet<>();

    @OneToMany(mappedBy = "administrateur")
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

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cours", "communications", "administrateur" }, allowSetters = true)
    private Set<Professeur> professeurs = new HashSet<>();

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "administrateur" }, allowSetters = true)
    private Set<GestionInfos> gestionInfos = new HashSet<>();

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "etudaint", "administrateur" }, allowSetters = true)
    private Set<EmploiTemps> emploiTemps = new HashSet<>();

    @OneToMany(mappedBy = "administrateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "administrateur", "professeur", "etudiants" }, allowSetters = true)
    private Set<Communication> communications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Administrateur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Administrateur nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return this.postnom;
    }

    public Administrateur postnom(String postnom) {
        this.setPostnom(postnom);
        return this;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Administrateur prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Administrateur adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return this.mail;
    }

    public Administrateur mail(String mail) {
        this.setMail(mail);
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Collecteinfo> getCollecteinfos() {
        return this.collecteinfos;
    }

    public void setCollecteinfos(Set<Collecteinfo> collecteinfos) {
        if (this.collecteinfos != null) {
            this.collecteinfos.forEach(i -> i.setAdministrateur(null));
        }
        if (collecteinfos != null) {
            collecteinfos.forEach(i -> i.setAdministrateur(this));
        }
        this.collecteinfos = collecteinfos;
    }

    public Administrateur collecteinfos(Set<Collecteinfo> collecteinfos) {
        this.setCollecteinfos(collecteinfos);
        return this;
    }

    public Administrateur addCollecteinfo(Collecteinfo collecteinfo) {
        this.collecteinfos.add(collecteinfo);
        collecteinfo.setAdministrateur(this);
        return this;
    }

    public Administrateur removeCollecteinfo(Collecteinfo collecteinfo) {
        this.collecteinfos.remove(collecteinfo);
        collecteinfo.setAdministrateur(null);
        return this;
    }

    public Set<Gestioninscrip> getGestioninscrips() {
        return this.gestioninscrips;
    }

    public void setGestioninscrips(Set<Gestioninscrip> gestioninscrips) {
        if (this.gestioninscrips != null) {
            this.gestioninscrips.forEach(i -> i.setAdministrateur(null));
        }
        if (gestioninscrips != null) {
            gestioninscrips.forEach(i -> i.setAdministrateur(this));
        }
        this.gestioninscrips = gestioninscrips;
    }

    public Administrateur gestioninscrips(Set<Gestioninscrip> gestioninscrips) {
        this.setGestioninscrips(gestioninscrips);
        return this;
    }

    public Administrateur addGestioninscrip(Gestioninscrip gestioninscrip) {
        this.gestioninscrips.add(gestioninscrip);
        gestioninscrip.setAdministrateur(this);
        return this;
    }

    public Administrateur removeGestioninscrip(Gestioninscrip gestioninscrip) {
        this.gestioninscrips.remove(gestioninscrip);
        gestioninscrip.setAdministrateur(null);
        return this;
    }

    public Set<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        if (this.etudiants != null) {
            this.etudiants.forEach(i -> i.setAdministrateur(null));
        }
        if (etudiants != null) {
            etudiants.forEach(i -> i.setAdministrateur(this));
        }
        this.etudiants = etudiants;
    }

    public Administrateur etudiants(Set<Etudiant> etudiants) {
        this.setEtudiants(etudiants);
        return this;
    }

    public Administrateur addEtudiant(Etudiant etudiant) {
        this.etudiants.add(etudiant);
        etudiant.setAdministrateur(this);
        return this;
    }

    public Administrateur removeEtudiant(Etudiant etudiant) {
        this.etudiants.remove(etudiant);
        etudiant.setAdministrateur(null);
        return this;
    }

    public Set<Professeur> getProfesseurs() {
        return this.professeurs;
    }

    public void setProfesseurs(Set<Professeur> professeurs) {
        if (this.professeurs != null) {
            this.professeurs.forEach(i -> i.setAdministrateur(null));
        }
        if (professeurs != null) {
            professeurs.forEach(i -> i.setAdministrateur(this));
        }
        this.professeurs = professeurs;
    }

    public Administrateur professeurs(Set<Professeur> professeurs) {
        this.setProfesseurs(professeurs);
        return this;
    }

    public Administrateur addProfesseur(Professeur professeur) {
        this.professeurs.add(professeur);
        professeur.setAdministrateur(this);
        return this;
    }

    public Administrateur removeProfesseur(Professeur professeur) {
        this.professeurs.remove(professeur);
        professeur.setAdministrateur(null);
        return this;
    }

    public Set<GestionInfos> getGestionInfos() {
        return this.gestionInfos;
    }

    public void setGestionInfos(Set<GestionInfos> gestionInfos) {
        if (this.gestionInfos != null) {
            this.gestionInfos.forEach(i -> i.setAdministrateur(null));
        }
        if (gestionInfos != null) {
            gestionInfos.forEach(i -> i.setAdministrateur(this));
        }
        this.gestionInfos = gestionInfos;
    }

    public Administrateur gestionInfos(Set<GestionInfos> gestionInfos) {
        this.setGestionInfos(gestionInfos);
        return this;
    }

    public Administrateur addGestionInfos(GestionInfos gestionInfos) {
        this.gestionInfos.add(gestionInfos);
        gestionInfos.setAdministrateur(this);
        return this;
    }

    public Administrateur removeGestionInfos(GestionInfos gestionInfos) {
        this.gestionInfos.remove(gestionInfos);
        gestionInfos.setAdministrateur(null);
        return this;
    }

    public Set<EmploiTemps> getEmploiTemps() {
        return this.emploiTemps;
    }

    public void setEmploiTemps(Set<EmploiTemps> emploiTemps) {
        if (this.emploiTemps != null) {
            this.emploiTemps.forEach(i -> i.setAdministrateur(null));
        }
        if (emploiTemps != null) {
            emploiTemps.forEach(i -> i.setAdministrateur(this));
        }
        this.emploiTemps = emploiTemps;
    }

    public Administrateur emploiTemps(Set<EmploiTemps> emploiTemps) {
        this.setEmploiTemps(emploiTemps);
        return this;
    }

    public Administrateur addEmploiTemps(EmploiTemps emploiTemps) {
        this.emploiTemps.add(emploiTemps);
        emploiTemps.setAdministrateur(this);
        return this;
    }

    public Administrateur removeEmploiTemps(EmploiTemps emploiTemps) {
        this.emploiTemps.remove(emploiTemps);
        emploiTemps.setAdministrateur(null);
        return this;
    }

    public Set<Communication> getCommunications() {
        return this.communications;
    }

    public void setCommunications(Set<Communication> communications) {
        if (this.communications != null) {
            this.communications.forEach(i -> i.setAdministrateur(null));
        }
        if (communications != null) {
            communications.forEach(i -> i.setAdministrateur(this));
        }
        this.communications = communications;
    }

    public Administrateur communications(Set<Communication> communications) {
        this.setCommunications(communications);
        return this;
    }

    public Administrateur addCommunication(Communication communication) {
        this.communications.add(communication);
        communication.setAdministrateur(this);
        return this;
    }

    public Administrateur removeCommunication(Communication communication) {
        this.communications.remove(communication);
        communication.setAdministrateur(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Administrateur)) {
            return false;
        }
        return id != null && id.equals(((Administrateur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Administrateur{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", postnom='" + getPostnom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", mail='" + getMail() + "'" +
            "}";
    }
}
