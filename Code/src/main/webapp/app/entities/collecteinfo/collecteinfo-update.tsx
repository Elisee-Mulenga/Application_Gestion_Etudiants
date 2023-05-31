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
import { ICollecteinfo } from 'app/shared/model/collecteinfo.model';
import { getEntity, updateEntity, createEntity, reset } from './collecteinfo.reducer';

export const CollecteinfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const administrateurs = useAppSelector(state => state.administrateur.entities);
  const collecteinfoEntity = useAppSelector(state => state.collecteinfo.entity);
  const loading = useAppSelector(state => state.collecteinfo.loading);
  const updating = useAppSelector(state => state.collecteinfo.updating);
  const updateSuccess = useAppSelector(state => state.collecteinfo.updateSuccess);

  const handleClose = () => {
    navigate('/collecteinfo');
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
      ...collecteinfoEntity,
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
          ...collecteinfoEntity,
          administrateur: collecteinfoEntity?.administrateur?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionEtudiantApp.collecteinfo.home.createOrEditLabel" data-cy="CollecteinfoCreateUpdateHeading">
            <Translate contentKey="gestionEtudiantApp.collecteinfo.home.createOrEditLabel">Create or edit a Collecteinfo</Translate>
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
                  id="collecteinfo-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gestionEtudiantApp.collecteinfo.infosperson')}
                id="collecteinfo-infosperson"
                name="infosperson"
                data-cy="infosperson"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.collecteinfo.infosacadem')}
                id="collecteinfo-infosacadem"
                name="infosacadem"
                data-cy="infosacadem"
                type="text"
              />
              <ValidatedField
                label={translate('gestionEtudiantApp.collecteinfo.infosadmi')}
                id="collecteinfo-infosadmi"
                name="infosadmi"
                data-cy="infosadmi"
                type="text"
              />
              <ValidatedField
                id="collecteinfo-administrateur"
                name="administrateur"
                data-cy="administrateur"
                label={translate('gestionEtudiantApp.collecteinfo.administrateur')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/collecteinfo" replace color="info">
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

export default CollecteinfoUpdate;
