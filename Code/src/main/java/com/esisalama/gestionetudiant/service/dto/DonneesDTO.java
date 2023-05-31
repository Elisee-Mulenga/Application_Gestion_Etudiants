package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Donnees} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DonneesDTO implements Serializable {

    private Long id;

    private String updateinfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdateinfo() {
        return updateinfo;
    }

    public void setUpdateinfo(String updateinfo) {
        this.updateinfo = updateinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DonneesDTO)) {
            return false;
        }

        DonneesDTO donneesDTO = (DonneesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, donneesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DonneesDTO{" +
            "id=" + getId() +
            ", updateinfo='" + getUpdateinfo() + "'" +
            "}";
    }
}
