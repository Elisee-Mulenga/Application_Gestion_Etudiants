import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Donnees from './donnees';
import DonneesDetail from './donnees-detail';
import DonneesUpdate from './donnees-update';
import DonneesDeleteDialog from './donnees-delete-dialog';

const DonneesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Donnees />} />
    <Route path="new" element={<DonneesUpdate />} />
    <Route path=":id">
      <Route index element={<DonneesDetail />} />
      <Route path="edit" element={<DonneesUpdate />} />
      <Route path="delete" element={<DonneesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DonneesRoutes;
