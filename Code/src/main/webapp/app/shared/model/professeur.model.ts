import { ICours } from 'app/shared/model/cours.model';
import { ICommunication } from 'app/shared/model/communication.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IProfesseur {
  id?: number;
  nom?: string | null;
  postnom?: string | null;
  prenom?: string | null;
  nomcours?: string | null;
  adresse?: string | null;
  mail?: string | null;
  cours?: ICours[] | null;
  communications?: ICommunication[] | null;
  administrateur?: IAdministrateur | null;
}

export const defaultValue: Readonly<IProfesseur> = {};
