import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import GestionInfos from './gestion-infos';
import GestionInfosDetail from './gestion-infos-detail';
import GestionInfosUpdate from './gestion-infos-update';
import GestionInfosDeleteDialog from './gestion-infos-delete-dialog';

const GestionInfosRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<GestionInfos />} />
    <Route path="new" element={<GestionInfosUpdate />} />
    <Route path=":id">
      <Route index element={<GestionInfosDetail />} />
      <Route path="edit" element={<GestionInfosUpdate />} />
      <Route path="delete" element={<GestionInfosDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GestionInfosRoutes;
