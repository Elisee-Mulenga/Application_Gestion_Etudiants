package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Gestioninscrip} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GestioninscripDTO implements Serializable {

    private Long id;

    private AdministrateurDTO administrateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof GestioninscripDTO)) {
            return false;
        }

        GestioninscripDTO gestioninscripDTO = (GestioninscripDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, gestioninscripDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GestioninscripDTO{" +
            "id=" + getId() +
            ", administrateur=" + getAdministrateur() +
            "}";
    }
}
