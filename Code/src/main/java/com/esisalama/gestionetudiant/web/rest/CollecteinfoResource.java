package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.CollecteinfoRepository;
import com.esisalama.gestionetudiant.service.CollecteinfoService;
import com.esisalama.gestionetudiant.service.dto.CollecteinfoDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Collecteinfo}.
 */
@RestController
@RequestMapping("/api")
public class CollecteinfoResource {

    private final Logger log = LoggerFactory.getLogger(CollecteinfoResource.class);

    private static final String ENTITY_NAME = "collecteinfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollecteinfoService collecteinfoService;

    private final CollecteinfoRepository collecteinfoRepository;

    public CollecteinfoResource(CollecteinfoService collecteinfoService, CollecteinfoRepository collecteinfoRepository) {
        this.collecteinfoService = collecteinfoService;
        this.collecteinfoRepository = collecteinfoRepository;
    }

    /**
     * {@code POST  /collecteinfos} : Create a new collecteinfo.
     *
     * @param collecteinfoDTO the collecteinfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collecteinfoDTO, or with status {@code 400 (Bad Request)} if the collecteinfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/collecteinfos")
    public ResponseEntity<CollecteinfoDTO> createCollecteinfo(@RequestBody CollecteinfoDTO collecteinfoDTO) throws URISyntaxException {
        log.debug("REST request to save Collecteinfo : {}", collecteinfoDTO);
        if (collecteinfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new collecteinfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollecteinfoDTO result = collecteinfoService.save(collecteinfoDTO);
        return ResponseEntity
            .created(new URI("/api/collecteinfos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /collecteinfos/:id} : Updates an existing collecteinfo.
     *
     * @param id the id of the collecteinfoDTO to save.
     * @param collecteinfoDTO the collecteinfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collecteinfoDTO,
     * or with status {@code 400 (Bad Request)} if the collecteinfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collecteinfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/collecteinfos/{id}")
    public ResponseEntity<CollecteinfoDTO> updateCollecteinfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CollecteinfoDTO collecteinfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Collecteinfo : {}, {}", id, collecteinfoDTO);
        if (collecteinfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, collecteinfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!collecteinfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CollecteinfoDTO result = collecteinfoService.update(collecteinfoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collecteinfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /collecteinfos/:id} : Partial updates given fields of an existing collecteinfo, field will ignore if it is null
     *
     * @param id the id of the collecteinfoDTO to save.
     * @param collecteinfoDTO the collecteinfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collecteinfoDTO,
     * or with status {@code 400 (Bad Request)} if the collecteinfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the collecteinfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the collecteinfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/collecteinfos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CollecteinfoDTO> partialUpdateCollecteinfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CollecteinfoDTO collecteinfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Collecteinfo partially : {}, {}", id, collecteinfoDTO);
        if (collecteinfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, collecteinfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!collecteinfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CollecteinfoDTO> result = collecteinfoService.partialUpdate(collecteinfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collecteinfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /collecteinfos} : get all the collecteinfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of collecteinfos in body.
     */
    @GetMapping("/collecteinfos")
    public List<CollecteinfoDTO> getAllCollecteinfos() {
        log.debug("REST request to get all Collecteinfos");
        return collecteinfoService.findAll();
    }

    /**
     * {@code GET  /collecteinfos/:id} : get the "id" collecteinfo.
     *
     * @param id the id of the collecteinfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collecteinfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/collecteinfos/{id}")
    public ResponseEntity<CollecteinfoDTO> getCollecteinfo(@PathVariable Long id) {
        log.debug("REST request to get Collecteinfo : {}", id);
        Optional<CollecteinfoDTO> collecteinfoDTO = collecteinfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collecteinfoDTO);
    }

    /**
     * {@code DELETE  /collecteinfos/:id} : delete the "id" collecteinfo.
     *
     * @param id the id of the collecteinfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/collecteinfos/{id}")
    public ResponseEntity<Void> deleteCollecteinfo(@PathVariable Long id) {
        log.debug("REST request to delete Collecteinfo : {}", id);
        collecteinfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
