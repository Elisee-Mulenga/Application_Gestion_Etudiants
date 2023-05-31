package com.esisalama.gestionetudiant.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.esisalama.gestionetudiant.domain.Collecteinfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CollecteinfoDTO implements Serializable {

    private Long id;

    private String infosperson;

    private String infosacadem;

    private String infosadmi;

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

    public String getInfosadmi() {
        return infosadmi;
    }

    public void setInfosadmi(String infosadmi) {
        this.infosadmi = infosadmi;
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
        if (!(o instanceof CollecteinfoDTO)) {
            return false;
        }

        CollecteinfoDTO collecteinfoDTO = (CollecteinfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, collecteinfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CollecteinfoDTO{" +
            "id=" + getId() +
            ", infosperson='" + getInfosperson() + "'" +
            ", infosacadem='" + getInfosacadem() + "'" +
            ", infosadmi='" + getInfosadmi() + "'" +
            ", administrateur=" + getAdministrateur() +
            "}";
    }
}
