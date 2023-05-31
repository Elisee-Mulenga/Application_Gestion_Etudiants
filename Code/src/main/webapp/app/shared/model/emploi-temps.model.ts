import { IEtudiant } from 'app/shared/model/etudiant.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IEmploiTemps {
  id?: number;
  cours?: string | null;
  semestre?: string | null;
  timestre?: string | null;
  horairecours?: string | null;
  horaireexam?: string | null;
  activite?: string | null;
  etudaint?: IEtudiant | null;
  administrateur?: IAdministrateur | null;
}

export const defaultValue: Readonly<IEmploiTemps> = {};
