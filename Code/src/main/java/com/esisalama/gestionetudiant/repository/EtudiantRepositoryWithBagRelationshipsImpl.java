package com.esisalama.gestionetudiant.repository;

import com.esisalama.gestionetudiant.domain.Etudiant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EtudiantRepositoryWithBagRelationshipsImpl implements EtudiantRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Etudiant> fetchBagRelationships(Optional<Etudiant> etudiant) {
        return etudiant.map(this::fetchCommunications);
    }

    @Override
    public Page<Etudiant> fetchBagRelationships(Page<Etudiant> etudiants) {
        return new PageImpl<>(fetchBagRelationships(etudiants.getContent()), etudiants.getPageable(), etudiants.getTotalElements());
    }

    @Override
    public List<Etudiant> fetchBagRelationships(List<Etudiant> etudiants) {
        return Optional.of(etudiants).map(this::fetchCommunications).orElse(Collections.emptyList());
    }

    Etudiant fetchCommunications(Etudiant result) {
        return entityManager
            .createQuery(
                "select etudiant from Etudiant etudiant left join fetch etudiant.communications where etudiant is :etudiant",
                Etudiant.class
            )
            .setParameter("etudiant", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Etudiant> fetchCommunications(List<Etudiant> etudiants) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, etudiants.size()).forEach(index -> order.put(etudiants.get(index).getId(), index));
        List<Etudiant> result = entityManager
            .createQuery(
                "select distinct etudiant from Etudiant etudiant left join fetch etudiant.communications where etudiant in :etudiants",
                Etudiant.class
            )
            .setParameter("etudiants", etudiants)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
