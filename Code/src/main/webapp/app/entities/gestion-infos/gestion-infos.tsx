import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGestionInfos } from 'app/shared/model/gestion-infos.model';
import { getEntities } from './gestion-infos.reducer';

export const GestionInfos = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const gestionInfosList = useAppSelector(state => state.gestionInfos.entities);
  const loading = useAppSelector(state => state.gestionInfos.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="gestion-infos-heading" data-cy="GestionInfosHeading">
        <Translate contentKey="gestionEtudiantApp.gestionInfos.home.title">Gestion Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.gestionInfos.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/gestion-infos/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.gestionInfos.home.createLabel">Create new Gestion Infos</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {gestionInfosList && gestionInfosList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestionInfos.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestionInfos.infosperson">Infosperson</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestionInfos.infosacadem">Infosacadem</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestionInfos.infosfinance">Infosfinance</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestionInfos.administrateur">Administrateur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {gestionInfosList.map((gestionInfos, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/gestion-infos/${gestionInfos.id}`} color="link" size="sm">
                      {gestionInfos.id}
                    </Button>
                  </td>
                  <td>{gestionInfos.infosperson}</td>
                  <td>{gestionInfos.infosacadem}</td>
                  <td>{gestionInfos.infosfinance}</td>
                  <td>
                    {gestionInfos.administrateur ? (
                      <Link to={`/administrateur/${gestionInfos.administrateur.id}`}>{gestionInfos.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/gestion-infos/${gestionInfos.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/gestion-infos/${gestionInfos.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/gestion-infos/${gestionInfos.id}/delete`}
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
              <Translate contentKey="gestionEtudiantApp.gestionInfos.home.notFound">No Gestion Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default GestionInfos;
