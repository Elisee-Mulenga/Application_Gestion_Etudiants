import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import EmploiTemps from './emploi-temps';
import EmploiTempsDetail from './emploi-temps-detail';
import EmploiTempsUpdate from './emploi-temps-update';
import EmploiTempsDeleteDialog from './emploi-temps-delete-dialog';

const EmploiTempsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<EmploiTemps />} />
    <Route path="new" element={<EmploiTempsUpdate />} />
    <Route path=":id">
      <Route index element={<EmploiTempsDetail />} />
      <Route path="edit" element={<EmploiTempsUpdate />} />
      <Route path="delete" element={<EmploiTempsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default EmploiTempsRoutes;
