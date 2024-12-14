import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../config/environment';


export interface Rendezvous {
  id: number;
  statut: string;
  clientId: number;
  professionalId: number;
  disponibiliteId: number;
}

@Injectable({
  providedIn: 'root',
})
export class RendezvousService {
  private apiUrl = `${environment.apiBaseUrl}/rendezvous`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Rendezvous[]> {
    return this.http.get<Rendezvous[]>(this.apiUrl);
  }

  getById(id: number): Observable<Rendezvous> {
    return this.http.get<Rendezvous>(`${this.apiUrl}/${id}`);
  }

  getByClientId(clientId: number): Observable<Rendezvous[]> {
    return this.http.get<Rendezvous[]>(`${this.apiUrl}/client/${clientId}`);
  }

  getByProfessionalId(professionalId: number): Observable<Rendezvous[]> {
    return this.http.get<Rendezvous[]>(`${this.apiUrl}/professional/${professionalId}`);
  }

  getDetails(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}/details`);
  }

  create(rendezvous: Rendezvous): Observable<Rendezvous> {
    return this.http.post<Rendezvous>(this.apiUrl, rendezvous);
  }

  update(id: number, rendezvous: Rendezvous): Observable<Rendezvous> {
    return this.http.patch<Rendezvous>(`${this.apiUrl}/${id}`, rendezvous);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
