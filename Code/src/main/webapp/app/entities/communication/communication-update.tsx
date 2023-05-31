import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { getEntities as getAdministrateurs } from 'app/entities/administrateur/administrateur.reducer';
import { IProfesseur } from 'app/shared/model/professeur.model';
import { getEntities as getProfesseurs } from 'app/entities/professeur/professeur.reducer';
import { IEtudiant } from 'app/shared/model/etudiant.model';
import { getEntities as getEtudiants } from 'app/entities/etudiant/etudiant.reducer';
import { ICommunication } from 'app/shared/model/communication.model';
import { getEntity, updateEntity, createEntity, reset } from './communication.reducer';

export const CommunicationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const administrateurs = useAppSelector(state => state.administrateur.entities);
  const professeurs = useAppSelector(state => state.professeur.entities);
  const etudiants = useAppSelector(state => state.etudiant.entities);
  const communicationEntity = useAppSelector(state => state.communication.entity);
  const loading = useAppSelector(state => state.communication.loading);
  const updating = useAppSelector(state => state.communication.updating);
  const updateSuccess = useAppSelector(state => state.communication.updateSuccess);

  const handleClose = () => {
    navigate('/communication');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAdministrateurs({}));
    dispatch(getProfesseurs({}));
    dispatch(getEtudiants({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...communicationEntity,
      ...values,
      administrateur: administrateurs.find(it => it.id.toString() === values.administrateur.toString()),
      professeur: professeurs.find(it => it.id.toString() === values.professeur.toString()),
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
          ...communicationEntity,
          administrateur: communicationEntity?.administrateur?.id,
          professeur: communicationEntity?.professeur?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionEtudiantApp.communication.home.createOrEditLabel" data-cy="CommunicationCreateUpdateHeading">
            <Translate contentKey="gestionEtudiantApp.communication.home.createOrEditLabel">Create or edit a Communication</Translate>
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
                  id="communication-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gestionEtudiantApp.communication.destinataire')}
                id="communication-destinataire"
                name="destinataire"
                data-cy="destinataire"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.communication.expeditaire')}
                id="communication-expeditaire"
                name="expeditaire"
                data-cy="expeditaire"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.communication.forum')}
                id="communication-forum"
                name="forum"
                data-cy="forum"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.communication.annonce')}
                id="communication-annonce"
                name="annonce"
                data-cy="annonce"
                type="text"
              />
              <ValidatedField
                id="communication-administrateur"
                name="administrateur"
                data-cy="administrateur"
                label={translate('gestionEtudiantApp.communication.administrateur')}
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
              <ValidatedField
                id="communication-professeur"
                name="professeur"
                data-cy="professeur"
                label={translate('gestionEtudiantApp.communication.professeur')}
                type="select"
              >
                <option value="" key="0" />
                {professeurs
                  ? professeurs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/communication" replace color="info">
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

export default CommunicationUpdate;
