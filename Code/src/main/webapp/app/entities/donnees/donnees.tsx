import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDonnees } from 'app/shared/model/donnees.model';
import { getEntities } from './donnees.reducer';

export const Donnees = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const donneesList = useAppSelector(state => state.donnees.entities);
  const loading = useAppSelector(state => state.donnees.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="donnees-heading" data-cy="DonneesHeading">
        <Translate contentKey="gestionEtudiantApp.donnees.home.title">Donnees</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.donnees.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/donnees/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.donnees.home.createLabel">Create new Donnees</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {donneesList && donneesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.donnees.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.donnees.updateinfo">Updateinfo</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {donneesList.map((donnees, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/donnees/${donnees.id}`} color="link" size="sm">
                      {donnees.id}
                    </Button>
                  </td>
                  <td>{donnees.updateinfo}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/donnees/${donnees.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/donnees/${donnees.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/donnees/${donnees.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="gestionEtudiantApp.donnees.home.notFound">No Donnees found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Donnees;
