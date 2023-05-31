import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Gestioninscrip from './gestioninscrip';
import GestioninscripDetail from './gestioninscrip-detail';
import GestioninscripUpdate from './gestioninscrip-update';
import GestioninscripDeleteDialog from './gestioninscrip-delete-dialog';

const GestioninscripRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Gestioninscrip />} />
    <Route path="new" element={<GestioninscripUpdate />} />
    <Route path=":id">
      <Route index element={<GestioninscripDetail />} />
      <Route path="edit" element={<GestioninscripUpdate />} />
      <Route path="delete" element={<GestioninscripDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GestioninscripRoutes;
