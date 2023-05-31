import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Progression from './progression';
import ProgressionDetail from './progression-detail';
import ProgressionUpdate from './progression-update';
import ProgressionDeleteDialog from './progression-delete-dialog';

const ProgressionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Progression />} />
    <Route path="new" element={<ProgressionUpdate />} />
    <Route path=":id">
      <Route index element={<ProgressionDetail />} />
      <Route path="edit" element={<ProgressionUpdate />} />
      <Route path="delete" element={<ProgressionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ProgressionRoutes;
