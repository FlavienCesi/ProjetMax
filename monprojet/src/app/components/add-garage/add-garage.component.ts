import { Component, OnInit } from '@angular/core';
import { Garage } from 'src/app/models/garage.model';
import { GarageService } from 'src/app/services/garage.service';

@Component({
  selector: 'app-add-garage',
  templateUrl: './add-garage.component.html',
  styleUrls: ['./add-garage.component.css'],
})
export class AddGarageComponent implements OnInit {
  newGarage: Garage = new Garage();
  submitted = false;
  successMessage = '';
  errorMessage = '';

  constructor(private garageService: GarageService) {}

  ngOnInit(): void {}

  addGarage() {
    const newVille = { ville: this.newGarage.ville };
    const newGarageWithVille = Object.assign({}, this.newGarage, {
      ville: newVille,
    });
    this.garageService.addGarage(newGarageWithVille).subscribe(
      () => {
        this.submitted = true;
        this.successMessage = 'Le garage a été ajouté avec succès.';
        this.errorMessage = '';
        this.newGarage = new Garage();
      },
      (error) => {
        this.submitted = true;
        this.successMessage = '';
        this.errorMessage = "Erreur lors de l'ajout du garage: " + error;
      }
    );
  }
}
