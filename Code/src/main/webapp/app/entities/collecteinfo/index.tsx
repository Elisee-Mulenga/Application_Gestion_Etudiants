import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Collecteinfo from './collecteinfo';
import CollecteinfoDetail from './collecteinfo-detail';
import CollecteinfoUpdate from './collecteinfo-update';
import CollecteinfoDeleteDialog from './collecteinfo-delete-dialog';

const CollecteinfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Collecteinfo />} />
    <Route path="new" element={<CollecteinfoUpdate />} />
    <Route path=":id">
      <Route index element={<CollecteinfoDetail />} />
      <Route path="edit" element={<CollecteinfoUpdate />} />
      <Route path="delete" element={<CollecteinfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CollecteinfoRoutes;
