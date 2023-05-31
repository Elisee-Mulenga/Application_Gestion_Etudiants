package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Gestioninscrip;
import com.esisalama.gestionetudiant.repository.GestioninscripRepository;
import com.esisalama.gestionetudiant.service.dto.GestioninscripDTO;
import com.esisalama.gestionetudiant.service.mapper.GestioninscripMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Gestioninscrip}.
 */
@Service
@Transactional
public class GestioninscripService {

    private final Logger log = LoggerFactory.getLogger(GestioninscripService.class);

    private final GestioninscripRepository gestioninscripRepository;

    private final GestioninscripMapper gestioninscripMapper;

    public GestioninscripService(GestioninscripRepository gestioninscripRepository, GestioninscripMapper gestioninscripMapper) {
        this.gestioninscripRepository = gestioninscripRepository;
        this.gestioninscripMapper = gestioninscripMapper;
    }

    /**
     * Save a gestioninscrip.
     *
     * @param gestioninscripDTO the entity to save.
     * @return the persisted entity.
     */
    public GestioninscripDTO save(GestioninscripDTO gestioninscripDTO) {
        log.debug("Request to save Gestioninscrip : {}", gestioninscripDTO);
        Gestioninscrip gestioninscrip = gestioninscripMapper.toEntity(gestioninscripDTO);
        gestioninscrip = gestioninscripRepository.save(gestioninscrip);
        return gestioninscripMapper.toDto(gestioninscrip);
    }

    /**
     * Update a gestioninscrip.
     *
     * @param gestioninscripDTO the entity to save.
     * @return the persisted entity.
     */
    public GestioninscripDTO update(GestioninscripDTO gestioninscripDTO) {
        log.debug("Request to update Gestioninscrip : {}", gestioninscripDTO);
        Gestioninscrip gestioninscrip = gestioninscripMapper.toEntity(gestioninscripDTO);
        gestioninscrip = gestioninscripRepository.save(gestioninscrip);
        return gestioninscripMapper.toDto(gestioninscrip);
    }

    /**
     * Partially update a gestioninscrip.
     *
     * @param gestioninscripDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GestioninscripDTO> partialUpdate(GestioninscripDTO gestioninscripDTO) {
        log.debug("Request to partially update Gestioninscrip : {}", gestioninscripDTO);

        return gestioninscripRepository
            .findById(gestioninscripDTO.getId())
            .map(existingGestioninscrip -> {
                gestioninscripMapper.partialUpdate(existingGestioninscrip, gestioninscripDTO);

                return existingGestioninscrip;
            })
            .map(gestioninscripRepository::save)
            .map(gestioninscripMapper::toDto);
    }

    /**
     * Get all the gestioninscrips.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GestioninscripDTO> findAll() {
        log.debug("Request to get all Gestioninscrips");
        return gestioninscripRepository
            .findAll()
            .stream()
            .map(gestioninscripMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gestioninscrip by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GestioninscripDTO> findOne(Long id) {
        log.debug("Request to get Gestioninscrip : {}", id);
        return gestioninscripRepository.findById(id).map(gestioninscripMapper::toDto);
    }

    /**
     * Delete the gestioninscrip by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Gestioninscrip : {}", id);
        gestioninscripRepository.deleteById(id);
    }
}
