import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Etudiant from './etudiant';
import Professeur from './professeur';
import Administrateur from './administrateur';
import Cours from './cours';
import Donnees from './donnees';
import Document from './document';
import Collecteinfo from './collecteinfo';
import GestionInfos from './gestion-infos';
import Inscription from './inscription';
import EmploiTemps from './emploi-temps';
import Gestioninscrip from './gestioninscrip';
import Dossiersacademique from './dossiersacademique';
import Communication from './communication';
import Resultat from './resultat';
import Progression from './progression';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="etudiant/*" element={<Etudiant />} />
        <Route path="professeur/*" element={<Professeur />} />
        <Route path="administrateur/*" element={<Administrateur />} />
        <Route path="cours/*" element={<Cours />} />
        <Route path="donnees/*" element={<Donnees />} />
        <Route path="document/*" element={<Document />} />
        <Route path="collecteinfo/*" element={<Collecteinfo />} />
        <Route path="gestion-infos/*" element={<GestionInfos />} />
        <Route path="inscription/*" element={<Inscription />} />
        <Route path="emploi-temps/*" element={<EmploiTemps />} />
        <Route path="gestioninscrip/*" element={<Gestioninscrip />} />
        <Route path="dossiersacademique/*" element={<Dossiersacademique />} />
        <Route path="communication/*" element={<Communication />} />
        <Route path="resultat/*" element={<Resultat />} />
        <Route path="progression/*" element={<Progression />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
