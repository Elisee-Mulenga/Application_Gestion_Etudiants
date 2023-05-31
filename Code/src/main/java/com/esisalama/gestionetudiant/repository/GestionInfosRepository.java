package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.GestionInfos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GestionInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GestionInfosRepository extends JpaRepository<GestionInfos, Long> {}
