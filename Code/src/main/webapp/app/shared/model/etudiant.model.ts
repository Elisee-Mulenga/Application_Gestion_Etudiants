import { IInscription } from 'app/shared/model/inscription.model';
import { IDossiersacademique } from 'app/shared/model/dossiersacademique.model';
import { IDonnees } from 'app/shared/model/donnees.model';
import { IResultat } from 'app/shared/model/resultat.model';
import { IDocument } from 'app/shared/model/document.model';
import { IProgression } from 'app/shared/model/progression.model';
import { ICours } from 'app/shared/model/cours.model';
import { IEmploiTemps } from 'app/shared/model/emploi-temps.model';
import { ICommunication } from 'app/shared/model/communication.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IEtudiant {
  id?: number;
  nom?: string | null;
  postnom?: string | null;
  prenom?: string | null;
  genre?: string | null;
  dateNaissance?: string | null;
  adresse?: string | null;
  matricule?: string | null;
  promotion?: string | null;
  inscription?: IInscription | null;
  dossiersacademique?: IDossiersacademique | null;
  donnees?: IDonnees | null;
  resultat?: IResultat | null;
  document?: IDocument | null;
  progression?: IProgression | null;
  cours?: ICours[] | null;
  emploiTemps?: IEmploiTemps[] | null;
  communications?: ICommunication[] | null;
  administrateur?: IAdministrateur | null;
}

export const defaultValue: Readonly<IEtudiant> = {};
