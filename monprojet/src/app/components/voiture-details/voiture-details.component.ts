import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Voiture } from 'src/app/models/voiture.model';
import { VoitureService } from 'src/app/services/voiture.service';
import { Garage } from 'src/app/models/garage.model';
import { GarageData } from 'src/app/models/garage-data.model';
import { GarageService } from 'src/app/services/garage.service';

@Component({
  selector: 'app-voiture-details',
  templateUrl: './voiture-details.component.html',
  styleUrls: ['./voiture-details.component.css'],
})
export class VoitureDetailsComponent implements OnInit {
  voiture: Voiture = {};
  isEditing: boolean = false;
  cancelMessage: string = '';
  successMessage: string = '';
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
    private garageService: GarageService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!isNaN(id)) {
      this.voitureService.getVoitureById(id).subscribe((voiture: Voiture) => {
        this.voiture = voiture;
      });
    }
    const currentYear = new Date().getFullYear();
    for (let i = currentYear; i >= 1990; i--) {
      this.years.push(i);
    }
    this.getGarages();
  }

  editVoiture(): void {
    this.isEditing = true;
  }

  cancelEdit(): void {
    if (this.voiture.id_voiture) {
      this.voitureService
        .getVoitureById(this.voiture.id_voiture)
        .subscribe((voiture: Voiture) => {
          this.voiture = voiture;
        });
    } else {
      this.voiture = {};
    }
    this.isEditing = false;
    this.cancelMessage = 'Les modifications ont été annulées.';
    this.successMessage = '';
    this.ville = '';
    this.nom = '';
  }

  saveVoiture(): void {
    this.voitureService
      .updateVoiture(this.voiture.id_voiture || 0, this.voiture)
      .subscribe((voiture: Voiture) => {
        this.voiture = voiture;
        this.isEditing = false;
        this.successMessage = 'Les modifications ont été enregistrées.';
        this.cancelMessage = '';
        this.ville = '';
        this.nom = '';
      });
    this.isEditing = false;
    this.successMessage = 'Les modifications ont été enregistrées.';
    this.cancelMessage = '';
    this.ville = '';
    this.nom = '';
  }

  deleteVoiture(): void {
    const confirmation = confirm(
      'Êtes-vous sûr de vouloir supprimer cette voiture ?'
    );
    if (confirmation) {
      this.voitureService
        .deleteVoiture(this.voiture.id_voiture || 0)
        .subscribe(() => {
          this.router.navigate(['/voitures']);
        });
    }
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

  getCurrentYear(): number {
    return new Date().getFullYear();
  }
}
