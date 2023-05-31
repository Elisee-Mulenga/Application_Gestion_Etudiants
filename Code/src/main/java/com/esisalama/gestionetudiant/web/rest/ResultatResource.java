package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.ResultatRepository;
import com.esisalama.gestionetudiant.service.ResultatService;
import com.esisalama.gestionetudiant.service.dto.ResultatDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Resultat}.
 */
@RestController
@RequestMapping("/api")
public class ResultatResource {

    private final Logger log = LoggerFactory.getLogger(ResultatResource.class);

    private static final String ENTITY_NAME = "resultat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultatService resultatService;

    private final ResultatRepository resultatRepository;

    public ResultatResource(ResultatService resultatService, ResultatRepository resultatRepository) {
        this.resultatService = resultatService;
        this.resultatRepository = resultatRepository;
    }

    /**
     * {@code POST  /resultats} : Create a new resultat.
     *
     * @param resultatDTO the resultatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resultatDTO, or with status {@code 400 (Bad Request)} if the resultat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resultats")
    public ResponseEntity<ResultatDTO> createResultat(@RequestBody ResultatDTO resultatDTO) throws URISyntaxException {
        log.debug("REST request to save Resultat : {}", resultatDTO);
        if (resultatDTO.getId() != null) {
            throw new BadRequestAlertException("A new resultat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResultatDTO result = resultatService.save(resultatDTO);
        return ResponseEntity
            .created(new URI("/api/resultats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resultats/:id} : Updates an existing resultat.
     *
     * @param id the id of the resultatDTO to save.
     * @param resultatDTO the resultatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resultatDTO,
     * or with status {@code 400 (Bad Request)} if the resultatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resultatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resultats/{id}")
    public ResponseEntity<ResultatDTO> updateResultat(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResultatDTO resultatDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Resultat : {}, {}", id, resultatDTO);
        if (resultatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resultatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resultatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResultatDTO result = resultatService.update(resultatDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resultatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /resultats/:id} : Partial updates given fields of an existing resultat, field will ignore if it is null
     *
     * @param id the id of the resultatDTO to save.
     * @param resultatDTO the resultatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resultatDTO,
     * or with status {@code 400 (Bad Request)} if the resultatDTO is not valid,
     * or with status {@code 404 (Not Found)} if the resultatDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the resultatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/resultats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResultatDTO> partialUpdateResultat(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResultatDTO resultatDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Resultat partially : {}, {}", id, resultatDTO);
        if (resultatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resultatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resultatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResultatDTO> result = resultatService.partialUpdate(resultatDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resultatDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /resultats} : get all the resultats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resultats in body.
     */
    @GetMapping("/resultats")
    public List<ResultatDTO> getAllResultats() {
        log.debug("REST request to get all Resultats");
        return resultatService.findAll();
    }

    /**
     * {@code GET  /resultats/:id} : get the "id" resultat.
     *
     * @param id the id of the resultatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resultatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resultats/{id}")
    public ResponseEntity<ResultatDTO> getResultat(@PathVariable Long id) {
        log.debug("REST request to get Resultat : {}", id);
        Optional<ResultatDTO> resultatDTO = resultatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultatDTO);
    }

    /**
     * {@code DELETE  /resultats/:id} : delete the "id" resultat.
     *
     * @param id the id of the resultatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resultats/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        log.debug("REST request to delete Resultat : {}", id);
        resultatService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
