package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Administrateur;
import com.esisalama.gestionetudiant.repository.AdministrateurRepository;
import com.esisalama.gestionetudiant.service.dto.AdministrateurDTO;
import com.esisalama.gestionetudiant.service.mapper.AdministrateurMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Administrateur}.
 */
@Service
@Transactional
public class AdministrateurService {

    private final Logger log = LoggerFactory.getLogger(AdministrateurService.class);

    private final AdministrateurRepository administrateurRepository;

    private final AdministrateurMapper administrateurMapper;

    public AdministrateurService(AdministrateurRepository administrateurRepository, AdministrateurMapper administrateurMapper) {
        this.administrateurRepository = administrateurRepository;
        this.administrateurMapper = administrateurMapper;
    }

    /**
     * Save a administrateur.
     *
     * @param administrateurDTO the entity to save.
     * @return the persisted entity.
     */
    public AdministrateurDTO save(AdministrateurDTO administrateurDTO) {
        log.debug("Request to save Administrateur : {}", administrateurDTO);
        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        administrateur = administrateurRepository.save(administrateur);
        return administrateurMapper.toDto(administrateur);
    }

    /**
     * Update a administrateur.
     *
     * @param administrateurDTO the entity to save.
     * @return the persisted entity.
     */
    public AdministrateurDTO update(AdministrateurDTO administrateurDTO) {
        log.debug("Request to update Administrateur : {}", administrateurDTO);
        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        administrateur = administrateurRepository.save(administrateur);
        return administrateurMapper.toDto(administrateur);
    }

    /**
     * Partially update a administrateur.
     *
     * @param administrateurDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdministrateurDTO> partialUpdate(AdministrateurDTO administrateurDTO) {
        log.debug("Request to partially update Administrateur : {}", administrateurDTO);

        return administrateurRepository
            .findById(administrateurDTO.getId())
            .map(existingAdministrateur -> {
                administrateurMapper.partialUpdate(existingAdministrateur, administrateurDTO);

                return existingAdministrateur;
            })
            .map(administrateurRepository::save)
            .map(administrateurMapper::toDto);
    }

    /**
     * Get all the administrateurs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdministrateurDTO> findAll() {
        log.debug("Request to get all Administrateurs");
        return administrateurRepository
            .findAll()
            .stream()
            .map(administrateurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one administrateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdministrateurDTO> findOne(Long id) {
        log.debug("Request to get Administrateur : {}", id);
        return administrateurRepository.findById(id).map(administrateurMapper::toDto);
    }

    /**
     * Delete the administrateur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Administrateur : {}", id);
        administrateurRepository.deleteById(id);
    }
}
