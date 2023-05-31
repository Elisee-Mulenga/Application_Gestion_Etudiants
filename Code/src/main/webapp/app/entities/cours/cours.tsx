import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICours } from 'app/shared/model/cours.model';
import { getEntities } from './cours.reducer';

export const Cours = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const coursList = useAppSelector(state => state.cours.entities);
  const loading = useAppSelector(state => state.cours.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="cours-heading" data-cy="CoursHeading">
        <Translate contentKey="gestionEtudiantApp.cours.home.title">Cours</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.cours.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/cours/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.cours.home.createLabel">Create new Cours</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {coursList && coursList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.cours.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.cours.titre">Titre</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.cours.note">Note</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.cours.etudiant">Etudiant</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.cours.professeur">Professeur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {coursList.map((cours, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/cours/${cours.id}`} color="link" size="sm">
                      {cours.id}
                    </Button>
                  </td>
                  <td>{cours.titre}</td>
                  <td>{cours.note}</td>
                  <td>{cours.etudiant ? <Link to={`/etudiant/${cours.etudiant.id}`}>{cours.etudiant.id}</Link> : ''}</td>
                  <td>{cours.professeur ? <Link to={`/professeur/${cours.professeur.id}`}>{cours.professeur.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/cours/${cours.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/cours/${cours.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/cours/${cours.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="gestionEtudiantApp.cours.home.notFound">No Cours found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Cours;
