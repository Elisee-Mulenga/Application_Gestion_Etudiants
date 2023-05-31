package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Etudiant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface EtudiantRepositoryWithBagRelationships {
    Optional<Etudiant> fetchBagRelationships(Optional<Etudiant> etudiant);

    List<Etudiant> fetchBagRelationships(List<Etudiant> etudiants);

    Page<Etudiant> fetchBagRelationships(Page<Etudiant> etudiants);
}
