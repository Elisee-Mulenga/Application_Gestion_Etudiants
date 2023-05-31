package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Progression;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Progression entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressionRepository extends JpaRepository<Progression, Long> {}
