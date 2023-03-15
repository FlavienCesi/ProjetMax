import { Marque } from './marque';
import { Modele } from './modele';
import { Annee } from './annee';
import { Carburant } from './carburant';
import { Garage } from './garage.model';

export class Voiture {
  id_voiture?: number;
  plaque?: string;
  kilometre?: number;
  marque?: Marque;
  modele?: Modele;
  annee?: Annee;
  carburant?: Carburant;
  garage?: Garage;
}
