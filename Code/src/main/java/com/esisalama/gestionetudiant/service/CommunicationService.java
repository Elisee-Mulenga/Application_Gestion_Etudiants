package com.esisalama.gestionetudiant.service;

import com.esisalama.gestionetudiant.domain.Communication;
import com.esisalama.gestionetudiant.repository.CommunicationRepository;
import com.esisalama.gestionetudiant.service.dto.CommunicationDTO;
import com.esisalama.gestionetudiant.service.mapper.CommunicationMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Communication}.
 */
@Service
@Transactional
public class CommunicationService {

    private final Logger log = LoggerFactory.getLogger(CommunicationService.class);

    private final CommunicationRepository communicationRepository;

    private final CommunicationMapper communicationMapper;

    public CommunicationService(CommunicationRepository communicationRepository, CommunicationMapper communicationMapper) {
        this.communicationRepository = communicationRepository;
        this.communicationMapper = communicationMapper;
    }

    /**
     * Save a communication.
     *
     * @param communicationDTO the entity to save.
     * @return the persisted entity.
     */
    public CommunicationDTO save(CommunicationDTO communicationDTO) {
        log.debug("Request to save Communication : {}", communicationDTO);
        Communication communication = communicationMapper.toEntity(communicationDTO);
        communication = communicationRepository.save(communication);
        return communicationMapper.toDto(communication);
    }

    /**
     * Update a communication.
     *
     * @param communicationDTO the entity to save.
     * @return the persisted entity.
     */
    public CommunicationDTO update(CommunicationDTO communicationDTO) {
        log.debug("Request to update Communication : {}", communicationDTO);
        Communication communication = communicationMapper.toEntity(communicationDTO);
        communication = communicationRepository.save(communication);
        return communicationMapper.toDto(communication);
    }

    /**
     * Partially update a communication.
     *
     * @param communicationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommunicationDTO> partialUpdate(CommunicationDTO communicationDTO) {
        log.debug("Request to partially update Communication : {}", communicationDTO);

        return communicationRepository
            .findById(communicationDTO.getId())
            .map(existingCommunication -> {
                communicationMapper.partialUpdate(existingCommunication, communicationDTO);

                return existingCommunication;
            })
            .map(communicationRepository::save)
            .map(communicationMapper::toDto);
    }

    /**
     * Get all the communications.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CommunicationDTO> findAll() {
        log.debug("Request to get all Communications");
        return communicationRepository.findAll().stream().map(communicationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one communication by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommunicationDTO> findOne(Long id) {
        log.debug("Request to get Communication : {}", id);
        return communicationRepository.findById(id).map(communicationMapper::toDto);
    }

    /**
     * Delete the communication by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Communication : {}", id);
        communicationRepository.deleteById(id);
    }
}
