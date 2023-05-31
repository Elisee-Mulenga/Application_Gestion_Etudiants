package com.esisalama.gestionetudiant.web.rest;

import com.esisalama.gestionetudiant.repository.ProfesseurRepository;
import com.esisalama.gestionetudiant.service.ProfesseurService;
import com.esisalama.gestionetudiant.service.dto.ProfesseurDTO;
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
 * REST controller for managing {@link com.esisalama.gestionetudiant.domain.Professeur}.
 */
@RestController
@RequestMapping("/api")
public class ProfesseurResource {

    private final Logger log = LoggerFactory.getLogger(ProfesseurResource.class);

    private static final String ENTITY_NAME = "professeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfesseurService professeurService;

    private final ProfesseurRepository professeurRepository;

    public ProfesseurResource(ProfesseurService professeurService, ProfesseurRepository professeurRepository) {
        this.professeurService = professeurService;
        this.professeurRepository = professeurRepository;
    }

    /**
     * {@code POST  /professeurs} : Create a new professeur.
     *
     * @param professeurDTO the professeurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new professeurDTO, or with status {@code 400 (Bad Request)} if the professeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/professeurs")
    public ResponseEntity<ProfesseurDTO> createProfesseur(@RequestBody ProfesseurDTO professeurDTO) throws URISyntaxException {
        log.debug("REST request to save Professeur : {}", professeurDTO);
        if (professeurDTO.getId() != null) {
            throw new BadRequestAlertException("A new professeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfesseurDTO result = professeurService.save(professeurDTO);
        return ResponseEntity
            .created(new URI("/api/professeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /professeurs/:id} : Updates an existing professeur.
     *
     * @param id the id of the professeurDTO to save.
     * @param professeurDTO the professeurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated professeurDTO,
     * or with status {@code 400 (Bad Request)} if the professeurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the professeurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/professeurs/{id}")
    public ResponseEntity<ProfesseurDTO> updateProfesseur(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProfesseurDTO professeurDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Professeur : {}, {}", id, professeurDTO);
        if (professeurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, professeurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!professeurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProfesseurDTO result = professeurService.update(professeurDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, professeurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /professeurs/:id} : Partial updates given fields of an existing professeur, field will ignore if it is null
     *
     * @param id the id of the professeurDTO to save.
     * @param professeurDTO the professeurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated professeurDTO,
     * or with status {@code 400 (Bad Request)} if the professeurDTO is not valid,
     * or with status {@code 404 (Not Found)} if the professeurDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the professeurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/professeurs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProfesseurDTO> partialUpdateProfesseur(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProfesseurDTO professeurDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Professeur partially : {}, {}", id, professeurDTO);
        if (professeurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, professeurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!professeurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProfesseurDTO> result = professeurService.partialUpdate(professeurDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, professeurDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /professeurs} : get all the professeurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of professeurs in body.
     */
    @GetMapping("/professeurs")
    public List<ProfesseurDTO> getAllProfesseurs() {
        log.debug("REST request to get all Professeurs");
        return professeurService.findAll();
    }

    /**
     * {@code GET  /professeurs/:id} : get the "id" professeur.
     *
     * @param id the id of the professeurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the professeurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/professeurs/{id}")
    public ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable Long id) {
        log.debug("REST request to get Professeur : {}", id);
        Optional<ProfesseurDTO> professeurDTO = professeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(professeurDTO);
    }

    /**
     * {@code DELETE  /professeurs/:id} : delete the "id" professeur.
     *
     * @param id the id of the professeurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/professeurs/{id}")
    public ResponseEntity<Void> deleteProfesseur(@PathVariable Long id) {
        log.debug("REST request to delete Professeur : {}", id);
        professeurService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
