import { Component, OnInit } from '@angular/core';
import { Voiture } from 'src/app/models/voiture.model';
import { VoitureService } from 'src/app/services/voiture.service';
import { Garage } from 'src/app/models/garage.model';
import { GarageData } from 'src/app/models/garage-data.model';
import { GarageService } from 'src/app/services/garage.service';

@Component({
  selector: 'app-add-voiture',
  templateUrl: './add-voiture.component.html',
  styleUrls: ['./add-voiture.component.css'],
})
export class AddVoitureComponent implements OnInit {
  newVoiture: Voiture = new Voiture();
  submitted = false;
  successMessage = '';
  errorMessage = '';
  years: number[] = [];

  garages: Garage[] = [];
  page: number = 1;
  perPage: number = 50;
  total: number = 0;
  totalPages: number = 0;
  nom: string = '';
  ville: string = '';

  constructor(
    private voitureService: VoitureService,
    private garageService: GarageService
  ) {}

  ngOnInit() {
    const currentYear = new Date().getFullYear();
    for (let i = currentYear; i >= 1990; i--) {
      this.years.push(i);
    }
    this.getGarages();
  }

  addVoiture() {
    const newMarque = { marque: this.newVoiture.marque };
    const newModele = { modele: this.newVoiture.modele };
    const newAnnee = { annee: this.newVoiture.annee };
    const newCarburant = { carburant: this.newVoiture.carburant };
    let newGarage = null;
    if (this.newVoiture.garage) {
      newGarage = { id_garage: this.newVoiture.garage };
    }

    const newVoitureWithObjects = Object.assign({}, this.newVoiture, {
      marque: newMarque,
      modele: newModele,
      annee: newAnnee,
      carburant: newCarburant,
      garage: newGarage,
    });
    this.voitureService.addVoiture(newVoitureWithObjects).subscribe(
      () => {
        this.submitted = true;
        this.successMessage = 'La voiture a été ajoutée avec succès.';
        this.errorMessage = '';
        this.newVoiture = new Voiture();
      },
      (error) => {
        this.submitted = true;
        this.successMessage = '';
        this.errorMessage = "Erreur lors de l'ajout de la voiture: " + error;
      }
    );
  }

  getCurrentYear(): number {
    return new Date().getFullYear();
  }

  getGarages(): void {
    this.garageService
      .getAllGarages(this.page, this.perPage, this.nom, this.ville)
      .subscribe((garages: GarageData) => {
        this.garages = garages.data;
        this.total = garages.total;
        this.totalPages = garages.total_pages;
      });
  }

  search(): void {
    this.page = 1;
    this.getGarages();
  }
}
