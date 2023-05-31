package com.esisalama.gestionetudiant.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.esisalama.gestionetudiant.IntegrationTest;
import com.esisalama.gestionetudiant.domain.Communication;
import com.esisalama.gestionetudiant.repository.CommunicationRepository;
import com.esisalama.gestionetudiant.service.dto.CommunicationDTO;
import com.esisalama.gestionetudiant.service.mapper.CommunicationMapper;
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
 * Integration tests for the {@link CommunicationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommunicationResourceIT {

    private static final String DEFAULT_DESTINATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPEDITAIRE = "AAAAAAAAAA";
    private static final String UPDATED_EXPEDITAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_FORUM = "AAAAAAAAAA";
    private static final String UPDATED_FORUM = "BBBBBBBBBB";

    private static final String DEFAULT_ANNONCE = "AAAAAAAAAA";
    private static final String UPDATED_ANNONCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/communications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CommunicationRepository communicationRepository;

    @Autowired
    private CommunicationMapper communicationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunicationMockMvc;

    private Communication communication;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Communication createEntity(EntityManager em) {
        Communication communication = new Communication()
            .destinataire(DEFAULT_DESTINATAIRE)
            .expeditaire(DEFAULT_EXPEDITAIRE)
            .forum(DEFAULT_FORUM)
            .annonce(DEFAULT_ANNONCE);
        return communication;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Communication createUpdatedEntity(EntityManager em) {
        Communication communication = new Communication()
            .destinataire(UPDATED_DESTINATAIRE)
            .expeditaire(UPDATED_EXPEDITAIRE)
            .forum(UPDATED_FORUM)
            .annonce(UPDATED_ANNONCE);
        return communication;
    }

    @BeforeEach
    public void initTest() {
        communication = createEntity(em);
    }

    @Test
    @Transactional
    void createCommunication() throws Exception {
        int databaseSizeBeforeCreate = communicationRepository.findAll().size();
        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);
        restCommunicationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeCreate + 1);
        Communication testCommunication = communicationList.get(communicationList.size() - 1);
        assertThat(testCommunication.getDestinataire()).isEqualTo(DEFAULT_DESTINATAIRE);
        assertThat(testCommunication.getExpeditaire()).isEqualTo(DEFAULT_EXPEDITAIRE);
        assertThat(testCommunication.getForum()).isEqualTo(DEFAULT_FORUM);
        assertThat(testCommunication.getAnnonce()).isEqualTo(DEFAULT_ANNONCE);
    }

    @Test
    @Transactional
    void createCommunicationWithExistingId() throws Exception {
        // Create the Communication with an existing ID
        communication.setId(1L);
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        int databaseSizeBeforeCreate = communicationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunicationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommunications() throws Exception {
        // Initialize the database
        communicationRepository.saveAndFlush(communication);

        // Get all the communicationList
        restCommunicationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communication.getId().intValue())))
            .andExpect(jsonPath("$.[*].destinataire").value(hasItem(DEFAULT_DESTINATAIRE)))
            .andExpect(jsonPath("$.[*].expeditaire").value(hasItem(DEFAULT_EXPEDITAIRE)))
            .andExpect(jsonPath("$.[*].forum").value(hasItem(DEFAULT_FORUM)))
            .andExpect(jsonPath("$.[*].annonce").value(hasItem(DEFAULT_ANNONCE)));
    }

    @Test
    @Transactional
    void getCommunication() throws Exception {
        // Initialize the database
        communicationRepository.saveAndFlush(communication);

        // Get the communication
        restCommunicationMockMvc
            .perform(get(ENTITY_API_URL_ID, communication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communication.getId().intValue()))
            .andExpect(jsonPath("$.destinataire").value(DEFAULT_DESTINATAIRE))
            .andExpect(jsonPath("$.expeditaire").value(DEFAULT_EXPEDITAIRE))
            .andExpect(jsonPath("$.forum").value(DEFAULT_FORUM))
            .andExpect(jsonPath("$.annonce").value(DEFAULT_ANNONCE));
    }

    @Test
    @Transactional
    void getNonExistingCommunication() throws Exception {
        // Get the communication
        restCommunicationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommunication() throws Exception {
        // Initialize the database
        communicationRepository.saveAndFlush(communication);

        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();

        // Update the communication
        Communication updatedCommunication = communicationRepository.findById(communication.getId()).get();
        // Disconnect from session so that the updates on updatedCommunication are not directly saved in db
        em.detach(updatedCommunication);
        updatedCommunication
            .destinataire(UPDATED_DESTINATAIRE)
            .expeditaire(UPDATED_EXPEDITAIRE)
            .forum(UPDATED_FORUM)
            .annonce(UPDATED_ANNONCE);
        CommunicationDTO communicationDTO = communicationMapper.toDto(updatedCommunication);

        restCommunicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, communicationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isOk());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
        Communication testCommunication = communicationList.get(communicationList.size() - 1);
        assertThat(testCommunication.getDestinataire()).isEqualTo(UPDATED_DESTINATAIRE);
        assertThat(testCommunication.getExpeditaire()).isEqualTo(UPDATED_EXPEDITAIRE);
        assertThat(testCommunication.getForum()).isEqualTo(UPDATED_FORUM);
        assertThat(testCommunication.getAnnonce()).isEqualTo(UPDATED_ANNONCE);
    }

    @Test
    @Transactional
    void putNonExistingCommunication() throws Exception {
        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();
        communication.setId(count.incrementAndGet());

        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, communicationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommunication() throws Exception {
        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();
        communication.setId(count.incrementAndGet());

        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommunication() throws Exception {
        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();
        communication.setId(count.incrementAndGet());

        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommunicationWithPatch() throws Exception {
        // Initialize the database
        communicationRepository.saveAndFlush(communication);

        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();

        // Update the communication using partial update
        Communication partialUpdatedCommunication = new Communication();
        partialUpdatedCommunication.setId(communication.getId());

        partialUpdatedCommunication
            .destinataire(UPDATED_DESTINATAIRE)
            .expeditaire(UPDATED_EXPEDITAIRE)
            .forum(UPDATED_FORUM)
            .annonce(UPDATED_ANNONCE);

        restCommunicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunication.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCommunication))
            )
            .andExpect(status().isOk());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
        Communication testCommunication = communicationList.get(communicationList.size() - 1);
        assertThat(testCommunication.getDestinataire()).isEqualTo(UPDATED_DESTINATAIRE);
        assertThat(testCommunication.getExpeditaire()).isEqualTo(UPDATED_EXPEDITAIRE);
        assertThat(testCommunication.getForum()).isEqualTo(UPDATED_FORUM);
        assertThat(testCommunication.getAnnonce()).isEqualTo(UPDATED_ANNONCE);
    }

    @Test
    @Transactional
    void fullUpdateCommunicationWithPatch() throws Exception {
        // Initialize the database
        communicationRepository.saveAndFlush(communication);

        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();

        // Update the communication using partial update
        Communication partialUpdatedCommunication = new Communication();
        partialUpdatedCommunication.setId(communication.getId());

        partialUpdatedCommunication
            .destinataire(UPDATED_DESTINATAIRE)
            .expeditaire(UPDATED_EXPEDITAIRE)
            .forum(UPDATED_FORUM)
            .annonce(UPDATED_ANNONCE);

        restCommunicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunication.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCommunication))
            )
            .andExpect(status().isOk());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
        Communication testCommunication = communicationList.get(communicationList.size() - 1);
        assertThat(testCommunication.getDestinataire()).isEqualTo(UPDATED_DESTINATAIRE);
        assertThat(testCommunication.getExpeditaire()).isEqualTo(UPDATED_EXPEDITAIRE);
        assertThat(testCommunication.getForum()).isEqualTo(UPDATED_FORUM);
        assertThat(testCommunication.getAnnonce()).isEqualTo(UPDATED_ANNONCE);
    }

    @Test
    @Transactional
    void patchNonExistingCommunication() throws Exception {
        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();
        communication.setId(count.incrementAndGet());

        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, communicationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommunication() throws Exception {
        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();
        communication.setId(count.incrementAndGet());

        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommunication() throws Exception {
        int databaseSizeBeforeUpdate = communicationRepository.findAll().size();
        communication.setId(count.incrementAndGet());

        // Create the Communication
        CommunicationDTO communicationDTO = communicationMapper.toDto(communication);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(communicationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Communication in the database
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommunication() throws Exception {
        // Initialize the database
        communicationRepository.saveAndFlush(communication);

        int databaseSizeBeforeDelete = communicationRepository.findAll().size();

        // Delete the communication
        restCommunicationMockMvc
            .perform(delete(ENTITY_API_URL_ID, communication.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Communication> communicationList = communicationRepository.findAll();
        assertThat(communicationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
