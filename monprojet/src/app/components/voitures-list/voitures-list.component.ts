import { Component, OnInit } from '@angular/core';
import { Voiture } from 'src/app/models/voiture.model';
import { VoitureData } from 'src/app/models/voiture-data.model';
import { VoitureService } from 'src/app/services/voiture.service';

@Component({
  selector: 'app-voitures-list',
  templateUrl: './voitures-list.component.html',
  styleUrls: ['./voitures-list.component.css'],
})
export class VoituresListComponent implements OnInit {
  voitures: Voiture[] = [];
  criterias: any = {};
  selectedCarburants: string[] = [];
  page: number = 1;
  perPage: number = 6;
  total: number = 0;
  totalPages: number = 0;

  constructor(private voitureService: VoitureService) {}

  ngOnInit(): void {
    this.getVoitures();
  }

  getVoitures(): void {
    this.voitureService
      .getAllVoitures(this.page, this.perPage, this.criterias)
      .subscribe((voitures: VoitureData) => {
        this.voitures = voitures.data;
        this.total = voitures.total;
        this.totalPages = voitures.total_pages;
      });
  }

  onCarburantChange(carburant: string): void {
    const index = this.selectedCarburants.indexOf(carburant);
    if (index === -1) {
      this.selectedCarburants.push(carburant);
    } else {
      this.selectedCarburants.splice(index, 1);
    }
    this.search();
  }

  search(): void {
    this.page = 1;
    this.criterias.Carburant = this.selectedCarburants;
    this.getVoitures();
  }

  getPageNumbers(): number[] {
    const pageNumbers: number[] = [];
    for (let i = 1; i <= this.totalPages; i++) {
      pageNumbers.push(i);
    }
    return pageNumbers;
  }

  onPageChange(page: number): void {
    this.page = page;
    this.getVoitures();
  }
}
