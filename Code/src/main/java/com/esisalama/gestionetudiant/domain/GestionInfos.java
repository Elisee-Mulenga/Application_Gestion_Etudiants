package com.esisalama.gestionetudiant.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A GestionInfos.
 */
@Entity
@Table(name = "gestion_infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GestionInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "infosperson")
    private String infosperson;

    @Column(name = "infosacadem")
    private String infosacadem;

    @Column(name = "infosfinance")
    private String infosfinance;

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

    public GestionInfos id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfosperson() {
        return this.infosperson;
    }

    public GestionInfos infosperson(String infosperson) {
        this.setInfosperson(infosperson);
        return this;
    }

    public void setInfosperson(String infosperson) {
        this.infosperson = infosperson;
    }

    public String getInfosacadem() {
        return this.infosacadem;
    }

    public GestionInfos infosacadem(String infosacadem) {
        this.setInfosacadem(infosacadem);
        return this;
    }

    public void setInfosacadem(String infosacadem) {
        this.infosacadem = infosacadem;
    }

    public String getInfosfinance() {
        return this.infosfinance;
    }

    public GestionInfos infosfinance(String infosfinance) {
        this.setInfosfinance(infosfinance);
        return this;
    }

    public void setInfosfinance(String infosfinance) {
        this.infosfinance = infosfinance;
    }

    public Administrateur getAdministrateur() {
        return this.administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public GestionInfos administrateur(Administrateur administrateur) {
        this.setAdministrateur(administrateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GestionInfos)) {
            return false;
        }
        return id != null && id.equals(((GestionInfos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GestionInfos{" +
            "id=" + getId() +
            ", infosperson='" + getInfosperson() + "'" +
            ", infosacadem='" + getInfosacadem() + "'" +
            ", infosfinance='" + getInfosfinance() + "'" +
            "}";
    }
}
