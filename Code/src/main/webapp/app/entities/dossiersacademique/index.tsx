import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Dossiersacademique from './dossiersacademique';
import DossiersacademiqueDetail from './dossiersacademique-detail';
import DossiersacademiqueUpdate from './dossiersacademique-update';
import DossiersacademiqueDeleteDialog from './dossiersacademique-delete-dialog';

const DossiersacademiqueRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Dossiersacademique />} />
    <Route path="new" element={<DossiersacademiqueUpdate />} />
    <Route path=":id">
      <Route index element={<DossiersacademiqueDetail />} />
      <Route path="edit" element={<DossiersacademiqueUpdate />} />
      <Route path="delete" element={<DossiersacademiqueDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DossiersacademiqueRoutes;
