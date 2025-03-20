import { createReducer, on } from '@ngrx/store';
import { Client } from '../../models/client.model';
import * as ClientActions from '../actions/client.actions';

export interface ClientState {
  clients: Client[];
  loading: boolean;
  error: any;
}

export const initialState: ClientState = {
  clients: [],
  loading: false,
  error: null
};

export const reducer = createReducer(
  initialState,
  on(ClientActions.loadClients, state => ({
    ...state,
    loading: true
  })),
  on(ClientActions.loadClientsSuccess, (state, { clients }) => ({
    ...state,
    loading: false,
    clients
  })),
  on(ClientActions.loadClientsFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  }))
);