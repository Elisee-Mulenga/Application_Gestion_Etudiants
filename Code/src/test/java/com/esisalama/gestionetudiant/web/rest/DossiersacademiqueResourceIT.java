package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.Dossiersacademique;
import com.esisalama.gestionetudiant.repository.DossiersacademiqueRepository;
import com.esisalama.gestionetudiant.service.dto.DossiersacademiqueDTO;
import com.esisalama.gestionetudiant.service.mapper.DossiersacademiqueMapper;
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
 * Integration tests for the {@link DossiersacademiqueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DossiersacademiqueResourceIT {

    private static final String DEFAULT_RELEVERCOTES = "AAAAAAAAAA";
    private static final String UPDATED_RELEVERCOTES = "BBBBBBBBBB";

    private static final String DEFAULT_BORDEREAU = "AAAAAAAAAA";
    private static final String UPDATED_BORDEREAU = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dossiersacademiques";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DossiersacademiqueRepository dossiersacademiqueRepository;

    @Autowired
    private DossiersacademiqueMapper dossiersacademiqueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDossiersacademiqueMockMvc;

    private Dossiersacademique dossiersacademique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dossiersacademique createEntity(EntityManager em) {
        Dossiersacademique dossiersacademique = new Dossiersacademique().relevercotes(DEFAULT_RELEVERCOTES).bordereau(DEFAULT_BORDEREAU);
        return dossiersacademique;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dossiersacademique createUpdatedEntity(EntityManager em) {
        Dossiersacademique dossiersacademique = new Dossiersacademique().relevercotes(UPDATED_RELEVERCOTES).bordereau(UPDATED_BORDEREAU);
        return dossiersacademique;
    }

    @BeforeEach
    public void initTest() {
        dossiersacademique = createEntity(em);
    }

    @Test
    @Transactional
    void createDossiersacademique() throws Exception {
        int databaseSizeBeforeCreate = dossiersacademiqueRepository.findAll().size();
        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);
        restDossiersacademiqueMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeCreate + 1);
        Dossiersacademique testDossiersacademique = dossiersacademiqueList.get(dossiersacademiqueList.size() - 1);
        assertThat(testDossiersacademique.getRelevercotes()).isEqualTo(DEFAULT_RELEVERCOTES);
        assertThat(testDossiersacademique.getBordereau()).isEqualTo(DEFAULT_BORDEREAU);
    }

    @Test
    @Transactional
    void createDossiersacademiqueWithExistingId() throws Exception {
        // Create the Dossiersacademique with an existing ID
        dossiersacademique.setId(1L);
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        int databaseSizeBeforeCreate = dossiersacademiqueRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDossiersacademiqueMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDossiersacademiques() throws Exception {
        // Initialize the database
        dossiersacademiqueRepository.saveAndFlush(dossiersacademique);

        // Get all the dossiersacademiqueList
        restDossiersacademiqueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossiersacademique.getId().intValue())))
            .andExpect(jsonPath("$.[*].relevercotes").value(hasItem(DEFAULT_RELEVERCOTES)))
            .andExpect(jsonPath("$.[*].bordereau").value(hasItem(DEFAULT_BORDEREAU)));
    }

    @Test
    @Transactional
    void getDossiersacademique() throws Exception {
        // Initialize the database
        dossiersacademiqueRepository.saveAndFlush(dossiersacademique);

        // Get the dossiersacademique
        restDossiersacademiqueMockMvc
            .perform(get(ENTITY_API_URL_ID, dossiersacademique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dossiersacademique.getId().intValue()))
            .andExpect(jsonPath("$.relevercotes").value(DEFAULT_RELEVERCOTES))
            .andExpect(jsonPath("$.bordereau").value(DEFAULT_BORDEREAU));
    }

    @Test
    @Transactional
    void getNonExistingDossiersacademique() throws Exception {
        // Get the dossiersacademique
        restDossiersacademiqueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDossiersacademique() throws Exception {
        // Initialize the database
        dossiersacademiqueRepository.saveAndFlush(dossiersacademique);

        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();

        // Update the dossiersacademique
        Dossiersacademique updatedDossiersacademique = dossiersacademiqueRepository.findById(dossiersacademique.getId()).get();
        // Disconnect from session so that the updates on updatedDossiersacademique are not directly saved in db
        em.detach(updatedDossiersacademique);
        updatedDossiersacademique.relevercotes(UPDATED_RELEVERCOTES).bordereau(UPDATED_BORDEREAU);
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(updatedDossiersacademique);

        restDossiersacademiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dossiersacademiqueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isOk());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
        Dossiersacademique testDossiersacademique = dossiersacademiqueList.get(dossiersacademiqueList.size() - 1);
        assertThat(testDossiersacademique.getRelevercotes()).isEqualTo(UPDATED_RELEVERCOTES);
        assertThat(testDossiersacademique.getBordereau()).isEqualTo(UPDATED_BORDEREAU);
    }

    @Test
    @Transactional
    void putNonExistingDossiersacademique() throws Exception {
        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();
        dossiersacademique.setId(count.incrementAndGet());

        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDossiersacademiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dossiersacademiqueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDossiersacademique() throws Exception {
        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();
        dossiersacademique.setId(count.incrementAndGet());

        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDossiersacademiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDossiersacademique() throws Exception {
        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();
        dossiersacademique.setId(count.incrementAndGet());

        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDossiersacademiqueMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDossiersacademiqueWithPatch() throws Exception {
        // Initialize the database
        dossiersacademiqueRepository.saveAndFlush(dossiersacademique);

        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();

        // Update the dossiersacademique using partial update
        Dossiersacademique partialUpdatedDossiersacademique = new Dossiersacademique();
        partialUpdatedDossiersacademique.setId(dossiersacademique.getId());

        partialUpdatedDossiersacademique.bordereau(UPDATED_BORDEREAU);

        restDossiersacademiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDossiersacademique.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDossiersacademique))
            )
            .andExpect(status().isOk());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
        Dossiersacademique testDossiersacademique = dossiersacademiqueList.get(dossiersacademiqueList.size() - 1);
        assertThat(testDossiersacademique.getRelevercotes()).isEqualTo(DEFAULT_RELEVERCOTES);
        assertThat(testDossiersacademique.getBordereau()).isEqualTo(UPDATED_BORDEREAU);
    }

    @Test
    @Transactional
    void fullUpdateDossiersacademiqueWithPatch() throws Exception {
        // Initialize the database
        dossiersacademiqueRepository.saveAndFlush(dossiersacademique);

        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();

        // Update the dossiersacademique using partial update
        Dossiersacademique partialUpdatedDossiersacademique = new Dossiersacademique();
        partialUpdatedDossiersacademique.setId(dossiersacademique.getId());

        partialUpdatedDossiersacademique.relevercotes(UPDATED_RELEVERCOTES).bordereau(UPDATED_BORDEREAU);

        restDossiersacademiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDossiersacademique.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDossiersacademique))
            )
            .andExpect(status().isOk());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
        Dossiersacademique testDossiersacademique = dossiersacademiqueList.get(dossiersacademiqueList.size() - 1);
        assertThat(testDossiersacademique.getRelevercotes()).isEqualTo(UPDATED_RELEVERCOTES);
        assertThat(testDossiersacademique.getBordereau()).isEqualTo(UPDATED_BORDEREAU);
    }

    @Test
    @Transactional
    void patchNonExistingDossiersacademique() throws Exception {
        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();
        dossiersacademique.setId(count.incrementAndGet());

        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDossiersacademiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dossiersacademiqueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDossiersacademique() throws Exception {
        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();
        dossiersacademique.setId(count.incrementAndGet());

        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDossiersacademiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDossiersacademique() throws Exception {
        int databaseSizeBeforeUpdate = dossiersacademiqueRepository.findAll().size();
        dossiersacademique.setId(count.incrementAndGet());

        // Create the Dossiersacademique
        DossiersacademiqueDTO dossiersacademiqueDTO = dossiersacademiqueMapper.toDto(dossiersacademique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDossiersacademiqueMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dossiersacademiqueDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Dossiersacademique in the database
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDossiersacademique() throws Exception {
        // Initialize the database
        dossiersacademiqueRepository.saveAndFlush(dossiersacademique);

        int databaseSizeBeforeDelete = dossiersacademiqueRepository.findAll().size();

        // Delete the dossiersacademique
        restDossiersacademiqueMockMvc
            .perform(delete(ENTITY_API_URL_ID, dossiersacademique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dossiersacademique> dossiersacademiqueList = dossiersacademiqueRepository.findAll();
        assertThat(dossiersacademiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
