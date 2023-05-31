import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './cours.reducer';

export const CoursDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const coursEntity = useAppSelector(state => state.cours.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="coursDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.cours.detail.title">Cours</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{coursEntity.id}</dd>
          <dt>
            <span id="titre">
              <Translate contentKey="gestionEtudiantApp.cours.titre">Titre</Translate>
            </span>
          </dt>
          <dd>{coursEntity.titre}</dd>
          <dt>
            <span id="note">
              <Translate contentKey="gestionEtudiantApp.cours.note">Note</Translate>
            </span>
          </dt>
          <dd>{coursEntity.note}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.cours.etudiant">Etudiant</Translate>
          </dt>
          <dd>{coursEntity.etudiant ? coursEntity.etudiant.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.cours.professeur">Professeur</Translate>
          </dt>
          <dd>{coursEntity.professeur ? coursEntity.professeur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/cours" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cours/${coursEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CoursDetail;
