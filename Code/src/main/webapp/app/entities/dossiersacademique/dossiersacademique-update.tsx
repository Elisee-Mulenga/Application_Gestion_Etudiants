import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDossiersacademique } from 'app/shared/model/dossiersacademique.model';
import { getEntity, updateEntity, createEntity, reset } from './dossiersacademique.reducer';

export const DossiersacademiqueUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dossiersacademiqueEntity = useAppSelector(state => state.dossiersacademique.entity);
  const loading = useAppSelector(state => state.dossiersacademique.loading);
  const updating = useAppSelector(state => state.dossiersacademique.updating);
  const updateSuccess = useAppSelector(state => state.dossiersacademique.updateSuccess);

  const handleClose = () => {
    navigate('/dossiersacademique');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...dossiersacademiqueEntity,
      ...values,
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
          ...dossiersacademiqueEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionEtudiantApp.dossiersacademique.home.createOrEditLabel" data-cy="DossiersacademiqueCreateUpdateHeading">
            <Translate contentKey="gestionEtudiantApp.dossiersacademique.home.createOrEditLabel">
              Create or edit a Dossiersacademique
            </Translate>
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
                  id="dossiersacademique-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gestionEtudiantApp.dossiersacademique.relevercotes')}
                id="dossiersacademique-relevercotes"
                name="relevercotes"
                data-cy="relevercotes"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.dossiersacademique.bordereau')}
                id="dossiersacademique-bordereau"
                name="bordereau"
                data-cy="bordereau"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/dossiersacademique" replace color="info">
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

export default DossiersacademiqueUpdate;
