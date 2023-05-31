import etudiant from 'app/entities/etudiant/etudiant.reducer';
import professeur from 'app/entities/professeur/professeur.reducer';
import administrateur from 'app/entities/administrateur/administrateur.reducer';
import cours from 'app/entities/cours/cours.reducer';
import donnees from 'app/entities/donnees/donnees.reducer';
import document from 'app/entities/document/document.reducer';
import collecteinfo from 'app/entities/collecteinfo/collecteinfo.reducer';
import gestionInfos from 'app/entities/gestion-infos/gestion-infos.reducer';
import inscription from 'app/entities/inscription/inscription.reducer';
import emploiTemps from 'app/entities/emploi-temps/emploi-temps.reducer';
import gestioninscrip from 'app/entities/gestioninscrip/gestioninscrip.reducer';
import dossiersacademique from 'app/entities/dossiersacademique/dossiersacademique.reducer';
import communication from 'app/entities/communication/communication.reducer';
import resultat from 'app/entities/resultat/resultat.reducer';
import progression from 'app/entities/progression/progression.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  etudiant,
  professeur,
  administrateur,
  cours,
  donnees,
  document,
  collecteinfo,
  gestionInfos,
  inscription,
  emploiTemps,
  gestioninscrip,
  dossiersacademique,
  communication,
  resultat,
  progression,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
