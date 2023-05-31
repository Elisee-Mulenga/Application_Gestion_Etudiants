package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.Progression;
import com.esisalama.gestionetudiant.repository.ProgressionRepository;
import com.esisalama.gestionetudiant.service.dto.ProgressionDTO;
import com.esisalama.gestionetudiant.service.mapper.ProgressionMapper;
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
 * Integration tests for the {@link ProgressionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressionResourceIT {

    private static final String ENTITY_API_URL = "/api/progressions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProgressionRepository progressionRepository;

    @Autowired
    private ProgressionMapper progressionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressionMockMvc;

    private Progression progression;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progression createEntity(EntityManager em) {
        Progression progression = new Progression();
        return progression;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progression createUpdatedEntity(EntityManager em) {
        Progression progression = new Progression();
        return progression;
    }

    @BeforeEach
    public void initTest() {
        progression = createEntity(em);
    }

    @Test
    @Transactional
    void createProgression() throws Exception {
        int databaseSizeBeforeCreate = progressionRepository.findAll().size();
        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);
        restProgressionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeCreate + 1);
        Progression testProgression = progressionList.get(progressionList.size() - 1);
    }

    @Test
    @Transactional
    void createProgressionWithExistingId() throws Exception {
        // Create the Progression with an existing ID
        progression.setId(1L);
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        int databaseSizeBeforeCreate = progressionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressions() throws Exception {
        // Initialize the database
        progressionRepository.saveAndFlush(progression);

        // Get all the progressionList
        restProgressionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progression.getId().intValue())));
    }

    @Test
    @Transactional
    void getProgression() throws Exception {
        // Initialize the database
        progressionRepository.saveAndFlush(progression);

        // Get the progression
        restProgressionMockMvc
            .perform(get(ENTITY_API_URL_ID, progression.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progression.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingProgression() throws Exception {
        // Get the progression
        restProgressionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgression() throws Exception {
        // Initialize the database
        progressionRepository.saveAndFlush(progression);

        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();

        // Update the progression
        Progression updatedProgression = progressionRepository.findById(progression.getId()).get();
        // Disconnect from session so that the updates on updatedProgression are not directly saved in db
        em.detach(updatedProgression);
        ProgressionDTO progressionDTO = progressionMapper.toDto(updatedProgression);

        restProgressionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
        Progression testProgression = progressionList.get(progressionList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingProgression() throws Exception {
        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();
        progression.setId(count.incrementAndGet());

        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgression() throws Exception {
        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();
        progression.setId(count.incrementAndGet());

        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgression() throws Exception {
        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();
        progression.setId(count.incrementAndGet());

        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(progressionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressionWithPatch() throws Exception {
        // Initialize the database
        progressionRepository.saveAndFlush(progression);

        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();

        // Update the progression using partial update
        Progression partialUpdatedProgression = new Progression();
        partialUpdatedProgression.setId(progression.getId());

        restProgressionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgression.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProgression))
            )
            .andExpect(status().isOk());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
        Progression testProgression = progressionList.get(progressionList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateProgressionWithPatch() throws Exception {
        // Initialize the database
        progressionRepository.saveAndFlush(progression);

        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();

        // Update the progression using partial update
        Progression partialUpdatedProgression = new Progression();
        partialUpdatedProgression.setId(progression.getId());

        restProgressionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgression.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProgression))
            )
            .andExpect(status().isOk());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
        Progression testProgression = progressionList.get(progressionList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingProgression() throws Exception {
        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();
        progression.setId(count.incrementAndGet());

        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgression() throws Exception {
        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();
        progression.setId(count.incrementAndGet());

        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgression() throws Exception {
        int databaseSizeBeforeUpdate = progressionRepository.findAll().size();
        progression.setId(count.incrementAndGet());

        // Create the Progression
        ProgressionDTO progressionDTO = progressionMapper.toDto(progression);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(progressionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progression in the database
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgression() throws Exception {
        // Initialize the database
        progressionRepository.saveAndFlush(progression);

        int databaseSizeBeforeDelete = progressionRepository.findAll().size();

        // Delete the progression
        restProgressionMockMvc
            .perform(delete(ENTITY_API_URL_ID, progression.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Progression> progressionList = progressionRepository.findAll();
        assertThat(progressionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
