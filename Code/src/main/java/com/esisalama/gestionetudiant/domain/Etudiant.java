package com.esisalama.gestionetudiant.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Etudiant.
 */
@Entity
@Table(name = "etudiant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;
    private static int counter = 0;

    @Column(name = "nom",nullable = false, length = 50)
    private String nom;

    @Column(name = "postnom",nullable = false, length = 50)
    private String postnom;

    @Column(name = "prenom",nullable = false, length = 50)
    private String prenom;

    @Column(name = "genre",nullable = false, length = 2)
    private String genre;

    @Column(name = "date_naissance",nullable = false, length = 50)
    private String dateNaissance;

    @Column(name = "adresse",nullable = false, length = 70)
    private String adresse;

    @Column(name = "matricule",nullable = false, unique = true, length = 10, updatable = false)
    private String matricule;

    @Column(name = "promotion")
    private String promotion;

    @OneToOne
    @JoinColumn(unique = true)
    private Inscription inscription;

    @OneToOne
    @JoinColumn(unique = true)
    private Dossiersacademique dossiersacademique;

    @OneToOne
    @JoinColumn(unique = true)
    private Donnees donnees;

    @OneToOne
    @JoinColumn(unique = true)
    private Resultat resultat;

    @OneToOne
    @JoinColumn(unique = true)
    private Document document;

    @OneToOne
    @JoinColumn(unique = true)
    private Progression progression;

    @OneToMany(mappedBy = "etudiant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "etudiant", "professeur" }, allowSetters = true)
    private Set<Cours> cours = new HashSet<>();

    @OneToMany(mappedBy = "etudaint")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "etudaint", "administrateur" }, allowSetters = true)
    private Set<EmploiTemps> emploiTemps = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_etudiant__communication",
        joinColumns = @JoinColumn(name = "etudiant_id"),
        inverseJoinColumns = @JoinColumn(name = "communication_id")
    )
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

    public Etudiant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Etudiant nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return this.postnom;
    }

    public Etudiant postnom(String postnom) {
        this.setPostnom(postnom);
        return this;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Etudiant prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return this.genre;
    }

    public Etudiant genre(String genre) {
        this.setGenre(genre);
        return this;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public Etudiant dateNaissance(String dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Etudiant adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public Etudiant matricule(String matricule) {
        this.setMatricule(matricule);
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPromotion() {
        return this.promotion;
    }

    public Etudiant promotion(String promotion) {
        this.setPromotion(promotion);
        return this;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Inscription getInscription() {
        return this.inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public Etudiant inscription(Inscription inscription) {
        this.setInscription(inscription);
        return this;
    }

    public Dossiersacademique getDossiersacademique() {
        return this.dossiersacademique;
    }

    public void setDossiersacademique(Dossiersacademique dossiersacademique) {
        this.dossiersacademique = dossiersacademique;
    }

    public Etudiant dossiersacademique(Dossiersacademique dossiersacademique) {
        this.setDossiersacademique(dossiersacademique);
        return this;
    }

    public Donnees getDonnees() {
        return this.donnees;
    }

    public void setDonnees(Donnees donnees) {
        this.donnees = donnees;
    }

    public Etudiant donnees(Donnees donnees) {
        this.setDonnees(donnees);
        return this;
    }

    public Resultat getResultat() {
        return this.resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public Etudiant resultat(Resultat resultat) {
        this.setResultat(resultat);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Etudiant document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Progression getProgression() {
        return this.progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    public Etudiant progression(Progression progression) {
        this.setProgression(progression);
        return this;
    }

    public Set<Cours> getCours() {
        return this.cours;
    }

    public void setCours(Set<Cours> cours) {
        if (this.cours != null) {
            this.cours.forEach(i -> i.setEtudiant(null));
        }
        if (cours != null) {
            cours.forEach(i -> i.setEtudiant(this));
        }
        this.cours = cours;
    }

    public Etudiant cours(Set<Cours> cours) {
        this.setCours(cours);
        return this;
    }

    public Etudiant addCours(Cours cours) {
        this.cours.add(cours);
        cours.setEtudiant(this);
        return this;
    }

    public Etudiant removeCours(Cours cours) {
        this.cours.remove(cours);
        cours.setEtudiant(null);
        return this;
    }

    public Set<EmploiTemps> getEmploiTemps() {
        return this.emploiTemps;
    }

    public void setEmploiTemps(Set<EmploiTemps> emploiTemps) {
        if (this.emploiTemps != null) {
            this.emploiTemps.forEach(i -> i.setEtudaint(null));
        }
        if (emploiTemps != null) {
            emploiTemps.forEach(i -> i.setEtudaint(this));
        }
        this.emploiTemps = emploiTemps;
    }

    public Etudiant emploiTemps(Set<EmploiTemps> emploiTemps) {
        this.setEmploiTemps(emploiTemps);
        return this;
    }

    public Etudiant addEmploiTemps(EmploiTemps emploiTemps) {
        this.emploiTemps.add(emploiTemps);
        emploiTemps.setEtudaint(this);
        return this;
    }

    public Etudiant removeEmploiTemps(EmploiTemps emploiTemps) {
        this.emploiTemps.remove(emploiTemps);
        emploiTemps.setEtudaint(null);
        return this;
    }

    public Set<Communication> getCommunications() {
        return this.communications;
    }

    public void setCommunications(Set<Communication> communications) {
        this.communications = communications;
    }

    public Etudiant communications(Set<Communication> communications) {
        this.setCommunications(communications);
        return this;
    }

    public Etudiant addCommunication(Communication communication) {
        this.communications.add(communication);
        communication.getEtudiants().add(this);
        return this;
    }

    public Etudiant removeCommunication(Communication communication) {
        this.communications.remove(communication);
        communication.getEtudiants().remove(this);
        return this;
    }

    public Administrateur getAdministrateur() {
        return this.administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Etudiant administrateur(Administrateur administrateur) {
        this.setAdministrateur(administrateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Etudiant)) {
            return false;
        }
        return id != null && id.equals(((Etudiant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Etudiant{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", postnom='" + getPostnom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", genre='" + getGenre() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", promotion='" + getPromotion() + "'" +
            "}";
    }
}
