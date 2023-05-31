package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Progression} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProgressionDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgressionDTO)) {
            return false;
        }

        ProgressionDTO progressionDTO = (ProgressionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, progressionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgressionDTO{" +
            "id=" + getId() +
            "}";
    }
}
