import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEmploiTemps } from 'app/shared/model/emploi-temps.model';
import { getEntities } from './emploi-temps.reducer';

export const EmploiTemps = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const emploiTempsList = useAppSelector(state => state.emploiTemps.entities);
  const loading = useAppSelector(state => state.emploiTemps.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="emploi-temps-heading" data-cy="EmploiTempsHeading">
        <Translate contentKey="gestionEtudiantApp.emploiTemps.home.title">Emploi Temps</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.emploiTemps.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/emploi-temps/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.emploiTemps.home.createLabel">Create new Emploi Temps</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {emploiTempsList && emploiTempsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.cours">Cours</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.semestre">Semestre</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.timestre">Timestre</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.horairecours">Horairecours</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.horaireexam">Horaireexam</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.activite">Activite</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.etudaint">Etudaint</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.emploiTemps.administrateur">Administrateur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {emploiTempsList.map((emploiTemps, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/emploi-temps/${emploiTemps.id}`} color="link" size="sm">
                      {emploiTemps.id}
                    </Button>
                  </td>
                  <td>{emploiTemps.cours}</td>
                  <td>{emploiTemps.semestre}</td>
                  <td>{emploiTemps.timestre}</td>
                  <td>{emploiTemps.horairecours}</td>
                  <td>{emploiTemps.horaireexam}</td>
                  <td>{emploiTemps.activite}</td>
                  <td>{emploiTemps.etudaint ? <Link to={`/etudiant/${emploiTemps.etudaint.id}`}>{emploiTemps.etudaint.id}</Link> : ''}</td>
                  <td>
                    {emploiTemps.administrateur ? (
                      <Link to={`/administrateur/${emploiTemps.administrateur.id}`}>{emploiTemps.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/emploi-temps/${emploiTemps.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/emploi-temps/${emploiTemps.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/emploi-temps/${emploiTemps.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
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
              <Translate contentKey="gestionEtudiantApp.emploiTemps.home.notFound">No Emploi Temps found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default EmploiTemps;
