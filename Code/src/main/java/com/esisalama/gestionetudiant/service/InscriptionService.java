package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Inscription;
import com.esisalama.gestionetudiant.repository.InscriptionRepository;
import com.esisalama.gestionetudiant.service.dto.InscriptionDTO;
import com.esisalama.gestionetudiant.service.mapper.InscriptionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Inscription}.
 */
@Service
@Transactional
public class InscriptionService {

    private final Logger log = LoggerFactory.getLogger(InscriptionService.class);

    private final InscriptionRepository inscriptionRepository;

    private final InscriptionMapper inscriptionMapper;

    public InscriptionService(InscriptionRepository inscriptionRepository, InscriptionMapper inscriptionMapper) {
        this.inscriptionRepository = inscriptionRepository;
        this.inscriptionMapper = inscriptionMapper;
    }

    /**
     * Save a inscription.
     *
     * @param inscriptionDTO the entity to save.
     * @return the persisted entity.
     */
    public InscriptionDTO save(InscriptionDTO inscriptionDTO) {
        log.debug("Request to save Inscription : {}", inscriptionDTO);
        Inscription inscription = inscriptionMapper.toEntity(inscriptionDTO);
        inscription = inscriptionRepository.save(inscription);
        return inscriptionMapper.toDto(inscription);
    }

    /**
     * Update a inscription.
     *
     * @param inscriptionDTO the entity to save.
     * @return the persisted entity.
     */
    public InscriptionDTO update(InscriptionDTO inscriptionDTO) {
        log.debug("Request to update Inscription : {}", inscriptionDTO);
        Inscription inscription = inscriptionMapper.toEntity(inscriptionDTO);
        inscription = inscriptionRepository.save(inscription);
        return inscriptionMapper.toDto(inscription);
    }

    /**
     * Partially update a inscription.
     *
     * @param inscriptionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InscriptionDTO> partialUpdate(InscriptionDTO inscriptionDTO) {
        log.debug("Request to partially update Inscription : {}", inscriptionDTO);

        return inscriptionRepository
            .findById(inscriptionDTO.getId())
            .map(existingInscription -> {
                inscriptionMapper.partialUpdate(existingInscription, inscriptionDTO);

                return existingInscription;
            })
            .map(inscriptionRepository::save)
            .map(inscriptionMapper::toDto);
    }

    /**
     * Get all the inscriptions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InscriptionDTO> findAll() {
        log.debug("Request to get all Inscriptions");
        return inscriptionRepository.findAll().stream().map(inscriptionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one inscription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InscriptionDTO> findOne(Long id) {
        log.debug("Request to get Inscription : {}", id);
        return inscriptionRepository.findById(id).map(inscriptionMapper::toDto);
    }

    /**
     * Delete the inscription by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Inscription : {}", id);
        inscriptionRepository.deleteById(id);
    }
}
