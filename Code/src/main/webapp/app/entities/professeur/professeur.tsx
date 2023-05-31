import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProfesseur } from 'app/shared/model/professeur.model';
import { getEntities } from './professeur.reducer';

export const Professeur = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const professeurList = useAppSelector(state => state.professeur.entities);
  const loading = useAppSelector(state => state.professeur.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="professeur-heading" data-cy="ProfesseurHeading">
        <Translate contentKey="gestionEtudiantApp.professeur.home.title">Professeurs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.professeur.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/professeur/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.professeur.home.createLabel">Create new Professeur</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {professeurList && professeurList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.postnom">Postnom</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.prenom">Prenom</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.nomcours">Nomcours</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.adresse">Adresse</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.mail">Mail</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.professeur.administrateur">Administrateur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {professeurList.map((professeur, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/professeur/${professeur.id}`} color="link" size="sm">
                      {professeur.id}
                    </Button>
                  </td>
                  <td>{professeur.nom}</td>
                  <td>{professeur.postnom}</td>
                  <td>{professeur.prenom}</td>
                  <td>{professeur.nomcours}</td>
                  <td>{professeur.adresse}</td>
                  <td>{professeur.mail}</td>
                  <td>
                    {professeur.administrateur ? (
                      <Link to={`/administrateur/${professeur.administrateur.id}`}>{professeur.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/professeur/${professeur.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/professeur/${professeur.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/professeur/${professeur.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="gestionEtudiantApp.professeur.home.notFound">No Professeurs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Professeur;
