import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './dossiersacademique.reducer';

export const DossiersacademiqueDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dossiersacademiqueEntity = useAppSelector(state => state.dossiersacademique.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dossiersacademiqueDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.dossiersacademique.detail.title">Dossiersacademique</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dossiersacademiqueEntity.id}</dd>
          <dt>
            <span id="relevercotes">
              <Translate contentKey="gestionEtudiantApp.dossiersacademique.relevercotes">Relevercotes</Translate>
            </span>
          </dt>
          <dd>{dossiersacademiqueEntity.relevercotes}</dd>
          <dt>
            <span id="bordereau">
              <Translate contentKey="gestionEtudiantApp.dossiersacademique.bordereau">Bordereau</Translate>
            </span>
          </dt>
          <dd>{dossiersacademiqueEntity.bordereau}</dd>
        </dl>
        <Button tag={Link} to="/dossiersacademique" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dossiersacademique/${dossiersacademiqueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DossiersacademiqueDetail;
