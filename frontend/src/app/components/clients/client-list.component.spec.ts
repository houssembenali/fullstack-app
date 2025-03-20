import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Store } from '@ngrx/store';
import { of } from 'rxjs';
import { ClientListComponent } from './client-list.component';
import { ClientService } from '../../services/client.service';
import * as ClientActions from '../../store/actions/client.actions';

/**
 * Suite de tests pour le composant ClientListComponent
 * Vérifie le bon fonctionnement de l'affichage et de la gestion des clients
 */
describe('ClientListComponent', () => {
  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;
  let clientService: jasmine.SpyObj<ClientService>;
  let store: jasmine.SpyObj<Store>;

  /** Données de test pour simuler la liste des clients */
  const mockClients = [
    { id: 1, firstName: 'Jean', lastName: 'Dupont', email: 'jean@example.com', phone: '+33123456789' },
    { id: 2, firstName: 'Marie', lastName: 'Martin', email: 'marie@example.com', phone: '+33987654321' }
  ];

  /**
   * Configuration initiale du module de test
   * Crée les espions pour le service client et le store
   */
  beforeEach(async () => {
    const clientServiceSpy = jasmine.createSpyObj('ClientService', ['getClients', 'deleteClient']);
    const storeSpy = jasmine.createSpyObj('Store', ['dispatch', 'select']);

    await TestBed.configureTestingModule({
      declarations: [ ClientListComponent ],
      providers: [
        { provide: ClientService, useValue: clientServiceSpy },
        { provide: Store, useValue: storeSpy }
      ]
    }).compileComponents();

    clientService = TestBed.inject(ClientService) as jasmine.SpyObj<ClientService>;
    store = TestBed.inject(Store) as jasmine.SpyObj<Store>;
  });

  /**
   * Configuration avant chaque test
   * Initialise le composant et configure le store pour retourner les clients mockés
   */
  beforeEach(() => {
    fixture = TestBed.createComponent(ClientListComponent);
    component = fixture.componentInstance;
    store.select.and.returnValue(of(mockClients));
    fixture.detectChanges();
  });

  /**
   * Vérifie que le composant est correctement créé
   */
  it('devrait créer le composant', () => {
    expect(component).toBeTruthy();
  });

  /**
   * Vérifie que l'action de chargement des clients est déclenchée au démarrage
   */
  it('devrait charger la liste des clients au démarrage', () => {
    expect(store.dispatch).toHaveBeenCalledWith(
      jasmine.objectContaining({
        type: ClientActions.loadClients.type
      })
    );
  });

  /**
   * Vérifie que la liste des clients est correctement affichée
   */
  it('devrait afficher la liste des clients', () => {
    expect(component.clients).toEqual(mockClients);
  });

  /**
   * Vérifie que la suppression d'un client déclenche l'action appropriée
   */
  it('devrait supprimer un client', () => {
    const clientId = 1;
    component.deleteClient(clientId);

    expect(store.dispatch).toHaveBeenCalledWith(
      jasmine.objectContaining({
        type: ClientActions.deleteClient.type,
        clientId
      })
    );
  });

  /**
   * Vérifie que le filtrage des clients fonctionne correctement
   */
  it('devrait filtrer les clients par nom', () => {
    component.searchTerm = 'Dupont';
    component.filterClients();
    
    expect(component.filteredClients.length).toBe(1);
    expect(component.filteredClients[0].lastName).toBe('Dupont');
  });
});