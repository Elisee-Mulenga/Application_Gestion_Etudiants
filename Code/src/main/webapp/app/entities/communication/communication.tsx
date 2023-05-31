import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICommunication } from 'app/shared/model/communication.model';
import { getEntities } from './communication.reducer';

export const Communication = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const communicationList = useAppSelector(state => state.communication.entities);
  const loading = useAppSelector(state => state.communication.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="communication-heading" data-cy="CommunicationHeading">
        <Translate contentKey="gestionEtudiantApp.communication.home.title">Communications</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gestionEtudiantApp.communication.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/communication/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gestionEtudiantApp.communication.home.createLabel">Create new Communication</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {communicationList && communicationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.destinataire">Destinataire</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.expeditaire">Expeditaire</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.forum">Forum</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.annonce">Annonce</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.administrateur">Administrateur</Translate>
                </th>
                <th>
                  <Translate contentKey="gestionEtudiantApp.communication.professeur">Professeur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {communicationList.map((communication, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/communication/${communication.id}`} color="link" size="sm">
                      {communication.id}
                    </Button>
                  </td>
                  <td>{communication.destinataire}</td>
                  <td>{communication.expeditaire}</td>
                  <td>{communication.forum}</td>
                  <td>{communication.annonce}</td>
                  <td>
                    {communication.administrateur ? (
                      <Link to={`/administrateur/${communication.administrateur.id}`}>{communication.administrateur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {communication.professeur ? (
                      <Link to={`/professeur/${communication.professeur.id}`}>{communication.professeur.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/communication/${communication.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/communication/${communication.id}/edit`}
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
                        to={`/communication/${communication.id}/delete`}
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
              <Translate contentKey="gestionEtudiantApp.communication.home.notFound">No Communications found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Communication;
