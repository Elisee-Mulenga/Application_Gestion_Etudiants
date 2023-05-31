import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './collecteinfo.reducer';

export const CollecteinfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const collecteinfoEntity = useAppSelector(state => state.collecteinfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="collecteinfoDetailsHeading">
          <Translate contentKey="gestionEtudiantApp.collecteinfo.detail.title">Collecteinfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{collecteinfoEntity.id}</dd>
          <dt>
            <span id="infosperson">
              <Translate contentKey="gestionEtudiantApp.collecteinfo.infosperson">Infosperson</Translate>
            </span>
          </dt>
          <dd>{collecteinfoEntity.infosperson}</dd>
          <dt>
            <span id="infosacadem">
              <Translate contentKey="gestionEtudiantApp.collecteinfo.infosacadem">Infosacadem</Translate>
            </span>
          </dt>
          <dd>{collecteinfoEntity.infosacadem}</dd>
          <dt>
            <span id="infosadmi">
              <Translate contentKey="gestionEtudiantApp.collecteinfo.infosadmi">Infosadmi</Translate>
            </span>
          </dt>
          <dd>{collecteinfoEntity.infosadmi}</dd>
          <dt>
            <Translate contentKey="gestionEtudiantApp.collecteinfo.administrateur">Administrateur</Translate>
          </dt>
          <dd>{collecteinfoEntity.administrateur ? collecteinfoEntity.administrateur.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/collecteinfo" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/collecteinfo/${collecteinfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CollecteinfoDetail;
