import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, mergeMap, catchError } from 'rxjs/operators';
import { ClientService } from '../../services/client.service';
import * as ClientActions from '../actions/client.actions';

@Injectable()
export class ClientEffects {
  loadClients$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ClientActions.loadClients),
      mergeMap(() =>
        this.clientService.getClients().pipe(
          map(clients => ClientActions.loadClientsSuccess({ clients })),
          catchError(error =>
            of(ClientActions.loadClientsFailure({ error }))
          )
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private clientService: ClientService
  ) {}
}