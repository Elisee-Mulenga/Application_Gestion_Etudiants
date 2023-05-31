import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEtudiant } from 'app/shared/model/etudiant.model';
import { getEntities } from './etudiant.reducer';

export const Etudiant = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const etudiantList = useAppSelector(state => state.etudiant.entities);
  const loading = useAppSelector(state => state.etudiant.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="etudiant-heading" data-cy="EtudiantHeading">
        <Translate contentKey="gestionEtudiantApp.etudiant.home.title">Etudiants</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.etudiant.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/etudiant/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.etudiant.home.createLabel">Create new Etudiant</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {etudiantList && etudiantList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.postnom">Postnom</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.prenom">Prenom</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.genre">Genre</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.dateNaissance">Date Naissance</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.adresse">Adresse</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.matricule">Matricule</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.promotion">Promotion</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.inscription">Inscription</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.dossiersacademique">Dossiersacademique</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.donnees">Donnees</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.resultat">Resultat</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.document">Document</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.progression">Progression</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.communication">Communication</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.etudiant.administrateur">Administrateur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {etudiantList.map((etudiant, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/etudiant/${etudiant.id}`} color="link" size="sm">
                      {etudiant.id}
                    </Button>
                  </td>
                  <td>{etudiant.nom}</td>
                  <td>{etudiant.postnom}</td>
                  <td>{etudiant.prenom}</td>
                  <td>{etudiant.genre}</td>
                  <td>{etudiant.dateNaissance}</td>
                  <td>{etudiant.adresse}</td>
                  <td>{etudiant.matricule}</td>
                  <td>{etudiant.promotion}</td>
                  <td>
                    {etudiant.inscription ? <Link to={`/inscription/${etudiant.inscription.id}`}>{etudiant.inscription.id}</Link> : ''}
                  </td>
                  <td>
                    {etudiant.dossiersacademique ? (
                      <Link to={`/dossiersacademique/${etudiant.dossiersacademique.id}`}>{etudiant.dossiersacademique.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{etudiant.donnees ? <Link to={`/donnees/${etudiant.donnees.id}`}>{etudiant.donnees.id}</Link> : ''}</td>
                  <td>{etudiant.resultat ? <Link to={`/resultat/${etudiant.resultat.id}`}>{etudiant.resultat.id}</Link> : ''}</td>
                  <td>{etudiant.document ? <Link to={`/document/${etudiant.document.id}`}>{etudiant.document.id}</Link> : ''}</td>
                  <td>
                    {etudiant.progression ? <Link to={`/progression/${etudiant.progression.id}`}>{etudiant.progression.id}</Link> : ''}
                  </td>
                  <td>
                    {etudiant.communications
                      ? etudiant.communications.map((val, j) => (
                          <span key={j}>
                            <Link to={`/communication/${val.id}`}>{val.id}</Link>
                            {j === etudiant.communications.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {etudiant.administrateur ? (
                      <Link to={`/administrateur/${etudiant.administrateur.id}`}>{etudiant.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/etudiant/${etudiant.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/etudiant/${etudiant.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/etudiant/${etudiant.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="gestionEtudiantApp.etudiant.home.notFound">No Etudiants found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Etudiant;
