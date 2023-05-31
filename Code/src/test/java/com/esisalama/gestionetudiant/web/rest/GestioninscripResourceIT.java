package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.Gestioninscrip;
import com.esisalama.gestionetudiant.repository.GestioninscripRepository;
import com.esisalama.gestionetudiant.service.dto.GestioninscripDTO;
import com.esisalama.gestionetudiant.service.mapper.GestioninscripMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link GestioninscripResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GestioninscripResourceIT {

    private static final String ENTITY_API_URL = "/api/gestioninscrips";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GestioninscripRepository gestioninscripRepository;

    @Autowired
    private GestioninscripMapper gestioninscripMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGestioninscripMockMvc;

    private Gestioninscrip gestioninscrip;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestioninscrip createEntity(EntityManager em) {
        Gestioninscrip gestioninscrip = new Gestioninscrip();
        return gestioninscrip;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestioninscrip createUpdatedEntity(EntityManager em) {
        Gestioninscrip gestioninscrip = new Gestioninscrip();
        return gestioninscrip;
    }

    @BeforeEach
    public void initTest() {
        gestioninscrip = createEntity(em);
    }

    @Test
    @Transactional
    void createGestioninscrip() throws Exception {
        int databaseSizeBeforeCreate = gestioninscripRepository.findAll().size();
        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);
        restGestioninscripMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeCreate + 1);
        Gestioninscrip testGestioninscrip = gestioninscripList.get(gestioninscripList.size() - 1);
    }

    @Test
    @Transactional
    void createGestioninscripWithExistingId() throws Exception {
        // Create the Gestioninscrip with an existing ID
        gestioninscrip.setId(1L);
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        int databaseSizeBeforeCreate = gestioninscripRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGestioninscripMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGestioninscrips() throws Exception {
        // Initialize the database
        gestioninscripRepository.saveAndFlush(gestioninscrip);

        // Get all the gestioninscripList
        restGestioninscripMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gestioninscrip.getId().intValue())));
    }

    @Test
    @Transactional
    void getGestioninscrip() throws Exception {
        // Initialize the database
        gestioninscripRepository.saveAndFlush(gestioninscrip);

        // Get the gestioninscrip
        restGestioninscripMockMvc
            .perform(get(ENTITY_API_URL_ID, gestioninscrip.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gestioninscrip.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingGestioninscrip() throws Exception {
        // Get the gestioninscrip
        restGestioninscripMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingGestioninscrip() throws Exception {
        // Initialize the database
        gestioninscripRepository.saveAndFlush(gestioninscrip);

        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();

        // Update the gestioninscrip
        Gestioninscrip updatedGestioninscrip = gestioninscripRepository.findById(gestioninscrip.getId()).get();
        // Disconnect from session so that the updates on updatedGestioninscrip are not directly saved in db
        em.detach(updatedGestioninscrip);
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(updatedGestioninscrip);

        restGestioninscripMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gestioninscripDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isOk());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
        Gestioninscrip testGestioninscrip = gestioninscripList.get(gestioninscripList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingGestioninscrip() throws Exception {
        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();
        gestioninscrip.setId(count.incrementAndGet());

        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGestioninscripMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gestioninscripDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGestioninscrip() throws Exception {
        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();
        gestioninscrip.setId(count.incrementAndGet());

        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestioninscripMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGestioninscrip() throws Exception {
        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();
        gestioninscrip.setId(count.incrementAndGet());

        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestioninscripMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGestioninscripWithPatch() throws Exception {
        // Initialize the database
        gestioninscripRepository.saveAndFlush(gestioninscrip);

        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();

        // Update the gestioninscrip using partial update
        Gestioninscrip partialUpdatedGestioninscrip = new Gestioninscrip();
        partialUpdatedGestioninscrip.setId(gestioninscrip.getId());

        restGestioninscripMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGestioninscrip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGestioninscrip))
            )
            .andExpect(status().isOk());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
        Gestioninscrip testGestioninscrip = gestioninscripList.get(gestioninscripList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateGestioninscripWithPatch() throws Exception {
        // Initialize the database
        gestioninscripRepository.saveAndFlush(gestioninscrip);

        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();

        // Update the gestioninscrip using partial update
        Gestioninscrip partialUpdatedGestioninscrip = new Gestioninscrip();
        partialUpdatedGestioninscrip.setId(gestioninscrip.getId());

        restGestioninscripMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGestioninscrip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGestioninscrip))
            )
            .andExpect(status().isOk());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
        Gestioninscrip testGestioninscrip = gestioninscripList.get(gestioninscripList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingGestioninscrip() throws Exception {
        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();
        gestioninscrip.setId(count.incrementAndGet());

        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGestioninscripMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, gestioninscripDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGestioninscrip() throws Exception {
        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();
        gestioninscrip.setId(count.incrementAndGet());

        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestioninscripMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGestioninscrip() throws Exception {
        int databaseSizeBeforeUpdate = gestioninscripRepository.findAll().size();
        gestioninscrip.setId(count.incrementAndGet());

        // Create the Gestioninscrip
        GestioninscripDTO gestioninscripDTO = gestioninscripMapper.toDto(gestioninscrip);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestioninscripMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gestioninscripDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gestioninscrip in the database
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGestioninscrip() throws Exception {
        // Initialize the database
        gestioninscripRepository.saveAndFlush(gestioninscrip);

        int databaseSizeBeforeDelete = gestioninscripRepository.findAll().size();

        // Delete the gestioninscrip
        restGestioninscripMockMvc
            .perform(delete(ENTITY_API_URL_ID, gestioninscrip.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gestioninscrip> gestioninscripList = gestioninscripRepository.findAll();
        assertThat(gestioninscripList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
