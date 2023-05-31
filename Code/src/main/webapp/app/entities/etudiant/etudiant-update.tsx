import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInscription } from 'app/shared/model/inscription.model';
import { getEntities as getInscriptions } from 'app/entities/inscription/inscription.reducer';
import { IDossiersacademique } from 'app/shared/model/dossiersacademique.model';
import { getEntities as getDossiersacademiques } from 'app/entities/dossiersacademique/dossiersacademique.reducer';
import { IDonnees } from 'app/shared/model/donnees.model';
import { getEntities as getDonnees } from 'app/entities/donnees/donnees.reducer';
import { IResultat } from 'app/shared/model/resultat.model';
import { getEntities as getResultats } from 'app/entities/resultat/resultat.reducer';
import { IDocument } from 'app/shared/model/document.model';
import { getEntities as getDocuments } from 'app/entities/document/document.reducer';
import { IProgression } from 'app/shared/model/progression.model';
import { getEntities as getProgressions } from 'app/entities/progression/progression.reducer';
import { ICommunication } from 'app/shared/model/communication.model';
import { getEntities as getCommunications } from 'app/entities/communication/communication.reducer';
import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { getEntities as getAdministrateurs } from 'app/entities/administrateur/administrateur.reducer';
import { IEtudiant } from 'app/shared/model/etudiant.model';
import { getEntity, updateEntity, createEntity, reset } from './etudiant.reducer';

export const EtudiantUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const inscriptions = useAppSelector(state => state.inscription.entities);
  const dossiersacademiques = useAppSelector(state => state.dossiersacademique.entities);
  const donnees = useAppSelector(state => state.donnees.entities);
  const resultats = useAppSelector(state => state.resultat.entities);
  const documents = useAppSelector(state => state.document.entities);
  const progressions = useAppSelector(state => state.progression.entities);
  const communications = useAppSelector(state => state.communication.entities);
  const administrateurs = useAppSelector(state => state.administrateur.entities);
  const etudiantEntity = useAppSelector(state => state.etudiant.entity);
  const loading = useAppSelector(state => state.etudiant.loading);
  const updating = useAppSelector(state => state.etudiant.updating);
  const updateSuccess = useAppSelector(state => state.etudiant.updateSuccess);

  const handleClose = () => {
    navigate('/etudiant');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getInscriptions({}));
    dispatch(getDossiersacademiques({}));
    dispatch(getDonnees({}));
    dispatch(getResultats({}));
    dispatch(getDocuments({}));
    dispatch(getProgressions({}));
    dispatch(getCommunications({}));
    dispatch(getAdministrateurs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...etudiantEntity,
      ...values,
      communications: mapIdList(values.communications),
      inscription: inscriptions.find(it => it.id.toString() === values.inscription.toString()),
      dossiersacademique: dossiersacademiques.find(it => it.id.toString() === values.dossiersacademique.toString()),
      donnees: donnees.find(it => it.id.toString() === values.donnees.toString()),
      resultat: resultats.find(it => it.id.toString() === values.resultat.toString()),
      document: documents.find(it => it.id.toString() === values.document.toString()),
      progression: progressions.find(it => it.id.toString() === values.progression.toString()),
      administrateur: administrateurs.find(it => it.id.toString() === values.administrateur.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...etudiantEntity,
          inscription: etudiantEntity?.inscription?.id,
          dossiersacademique: etudiantEntity?.dossiersacademique?.id,
          donnees: etudiantEntity?.donnees?.id,
          resultat: etudiantEntity?.resultat?.id,
          document: etudiantEntity?.document?.id,
          progression: etudiantEntity?.progression?.id,
          communications: etudiantEntity?.communications?.map(e => e.id.toString()),
          administrateur: etudiantEntity?.administrateur?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionEtudiantApp.etudiant.home.createOrEditLabel" data-cy="EtudiantCreateUpdateHeading">
            <Translate contentKey="gestionEtudiantApp.etudiant.home.createOrEditLabel">Create or edit a Etudiant</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="etudiant-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('gestionEtudiantApp.etudiant.nom')} id="etudiant-nom" name="nom" data-cy="nom" type="text" />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.postnom')}
                id="etudiant-postnom"
                name="postnom"
                data-cy="postnom"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.prenom')}
                id="etudiant-prenom"
                name="prenom"
                data-cy="prenom"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.genre')}
                id="etudiant-genre"
                name="genre"
                data-cy="genre"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.dateNaissance')}
                id="etudiant-dateNaissance"
                name="dateNaissance"
                data-cy="dateNaissance"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.adresse')}
                id="etudiant-adresse"
                name="adresse"
                data-cy="adresse"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.matricule')}
                id="etudiant-matricule"
                name="matricule"
                data-cy="matricule"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.promotion')}
                id="etudiant-promotion"
                name="promotion"
                data-cy="promotion"
                type="text"
              />
              <ValidatedField
                id="etudiant-inscription"
                name="inscription"
                data-cy="inscription"
                label={translate('gestionEtudiantApp.etudiant.inscription')}
                type="select"
              >
                <option value="" key="0" />
                {inscriptions
                  ? inscriptions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="etudiant-dossiersacademique"
                name="dossiersacademique"
                data-cy="dossiersacademique"
                label={translate('gestionEtudiantApp.etudiant.dossiersacademique')}
                type="select"
              >
                <option value="" key="0" />
                {dossiersacademiques
                  ? dossiersacademiques.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="etudiant-donnees"
                name="donnees"
                data-cy="donnees"
                label={translate('gestionEtudiantApp.etudiant.donnees')}
                type="select"
              >
                <option value="" key="0" />
                {donnees
                  ? donnees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="etudiant-resultat"
                name="resultat"
                data-cy="resultat"
                label={translate('gestionEtudiantApp.etudiant.resultat')}
                type="select"
              >
                <option value="" key="0" />
                {resultats
                  ? resultats.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="etudiant-document"
                name="document"
                data-cy="document"
                label={translate('gestionEtudiantApp.etudiant.document')}
                type="select"
              >
                <option value="" key="0" />
                {documents
                  ? documents.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="etudiant-progression"
                name="progression"
                data-cy="progression"
                label={translate('gestionEtudiantApp.etudiant.progression')}
                type="select"
              >
                <option value="" key="0" />
                {progressions
                  ? progressions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('gestionEtudiantApp.etudiant.communication')}
                id="etudiant-communication"
                data-cy="communication"
                type="select"
                multiple
                name="communications"
              >
                <option value="" key="0" />
                {communications
                  ? communications.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="etudiant-administrateur"
                name="administrateur"
                data-cy="administrateur"
                label={translate('gestionEtudiantApp.etudiant.administrateur')}
                type="select"
              >
                <option value="" key="0" />
                {administrateurs
                  ? administrateurs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/etudiant" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default EtudiantUpdate;
