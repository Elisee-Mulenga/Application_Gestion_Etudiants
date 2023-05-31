import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IGestioninscrip {
  id?: number;
  administrateur?: IAdministrateur | null;
}

export const defaultValue: Readonly<IGestioninscrip> = {};
