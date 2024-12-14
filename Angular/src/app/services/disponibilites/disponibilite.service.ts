import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../config/environment';


export interface Disponibilite {
  id: number;
  professionalId: number;
  dateDebut: string;
  dateFin: string;
}

@Injectable({
  providedIn: 'root',
})
export class DisponibilitesService {
  private apiUrl = `${environment.apiBaseUrl}/disponibilites`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Disponibilite[]> {
    return this.http.get<Disponibilite[]>(this.apiUrl);
  }

  getById(id: number): Observable<Disponibilite> {
    return this.http.get<Disponibilite>(`${this.apiUrl}/${id}`);
  }

  getByProfessionalId(professionalId: number): Observable<Disponibilite[]> {
    return this.http.get<Disponibilite[]>(`${this.apiUrl}/professional/${professionalId}`);
  }

  create(disponibilite: Disponibilite): Observable<Disponibilite> {
    return this.http.post<Disponibilite>(this.apiUrl, disponibilite);
  }

  update(id: number, disponibilite: Disponibilite): Observable<Disponibilite> {
    return this.http.patch<Disponibilite>(`${this.apiUrl}/${id}`, disponibilite);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
