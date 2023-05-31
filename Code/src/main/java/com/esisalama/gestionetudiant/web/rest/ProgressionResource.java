package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.ProgressionRepository;
import com.esisalama.gestionetudiant.service.ProgressionService;
import com.esisalama.gestionetudiant.service.dto.ProgressionDTO;
import com.esisalama.gestionetudiant.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Progression}.
 */
@RestController
@RequestMapping("/api")
public class ProgressionResource {

    private final Logger log = LoggerFactory.getLogger(ProgressionResource.class);

    private static final String ENTITY_NAME = "progression";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgressionService progressionService;

    private final ProgressionRepository progressionRepository;

    public ProgressionResource(ProgressionService progressionService, ProgressionRepository progressionRepository) {
        this.progressionService = progressionService;
        this.progressionRepository = progressionRepository;
    }

    /**
     * {@code POST  /progressions} : Create a new progression.
     *
     * @param progressionDTO the progressionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressionDTO, or with status {@code 400 (Bad Request)} if the progression has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/progressions")
    public ResponseEntity<ProgressionDTO> createProgression(@RequestBody ProgressionDTO progressionDTO) throws URISyntaxException {
        log.debug("REST request to save Progression : {}", progressionDTO);
        if (progressionDTO.getId() != null) {
            throw new BadRequestAlertException("A new progression cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgressionDTO result = progressionService.save(progressionDTO);
        return ResponseEntity
            .created(new URI("/api/progressions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /progressions/:id} : Updates an existing progression.
     *
     * @param id the id of the progressionDTO to save.
     * @param progressionDTO the progressionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressionDTO,
     * or with status {@code 400 (Bad Request)} if the progressionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/progressions/{id}")
    public ResponseEntity<ProgressionDTO> updateProgression(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProgressionDTO progressionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Progression : {}, {}", id, progressionDTO);
        if (progressionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProgressionDTO result = progressionService.update(progressionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /progressions/:id} : Partial updates given fields of an existing progression, field will ignore if it is null
     *
     * @param id the id of the progressionDTO to save.
     * @param progressionDTO the progressionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressionDTO,
     * or with status {@code 400 (Bad Request)} if the progressionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the progressionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the progressionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/progressions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProgressionDTO> partialUpdateProgression(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProgressionDTO progressionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Progression partially : {}, {}", id, progressionDTO);
        if (progressionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, progressionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!progressionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProgressionDTO> result = progressionService.partialUpdate(progressionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, progressionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /progressions} : get all the progressions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progressions in body.
     */
    @GetMapping("/progressions")
    public List<ProgressionDTO> getAllProgressions() {
        log.debug("REST request to get all Progressions");
        return progressionService.findAll();
    }

    /**
     * {@code GET  /progressions/:id} : get the "id" progression.
     *
     * @param id the id of the progressionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/progressions/{id}")
    public ResponseEntity<ProgressionDTO> getProgression(@PathVariable Long id) {
        log.debug("REST request to get Progression : {}", id);
        Optional<ProgressionDTO> progressionDTO = progressionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(progressionDTO);
    }

    /**
     * {@code DELETE  /progressions/:id} : delete the "id" progression.
     *
     * @param id the id of the progressionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/progressions/{id}")
    public ResponseEntity<Void> deleteProgression(@PathVariable Long id) {
        log.debug("REST request to delete Progression : {}", id);
        progressionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
