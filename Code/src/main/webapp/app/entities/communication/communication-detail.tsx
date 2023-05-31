import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './communication.reducer';

export const CommunicationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const communicationEntity = useAppSelector(state => state.communication.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="communicationDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.communication.detail.title">Communication</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{communicationEntity.id}</dd>
          <dt>
            <span id="destinataire">
              <Translate contentKey="gestionEtudiantApp.communication.destinataire">Destinataire</Translate>
            </span>
          </dt>
          <dd>{communicationEntity.destinataire}</dd>
          <dt>
            <span id="expeditaire">
              <Translate contentKey="gestionEtudiantApp.communication.expeditaire">Expeditaire</Translate>
            </span>
          </dt>
          <dd>{communicationEntity.expeditaire}</dd>
          <dt>
            <span id="forum">
              <Translate contentKey="gestionEtudiantApp.communication.forum">Forum</Translate>
            </span>
          </dt>
          <dd>{communicationEntity.forum}</dd>
          <dt>
            <span id="annonce">
              <Translate contentKey="gestionEtudiantApp.communication.annonce">Annonce</Translate>
            </span>
          </dt>
          <dd>{communicationEntity.annonce}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.communication.administrateur">Administrateur</Translate>
          </dt>
          <dd>{communicationEntity.administrateur ? communicationEntity.administrateur.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.communication.professeur">Professeur</Translate>
          </dt>
          <dd>{communicationEntity.professeur ? communicationEntity.professeur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/communication" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/communication/${communicationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CommunicationDetail;
