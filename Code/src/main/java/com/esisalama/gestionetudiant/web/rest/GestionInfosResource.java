package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.GestionInfosRepository;
import com.esisalama.gestionetudiant.service.GestionInfosService;
import com.esisalama.gestionetudiant.service.dto.GestionInfosDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.GestionInfos}.
 */
@RestController
@RequestMapping("/api")
public class GestionInfosResource {

    private final Logger log = LoggerFactory.getLogger(GestionInfosResource.class);

    private static final String ENTITY_NAME = "gestionInfos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GestionInfosService gestionInfosService;

    private final GestionInfosRepository gestionInfosRepository;

    public GestionInfosResource(GestionInfosService gestionInfosService, GestionInfosRepository gestionInfosRepository) {
        this.gestionInfosService = gestionInfosService;
        this.gestionInfosRepository = gestionInfosRepository;
    }

    /**
     * {@code POST  /gestion-infos} : Create a new gestionInfos.
     *
     * @param gestionInfosDTO the gestionInfosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gestionInfosDTO, or with status {@code 400 (Bad Request)} if the gestionInfos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gestion-infos")
    public ResponseEntity<GestionInfosDTO> createGestionInfos(@RequestBody GestionInfosDTO gestionInfosDTO) throws URISyntaxException {
        log.debug("REST request to save GestionInfos : {}", gestionInfosDTO);
        if (gestionInfosDTO.getId() != null) {
            throw new BadRequestAlertException("A new gestionInfos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GestionInfosDTO result = gestionInfosService.save(gestionInfosDTO);
        return ResponseEntity
            .created(new URI("/api/gestion-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gestion-infos/:id} : Updates an existing gestionInfos.
     *
     * @param id the id of the gestionInfosDTO to save.
     * @param gestionInfosDTO the gestionInfosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gestionInfosDTO,
     * or with status {@code 400 (Bad Request)} if the gestionInfosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gestionInfosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gestion-infos/{id}")
    public ResponseEntity<GestionInfosDTO> updateGestionInfos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GestionInfosDTO gestionInfosDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GestionInfos : {}, {}", id, gestionInfosDTO);
        if (gestionInfosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gestionInfosDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gestionInfosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GestionInfosDTO result = gestionInfosService.update(gestionInfosDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gestionInfosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /gestion-infos/:id} : Partial updates given fields of an existing gestionInfos, field will ignore if it is null
     *
     * @param id the id of the gestionInfosDTO to save.
     * @param gestionInfosDTO the gestionInfosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gestionInfosDTO,
     * or with status {@code 400 (Bad Request)} if the gestionInfosDTO is not valid,
     * or with status {@code 404 (Not Found)} if the gestionInfosDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the gestionInfosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/gestion-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GestionInfosDTO> partialUpdateGestionInfos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GestionInfosDTO gestionInfosDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GestionInfos partially : {}, {}", id, gestionInfosDTO);
        if (gestionInfosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gestionInfosDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gestionInfosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GestionInfosDTO> result = gestionInfosService.partialUpdate(gestionInfosDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gestionInfosDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /gestion-infos} : get all the gestionInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gestionInfos in body.
     */
    @GetMapping("/gestion-infos")
    public List<GestionInfosDTO> getAllGestionInfos() {
        log.debug("REST request to get all GestionInfos");
        return gestionInfosService.findAll();
    }

    /**
     * {@code GET  /gestion-infos/:id} : get the "id" gestionInfos.
     *
     * @param id the id of the gestionInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gestionInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gestion-infos/{id}")
    public ResponseEntity<GestionInfosDTO> getGestionInfos(@PathVariable Long id) {
        log.debug("REST request to get GestionInfos : {}", id);
        Optional<GestionInfosDTO> gestionInfosDTO = gestionInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gestionInfosDTO);
    }

    /**
     * {@code DELETE  /gestion-infos/:id} : delete the "id" gestionInfos.
     *
     * @param id the id of the gestionInfosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gestion-infos/{id}")
    public ResponseEntity<Void> deleteGestionInfos(@PathVariable Long id) {
        log.debug("REST request to delete GestionInfos : {}", id);
        gestionInfosService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
