package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.GestioninscripRepository;
import com.esisalama.gestionetudiant.service.GestioninscripService;
import com.esisalama.gestionetudiant.service.dto.GestioninscripDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Gestioninscrip}.
 */
@RestController
@RequestMapping("/api")
public class GestioninscripResource {

    private final Logger log = LoggerFactory.getLogger(GestioninscripResource.class);

    private static final String ENTITY_NAME = "gestioninscrip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GestioninscripService gestioninscripService;

    private final GestioninscripRepository gestioninscripRepository;

    public GestioninscripResource(GestioninscripService gestioninscripService, GestioninscripRepository gestioninscripRepository) {
        this.gestioninscripService = gestioninscripService;
        this.gestioninscripRepository = gestioninscripRepository;
    }

    /**
     * {@code POST  /gestioninscrips} : Create a new gestioninscrip.
     *
     * @param gestioninscripDTO the gestioninscripDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gestioninscripDTO, or with status {@code 400 (Bad Request)} if the gestioninscrip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gestioninscrips")
    public ResponseEntity<GestioninscripDTO> createGestioninscrip(@RequestBody GestioninscripDTO gestioninscripDTO)
        throws URISyntaxException {
        log.debug("REST request to save Gestioninscrip : {}", gestioninscripDTO);
        if (gestioninscripDTO.getId() != null) {
            throw new BadRequestAlertException("A new gestioninscrip cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GestioninscripDTO result = gestioninscripService.save(gestioninscripDTO);
        return ResponseEntity
            .created(new URI("/api/gestioninscrips/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gestioninscrips/:id} : Updates an existing gestioninscrip.
     *
     * @param id the id of the gestioninscripDTO to save.
     * @param gestioninscripDTO the gestioninscripDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gestioninscripDTO,
     * or with status {@code 400 (Bad Request)} if the gestioninscripDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gestioninscripDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gestioninscrips/{id}")
    public ResponseEntity<GestioninscripDTO> updateGestioninscrip(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GestioninscripDTO gestioninscripDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Gestioninscrip : {}, {}", id, gestioninscripDTO);
        if (gestioninscripDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gestioninscripDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gestioninscripRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GestioninscripDTO result = gestioninscripService.update(gestioninscripDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gestioninscripDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /gestioninscrips/:id} : Partial updates given fields of an existing gestioninscrip, field will ignore if it is null
     *
     * @param id the id of the gestioninscripDTO to save.
     * @param gestioninscripDTO the gestioninscripDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gestioninscripDTO,
     * or with status {@code 400 (Bad Request)} if the gestioninscripDTO is not valid,
     * or with status {@code 404 (Not Found)} if the gestioninscripDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the gestioninscripDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/gestioninscrips/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GestioninscripDTO> partialUpdateGestioninscrip(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GestioninscripDTO gestioninscripDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Gestioninscrip partially : {}, {}", id, gestioninscripDTO);
        if (gestioninscripDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gestioninscripDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gestioninscripRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GestioninscripDTO> result = gestioninscripService.partialUpdate(gestioninscripDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gestioninscripDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /gestioninscrips} : get all the gestioninscrips.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gestioninscrips in body.
     */
    @GetMapping("/gestioninscrips")
    public List<GestioninscripDTO> getAllGestioninscrips() {
        log.debug("REST request to get all Gestioninscrips");
        return gestioninscripService.findAll();
    }

    /**
     * {@code GET  /gestioninscrips/:id} : get the "id" gestioninscrip.
     *
     * @param id the id of the gestioninscripDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gestioninscripDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gestioninscrips/{id}")
    public ResponseEntity<GestioninscripDTO> getGestioninscrip(@PathVariable Long id) {
        log.debug("REST request to get Gestioninscrip : {}", id);
        Optional<GestioninscripDTO> gestioninscripDTO = gestioninscripService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gestioninscripDTO);
    }

    /**
     * {@code DELETE  /gestioninscrips/:id} : delete the "id" gestioninscrip.
     *
     * @param id the id of the gestioninscripDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gestioninscrips/{id}")
    public ResponseEntity<Void> deleteGestioninscrip(@PathVariable Long id) {
        log.debug("REST request to delete Gestioninscrip : {}", id);
        gestioninscripService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
