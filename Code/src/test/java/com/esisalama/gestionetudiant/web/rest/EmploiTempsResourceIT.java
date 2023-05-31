package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.EmploiTemps;
import com.esisalama.gestionetudiant.repository.EmploiTempsRepository;
import com.esisalama.gestionetudiant.service.dto.EmploiTempsDTO;
import com.esisalama.gestionetudiant.service.mapper.EmploiTempsMapper;
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
 * Integration tests for the {@link EmploiTempsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmploiTempsResourceIT {

    private static final String DEFAULT_COURS = "AAAAAAAAAA";
    private static final String UPDATED_COURS = "BBBBBBBBBB";

    private static final String DEFAULT_SEMESTRE = "AAAAAAAAAA";
    private static final String UPDATED_SEMESTRE = "BBBBBBBBBB";

    private static final String DEFAULT_TIMESTRE = "AAAAAAAAAA";
    private static final String UPDATED_TIMESTRE = "BBBBBBBBBB";

    private static final String DEFAULT_HORAIRECOURS = "AAAAAAAAAA";
    private static final String UPDATED_HORAIRECOURS = "BBBBBBBBBB";

    private static final String DEFAULT_HORAIREEXAM = "AAAAAAAAAA";
    private static final String UPDATED_HORAIREEXAM = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/emploi-temps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EmploiTempsRepository emploiTempsRepository;

    @Autowired
    private EmploiTempsMapper emploiTempsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmploiTempsMockMvc;

    private EmploiTemps emploiTemps;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmploiTemps createEntity(EntityManager em) {
        EmploiTemps emploiTemps = new EmploiTemps()
            .cours(DEFAULT_COURS)
            .semestre(DEFAULT_SEMESTRE)
            .timestre(DEFAULT_TIMESTRE)
            .horairecours(DEFAULT_HORAIRECOURS)
            .horaireexam(DEFAULT_HORAIREEXAM)
            .activite(DEFAULT_ACTIVITE);
        return emploiTemps;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmploiTemps createUpdatedEntity(EntityManager em) {
        EmploiTemps emploiTemps = new EmploiTemps()
            .cours(UPDATED_COURS)
            .semestre(UPDATED_SEMESTRE)
            .timestre(UPDATED_TIMESTRE)
            .horairecours(UPDATED_HORAIRECOURS)
            .horaireexam(UPDATED_HORAIREEXAM)
            .activite(UPDATED_ACTIVITE);
        return emploiTemps;
    }

    @BeforeEach
    public void initTest() {
        emploiTemps = createEntity(em);
    }

    @Test
    @Transactional
    void createEmploiTemps() throws Exception {
        int databaseSizeBeforeCreate = emploiTempsRepository.findAll().size();
        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);
        restEmploiTempsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeCreate + 1);
        EmploiTemps testEmploiTemps = emploiTempsList.get(emploiTempsList.size() - 1);
        assertThat(testEmploiTemps.getCours()).isEqualTo(DEFAULT_COURS);
        assertThat(testEmploiTemps.getSemestre()).isEqualTo(DEFAULT_SEMESTRE);
        assertThat(testEmploiTemps.getTimestre()).isEqualTo(DEFAULT_TIMESTRE);
        assertThat(testEmploiTemps.getHorairecours()).isEqualTo(DEFAULT_HORAIRECOURS);
        assertThat(testEmploiTemps.getHoraireexam()).isEqualTo(DEFAULT_HORAIREEXAM);
        assertThat(testEmploiTemps.getActivite()).isEqualTo(DEFAULT_ACTIVITE);
    }

    @Test
    @Transactional
    void createEmploiTempsWithExistingId() throws Exception {
        // Create the EmploiTemps with an existing ID
        emploiTemps.setId(1L);
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        int databaseSizeBeforeCreate = emploiTempsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmploiTempsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmploiTemps() throws Exception {
        // Initialize the database
        emploiTempsRepository.saveAndFlush(emploiTemps);

        // Get all the emploiTempsList
        restEmploiTempsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emploiTemps.getId().intValue())))
            .andExpect(jsonPath("$.[*].cours").value(hasItem(DEFAULT_COURS)))
            .andExpect(jsonPath("$.[*].semestre").value(hasItem(DEFAULT_SEMESTRE)))
            .andExpect(jsonPath("$.[*].timestre").value(hasItem(DEFAULT_TIMESTRE)))
            .andExpect(jsonPath("$.[*].horairecours").value(hasItem(DEFAULT_HORAIRECOURS)))
            .andExpect(jsonPath("$.[*].horaireexam").value(hasItem(DEFAULT_HORAIREEXAM)))
            .andExpect(jsonPath("$.[*].activite").value(hasItem(DEFAULT_ACTIVITE)));
    }

    @Test
    @Transactional
    void getEmploiTemps() throws Exception {
        // Initialize the database
        emploiTempsRepository.saveAndFlush(emploiTemps);

        // Get the emploiTemps
        restEmploiTempsMockMvc
            .perform(get(ENTITY_API_URL_ID, emploiTemps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emploiTemps.getId().intValue()))
            .andExpect(jsonPath("$.cours").value(DEFAULT_COURS))
            .andExpect(jsonPath("$.semestre").value(DEFAULT_SEMESTRE))
            .andExpect(jsonPath("$.timestre").value(DEFAULT_TIMESTRE))
            .andExpect(jsonPath("$.horairecours").value(DEFAULT_HORAIRECOURS))
            .andExpect(jsonPath("$.horaireexam").value(DEFAULT_HORAIREEXAM))
            .andExpect(jsonPath("$.activite").value(DEFAULT_ACTIVITE));
    }

    @Test
    @Transactional
    void getNonExistingEmploiTemps() throws Exception {
        // Get the emploiTemps
        restEmploiTempsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEmploiTemps() throws Exception {
        // Initialize the database
        emploiTempsRepository.saveAndFlush(emploiTemps);

        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();

        // Update the emploiTemps
        EmploiTemps updatedEmploiTemps = emploiTempsRepository.findById(emploiTemps.getId()).get();
        // Disconnect from session so that the updates on updatedEmploiTemps are not directly saved in db
        em.detach(updatedEmploiTemps);
        updatedEmploiTemps
            .cours(UPDATED_COURS)
            .semestre(UPDATED_SEMESTRE)
            .timestre(UPDATED_TIMESTRE)
            .horairecours(UPDATED_HORAIRECOURS)
            .horaireexam(UPDATED_HORAIREEXAM)
            .activite(UPDATED_ACTIVITE);
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(updatedEmploiTemps);

        restEmploiTempsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, emploiTempsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isOk());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
        EmploiTemps testEmploiTemps = emploiTempsList.get(emploiTempsList.size() - 1);
        assertThat(testEmploiTemps.getCours()).isEqualTo(UPDATED_COURS);
        assertThat(testEmploiTemps.getSemestre()).isEqualTo(UPDATED_SEMESTRE);
        assertThat(testEmploiTemps.getTimestre()).isEqualTo(UPDATED_TIMESTRE);
        assertThat(testEmploiTemps.getHorairecours()).isEqualTo(UPDATED_HORAIRECOURS);
        assertThat(testEmploiTemps.getHoraireexam()).isEqualTo(UPDATED_HORAIREEXAM);
        assertThat(testEmploiTemps.getActivite()).isEqualTo(UPDATED_ACTIVITE);
    }

    @Test
    @Transactional
    void putNonExistingEmploiTemps() throws Exception {
        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();
        emploiTemps.setId(count.incrementAndGet());

        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmploiTempsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, emploiTempsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmploiTemps() throws Exception {
        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();
        emploiTemps.setId(count.incrementAndGet());

        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmploiTempsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmploiTemps() throws Exception {
        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();
        emploiTemps.setId(count.incrementAndGet());

        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmploiTempsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmploiTempsWithPatch() throws Exception {
        // Initialize the database
        emploiTempsRepository.saveAndFlush(emploiTemps);

        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();

        // Update the emploiTemps using partial update
        EmploiTemps partialUpdatedEmploiTemps = new EmploiTemps();
        partialUpdatedEmploiTemps.setId(emploiTemps.getId());

        partialUpdatedEmploiTemps.semestre(UPDATED_SEMESTRE).horaireexam(UPDATED_HORAIREEXAM);

        restEmploiTempsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmploiTemps.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmploiTemps))
            )
            .andExpect(status().isOk());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
        EmploiTemps testEmploiTemps = emploiTempsList.get(emploiTempsList.size() - 1);
        assertThat(testEmploiTemps.getCours()).isEqualTo(DEFAULT_COURS);
        assertThat(testEmploiTemps.getSemestre()).isEqualTo(UPDATED_SEMESTRE);
        assertThat(testEmploiTemps.getTimestre()).isEqualTo(DEFAULT_TIMESTRE);
        assertThat(testEmploiTemps.getHorairecours()).isEqualTo(DEFAULT_HORAIRECOURS);
        assertThat(testEmploiTemps.getHoraireexam()).isEqualTo(UPDATED_HORAIREEXAM);
        assertThat(testEmploiTemps.getActivite()).isEqualTo(DEFAULT_ACTIVITE);
    }

    @Test
    @Transactional
    void fullUpdateEmploiTempsWithPatch() throws Exception {
        // Initialize the database
        emploiTempsRepository.saveAndFlush(emploiTemps);

        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();

        // Update the emploiTemps using partial update
        EmploiTemps partialUpdatedEmploiTemps = new EmploiTemps();
        partialUpdatedEmploiTemps.setId(emploiTemps.getId());

        partialUpdatedEmploiTemps
            .cours(UPDATED_COURS)
            .semestre(UPDATED_SEMESTRE)
            .timestre(UPDATED_TIMESTRE)
            .horairecours(UPDATED_HORAIRECOURS)
            .horaireexam(UPDATED_HORAIREEXAM)
            .activite(UPDATED_ACTIVITE);

        restEmploiTempsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmploiTemps.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmploiTemps))
            )
            .andExpect(status().isOk());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
        EmploiTemps testEmploiTemps = emploiTempsList.get(emploiTempsList.size() - 1);
        assertThat(testEmploiTemps.getCours()).isEqualTo(UPDATED_COURS);
        assertThat(testEmploiTemps.getSemestre()).isEqualTo(UPDATED_SEMESTRE);
        assertThat(testEmploiTemps.getTimestre()).isEqualTo(UPDATED_TIMESTRE);
        assertThat(testEmploiTemps.getHorairecours()).isEqualTo(UPDATED_HORAIRECOURS);
        assertThat(testEmploiTemps.getHoraireexam()).isEqualTo(UPDATED_HORAIREEXAM);
        assertThat(testEmploiTemps.getActivite()).isEqualTo(UPDATED_ACTIVITE);
    }

    @Test
    @Transactional
    void patchNonExistingEmploiTemps() throws Exception {
        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();
        emploiTemps.setId(count.incrementAndGet());

        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmploiTempsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, emploiTempsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmploiTemps() throws Exception {
        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();
        emploiTemps.setId(count.incrementAndGet());

        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmploiTempsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmploiTemps() throws Exception {
        int databaseSizeBeforeUpdate = emploiTempsRepository.findAll().size();
        emploiTemps.setId(count.incrementAndGet());

        // Create the EmploiTemps
        EmploiTempsDTO emploiTempsDTO = emploiTempsMapper.toDto(emploiTemps);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmploiTempsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(emploiTempsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmploiTemps in the database
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmploiTemps() throws Exception {
        // Initialize the database
        emploiTempsRepository.saveAndFlush(emploiTemps);

        int databaseSizeBeforeDelete = emploiTempsRepository.findAll().size();

        // Delete the emploiTemps
        restEmploiTempsMockMvc
            .perform(delete(ENTITY_API_URL_ID, emploiTemps.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmploiTemps> emploiTempsList = emploiTempsRepository.findAll();
        assertThat(emploiTempsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
