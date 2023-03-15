import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Voiture } from 'src/app/models/voiture.model';
import { VoitureService } from 'src/app/services/voiture.service';

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

  constructor(
    private voitureService: VoitureService,
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
  }

  saveVoiture(): void {
    this.voitureService
      .updateVoiture(this.voiture.id_voiture || 0, this.voiture)
      .subscribe((voiture: Voiture) => {
        this.voiture = voiture;
        this.isEditing = false;
        this.successMessage = 'Les modifications ont été enregistrées.';
        this.cancelMessage = '';
      });
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
}
