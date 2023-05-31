import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEtudiant } from 'app/shared/model/etudiant.model';
import { getEntities as getEtudiants } from 'app/entities/etudiant/etudiant.reducer';
import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { getEntities as getAdministrateurs } from 'app/entities/administrateur/administrateur.reducer';
import { IEmploiTemps } from 'app/shared/model/emploi-temps.model';
import { getEntity, updateEntity, createEntity, reset } from './emploi-temps.reducer';

export const EmploiTempsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const etudiants = useAppSelector(state => state.etudiant.entities);
  const administrateurs = useAppSelector(state => state.administrateur.entities);
  const emploiTempsEntity = useAppSelector(state => state.emploiTemps.entity);
  const loading = useAppSelector(state => state.emploiTemps.loading);
  const updating = useAppSelector(state => state.emploiTemps.updating);
  const updateSuccess = useAppSelector(state => state.emploiTemps.updateSuccess);

  const handleClose = () => {
    navigate('/emploi-temps');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getEtudiants({}));
    dispatch(getAdministrateurs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...emploiTempsEntity,
      ...values,
      etudaint: etudiants.find(it => it.id.toString() === values.etudaint.toString()),
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
          ...emploiTempsEntity,
          etudaint: emploiTempsEntity?.etudaint?.id,
          administrateur: emploiTempsEntity?.administrateur?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionEtudiantApp.emploiTemps.home.createOrEditLabel" data-cy="EmploiTempsCreateUpdateHeading">
            <Translate contentKey="gestionEtudiantApp.emploiTemps.home.createOrEditLabel">Create or edit a EmploiTemps</Translate>
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
                  id="emploi-temps-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gestionEtudiantApp.emploiTemps.cours')}
                id="emploi-temps-cours"
                name="cours"
                data-cy="cours"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.emploiTemps.semestre')}
                id="emploi-temps-semestre"
                name="semestre"
                data-cy="semestre"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.emploiTemps.timestre')}
                id="emploi-temps-timestre"
                name="timestre"
                data-cy="timestre"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.emploiTemps.horairecours')}
                id="emploi-temps-horairecours"
                name="horairecours"
                data-cy="horairecours"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.emploiTemps.horaireexam')}
                id="emploi-temps-horaireexam"
                name="horaireexam"
                data-cy="horaireexam"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.emploiTemps.activite')}
                id="emploi-temps-activite"
                name="activite"
                data-cy="activite"
                type="text"
              />
              <ValidatedField
                id="emploi-temps-etudaint"
                name="etudaint"
                data-cy="etudaint"
                label={translate('gestionEtudiantApp.emploiTemps.etudaint')}
                type="select"
              >
                <option value="" key="0" />
                {etudiants
                  ? etudiants.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="emploi-temps-administrateur"
                name="administrateur"
                data-cy="administrateur"
                label={translate('gestionEtudiantApp.emploiTemps.administrateur')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/emploi-temps" replace color="info">
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

export default EmploiTempsUpdate;
