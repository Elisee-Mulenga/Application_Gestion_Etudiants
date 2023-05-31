package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.GestionInfos;
import com.esisalama.gestionetudiant.repository.GestionInfosRepository;
import com.esisalama.gestionetudiant.service.dto.GestionInfosDTO;
import com.esisalama.gestionetudiant.service.mapper.GestionInfosMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GestionInfos}.
 */
@Service
@Transactional
public class GestionInfosService {

    private final Logger log = LoggerFactory.getLogger(GestionInfosService.class);

    private final GestionInfosRepository gestionInfosRepository;

    private final GestionInfosMapper gestionInfosMapper;

    public GestionInfosService(GestionInfosRepository gestionInfosRepository, GestionInfosMapper gestionInfosMapper) {
        this.gestionInfosRepository = gestionInfosRepository;
        this.gestionInfosMapper = gestionInfosMapper;
    }

    /**
     * Save a gestionInfos.
     *
     * @param gestionInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public GestionInfosDTO save(GestionInfosDTO gestionInfosDTO) {
        log.debug("Request to save GestionInfos : {}", gestionInfosDTO);
        GestionInfos gestionInfos = gestionInfosMapper.toEntity(gestionInfosDTO);
        gestionInfos = gestionInfosRepository.save(gestionInfos);
        return gestionInfosMapper.toDto(gestionInfos);
    }

    /**
     * Update a gestionInfos.
     *
     * @param gestionInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public GestionInfosDTO update(GestionInfosDTO gestionInfosDTO) {
        log.debug("Request to update GestionInfos : {}", gestionInfosDTO);
        GestionInfos gestionInfos = gestionInfosMapper.toEntity(gestionInfosDTO);
        gestionInfos = gestionInfosRepository.save(gestionInfos);
        return gestionInfosMapper.toDto(gestionInfos);
    }

    /**
     * Partially update a gestionInfos.
     *
     * @param gestionInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GestionInfosDTO> partialUpdate(GestionInfosDTO gestionInfosDTO) {
        log.debug("Request to partially update GestionInfos : {}", gestionInfosDTO);

        return gestionInfosRepository
            .findById(gestionInfosDTO.getId())
            .map(existingGestionInfos -> {
                gestionInfosMapper.partialUpdate(existingGestionInfos, gestionInfosDTO);

                return existingGestionInfos;
            })
            .map(gestionInfosRepository::save)
            .map(gestionInfosMapper::toDto);
    }

    /**
     * Get all the gestionInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GestionInfosDTO> findAll() {
        log.debug("Request to get all GestionInfos");
        return gestionInfosRepository.findAll().stream().map(gestionInfosMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gestionInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GestionInfosDTO> findOne(Long id) {
        log.debug("Request to get GestionInfos : {}", id);
        return gestionInfosRepository.findById(id).map(gestionInfosMapper::toDto);
    }

    /**
     * Delete the gestionInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GestionInfos : {}", id);
        gestionInfosRepository.deleteById(id);
    }
}
