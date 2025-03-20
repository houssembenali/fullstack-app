import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Client } from '../../models/client.model';
import { ClientState } from '../../store/reducers/client.reducer';
import * as ClientActions from '../../store/actions/client.actions';

/**
 * Composant de liste des clients
 * Affiche la liste des clients et gère leur chargement via NgRx
 */
@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  /** Observable contenant la liste des clients */
  clients$: Observable<Client[]>;
  
  /** Observable indiquant si les données sont en cours de chargement */
  loading$: Observable<boolean>;

  /**
   * Constructeur du composant
   * @param store Store NgRx pour la gestion de l'état des clients
   */
  constructor(private store: Store<{ client: ClientState }>) {
    this.clients$ = store.select(state => state.client.clients);
    this.loading$ = store.select(state => state.client.loading);
  }

  /**
   * Initialisation du composant
   * Déclenche le chargement des clients au démarrage
   */
  ngOnInit() {
    this.store.dispatch(ClientActions.loadClients());
  }
}