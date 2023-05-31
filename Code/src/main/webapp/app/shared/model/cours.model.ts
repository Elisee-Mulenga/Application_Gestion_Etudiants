import { IEtudiant } from 'app/shared/model/etudiant.model';
import { IProfesseur } from 'app/shared/model/professeur.model';

export interface ICours {
  id?: number;
  titre?: string | null;
  note?: number | null;
  etudiant?: IEtudiant | null;
  professeur?: IProfesseur | null;
}

export const defaultValue: Readonly<ICours> = {};
