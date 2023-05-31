import { ICollecteinfo } from 'app/shared/model/collecteinfo.model';
import { IGestioninscrip } from 'app/shared/model/gestioninscrip.model';
import { IEtudiant } from 'app/shared/model/etudiant.model';
import { IProfesseur } from 'app/shared/model/professeur.model';
import { IGestionInfos } from 'app/shared/model/gestion-infos.model';
import { IEmploiTemps } from 'app/shared/model/emploi-temps.model';
import { ICommunication } from 'app/shared/model/communication.model';

export interface IAdministrateur {
  id?: number;
  nom?: string | null;
  postnom?: string | null;
  prenom?: string | null;
  adresse?: string | null;
  mail?: string | null;
  collecteinfos?: ICollecteinfo[] | null;
  gestioninscrips?: IGestioninscrip[] | null;
  etudiants?: IEtudiant[] | null;
  professeurs?: IProfesseur[] | null;
  gestionInfos?: IGestionInfos[] | null;
  emploiTemps?: IEmploiTemps[] | null;
  communications?: ICommunication[] | null;
}

export const defaultValue: Readonly<IAdministrateur> = {};
