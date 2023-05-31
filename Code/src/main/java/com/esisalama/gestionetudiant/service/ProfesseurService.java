package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Professeur;
import com.esisalama.gestionetudiant.repository.ProfesseurRepository;
import com.esisalama.gestionetudiant.service.dto.ProfesseurDTO;
import com.esisalama.gestionetudiant.service.mapper.ProfesseurMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Professeur}.
 */
@Service
@Transactional
public class ProfesseurService {

    private final Logger log = LoggerFactory.getLogger(ProfesseurService.class);

    private final ProfesseurRepository professeurRepository;

    private final ProfesseurMapper professeurMapper;

    public ProfesseurService(ProfesseurRepository professeurRepository, ProfesseurMapper professeurMapper) {
        this.professeurRepository = professeurRepository;
        this.professeurMapper = professeurMapper;
    }

    /**
     * Save a professeur.
     *
     * @param professeurDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfesseurDTO save(ProfesseurDTO professeurDTO) {
        log.debug("Request to save Professeur : {}", professeurDTO);
        Professeur professeur = professeurMapper.toEntity(professeurDTO);
        professeur = professeurRepository.save(professeur);
        return professeurMapper.toDto(professeur);
    }

    /**
     * Update a professeur.
     *
     * @param professeurDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfesseurDTO update(ProfesseurDTO professeurDTO) {
        log.debug("Request to update Professeur : {}", professeurDTO);
        Professeur professeur = professeurMapper.toEntity(professeurDTO);
        professeur = professeurRepository.save(professeur);
        return professeurMapper.toDto(professeur);
    }

    /**
     * Partially update a professeur.
     *
     * @param professeurDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProfesseurDTO> partialUpdate(ProfesseurDTO professeurDTO) {
        log.debug("Request to partially update Professeur : {}", professeurDTO);

        return professeurRepository
            .findById(professeurDTO.getId())
            .map(existingProfesseur -> {
                professeurMapper.partialUpdate(existingProfesseur, professeurDTO);

                return existingProfesseur;
            })
            .map(professeurRepository::save)
            .map(professeurMapper::toDto);
    }

    /**
     * Get all the professeurs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProfesseurDTO> findAll() {
        log.debug("Request to get all Professeurs");
        return professeurRepository.findAll().stream().map(professeurMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one professeur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProfesseurDTO> findOne(Long id) {
        log.debug("Request to get Professeur : {}", id);
        return professeurRepository.findById(id).map(professeurMapper::toDto);
    }

    /**
     * Delete the professeur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Professeur : {}", id);
        professeurRepository.deleteById(id);
    }
}
