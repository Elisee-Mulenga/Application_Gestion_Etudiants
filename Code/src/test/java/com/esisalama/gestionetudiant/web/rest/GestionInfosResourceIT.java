package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.GestionInfos;
import com.esisalama.gestionetudiant.repository.GestionInfosRepository;
import com.esisalama.gestionetudiant.service.dto.GestionInfosDTO;
import com.esisalama.gestionetudiant.service.mapper.GestionInfosMapper;
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
 * Integration tests for the {@link GestionInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GestionInfosResourceIT {

    private static final String DEFAULT_INFOSPERSON = "AAAAAAAAAA";
    private static final String UPDATED_INFOSPERSON = "BBBBBBBBBB";

    private static final String DEFAULT_INFOSACADEM = "AAAAAAAAAA";
    private static final String UPDATED_INFOSACADEM = "BBBBBBBBBB";

    private static final String DEFAULT_INFOSFINANCE = "AAAAAAAAAA";
    private static final String UPDATED_INFOSFINANCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gestion-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GestionInfosRepository gestionInfosRepository;

    @Autowired
    private GestionInfosMapper gestionInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGestionInfosMockMvc;

    private GestionInfos gestionInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GestionInfos createEntity(EntityManager em) {
        GestionInfos gestionInfos = new GestionInfos()
            .infosperson(DEFAULT_INFOSPERSON)
            .infosacadem(DEFAULT_INFOSACADEM)
            .infosfinance(DEFAULT_INFOSFINANCE);
        return gestionInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GestionInfos createUpdatedEntity(EntityManager em) {
        GestionInfos gestionInfos = new GestionInfos()
            .infosperson(UPDATED_INFOSPERSON)
            .infosacadem(UPDATED_INFOSACADEM)
            .infosfinance(UPDATED_INFOSFINANCE);
        return gestionInfos;
    }

    @BeforeEach
    public void initTest() {
        gestionInfos = createEntity(em);
    }

    @Test
    @Transactional
    void createGestionInfos() throws Exception {
        int databaseSizeBeforeCreate = gestionInfosRepository.findAll().size();
        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);
        restGestionInfosMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeCreate + 1);
        GestionInfos testGestionInfos = gestionInfosList.get(gestionInfosList.size() - 1);
        assertThat(testGestionInfos.getInfosperson()).isEqualTo(DEFAULT_INFOSPERSON);
        assertThat(testGestionInfos.getInfosacadem()).isEqualTo(DEFAULT_INFOSACADEM);
        assertThat(testGestionInfos.getInfosfinance()).isEqualTo(DEFAULT_INFOSFINANCE);
    }

    @Test
    @Transactional
    void createGestionInfosWithExistingId() throws Exception {
        // Create the GestionInfos with an existing ID
        gestionInfos.setId(1L);
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        int databaseSizeBeforeCreate = gestionInfosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGestionInfosMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGestionInfos() throws Exception {
        // Initialize the database
        gestionInfosRepository.saveAndFlush(gestionInfos);

        // Get all the gestionInfosList
        restGestionInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gestionInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].infosperson").value(hasItem(DEFAULT_INFOSPERSON)))
            .andExpect(jsonPath("$.[*].infosacadem").value(hasItem(DEFAULT_INFOSACADEM)))
            .andExpect(jsonPath("$.[*].infosfinance").value(hasItem(DEFAULT_INFOSFINANCE)));
    }

    @Test
    @Transactional
    void getGestionInfos() throws Exception {
        // Initialize the database
        gestionInfosRepository.saveAndFlush(gestionInfos);

        // Get the gestionInfos
        restGestionInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, gestionInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gestionInfos.getId().intValue()))
            .andExpect(jsonPath("$.infosperson").value(DEFAULT_INFOSPERSON))
            .andExpect(jsonPath("$.infosacadem").value(DEFAULT_INFOSACADEM))
            .andExpect(jsonPath("$.infosfinance").value(DEFAULT_INFOSFINANCE));
    }

    @Test
    @Transactional
    void getNonExistingGestionInfos() throws Exception {
        // Get the gestionInfos
        restGestionInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingGestionInfos() throws Exception {
        // Initialize the database
        gestionInfosRepository.saveAndFlush(gestionInfos);

        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();

        // Update the gestionInfos
        GestionInfos updatedGestionInfos = gestionInfosRepository.findById(gestionInfos.getId()).get();
        // Disconnect from session so that the updates on updatedGestionInfos are not directly saved in db
        em.detach(updatedGestionInfos);
        updatedGestionInfos.infosperson(UPDATED_INFOSPERSON).infosacadem(UPDATED_INFOSACADEM).infosfinance(UPDATED_INFOSFINANCE);
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(updatedGestionInfos);

        restGestionInfosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gestionInfosDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isOk());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
        GestionInfos testGestionInfos = gestionInfosList.get(gestionInfosList.size() - 1);
        assertThat(testGestionInfos.getInfosperson()).isEqualTo(UPDATED_INFOSPERSON);
        assertThat(testGestionInfos.getInfosacadem()).isEqualTo(UPDATED_INFOSACADEM);
        assertThat(testGestionInfos.getInfosfinance()).isEqualTo(UPDATED_INFOSFINANCE);
    }

    @Test
    @Transactional
    void putNonExistingGestionInfos() throws Exception {
        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();
        gestionInfos.setId(count.incrementAndGet());

        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGestionInfosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gestionInfosDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGestionInfos() throws Exception {
        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();
        gestionInfos.setId(count.incrementAndGet());

        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestionInfosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGestionInfos() throws Exception {
        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();
        gestionInfos.setId(count.incrementAndGet());

        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestionInfosMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGestionInfosWithPatch() throws Exception {
        // Initialize the database
        gestionInfosRepository.saveAndFlush(gestionInfos);

        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();

        // Update the gestionInfos using partial update
        GestionInfos partialUpdatedGestionInfos = new GestionInfos();
        partialUpdatedGestionInfos.setId(gestionInfos.getId());

        restGestionInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGestionInfos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGestionInfos))
            )
            .andExpect(status().isOk());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
        GestionInfos testGestionInfos = gestionInfosList.get(gestionInfosList.size() - 1);
        assertThat(testGestionInfos.getInfosperson()).isEqualTo(DEFAULT_INFOSPERSON);
        assertThat(testGestionInfos.getInfosacadem()).isEqualTo(DEFAULT_INFOSACADEM);
        assertThat(testGestionInfos.getInfosfinance()).isEqualTo(DEFAULT_INFOSFINANCE);
    }

    @Test
    @Transactional
    void fullUpdateGestionInfosWithPatch() throws Exception {
        // Initialize the database
        gestionInfosRepository.saveAndFlush(gestionInfos);

        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();

        // Update the gestionInfos using partial update
        GestionInfos partialUpdatedGestionInfos = new GestionInfos();
        partialUpdatedGestionInfos.setId(gestionInfos.getId());

        partialUpdatedGestionInfos.infosperson(UPDATED_INFOSPERSON).infosacadem(UPDATED_INFOSACADEM).infosfinance(UPDATED_INFOSFINANCE);

        restGestionInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGestionInfos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGestionInfos))
            )
            .andExpect(status().isOk());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
        GestionInfos testGestionInfos = gestionInfosList.get(gestionInfosList.size() - 1);
        assertThat(testGestionInfos.getInfosperson()).isEqualTo(UPDATED_INFOSPERSON);
        assertThat(testGestionInfos.getInfosacadem()).isEqualTo(UPDATED_INFOSACADEM);
        assertThat(testGestionInfos.getInfosfinance()).isEqualTo(UPDATED_INFOSFINANCE);
    }

    @Test
    @Transactional
    void patchNonExistingGestionInfos() throws Exception {
        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();
        gestionInfos.setId(count.incrementAndGet());

        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGestionInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, gestionInfosDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGestionInfos() throws Exception {
        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();
        gestionInfos.setId(count.incrementAndGet());

        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestionInfosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGestionInfos() throws Exception {
        int databaseSizeBeforeUpdate = gestionInfosRepository.findAll().size();
        gestionInfos.setId(count.incrementAndGet());

        // Create the GestionInfos
        GestionInfosDTO gestionInfosDTO = gestionInfosMapper.toDto(gestionInfos);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGestionInfosMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gestionInfosDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GestionInfos in the database
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGestionInfos() throws Exception {
        // Initialize the database
        gestionInfosRepository.saveAndFlush(gestionInfos);

        int databaseSizeBeforeDelete = gestionInfosRepository.findAll().size();

        // Delete the gestionInfos
        restGestionInfosMockMvc
            .perform(delete(ENTITY_API_URL_ID, gestionInfos.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GestionInfos> gestionInfosList = gestionInfosRepository.findAll();
        assertThat(gestionInfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
