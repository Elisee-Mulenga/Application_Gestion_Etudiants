import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './professeur.reducer';

export const ProfesseurDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const professeurEntity = useAppSelector(state => state.professeur.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="professeurDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.professeur.detail.title">Professeur</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.id}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="gestionEtudiantApp.professeur.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.nom}</dd>
          <dt>
            <span id="postnom">
              <Translate contentKey="gestionEtudiantApp.professeur.postnom">Postnom</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.postnom}</dd>
          <dt>
            <span id="prenom">
              <Translate contentKey="gestionEtudiantApp.professeur.prenom">Prenom</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.prenom}</dd>
          <dt>
            <span id="nomcours">
              <Translate contentKey="gestionEtudiantApp.professeur.nomcours">Nomcours</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.nomcours}</dd>
          <dt>
            <span id="adresse">
              <Translate contentKey="gestionEtudiantApp.professeur.adresse">Adresse</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.adresse}</dd>
          <dt>
            <span id="mail">
              <Translate contentKey="gestionEtudiantApp.professeur.mail">Mail</Translate>
            </span>
          </dt>
          <dd>{professeurEntity.mail}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.professeur.administrateur">Administrateur</Translate>
          </dt>
          <dd>{professeurEntity.administrateur ? professeurEntity.administrateur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/professeur" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/professeur/${professeurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProfesseurDetail;
