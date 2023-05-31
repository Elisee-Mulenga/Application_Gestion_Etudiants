import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGestioninscrip } from 'app/shared/model/gestioninscrip.model';
import { getEntities } from './gestioninscrip.reducer';

export const Gestioninscrip = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const gestioninscripList = useAppSelector(state => state.gestioninscrip.entities);
  const loading = useAppSelector(state => state.gestioninscrip.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="gestioninscrip-heading" data-cy="GestioninscripHeading">
        <Translate contentKey="gestionEtudiantApp.gestioninscrip.home.title">Gestioninscrips</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.gestioninscrip.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/gestioninscrip/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.gestioninscrip.home.createLabel">Create new Gestioninscrip</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {gestioninscripList && gestioninscripList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestioninscrip.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.gestioninscrip.administrateur">Administrateur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {gestioninscripList.map((gestioninscrip, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/gestioninscrip/${gestioninscrip.id}`} color="link" size="sm">
                      {gestioninscrip.id}
                    </Button>
                  </td>
                  <td>
                    {gestioninscrip.administrateur ? (
                      <Link to={`/administrateur/${gestioninscrip.administrateur.id}`}>{gestioninscrip.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/gestioninscrip/${gestioninscrip.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/gestioninscrip/${gestioninscrip.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/gestioninscrip/${gestioninscrip.id}/delete`}
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
              <Translate contentKey="gestionEtudiantApp.gestioninscrip.home.notFound">No Gestioninscrips found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Gestioninscrip;
