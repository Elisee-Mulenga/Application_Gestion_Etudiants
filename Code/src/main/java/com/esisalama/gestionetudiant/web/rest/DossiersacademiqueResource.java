package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.DossiersacademiqueRepository;
import com.esisalama.gestionetudiant.service.DossiersacademiqueService;
import com.esisalama.gestionetudiant.service.dto.DossiersacademiqueDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Dossiersacademique}.
 */
@RestController
@RequestMapping("/api")
public class DossiersacademiqueResource {

    private final Logger log = LoggerFactory.getLogger(DossiersacademiqueResource.class);

    private static final String ENTITY_NAME = "dossiersacademique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DossiersacademiqueService dossiersacademiqueService;

    private final DossiersacademiqueRepository dossiersacademiqueRepository;

    public DossiersacademiqueResource(
        DossiersacademiqueService dossiersacademiqueService,
        DossiersacademiqueRepository dossiersacademiqueRepository
    ) {
        this.dossiersacademiqueService = dossiersacademiqueService;
        this.dossiersacademiqueRepository = dossiersacademiqueRepository;
    }

    /**
     * {@code POST  /dossiersacademiques} : Create a new dossiersacademique.
     *
     * @param dossiersacademiqueDTO the dossiersacademiqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dossiersacademiqueDTO, or with status {@code 400 (Bad Request)} if the dossiersacademique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dossiersacademiques")
    public ResponseEntity<DossiersacademiqueDTO> createDossiersacademique(@RequestBody DossiersacademiqueDTO dossiersacademiqueDTO)
        throws URISyntaxException {
        log.debug("REST request to save Dossiersacademique : {}", dossiersacademiqueDTO);
        if (dossiersacademiqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new dossiersacademique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DossiersacademiqueDTO result = dossiersacademiqueService.save(dossiersacademiqueDTO);
        return ResponseEntity
            .created(new URI("/api/dossiersacademiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dossiersacademiques/:id} : Updates an existing dossiersacademique.
     *
     * @param id the id of the dossiersacademiqueDTO to save.
     * @param dossiersacademiqueDTO the dossiersacademiqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dossiersacademiqueDTO,
     * or with status {@code 400 (Bad Request)} if the dossiersacademiqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dossiersacademiqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dossiersacademiques/{id}")
    public ResponseEntity<DossiersacademiqueDTO> updateDossiersacademique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DossiersacademiqueDTO dossiersacademiqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Dossiersacademique : {}, {}", id, dossiersacademiqueDTO);
        if (dossiersacademiqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dossiersacademiqueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dossiersacademiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DossiersacademiqueDTO result = dossiersacademiqueService.update(dossiersacademiqueDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dossiersacademiqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /dossiersacademiques/:id} : Partial updates given fields of an existing dossiersacademique, field will ignore if it is null
     *
     * @param id the id of the dossiersacademiqueDTO to save.
     * @param dossiersacademiqueDTO the dossiersacademiqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dossiersacademiqueDTO,
     * or with status {@code 400 (Bad Request)} if the dossiersacademiqueDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dossiersacademiqueDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dossiersacademiqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/dossiersacademiques/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DossiersacademiqueDTO> partialUpdateDossiersacademique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DossiersacademiqueDTO dossiersacademiqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Dossiersacademique partially : {}, {}", id, dossiersacademiqueDTO);
        if (dossiersacademiqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dossiersacademiqueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dossiersacademiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DossiersacademiqueDTO> result = dossiersacademiqueService.partialUpdate(dossiersacademiqueDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dossiersacademiqueDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /dossiersacademiques} : get all the dossiersacademiques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dossiersacademiques in body.
     */
    @GetMapping("/dossiersacademiques")
    public List<DossiersacademiqueDTO> getAllDossiersacademiques() {
        log.debug("REST request to get all Dossiersacademiques");
        return dossiersacademiqueService.findAll();
    }

    /**
     * {@code GET  /dossiersacademiques/:id} : get the "id" dossiersacademique.
     *
     * @param id the id of the dossiersacademiqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dossiersacademiqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dossiersacademiques/{id}")
    public ResponseEntity<DossiersacademiqueDTO> getDossiersacademique(@PathVariable Long id) {
        log.debug("REST request to get Dossiersacademique : {}", id);
        Optional<DossiersacademiqueDTO> dossiersacademiqueDTO = dossiersacademiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dossiersacademiqueDTO);
    }

    /**
     * {@code DELETE  /dossiersacademiques/:id} : delete the "id" dossiersacademique.
     *
     * @param id the id of the dossiersacademiqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dossiersacademiques/{id}")
    public ResponseEntity<Void> deleteDossiersacademique(@PathVariable Long id) {
        log.debug("REST request to delete Dossiersacademique : {}", id);
        dossiersacademiqueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
