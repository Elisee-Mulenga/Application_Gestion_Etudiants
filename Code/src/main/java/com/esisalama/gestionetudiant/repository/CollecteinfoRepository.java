package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Collecteinfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Collecteinfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollecteinfoRepository extends JpaRepository<Collecteinfo, Long> {}
