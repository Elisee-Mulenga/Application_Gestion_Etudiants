package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Donnees;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Donnees entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonneesRepository extends JpaRepository<Donnees, Long> {}
