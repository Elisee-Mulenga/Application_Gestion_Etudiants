package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.DonneesRepository;
import com.esisalama.gestionetudiant.service.DonneesService;
import com.esisalama.gestionetudiant.service.dto.DonneesDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Donnees}.
 */
@RestController
@RequestMapping("/api")
public class DonneesResource {

    private final Logger log = LoggerFactory.getLogger(DonneesResource.class);

    private static final String ENTITY_NAME = "donnees";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DonneesService donneesService;

    private final DonneesRepository donneesRepository;

    public DonneesResource(DonneesService donneesService, DonneesRepository donneesRepository) {
        this.donneesService = donneesService;
        this.donneesRepository = donneesRepository;
    }

    /**
     * {@code POST  /donnees} : Create a new donnees.
     *
     * @param donneesDTO the donneesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new donneesDTO, or with status {@code 400 (Bad Request)} if the donnees has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/donnees")
    public ResponseEntity<DonneesDTO> createDonnees(@RequestBody DonneesDTO donneesDTO) throws URISyntaxException {
        log.debug("REST request to save Donnees : {}", donneesDTO);
        if (donneesDTO.getId() != null) {
            throw new BadRequestAlertException("A new donnees cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonneesDTO result = donneesService.save(donneesDTO);
        return ResponseEntity
            .created(new URI("/api/donnees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /donnees/:id} : Updates an existing donnees.
     *
     * @param id the id of the donneesDTO to save.
     * @param donneesDTO the donneesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donneesDTO,
     * or with status {@code 400 (Bad Request)} if the donneesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the donneesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/donnees/{id}")
    public ResponseEntity<DonneesDTO> updateDonnees(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DonneesDTO donneesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Donnees : {}, {}", id, donneesDTO);
        if (donneesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, donneesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!donneesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DonneesDTO result = donneesService.update(donneesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, donneesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /donnees/:id} : Partial updates given fields of an existing donnees, field will ignore if it is null
     *
     * @param id the id of the donneesDTO to save.
     * @param donneesDTO the donneesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donneesDTO,
     * or with status {@code 400 (Bad Request)} if the donneesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the donneesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the donneesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/donnees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DonneesDTO> partialUpdateDonnees(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DonneesDTO donneesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Donnees partially : {}, {}", id, donneesDTO);
        if (donneesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, donneesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!donneesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DonneesDTO> result = donneesService.partialUpdate(donneesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, donneesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /donnees} : get all the donnees.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of donnees in body.
     */
    @GetMapping("/donnees")
    public List<DonneesDTO> getAllDonnees() {
        log.debug("REST request to get all Donnees");
        return donneesService.findAll();
    }

    /**
     * {@code GET  /donnees/:id} : get the "id" donnees.
     *
     * @param id the id of the donneesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the donneesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/donnees/{id}")
    public ResponseEntity<DonneesDTO> getDonnees(@PathVariable Long id) {
        log.debug("REST request to get Donnees : {}", id);
        Optional<DonneesDTO> donneesDTO = donneesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donneesDTO);
    }

    /**
     * {@code DELETE  /donnees/:id} : delete the "id" donnees.
     *
     * @param id the id of the donneesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/donnees/{id}")
    public ResponseEntity<Void> deleteDonnees(@PathVariable Long id) {
        log.debug("REST request to delete Donnees : {}", id);
        donneesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
