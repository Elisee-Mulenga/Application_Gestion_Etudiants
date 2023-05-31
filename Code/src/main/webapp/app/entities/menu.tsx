import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/etudiant">
        <Translate contentKey="global.menu.entities.etudiant" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/professeur">
        <Translate contentKey="global.menu.entities.professeur" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/administrateur">
        <Translate contentKey="global.menu.entities.administrateur" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/cours">
        <Translate contentKey="global.menu.entities.cours" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/donnees">
        <Translate contentKey="global.menu.entities.donnees" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/document">
        <Translate contentKey="global.menu.entities.document" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/collecteinfo">
        <Translate contentKey="global.menu.entities.collecteinfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/gestion-infos">
        <Translate contentKey="global.menu.entities.gestionInfos" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/inscription">
        <Translate contentKey="global.menu.entities.inscription" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/emploi-temps">
        <Translate contentKey="global.menu.entities.emploiTemps" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/gestioninscrip">
        <Translate contentKey="global.menu.entities.gestioninscrip" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/dossiersacademique">
        <Translate contentKey="global.menu.entities.dossiersacademique" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/communication">
        <Translate contentKey="global.menu.entities.communication" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/resultat">
        <Translate contentKey="global.menu.entities.resultat" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/progression">
        <Translate contentKey="global.menu.entities.progression" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
