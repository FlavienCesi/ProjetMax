import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Garage } from '../models/garage.model';
import { GarageData } from '../models/garage-data.model';

@Injectable({
  providedIn: 'root',
})
export class GarageService {
  private baseUrl = 'http://localhost:8080/api/garages';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  getAllGarages(
    page: number,
    perPage: number,
    nom: string,
    ville: string
  ): Observable<GarageData> {
    const url = `${this.baseUrl}?page=${page}&perPage=${perPage}&nom=${nom}&ville=${ville}`;
    return this.http.get<GarageData>(url);
  }

  getGarageById(id: number): Observable<Garage> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Garage>(url);
  }

  addGarage(garage: Garage): Observable<Garage> {
    return this.http.post<Garage>(this.baseUrl, garage, {
      headers: this.httpHeaders,
    });
  }

  updateGarage(id: number, garage: Garage): Observable<Garage> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Garage>(url, garage, { headers: this.httpHeaders });
  }

  deleteGarage(id: number): Observable<any> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete(url);
  }
}
