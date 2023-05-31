package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.EmploiTempsRepository;
import com.esisalama.gestionetudiant.service.EmploiTempsService;
import com.esisalama.gestionetudiant.service.dto.EmploiTempsDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.EmploiTemps}.
 */
@RestController
@RequestMapping("/api")
public class EmploiTempsResource {

    private final Logger log = LoggerFactory.getLogger(EmploiTempsResource.class);

    private static final String ENTITY_NAME = "emploiTemps";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmploiTempsService emploiTempsService;

    private final EmploiTempsRepository emploiTempsRepository;

    public EmploiTempsResource(EmploiTempsService emploiTempsService, EmploiTempsRepository emploiTempsRepository) {
        this.emploiTempsService = emploiTempsService;
        this.emploiTempsRepository = emploiTempsRepository;
    }

    /**
     * {@code POST  /emploi-temps} : Create a new emploiTemps.
     *
     * @param emploiTempsDTO the emploiTempsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emploiTempsDTO, or with status {@code 400 (Bad Request)} if the emploiTemps has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emploi-temps")
    public ResponseEntity<EmploiTempsDTO> createEmploiTemps(@RequestBody EmploiTempsDTO emploiTempsDTO) throws URISyntaxException {
        log.debug("REST request to save EmploiTemps : {}", emploiTempsDTO);
        if (emploiTempsDTO.getId() != null) {
            throw new BadRequestAlertException("A new emploiTemps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmploiTempsDTO result = emploiTempsService.save(emploiTempsDTO);
        return ResponseEntity
            .created(new URI("/api/emploi-temps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emploi-temps/:id} : Updates an existing emploiTemps.
     *
     * @param id the id of the emploiTempsDTO to save.
     * @param emploiTempsDTO the emploiTempsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emploiTempsDTO,
     * or with status {@code 400 (Bad Request)} if the emploiTempsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emploiTempsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emploi-temps/{id}")
    public ResponseEntity<EmploiTempsDTO> updateEmploiTemps(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmploiTempsDTO emploiTempsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EmploiTemps : {}, {}", id, emploiTempsDTO);
        if (emploiTempsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emploiTempsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emploiTempsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EmploiTempsDTO result = emploiTempsService.update(emploiTempsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emploiTempsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /emploi-temps/:id} : Partial updates given fields of an existing emploiTemps, field will ignore if it is null
     *
     * @param id the id of the emploiTempsDTO to save.
     * @param emploiTempsDTO the emploiTempsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emploiTempsDTO,
     * or with status {@code 400 (Bad Request)} if the emploiTempsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the emploiTempsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the emploiTempsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/emploi-temps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmploiTempsDTO> partialUpdateEmploiTemps(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmploiTempsDTO emploiTempsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmploiTemps partially : {}, {}", id, emploiTempsDTO);
        if (emploiTempsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emploiTempsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emploiTempsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmploiTempsDTO> result = emploiTempsService.partialUpdate(emploiTempsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emploiTempsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /emploi-temps} : get all the emploiTemps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emploiTemps in body.
     */
    @GetMapping("/emploi-temps")
    public List<EmploiTempsDTO> getAllEmploiTemps() {
        log.debug("REST request to get all EmploiTemps");
        return emploiTempsService.findAll();
    }

    /**
     * {@code GET  /emploi-temps/:id} : get the "id" emploiTemps.
     *
     * @param id the id of the emploiTempsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emploiTempsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emploi-temps/{id}")
    public ResponseEntity<EmploiTempsDTO> getEmploiTemps(@PathVariable Long id) {
        log.debug("REST request to get EmploiTemps : {}", id);
        Optional<EmploiTempsDTO> emploiTempsDTO = emploiTempsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emploiTempsDTO);
    }

    /**
     * {@code DELETE  /emploi-temps/:id} : delete the "id" emploiTemps.
     *
     * @param id the id of the emploiTempsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emploi-temps/{id}")
    public ResponseEntity<Void> deleteEmploiTemps(@PathVariable Long id) {
        log.debug("REST request to delete EmploiTemps : {}", id);
        emploiTempsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
