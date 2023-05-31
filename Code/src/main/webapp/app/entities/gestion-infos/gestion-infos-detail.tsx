import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './gestion-infos.reducer';

export const GestionInfosDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const gestionInfosEntity = useAppSelector(state => state.gestionInfos.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="gestionInfosDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.gestionInfos.detail.title">GestionInfos</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{gestionInfosEntity.id}</dd>
          <dt>
            <span id="infosperson">
              <Translate contentKey="gestionEtudiantApp.gestionInfos.infosperson">Infosperson</Translate>
            </span>
          </dt>
          <dd>{gestionInfosEntity.infosperson}</dd>
          <dt>
            <span id="infosacadem">
              <Translate contentKey="gestionEtudiantApp.gestionInfos.infosacadem">Infosacadem</Translate>
            </span>
          </dt>
          <dd>{gestionInfosEntity.infosacadem}</dd>
          <dt>
            <span id="infosfinance">
              <Translate contentKey="gestionEtudiantApp.gestionInfos.infosfinance">Infosfinance</Translate>
            </span>
          </dt>
          <dd>{gestionInfosEntity.infosfinance}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.gestionInfos.administrateur">Administrateur</Translate>
          </dt>
          <dd>{gestionInfosEntity.administrateur ? gestionInfosEntity.administrateur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/gestion-infos" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/gestion-infos/${gestionInfosEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GestionInfosDetail;
