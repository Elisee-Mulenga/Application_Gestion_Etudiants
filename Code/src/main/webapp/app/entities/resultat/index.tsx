import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Resultat from './resultat';
import ResultatDetail from './resultat-detail';
import ResultatUpdate from './resultat-update';
import ResultatDeleteDialog from './resultat-delete-dialog';

const ResultatRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Resultat />} />
    <Route path="new" element={<ResultatUpdate />} />
    <Route path=":id">
      <Route index element={<ResultatDetail />} />
      <Route path="edit" element={<ResultatUpdate />} />
      <Route path="delete" element={<ResultatDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ResultatRoutes;
