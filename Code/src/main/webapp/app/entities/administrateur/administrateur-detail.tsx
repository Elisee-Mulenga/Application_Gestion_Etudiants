import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './administrateur.reducer';

export const AdministrateurDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const administrateurEntity = useAppSelector(state => state.administrateur.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="administrateurDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.administrateur.detail.title">Administrateur</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{administrateurEntity.id}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="gestionEtudiantApp.administrateur.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{administrateurEntity.nom}</dd>
          <dt>
            <span id="postnom">
              <Translate contentKey="gestionEtudiantApp.administrateur.postnom">Postnom</Translate>
            </span>
          </dt>
          <dd>{administrateurEntity.postnom}</dd>
          <dt>
            <span id="prenom">
              <Translate contentKey="gestionEtudiantApp.administrateur.prenom">Prenom</Translate>
            </span>
          </dt>
          <dd>{administrateurEntity.prenom}</dd>
          <dt>
            <span id="adresse">
              <Translate contentKey="gestionEtudiantApp.administrateur.adresse">Adresse</Translate>
            </span>
          </dt>
          <dd>{administrateurEntity.adresse}</dd>
          <dt>
            <span id="mail">
              <Translate contentKey="gestionEtudiantApp.administrateur.mail">Mail</Translate>
            </span>
          </dt>
          <dd>{administrateurEntity.mail}</dd>
        </dl>
        <Button tag={Link} to="/administrateur" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/administrateur/${administrateurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AdministrateurDetail;
