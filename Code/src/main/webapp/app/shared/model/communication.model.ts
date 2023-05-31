import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { IProfesseur } from 'app/shared/model/professeur.model';
import { IEtudiant } from 'app/shared/model/etudiant.model';

export interface ICommunication {
  id?: number;
  destinataire?: string | null;
  expeditaire?: string | null;
  forum?: string | null;
  annonce?: string | null;
  administrateur?: IAdministrateur | null;
  professeur?: IProfesseur | null;
  etudiants?: IEtudiant[] | null;
}

export const defaultValue: Readonly<ICommunication> = {};
