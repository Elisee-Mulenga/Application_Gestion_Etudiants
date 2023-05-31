package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.EmploiTemps;
import com.esisalama.gestionetudiant.repository.EmploiTempsRepository;
import com.esisalama.gestionetudiant.service.dto.EmploiTempsDTO;
import com.esisalama.gestionetudiant.service.mapper.EmploiTempsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EmploiTemps}.
 */
@Service
@Transactional
public class EmploiTempsService {

    private final Logger log = LoggerFactory.getLogger(EmploiTempsService.class);

    private final EmploiTempsRepository emploiTempsRepository;

    private final EmploiTempsMapper emploiTempsMapper;

    public EmploiTempsService(EmploiTempsRepository emploiTempsRepository, EmploiTempsMapper emploiTempsMapper) {
        this.emploiTempsRepository = emploiTempsRepository;
        this.emploiTempsMapper = emploiTempsMapper;
    }

    /**
     * Save a emploiTemps.
     *
     * @param emploiTempsDTO the entity to save.
     * @return the persisted entity.
     */
    public EmploiTempsDTO save(EmploiTempsDTO emploiTempsDTO) {
        log.debug("Request to save EmploiTemps : {}", emploiTempsDTO);
        EmploiTemps emploiTemps = emploiTempsMapper.toEntity(emploiTempsDTO);
        emploiTemps = emploiTempsRepository.save(emploiTemps);
        return emploiTempsMapper.toDto(emploiTemps);
    }

    /**
     * Update a emploiTemps.
     *
     * @param emploiTempsDTO the entity to save.
     * @return the persisted entity.
     */
    public EmploiTempsDTO update(EmploiTempsDTO emploiTempsDTO) {
        log.debug("Request to update EmploiTemps : {}", emploiTempsDTO);
        EmploiTemps emploiTemps = emploiTempsMapper.toEntity(emploiTempsDTO);
        emploiTemps = emploiTempsRepository.save(emploiTemps);
        return emploiTempsMapper.toDto(emploiTemps);
    }

    /**
     * Partially update a emploiTemps.
     *
     * @param emploiTempsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmploiTempsDTO> partialUpdate(EmploiTempsDTO emploiTempsDTO) {
        log.debug("Request to partially update EmploiTemps : {}", emploiTempsDTO);

        return emploiTempsRepository
            .findById(emploiTempsDTO.getId())
            .map(existingEmploiTemps -> {
                emploiTempsMapper.partialUpdate(existingEmploiTemps, emploiTempsDTO);

                return existingEmploiTemps;
            })
            .map(emploiTempsRepository::save)
            .map(emploiTempsMapper::toDto);
    }

    /**
     * Get all the emploiTemps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmploiTempsDTO> findAll() {
        log.debug("Request to get all EmploiTemps");
        return emploiTempsRepository.findAll().stream().map(emploiTempsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one emploiTemps by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmploiTempsDTO> findOne(Long id) {
        log.debug("Request to get EmploiTemps : {}", id);
        return emploiTempsRepository.findById(id).map(emploiTempsMapper::toDto);
    }

    /**
     * Delete the emploiTemps by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmploiTemps : {}", id);
        emploiTempsRepository.deleteById(id);
    }
}
