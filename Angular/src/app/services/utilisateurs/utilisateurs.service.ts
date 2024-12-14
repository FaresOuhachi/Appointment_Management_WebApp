import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../config/environment';


export interface Utilisateur {
  id: number;
  nom: string;
  motDePasse: string;
  email: string;
  role: string;
}

@Injectable({
  providedIn: 'root',
})
export class UtilisateursService {
  private apiUrl = `${environment.apiBaseUrl}/utilisateurs`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.apiUrl);
  }

  getClients(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiUrl}/clients`);
  }

  getProfessionals(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiUrl}/professionals`);
  }

  getById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.apiUrl}/${id}`);
  }

  create(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(this.apiUrl, utilisateur);
  }

  update(id: number, utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.patch<Utilisateur>(`${this.apiUrl}/${id}`, utilisateur);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
