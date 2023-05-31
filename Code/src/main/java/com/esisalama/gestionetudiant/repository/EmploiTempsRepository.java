package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.EmploiTemps;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EmploiTemps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmploiTempsRepository extends JpaRepository<EmploiTemps, Long> {}
