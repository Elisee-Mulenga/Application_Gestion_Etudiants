package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.Collecteinfo;
import com.esisalama.gestionetudiant.repository.CollecteinfoRepository;
import com.esisalama.gestionetudiant.service.dto.CollecteinfoDTO;
import com.esisalama.gestionetudiant.service.mapper.CollecteinfoMapper;
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
 * Integration tests for the {@link CollecteinfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CollecteinfoResourceIT {

    private static final String DEFAULT_INFOSPERSON = "AAAAAAAAAA";
    private static final String UPDATED_INFOSPERSON = "BBBBBBBBBB";

    private static final String DEFAULT_INFOSACADEM = "AAAAAAAAAA";
    private static final String UPDATED_INFOSACADEM = "BBBBBBBBBB";

    private static final String DEFAULT_INFOSADMI = "AAAAAAAAAA";
    private static final String UPDATED_INFOSADMI = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/collecteinfos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CollecteinfoRepository collecteinfoRepository;

    @Autowired
    private CollecteinfoMapper collecteinfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCollecteinfoMockMvc;

    private Collecteinfo collecteinfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Collecteinfo createEntity(EntityManager em) {
        Collecteinfo collecteinfo = new Collecteinfo()
            .infosperson(DEFAULT_INFOSPERSON)
            .infosacadem(DEFAULT_INFOSACADEM)
            .infosadmi(DEFAULT_INFOSADMI);
        return collecteinfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Collecteinfo createUpdatedEntity(EntityManager em) {
        Collecteinfo collecteinfo = new Collecteinfo()
            .infosperson(UPDATED_INFOSPERSON)
            .infosacadem(UPDATED_INFOSACADEM)
            .infosadmi(UPDATED_INFOSADMI);
        return collecteinfo;
    }

    @BeforeEach
    public void initTest() {
        collecteinfo = createEntity(em);
    }

    @Test
    @Transactional
    void createCollecteinfo() throws Exception {
        int databaseSizeBeforeCreate = collecteinfoRepository.findAll().size();
        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);
        restCollecteinfoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeCreate + 1);
        Collecteinfo testCollecteinfo = collecteinfoList.get(collecteinfoList.size() - 1);
        assertThat(testCollecteinfo.getInfosperson()).isEqualTo(DEFAULT_INFOSPERSON);
        assertThat(testCollecteinfo.getInfosacadem()).isEqualTo(DEFAULT_INFOSACADEM);
        assertThat(testCollecteinfo.getInfosadmi()).isEqualTo(DEFAULT_INFOSADMI);
    }

    @Test
    @Transactional
    void createCollecteinfoWithExistingId() throws Exception {
        // Create the Collecteinfo with an existing ID
        collecteinfo.setId(1L);
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        int databaseSizeBeforeCreate = collecteinfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollecteinfoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCollecteinfos() throws Exception {
        // Initialize the database
        collecteinfoRepository.saveAndFlush(collecteinfo);

        // Get all the collecteinfoList
        restCollecteinfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(collecteinfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].infosperson").value(hasItem(DEFAULT_INFOSPERSON)))
            .andExpect(jsonPath("$.[*].infosacadem").value(hasItem(DEFAULT_INFOSACADEM)))
            .andExpect(jsonPath("$.[*].infosadmi").value(hasItem(DEFAULT_INFOSADMI)));
    }

    @Test
    @Transactional
    void getCollecteinfo() throws Exception {
        // Initialize the database
        collecteinfoRepository.saveAndFlush(collecteinfo);

        // Get the collecteinfo
        restCollecteinfoMockMvc
            .perform(get(ENTITY_API_URL_ID, collecteinfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(collecteinfo.getId().intValue()))
            .andExpect(jsonPath("$.infosperson").value(DEFAULT_INFOSPERSON))
            .andExpect(jsonPath("$.infosacadem").value(DEFAULT_INFOSACADEM))
            .andExpect(jsonPath("$.infosadmi").value(DEFAULT_INFOSADMI));
    }

    @Test
    @Transactional
    void getNonExistingCollecteinfo() throws Exception {
        // Get the collecteinfo
        restCollecteinfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCollecteinfo() throws Exception {
        // Initialize the database
        collecteinfoRepository.saveAndFlush(collecteinfo);

        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();

        // Update the collecteinfo
        Collecteinfo updatedCollecteinfo = collecteinfoRepository.findById(collecteinfo.getId()).get();
        // Disconnect from session so that the updates on updatedCollecteinfo are not directly saved in db
        em.detach(updatedCollecteinfo);
        updatedCollecteinfo.infosperson(UPDATED_INFOSPERSON).infosacadem(UPDATED_INFOSACADEM).infosadmi(UPDATED_INFOSADMI);
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(updatedCollecteinfo);

        restCollecteinfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, collecteinfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
        Collecteinfo testCollecteinfo = collecteinfoList.get(collecteinfoList.size() - 1);
        assertThat(testCollecteinfo.getInfosperson()).isEqualTo(UPDATED_INFOSPERSON);
        assertThat(testCollecteinfo.getInfosacadem()).isEqualTo(UPDATED_INFOSACADEM);
        assertThat(testCollecteinfo.getInfosadmi()).isEqualTo(UPDATED_INFOSADMI);
    }

    @Test
    @Transactional
    void putNonExistingCollecteinfo() throws Exception {
        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();
        collecteinfo.setId(count.incrementAndGet());

        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollecteinfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, collecteinfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCollecteinfo() throws Exception {
        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();
        collecteinfo.setId(count.incrementAndGet());

        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollecteinfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCollecteinfo() throws Exception {
        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();
        collecteinfo.setId(count.incrementAndGet());

        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollecteinfoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCollecteinfoWithPatch() throws Exception {
        // Initialize the database
        collecteinfoRepository.saveAndFlush(collecteinfo);

        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();

        // Update the collecteinfo using partial update
        Collecteinfo partialUpdatedCollecteinfo = new Collecteinfo();
        partialUpdatedCollecteinfo.setId(collecteinfo.getId());

        partialUpdatedCollecteinfo.infosadmi(UPDATED_INFOSADMI);

        restCollecteinfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCollecteinfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCollecteinfo))
            )
            .andExpect(status().isOk());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
        Collecteinfo testCollecteinfo = collecteinfoList.get(collecteinfoList.size() - 1);
        assertThat(testCollecteinfo.getInfosperson()).isEqualTo(DEFAULT_INFOSPERSON);
        assertThat(testCollecteinfo.getInfosacadem()).isEqualTo(DEFAULT_INFOSACADEM);
        assertThat(testCollecteinfo.getInfosadmi()).isEqualTo(UPDATED_INFOSADMI);
    }

    @Test
    @Transactional
    void fullUpdateCollecteinfoWithPatch() throws Exception {
        // Initialize the database
        collecteinfoRepository.saveAndFlush(collecteinfo);

        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();

        // Update the collecteinfo using partial update
        Collecteinfo partialUpdatedCollecteinfo = new Collecteinfo();
        partialUpdatedCollecteinfo.setId(collecteinfo.getId());

        partialUpdatedCollecteinfo.infosperson(UPDATED_INFOSPERSON).infosacadem(UPDATED_INFOSACADEM).infosadmi(UPDATED_INFOSADMI);

        restCollecteinfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCollecteinfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCollecteinfo))
            )
            .andExpect(status().isOk());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
        Collecteinfo testCollecteinfo = collecteinfoList.get(collecteinfoList.size() - 1);
        assertThat(testCollecteinfo.getInfosperson()).isEqualTo(UPDATED_INFOSPERSON);
        assertThat(testCollecteinfo.getInfosacadem()).isEqualTo(UPDATED_INFOSACADEM);
        assertThat(testCollecteinfo.getInfosadmi()).isEqualTo(UPDATED_INFOSADMI);
    }

    @Test
    @Transactional
    void patchNonExistingCollecteinfo() throws Exception {
        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();
        collecteinfo.setId(count.incrementAndGet());

        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollecteinfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, collecteinfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCollecteinfo() throws Exception {
        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();
        collecteinfo.setId(count.incrementAndGet());

        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollecteinfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCollecteinfo() throws Exception {
        int databaseSizeBeforeUpdate = collecteinfoRepository.findAll().size();
        collecteinfo.setId(count.incrementAndGet());

        // Create the Collecteinfo
        CollecteinfoDTO collecteinfoDTO = collecteinfoMapper.toDto(collecteinfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCollecteinfoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(collecteinfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Collecteinfo in the database
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCollecteinfo() throws Exception {
        // Initialize the database
        collecteinfoRepository.saveAndFlush(collecteinfo);

        int databaseSizeBeforeDelete = collecteinfoRepository.findAll().size();

        // Delete the collecteinfo
        restCollecteinfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, collecteinfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Collecteinfo> collecteinfoList = collecteinfoRepository.findAll();
        assertThat(collecteinfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
