import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Voiture } from '../models/voiture.model';
import { VoitureData } from '../models/voiture-data.model';
import { GarageData } from '../models/garage-data.model';

@Injectable({
  providedIn: 'root',
})
export class VoitureService {
  private baseUrl = 'http://localhost:8080/api/voitures';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  getAllVoitures(
    page: number,
    perPage: number,
    criterias: any
  ): Observable<VoitureData> {
    let url = `${this.baseUrl}?page=${page}&perPage=${perPage}`;
    if (criterias) {
      if (criterias.KilometreMin) {
        url += `&KilometreMin=${criterias.KilometreMin}`;
      }
      if (criterias.KilometreMax) {
        url += `&KilometreMax=${criterias.KilometreMax}`;
      }
      if (criterias.Marque) {
        url += `&Marque=${criterias.Marque}`;
      }
      if (criterias.Modele) {
        url += `&Modele=${criterias.Modele}`;
      }
      if (criterias.AnneeMin) {
        url += `&AnneeMin=${criterias.AnneeMin}`;
      }
      if (criterias.AnneeMax) {
        url += `&AnneeMax=${criterias.AnneeMax}`;
      }
      if (criterias.Carburant) {
        url += `&Carburant=${criterias.Carburant.join(',')}`;
      }
      if (criterias.Garage) {
        url += `&Garage=${criterias.Garage}`;
      }
    }
    return this.http.get<VoitureData>(url);
  }

  getVoitureById(id: number): Observable<Voiture> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Voiture>(url).pipe(
      map((voiture) => {
        if (!voiture.garage) {
          voiture.garage = { id_garage: 0 };
        }
        return voiture;
      })
    );
  }

  addVoiture(voiture: Voiture): Observable<Voiture> {
    return this.http.post<Voiture>(this.baseUrl, voiture, {
      headers: this.httpHeaders,
    });
  }

  updateVoiture(id: number, voiture: Voiture): Observable<Voiture> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Voiture>(url, voiture, { headers: this.httpHeaders });
  }

  deleteVoiture(id: number): Observable<any> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete(url, { responseType: 'text' });
  }

  getAllGarages(
    page: number,
    perPage: number,
    nom: string,
    ville: string
  ): Observable<GarageData> {
    const url = `http://localhost:8080/api/garages?page=${page}&perPage=${perPage}&nom=${nom}&ville=${ville}`;
    return this.http.get<GarageData>(url);
  }
}
