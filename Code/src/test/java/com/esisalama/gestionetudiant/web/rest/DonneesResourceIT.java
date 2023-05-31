package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.Donnees;
import com.esisalama.gestionetudiant.repository.DonneesRepository;
import com.esisalama.gestionetudiant.service.dto.DonneesDTO;
import com.esisalama.gestionetudiant.service.mapper.DonneesMapper;
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
 * Integration tests for the {@link DonneesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DonneesResourceIT {

    private static final String DEFAULT_UPDATEINFO = "AAAAAAAAAA";
    private static final String UPDATED_UPDATEINFO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/donnees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DonneesRepository donneesRepository;

    @Autowired
    private DonneesMapper donneesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDonneesMockMvc;

    private Donnees donnees;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Donnees createEntity(EntityManager em) {
        Donnees donnees = new Donnees().updateinfo(DEFAULT_UPDATEINFO);
        return donnees;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Donnees createUpdatedEntity(EntityManager em) {
        Donnees donnees = new Donnees().updateinfo(UPDATED_UPDATEINFO);
        return donnees;
    }

    @BeforeEach
    public void initTest() {
        donnees = createEntity(em);
    }

    @Test
    @Transactional
    void createDonnees() throws Exception {
        int databaseSizeBeforeCreate = donneesRepository.findAll().size();
        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);
        restDonneesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(donneesDTO)))
            .andExpect(status().isCreated());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeCreate + 1);
        Donnees testDonnees = donneesList.get(donneesList.size() - 1);
        assertThat(testDonnees.getUpdateinfo()).isEqualTo(DEFAULT_UPDATEINFO);
    }

    @Test
    @Transactional
    void createDonneesWithExistingId() throws Exception {
        // Create the Donnees with an existing ID
        donnees.setId(1L);
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        int databaseSizeBeforeCreate = donneesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonneesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(donneesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDonnees() throws Exception {
        // Initialize the database
        donneesRepository.saveAndFlush(donnees);

        // Get all the donneesList
        restDonneesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donnees.getId().intValue())))
            .andExpect(jsonPath("$.[*].updateinfo").value(hasItem(DEFAULT_UPDATEINFO)));
    }

    @Test
    @Transactional
    void getDonnees() throws Exception {
        // Initialize the database
        donneesRepository.saveAndFlush(donnees);

        // Get the donnees
        restDonneesMockMvc
            .perform(get(ENTITY_API_URL_ID, donnees.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(donnees.getId().intValue()))
            .andExpect(jsonPath("$.updateinfo").value(DEFAULT_UPDATEINFO));
    }

    @Test
    @Transactional
    void getNonExistingDonnees() throws Exception {
        // Get the donnees
        restDonneesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDonnees() throws Exception {
        // Initialize the database
        donneesRepository.saveAndFlush(donnees);

        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();

        // Update the donnees
        Donnees updatedDonnees = donneesRepository.findById(donnees.getId()).get();
        // Disconnect from session so that the updates on updatedDonnees are not directly saved in db
        em.detach(updatedDonnees);
        updatedDonnees.updateinfo(UPDATED_UPDATEINFO);
        DonneesDTO donneesDTO = donneesMapper.toDto(updatedDonnees);

        restDonneesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, donneesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(donneesDTO))
            )
            .andExpect(status().isOk());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
        Donnees testDonnees = donneesList.get(donneesList.size() - 1);
        assertThat(testDonnees.getUpdateinfo()).isEqualTo(UPDATED_UPDATEINFO);
    }

    @Test
    @Transactional
    void putNonExistingDonnees() throws Exception {
        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();
        donnees.setId(count.incrementAndGet());

        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonneesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, donneesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(donneesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDonnees() throws Exception {
        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();
        donnees.setId(count.incrementAndGet());

        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonneesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(donneesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDonnees() throws Exception {
        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();
        donnees.setId(count.incrementAndGet());

        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonneesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(donneesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDonneesWithPatch() throws Exception {
        // Initialize the database
        donneesRepository.saveAndFlush(donnees);

        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();

        // Update the donnees using partial update
        Donnees partialUpdatedDonnees = new Donnees();
        partialUpdatedDonnees.setId(donnees.getId());

        partialUpdatedDonnees.updateinfo(UPDATED_UPDATEINFO);

        restDonneesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDonnees.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDonnees))
            )
            .andExpect(status().isOk());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
        Donnees testDonnees = donneesList.get(donneesList.size() - 1);
        assertThat(testDonnees.getUpdateinfo()).isEqualTo(UPDATED_UPDATEINFO);
    }

    @Test
    @Transactional
    void fullUpdateDonneesWithPatch() throws Exception {
        // Initialize the database
        donneesRepository.saveAndFlush(donnees);

        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();

        // Update the donnees using partial update
        Donnees partialUpdatedDonnees = new Donnees();
        partialUpdatedDonnees.setId(donnees.getId());

        partialUpdatedDonnees.updateinfo(UPDATED_UPDATEINFO);

        restDonneesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDonnees.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDonnees))
            )
            .andExpect(status().isOk());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
        Donnees testDonnees = donneesList.get(donneesList.size() - 1);
        assertThat(testDonnees.getUpdateinfo()).isEqualTo(UPDATED_UPDATEINFO);
    }

    @Test
    @Transactional
    void patchNonExistingDonnees() throws Exception {
        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();
        donnees.setId(count.incrementAndGet());

        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonneesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, donneesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(donneesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDonnees() throws Exception {
        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();
        donnees.setId(count.incrementAndGet());

        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonneesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(donneesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDonnees() throws Exception {
        int databaseSizeBeforeUpdate = donneesRepository.findAll().size();
        donnees.setId(count.incrementAndGet());

        // Create the Donnees
        DonneesDTO donneesDTO = donneesMapper.toDto(donnees);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonneesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(donneesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Donnees in the database
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDonnees() throws Exception {
        // Initialize the database
        donneesRepository.saveAndFlush(donnees);

        int databaseSizeBeforeDelete = donneesRepository.findAll().size();

        // Delete the donnees
        restDonneesMockMvc
            .perform(delete(ENTITY_API_URL_ID, donnees.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Donnees> donneesList = donneesRepository.findAll();
        assertThat(donneesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
