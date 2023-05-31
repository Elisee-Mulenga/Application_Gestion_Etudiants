package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Dossiersacademique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Dossiersacademique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DossiersacademiqueRepository extends JpaRepository<Dossiersacademique, Long> {}
