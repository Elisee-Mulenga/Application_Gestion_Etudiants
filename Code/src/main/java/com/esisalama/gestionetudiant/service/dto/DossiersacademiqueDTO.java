package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Dossiersacademique} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DossiersacademiqueDTO implements Serializable {

    private Long id;

    private String relevercotes;

    private String bordereau;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelevercotes() {
        return relevercotes;
    }

    public void setRelevercotes(String relevercotes) {
        this.relevercotes = relevercotes;
    }

    public String getBordereau() {
        return bordereau;
    }

    public void setBordereau(String bordereau) {
        this.bordereau = bordereau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DossiersacademiqueDTO)) {
            return false;
        }

        DossiersacademiqueDTO dossiersacademiqueDTO = (DossiersacademiqueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dossiersacademiqueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DossiersacademiqueDTO{" +
            "id=" + getId() +
            ", relevercotes='" + getRelevercotes() + "'" +
            ", bordereau='" + getBordereau() + "'" +
            "}";
    }
}
