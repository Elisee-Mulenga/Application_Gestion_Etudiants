package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.GestionInfos} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GestionInfosDTO implements Serializable {

    private Long id;

    private String infosperson;

    private String infosacadem;

    private String infosfinance;

    private AdministrateurDTO administrateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfosperson() {
        return infosperson;
    }

    public void setInfosperson(String infosperson) {
        this.infosperson = infosperson;
    }

    public String getInfosacadem() {
        return infosacadem;
    }

    public void setInfosacadem(String infosacadem) {
        this.infosacadem = infosacadem;
    }

    public String getInfosfinance() {
        return infosfinance;
    }

    public void setInfosfinance(String infosfinance) {
        this.infosfinance = infosfinance;
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
        if (!(o instanceof GestionInfosDTO)) {
            return false;
        }

        GestionInfosDTO gestionInfosDTO = (GestionInfosDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, gestionInfosDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GestionInfosDTO{" +
            "id=" + getId() +
            ", infosperson='" + getInfosperson() + "'" +
            ", infosacadem='" + getInfosacadem() + "'" +
            ", infosfinance='" + getInfosfinance() + "'" +
            ", administrateur=" + getAdministrateur() +
            "}";
    }
}
