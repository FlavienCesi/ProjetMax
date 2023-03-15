import { Component, OnInit } from '@angular/core';
import { Voiture } from 'src/app/models/voiture.model';
import { VoitureService } from 'src/app/services/voiture.service';

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

  constructor(private voitureService: VoitureService) {}

  ngOnInit() {
    const currentYear = new Date().getFullYear();
    for (let i = currentYear; i >= 1990; i--) {
      this.years.push(i);
    }
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
}
