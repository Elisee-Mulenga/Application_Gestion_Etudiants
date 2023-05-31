package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Dossiersacademique;
import com.esisalama.gestionetudiant.repository.DossiersacademiqueRepository;
import com.esisalama.gestionetudiant.service.dto.DossiersacademiqueDTO;
import com.esisalama.gestionetudiant.service.mapper.DossiersacademiqueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Dossiersacademique}.
 */
@Service
@Transactional
public class DossiersacademiqueService {

    private final Logger log = LoggerFactory.getLogger(DossiersacademiqueService.class);

    private final DossiersacademiqueRepository dossiersacademiqueRepository;

    private final DossiersacademiqueMapper dossiersacademiqueMapper;

    public DossiersacademiqueService(
        DossiersacademiqueRepository dossiersacademiqueRepository,
        DossiersacademiqueMapper dossiersacademiqueMapper
    ) {
        this.dossiersacademiqueRepository = dossiersacademiqueRepository;
        this.dossiersacademiqueMapper = dossiersacademiqueMapper;
    }

    /**
     * Save a dossiersacademique.
     *
     * @param dossiersacademiqueDTO the entity to save.
     * @return the persisted entity.
     */
    public DossiersacademiqueDTO save(DossiersacademiqueDTO dossiersacademiqueDTO) {
        log.debug("Request to save Dossiersacademique : {}", dossiersacademiqueDTO);
        Dossiersacademique dossiersacademique = dossiersacademiqueMapper.toEntity(dossiersacademiqueDTO);
        dossiersacademique = dossiersacademiqueRepository.save(dossiersacademique);
        return dossiersacademiqueMapper.toDto(dossiersacademique);
    }

    /**
     * Update a dossiersacademique.
     *
     * @param dossiersacademiqueDTO the entity to save.
     * @return the persisted entity.
     */
    public DossiersacademiqueDTO update(DossiersacademiqueDTO dossiersacademiqueDTO) {
        log.debug("Request to update Dossiersacademique : {}", dossiersacademiqueDTO);
        Dossiersacademique dossiersacademique = dossiersacademiqueMapper.toEntity(dossiersacademiqueDTO);
        dossiersacademique = dossiersacademiqueRepository.save(dossiersacademique);
        return dossiersacademiqueMapper.toDto(dossiersacademique);
    }

    /**
     * Partially update a dossiersacademique.
     *
     * @param dossiersacademiqueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DossiersacademiqueDTO> partialUpdate(DossiersacademiqueDTO dossiersacademiqueDTO) {
        log.debug("Request to partially update Dossiersacademique : {}", dossiersacademiqueDTO);

        return dossiersacademiqueRepository
            .findById(dossiersacademiqueDTO.getId())
            .map(existingDossiersacademique -> {
                dossiersacademiqueMapper.partialUpdate(existingDossiersacademique, dossiersacademiqueDTO);

                return existingDossiersacademique;
            })
            .map(dossiersacademiqueRepository::save)
            .map(dossiersacademiqueMapper::toDto);
    }

    /**
     * Get all the dossiersacademiques.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DossiersacademiqueDTO> findAll() {
        log.debug("Request to get all Dossiersacademiques");
        return dossiersacademiqueRepository
            .findAll()
            .stream()
            .map(dossiersacademiqueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dossiersacademique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DossiersacademiqueDTO> findOne(Long id) {
        log.debug("Request to get Dossiersacademique : {}", id);
        return dossiersacademiqueRepository.findById(id).map(dossiersacademiqueMapper::toDto);
    }

    /**
     * Delete the dossiersacademique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Dossiersacademique : {}", id);
        dossiersacademiqueRepository.deleteById(id);
    }
}
