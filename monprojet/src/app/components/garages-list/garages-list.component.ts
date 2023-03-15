import { Component, OnInit } from '@angular/core';
import { Garage } from 'src/app/models/garage.model';
import { GarageData } from 'src/app/models/garage-data.model';
import { GarageService } from 'src/app/services/garage.service';

@Component({
  selector: 'app-garages-list',
  templateUrl: './garages-list.component.html',
  styleUrls: ['./garages-list.component.css'],
})
export class GaragesListComponent implements OnInit {
  garages: Garage[] = [];
  page: number = 1;
  perPage: number = 6;
  total: number = 0;
  totalPages: number = 0;
  nom: string = '';
  ville: string = '';

  constructor(private garageService: GarageService) {}

  ngOnInit(): void {
    this.getGarages();
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

  getPageNumbers(): number[] {
    const pageNumbers: number[] = [];
    for (let i = 1; i <= this.totalPages; i++) {
      pageNumbers.push(i);
    }
    return pageNumbers;
  }

  onPageChange(page: number): void {
    this.page = page;
    this.getGarages();
  }
}
