import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Garage } from 'src/app/models/garage.model';
import { GarageService } from 'src/app/services/garage.service';

@Component({
  selector: 'app-garage-details',
  templateUrl: './garage-details.component.html',
  styleUrls: ['./garage-details.component.css'],
})
export class GarageDetailsComponent implements OnInit {
  garage: Garage = {};
  isEditing: boolean = false;
  cancelMessage: string = '';
  successMessage: string = '';

  constructor(
    private garageService: GarageService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!isNaN(id)) {
      this.garageService.getGarageById(id).subscribe((garage: Garage) => {
        this.garage = garage;
      });
    }
  }

  editGarage(): void {
    this.isEditing = true;
  }

  cancelEdit(): void {
    if (this.garage.id_garage) {
      this.garageService
        .getGarageById(this.garage.id_garage)
        .subscribe((garage: Garage) => {
          this.garage = garage;
        });
    } else {
      this.garage = {};
    }
    this.isEditing = false;
    this.cancelMessage = 'Les modifications ont été annulées.';
    this.successMessage = '';
  }

  saveGarage(): void {
    this.garageService
      .updateGarage(this.garage.id_garage || 0, this.garage)
      .subscribe((garage: Garage) => {
        this.garage = garage;
        this.isEditing = false;
        this.successMessage = 'Les modifications ont été enregistrées.';
        this.cancelMessage = '';
      });
  }

  deleteGarage(): void {
    const confirmation = confirm(
      'Êtes-vous sûr de vouloir supprimer ce garage ?'
    );
    if (confirmation) {
      this.garageService
        .deleteGarage(this.garage.id_garage || 0)
        .subscribe(() => {
          this.router.navigate(['/garages']);
        });
    }
  }
}
