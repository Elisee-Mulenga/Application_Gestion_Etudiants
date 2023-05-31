import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IResultat } from 'app/shared/model/resultat.model';
import { getEntities } from './resultat.reducer';

export const Resultat = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const resultatList = useAppSelector(state => state.resultat.entities);
  const loading = useAppSelector(state => state.resultat.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="resultat-heading" data-cy="ResultatHeading">
        <Translate contentKey="gestionEtudiantApp.resultat.home.title">Resultats</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.resultat.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/resultat/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.resultat.home.createLabel">Create new Resultat</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {resultatList && resultatList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.resultat.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.resultat.moyenne">Moyenne</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {resultatList.map((resultat, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/resultat/${resultat.id}`} color="link" size="sm">
                      {resultat.id}
                    </Button>
                  </td>
                  <td>{resultat.moyenne}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/resultat/${resultat.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/resultat/${resultat.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/resultat/${resultat.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="gestionEtudiantApp.resultat.home.notFound">No Resultats found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Resultat;
