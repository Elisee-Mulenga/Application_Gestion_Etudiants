package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Resultat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResultatDTO implements Serializable {

    private Long id;

    private String moyenne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResultatDTO)) {
            return false;
        }

        ResultatDTO resultatDTO = (ResultatDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resultatDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResultatDTO{" +
            "id=" + getId() +
            ", moyenne='" + getMoyenne() + "'" +
            "}";
    }
}
