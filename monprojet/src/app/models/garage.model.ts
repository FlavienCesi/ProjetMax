import { Ville } from './ville.model';

export class Garage {
  id_garage?: number | null;
  nom?: string;
  numeroTelephone?: string;
  mail?: string;
  ville?: Ville;
}
