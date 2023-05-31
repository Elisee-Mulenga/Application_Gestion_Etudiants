import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface ICollecteinfo {
  id?: number;
  infosperson?: string | null;
  infosacadem?: string | null;
  infosadmi?: string | null;
  administrateur?: IAdministrateur | null;
}

export const defaultValue: Readonly<ICollecteinfo> = {};
