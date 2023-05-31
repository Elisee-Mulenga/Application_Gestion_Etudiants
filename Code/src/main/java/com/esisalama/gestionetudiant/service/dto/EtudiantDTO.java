package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Etudiant} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EtudiantDTO implements Serializable {

    private Long id;

    private String nom;

    private String postnom;

    private String prenom;

    private String genre;

    private String dateNaissance;

    private String adresse;

    private String matricule;

    private String promotion;

    private InscriptionDTO inscription;

    private DossiersacademiqueDTO dossiersacademique;

    private DonneesDTO donnees;

    private ResultatDTO resultat;

    private DocumentDTO document;

    private ProgressionDTO progression;

    private Set<CommunicationDTO> communications = new HashSet<>();

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public InscriptionDTO getInscription() {
        return inscription;
    }

    public void setInscription(InscriptionDTO inscription) {
        this.inscription = inscription;
    }

    public DossiersacademiqueDTO getDossiersacademique() {
        return dossiersacademique;
    }

    public void setDossiersacademique(DossiersacademiqueDTO dossiersacademique) {
        this.dossiersacademique = dossiersacademique;
    }

    public DonneesDTO getDonnees() {
        return donnees;
    }

    public void setDonnees(DonneesDTO donnees) {
        this.donnees = donnees;
    }

    public ResultatDTO getResultat() {
        return resultat;
    }

    public void setResultat(ResultatDTO resultat) {
        this.resultat = resultat;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    public ProgressionDTO getProgression() {
        return progression;
    }

    public void setProgression(ProgressionDTO progression) {
        this.progression = progression;
    }

    public Set<CommunicationDTO> getCommunications() {
        return communications;
    }

    public void setCommunications(Set<CommunicationDTO> communications) {
        this.communications = communications;
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
        if (!(o instanceof EtudiantDTO)) {
            return false;
        }

        EtudiantDTO etudiantDTO = (EtudiantDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, etudiantDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtudiantDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", postnom='" + getPostnom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", genre='" + getGenre() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", promotion='" + getPromotion() + "'" +
            ", inscription=" + getInscription() +
            ", dossiersacademique=" + getDossiersacademique() +
            ", donnees=" + getDonnees() +
            ", resultat=" + getResultat() +
            ", document=" + getDocument() +
            ", progression=" + getProgression() +
            ", communications=" + getCommunications() +
            ", administrateur=" + getAdministrateur() +
            "}";
    }
}
