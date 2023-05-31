import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './emploi-temps.reducer';

export const EmploiTempsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const emploiTempsEntity = useAppSelector(state => state.emploiTemps.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="emploiTempsDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.emploiTemps.detail.title">EmploiTemps</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.id}</dd>
          <dt>
            <span id="cours">
              <Translate contentKey="gestionEtudiantApp.emploiTemps.cours">Cours</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.cours}</dd>
          <dt>
            <span id="semestre">
              <Translate contentKey="gestionEtudiantApp.emploiTemps.semestre">Semestre</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.semestre}</dd>
          <dt>
            <span id="timestre">
              <Translate contentKey="gestionEtudiantApp.emploiTemps.timestre">Timestre</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.timestre}</dd>
          <dt>
            <span id="horairecours">
              <Translate contentKey="gestionEtudiantApp.emploiTemps.horairecours">Horairecours</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.horairecours}</dd>
          <dt>
            <span id="horaireexam">
              <Translate contentKey="gestionEtudiantApp.emploiTemps.horaireexam">Horaireexam</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.horaireexam}</dd>
          <dt>
            <span id="activite">
              <Translate contentKey="gestionEtudiantApp.emploiTemps.activite">Activite</Translate>
            </span>
          </dt>
          <dd>{emploiTempsEntity.activite}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.emploiTemps.etudaint">Etudaint</Translate>
          </dt>
          <dd>{emploiTempsEntity.etudaint ? emploiTempsEntity.etudaint.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.emploiTemps.administrateur">Administrateur</Translate>
          </dt>
          <dd>{emploiTempsEntity.administrateur ? emploiTempsEntity.administrateur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/emploi-temps" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/emploi-temps/${emploiTempsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmploiTempsDetail;
