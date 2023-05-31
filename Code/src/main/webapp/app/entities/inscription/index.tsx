import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Inscription from './inscription';
import InscriptionDetail from './inscription-detail';
import InscriptionUpdate from './inscription-update';
import InscriptionDeleteDialog from './inscription-delete-dialog';

const InscriptionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Inscription />} />
    <Route path="new" element={<InscriptionUpdate />} />
    <Route path=":id">
      <Route index element={<InscriptionDetail />} />
      <Route path="edit" element={<InscriptionUpdate />} />
      <Route path="delete" element={<InscriptionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InscriptionRoutes;
