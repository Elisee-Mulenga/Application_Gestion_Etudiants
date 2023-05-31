import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IGestionInfos {
  id?: number;
  infosperson?: string | null;
  infosacadem?: string | null;
  infosfinance?: string | null;
  administrateur?: IAdministrateur | null;
}

export const defaultValue: Readonly<IGestionInfos> = {};
