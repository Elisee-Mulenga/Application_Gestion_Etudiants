import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './etudiant.reducer';

export const EtudiantDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const etudiantEntity = useAppSelector(state => state.etudiant.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="etudiantDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.etudiant.detail.title">Etudiant</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.id}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="gestionEtudiantApp.etudiant.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.nom}</dd>
          <dt>
            <span id="postnom">
              <Translate contentKey="gestionEtudiantApp.etudiant.postnom">Postnom</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.postnom}</dd>
          <dt>
            <span id="prenom">
              <Translate contentKey="gestionEtudiantApp.etudiant.prenom">Prenom</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.prenom}</dd>
          <dt>
            <span id="genre">
              <Translate contentKey="gestionEtudiantApp.etudiant.genre">Genre</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.genre}</dd>
          <dt>
            <span id="dateNaissance">
              <Translate contentKey="gestionEtudiantApp.etudiant.dateNaissance">Date Naissance</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.dateNaissance}</dd>
          <dt>
            <span id="adresse">
              <Translate contentKey="gestionEtudiantApp.etudiant.adresse">Adresse</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.adresse}</dd>
          <dt>
            <span id="matricule">
              <Translate contentKey="gestionEtudiantApp.etudiant.matricule">Matricule</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.matricule}</dd>
          <dt>
            <span id="promotion">
              <Translate contentKey="gestionEtudiantApp.etudiant.promotion">Promotion</Translate>
            </span>
          </dt>
          <dd>{etudiantEntity.promotion}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.inscription">Inscription</Translate>
          </dt>
          <dd>{etudiantEntity.inscription ? etudiantEntity.inscription.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.dossiersacademique">Dossiersacademique</Translate>
          </dt>
          <dd>{etudiantEntity.dossiersacademique ? etudiantEntity.dossiersacademique.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.donnees">Donnees</Translate>
          </dt>
          <dd>{etudiantEntity.donnees ? etudiantEntity.donnees.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.resultat">Resultat</Translate>
          </dt>
          <dd>{etudiantEntity.resultat ? etudiantEntity.resultat.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.document">Document</Translate>
          </dt>
          <dd>{etudiantEntity.document ? etudiantEntity.document.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.progression">Progression</Translate>
          </dt>
          <dd>{etudiantEntity.progression ? etudiantEntity.progression.id : ''}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.communication">Communication</Translate>
          </dt>
          <dd>
            {etudiantEntity.communications
              ? etudiantEntity.communications.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {etudiantEntity.communications && i === etudiantEntity.communications.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.etudiant.administrateur">Administrateur</Translate>
          </dt>
          <dd>{etudiantEntity.administrateur ? etudiantEntity.administrateur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/etudiant" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/etudiant/${etudiantEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EtudiantDetail;
