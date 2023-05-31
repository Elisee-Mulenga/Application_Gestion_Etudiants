package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Resultat;
import com.esisalama.gestionetudiant.repository.ResultatRepository;
import com.esisalama.gestionetudiant.service.dto.ResultatDTO;
import com.esisalama.gestionetudiant.service.mapper.ResultatMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Resultat}.
 */
@Service
@Transactional
public class ResultatService {

    private final Logger log = LoggerFactory.getLogger(ResultatService.class);

    private final ResultatRepository resultatRepository;

    private final ResultatMapper resultatMapper;

    public ResultatService(ResultatRepository resultatRepository, ResultatMapper resultatMapper) {
        this.resultatRepository = resultatRepository;
        this.resultatMapper = resultatMapper;
    }

    /**
     * Save a resultat.
     *
     * @param resultatDTO the entity to save.
     * @return the persisted entity.
     */
    public ResultatDTO save(ResultatDTO resultatDTO) {
        log.debug("Request to save Resultat : {}", resultatDTO);
        Resultat resultat = resultatMapper.toEntity(resultatDTO);
        resultat = resultatRepository.save(resultat);
        return resultatMapper.toDto(resultat);
    }

    /**
     * Update a resultat.
     *
     * @param resultatDTO the entity to save.
     * @return the persisted entity.
     */
    public ResultatDTO update(ResultatDTO resultatDTO) {
        log.debug("Request to update Resultat : {}", resultatDTO);
        Resultat resultat = resultatMapper.toEntity(resultatDTO);
        resultat = resultatRepository.save(resultat);
        return resultatMapper.toDto(resultat);
    }

    /**
     * Partially update a resultat.
     *
     * @param resultatDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResultatDTO> partialUpdate(ResultatDTO resultatDTO) {
        log.debug("Request to partially update Resultat : {}", resultatDTO);

        return resultatRepository
            .findById(resultatDTO.getId())
            .map(existingResultat -> {
                resultatMapper.partialUpdate(existingResultat, resultatDTO);

                return existingResultat;
            })
            .map(resultatRepository::save)
            .map(resultatMapper::toDto);
    }

    /**
     * Get all the resultats.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResultatDTO> findAll() {
        log.debug("Request to get all Resultats");
        return resultatRepository.findAll().stream().map(resultatMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resultat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResultatDTO> findOne(Long id) {
        log.debug("Request to get Resultat : {}", id);
        return resultatRepository.findById(id).map(resultatMapper::toDto);
    }

    /**
     * Delete the resultat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Resultat : {}", id);
        resultatRepository.deleteById(id);
    }
}
