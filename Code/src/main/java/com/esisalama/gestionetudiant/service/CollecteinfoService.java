package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Collecteinfo;
import com.esisalama.gestionetudiant.repository.CollecteinfoRepository;
import com.esisalama.gestionetudiant.service.dto.CollecteinfoDTO;
import com.esisalama.gestionetudiant.service.mapper.CollecteinfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Collecteinfo}.
 */
@Service
@Transactional
public class CollecteinfoService {

    private final Logger log = LoggerFactory.getLogger(CollecteinfoService.class);

    private final CollecteinfoRepository collecteinfoRepository;

    private final CollecteinfoMapper collecteinfoMapper;

    public CollecteinfoService(CollecteinfoRepository collecteinfoRepository, CollecteinfoMapper collecteinfoMapper) {
        this.collecteinfoRepository = collecteinfoRepository;
        this.collecteinfoMapper = collecteinfoMapper;
    }

    /**
     * Save a collecteinfo.
     *
     * @param collecteinfoDTO the entity to save.
     * @return the persisted entity.
     */
    public CollecteinfoDTO save(CollecteinfoDTO collecteinfoDTO) {
        log.debug("Request to save Collecteinfo : {}", collecteinfoDTO);
        Collecteinfo collecteinfo = collecteinfoMapper.toEntity(collecteinfoDTO);
        collecteinfo = collecteinfoRepository.save(collecteinfo);
        return collecteinfoMapper.toDto(collecteinfo);
    }

    /**
     * Update a collecteinfo.
     *
     * @param collecteinfoDTO the entity to save.
     * @return the persisted entity.
     */
    public CollecteinfoDTO update(CollecteinfoDTO collecteinfoDTO) {
        log.debug("Request to update Collecteinfo : {}", collecteinfoDTO);
        Collecteinfo collecteinfo = collecteinfoMapper.toEntity(collecteinfoDTO);
        collecteinfo = collecteinfoRepository.save(collecteinfo);
        return collecteinfoMapper.toDto(collecteinfo);
    }

    /**
     * Partially update a collecteinfo.
     *
     * @param collecteinfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CollecteinfoDTO> partialUpdate(CollecteinfoDTO collecteinfoDTO) {
        log.debug("Request to partially update Collecteinfo : {}", collecteinfoDTO);

        return collecteinfoRepository
            .findById(collecteinfoDTO.getId())
            .map(existingCollecteinfo -> {
                collecteinfoMapper.partialUpdate(existingCollecteinfo, collecteinfoDTO);

                return existingCollecteinfo;
            })
            .map(collecteinfoRepository::save)
            .map(collecteinfoMapper::toDto);
    }

    /**
     * Get all the collecteinfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CollecteinfoDTO> findAll() {
        log.debug("Request to get all Collecteinfos");
        return collecteinfoRepository.findAll().stream().map(collecteinfoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one collecteinfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CollecteinfoDTO> findOne(Long id) {
        log.debug("Request to get Collecteinfo : {}", id);
        return collecteinfoRepository.findById(id).map(collecteinfoMapper::toDto);
    }

    /**
     * Delete the collecteinfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Collecteinfo : {}", id);
        collecteinfoRepository.deleteById(id);
    }
}
