package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Gestioninscrip;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Gestioninscrip entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GestioninscripRepository extends JpaRepository<Gestioninscrip, Long> {}
