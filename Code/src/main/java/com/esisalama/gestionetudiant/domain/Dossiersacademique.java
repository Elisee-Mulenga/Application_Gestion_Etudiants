package com.esisalama.gestionetudiant.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Dossiersacademique.
 */
@Entity
@Table(name = "dossiersacademique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Dossiersacademique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "relevercotes")
    private String relevercotes;

    @Column(name = "bordereau")
    private String bordereau;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Dossiersacademique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelevercotes() {
        return this.relevercotes;
    }

    public Dossiersacademique relevercotes(String relevercotes) {
        this.setRelevercotes(relevercotes);
        return this;
    }

    public void setRelevercotes(String relevercotes) {
        this.relevercotes = relevercotes;
    }

    public String getBordereau() {
        return this.bordereau;
    }

    public Dossiersacademique bordereau(String bordereau) {
        this.setBordereau(bordereau);
        return this;
    }

    public void setBordereau(String bordereau) {
        this.bordereau = bordereau;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dossiersacademique)) {
            return false;
        }
        return id != null && id.equals(((Dossiersacademique) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dossiersacademique{" +
            "id=" + getId() +
            ", relevercotes='" + getRelevercotes() + "'" +
            ", bordereau='" + getBordereau() + "'" +
            "}";
    }
}
