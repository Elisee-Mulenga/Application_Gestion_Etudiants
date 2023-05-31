package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Donnees;
import com.esisalama.gestionetudiant.repository.DonneesRepository;
import com.esisalama.gestionetudiant.service.dto.DonneesDTO;
import com.esisalama.gestionetudiant.service.mapper.DonneesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Donnees}.
 */
@Service
@Transactional
public class DonneesService {

    private final Logger log = LoggerFactory.getLogger(DonneesService.class);

    private final DonneesRepository donneesRepository;

    private final DonneesMapper donneesMapper;

    public DonneesService(DonneesRepository donneesRepository, DonneesMapper donneesMapper) {
        this.donneesRepository = donneesRepository;
        this.donneesMapper = donneesMapper;
    }

    /**
     * Save a donnees.
     *
     * @param donneesDTO the entity to save.
     * @return the persisted entity.
     */
    public DonneesDTO save(DonneesDTO donneesDTO) {
        log.debug("Request to save Donnees : {}", donneesDTO);
        Donnees donnees = donneesMapper.toEntity(donneesDTO);
        donnees = donneesRepository.save(donnees);
        return donneesMapper.toDto(donnees);
    }

    /**
     * Update a donnees.
     *
     * @param donneesDTO the entity to save.
     * @return the persisted entity.
     */
    public DonneesDTO update(DonneesDTO donneesDTO) {
        log.debug("Request to update Donnees : {}", donneesDTO);
        Donnees donnees = donneesMapper.toEntity(donneesDTO);
        donnees = donneesRepository.save(donnees);
        return donneesMapper.toDto(donnees);
    }

    /**
     * Partially update a donnees.
     *
     * @param donneesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DonneesDTO> partialUpdate(DonneesDTO donneesDTO) {
        log.debug("Request to partially update Donnees : {}", donneesDTO);

        return donneesRepository
            .findById(donneesDTO.getId())
            .map(existingDonnees -> {
                donneesMapper.partialUpdate(existingDonnees, donneesDTO);

                return existingDonnees;
            })
            .map(donneesRepository::save)
            .map(donneesMapper::toDto);
    }

    /**
     * Get all the donnees.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DonneesDTO> findAll() {
        log.debug("Request to get all Donnees");
        return donneesRepository.findAll().stream().map(donneesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one donnees by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DonneesDTO> findOne(Long id) {
        log.debug("Request to get Donnees : {}", id);
        return donneesRepository.findById(id).map(donneesMapper::toDto);
    }

    /**
     * Delete the donnees by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Donnees : {}", id);
        donneesRepository.deleteById(id);
    }
}
