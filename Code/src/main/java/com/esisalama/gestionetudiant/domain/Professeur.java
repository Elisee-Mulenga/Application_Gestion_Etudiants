package com.esisalama.gestionetudiant.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Professeur.
 */
@Entity
@Table(name = "professeur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Professeur implements Serializable {

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

    @Column(name = "nomcours")
    private String nomcours;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "mail")
    private String mail;

    @OneToMany(mappedBy = "professeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "etudiant", "professeur" }, allowSetters = true)
    private Set<Cours> cours = new HashSet<>();

    @OneToMany(mappedBy = "professeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "administrateur", "professeur", "etudiants" }, allowSetters = true)
    private Set<Communication> communications = new HashSet<>();

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

    public Professeur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Professeur nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return this.postnom;
    }

    public Professeur postnom(String postnom) {
        this.setPostnom(postnom);
        return this;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Professeur prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomcours() {
        return this.nomcours;
    }

    public Professeur nomcours(String nomcours) {
        this.setNomcours(nomcours);
        return this;
    }

    public void setNomcours(String nomcours) {
        this.nomcours = nomcours;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Professeur adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return this.mail;
    }

    public Professeur mail(String mail) {
        this.setMail(mail);
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Cours> getCours() {
        return this.cours;
    }

    public void setCours(Set<Cours> cours) {
        if (this.cours != null) {
            this.cours.forEach(i -> i.setProfesseur(null));
        }
        if (cours != null) {
            cours.forEach(i -> i.setProfesseur(this));
        }
        this.cours = cours;
    }

    public Professeur cours(Set<Cours> cours) {
        this.setCours(cours);
        return this;
    }

    public Professeur addCours(Cours cours) {
        this.cours.add(cours);
        cours.setProfesseur(this);
        return this;
    }

    public Professeur removeCours(Cours cours) {
        this.cours.remove(cours);
        cours.setProfesseur(null);
        return this;
    }

    public Set<Communication> getCommunications() {
        return this.communications;
    }

    public void setCommunications(Set<Communication> communications) {
        if (this.communications != null) {
            this.communications.forEach(i -> i.setProfesseur(null));
        }
        if (communications != null) {
            communications.forEach(i -> i.setProfesseur(this));
        }
        this.communications = communications;
    }

    public Professeur communications(Set<Communication> communications) {
        this.setCommunications(communications);
        return this;
    }

    public Professeur addCommunication(Communication communication) {
        this.communications.add(communication);
        communication.setProfesseur(this);
        return this;
    }

    public Professeur removeCommunication(Communication communication) {
        this.communications.remove(communication);
        communication.setProfesseur(null);
        return this;
    }

    public Administrateur getAdministrateur() {
        return this.administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Professeur administrateur(Administrateur administrateur) {
        this.setAdministrateur(administrateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Professeur)) {
            return false;
        }
        return id != null && id.equals(((Professeur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Professeur{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", postnom='" + getPostnom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nomcours='" + getNomcours() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", mail='" + getMail() + "'" +
            "}";
    }
}
