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
import { IGestionInfos } from 'app/shared/model/gestion-infos.model';
import { getEntity, updateEntity, createEntity, reset } from './gestion-infos.reducer';

export const GestionInfosUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const administrateurs = useAppSelector(state => state.administrateur.entities);
  const gestionInfosEntity = useAppSelector(state => state.gestionInfos.entity);
  const loading = useAppSelector(state => state.gestionInfos.loading);
  const updating = useAppSelector(state => state.gestionInfos.updating);
  const updateSuccess = useAppSelector(state => state.gestionInfos.updateSuccess);

  const handleClose = () => {
    navigate('/gestion-infos');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAdministrateurs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...gestionInfosEntity,
      ...values,
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
          ...gestionInfosEntity,
          administrateur: gestionInfosEntity?.administrateur?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionEtudiantApp.gestionInfos.home.createOrEditLabel" data-cy="GestionInfosCreateUpdateHeading">
            <Translate contentKey="gestionEtudiantApp.gestionInfos.home.createOrEditLabel">Create or edit a GestionInfos</Translate>
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
                  id="gestion-infos-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gestionEtudiantApp.gestionInfos.infosperson')}
                id="gestion-infos-infosperson"
                name="infosperson"
                data-cy="infosperson"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.gestionInfos.infosacadem')}
                id="gestion-infos-infosacadem"
                name="infosacadem"
                data-cy="infosacadem"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.gestionInfos.infosfinance')}
                id="gestion-infos-infosfinance"
                name="infosfinance"
                data-cy="infosfinance"
                type="text"
              />
              <ValidatedField
                id="gestion-infos-administrateur"
                name="administrateur"
                data-cy="administrateur"
                label={translate('gestionEtudiantApp.gestionInfos.administrateur')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/gestion-infos" replace color="info">
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

export default GestionInfosUpdate;
