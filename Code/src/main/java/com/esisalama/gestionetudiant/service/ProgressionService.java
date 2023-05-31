package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Progression;
import com.esisalama.gestionetudiant.repository.ProgressionRepository;
import com.esisalama.gestionetudiant.service.dto.ProgressionDTO;
import com.esisalama.gestionetudiant.service.mapper.ProgressionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Progression}.
 */
@Service
@Transactional
public class ProgressionService {

    private final Logger log = LoggerFactory.getLogger(ProgressionService.class);

    private final ProgressionRepository progressionRepository;

    private final ProgressionMapper progressionMapper;

    public ProgressionService(ProgressionRepository progressionRepository, ProgressionMapper progressionMapper) {
        this.progressionRepository = progressionRepository;
        this.progressionMapper = progressionMapper;
    }

    /**
     * Save a progression.
     *
     * @param progressionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProgressionDTO save(ProgressionDTO progressionDTO) {
        log.debug("Request to save Progression : {}", progressionDTO);
        Progression progression = progressionMapper.toEntity(progressionDTO);
        progression = progressionRepository.save(progression);
        return progressionMapper.toDto(progression);
    }

    /**
     * Update a progression.
     *
     * @param progressionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProgressionDTO update(ProgressionDTO progressionDTO) {
        log.debug("Request to update Progression : {}", progressionDTO);
        Progression progression = progressionMapper.toEntity(progressionDTO);
        // no save call needed as we have no fields that can be updated
        return progressionMapper.toDto(progression);
    }

    /**
     * Partially update a progression.
     *
     * @param progressionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProgressionDTO> partialUpdate(ProgressionDTO progressionDTO) {
        log.debug("Request to partially update Progression : {}", progressionDTO);

        return progressionRepository
            .findById(progressionDTO.getId())
            .map(existingProgression -> {
                progressionMapper.partialUpdate(existingProgression, progressionDTO);

                return existingProgression;
            })
            // .map(progressionRepository::save)
            .map(progressionMapper::toDto);
    }

    /**
     * Get all the progressions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProgressionDTO> findAll() {
        log.debug("Request to get all Progressions");
        return progressionRepository.findAll().stream().map(progressionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one progression by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProgressionDTO> findOne(Long id) {
        log.debug("Request to get Progression : {}", id);
        return progressionRepository.findById(id).map(progressionMapper::toDto);
    }

    /**
     * Delete the progression by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Progression : {}", id);
        progressionRepository.deleteById(id);
    }
}
