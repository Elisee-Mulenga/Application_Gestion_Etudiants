import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICollecteinfo } from 'app/shared/model/collecteinfo.model';
import { getEntities } from './collecteinfo.reducer';

export const Collecteinfo = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const collecteinfoList = useAppSelector(state => state.collecteinfo.entities);
  const loading = useAppSelector(state => state.collecteinfo.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="collecteinfo-heading" data-cy="CollecteinfoHeading">
        <Translate contentKey="gestionEtudiantApp.collecteinfo.home.title">Collecteinfos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.collecteinfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/collecteinfo/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.collecteinfo.home.createLabel">Create new Collecteinfo</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {collecteinfoList && collecteinfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.collecteinfo.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.collecteinfo.infosperson">Infosperson</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.collecteinfo.infosacadem">Infosacadem</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.collecteinfo.infosadmi">Infosadmi</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.collecteinfo.administrateur">Administrateur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {collecteinfoList.map((collecteinfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/collecteinfo/${collecteinfo.id}`} color="link" size="sm">
                      {collecteinfo.id}
                    </Button>
                  </td>
                  <td>{collecteinfo.infosperson}</td>
                  <td>{collecteinfo.infosacadem}</td>
                  <td>{collecteinfo.infosadmi}</td>
                  <td>
                    {collecteinfo.administrateur ? (
                      <Link to={`/administrateur/${collecteinfo.administrateur.id}`}>{collecteinfo.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/collecteinfo/${collecteinfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/collecteinfo/${collecteinfo.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/collecteinfo/${collecteinfo.id}/delete`}
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
              <Translate contentKey="gestionEtudiantApp.collecteinfo.home.notFound">No Collecteinfos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Collecteinfo;
