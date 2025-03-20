import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../models/client.model';

/**
 * Service de gestion des clients
 * Fournit les méthodes pour interagir avec l'API backend des clients
 */
@Injectable({
  providedIn: 'root'
})
export class ClientService {
  /** URL de base de l'API des clients */
  private apiUrl = 'http://localhost:8080/api/clients';

  /**
   * Constructeur du service
   * @param http Client HTTP pour effectuer les requêtes vers l'API
   */
  constructor(private http: HttpClient) {}

  /**
   * Récupère la liste de tous les clients depuis l'API
   * @returns Observable contenant un tableau de clients
   */
  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }
}