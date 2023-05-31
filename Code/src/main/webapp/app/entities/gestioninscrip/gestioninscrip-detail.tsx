import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './gestioninscrip.reducer';

export const GestioninscripDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const gestioninscripEntity = useAppSelector(state => state.gestioninscrip.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="gestioninscripDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.gestioninscrip.detail.title">Gestioninscrip</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{gestioninscripEntity.id}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.gestioninscrip.administrateur">Administrateur</Translate>
          </dt>
          <dd>{gestioninscripEntity.administrateur ? gestioninscripEntity.administrateur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/gestioninscrip" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/gestioninscrip/${gestioninscripEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GestioninscripDetail;
