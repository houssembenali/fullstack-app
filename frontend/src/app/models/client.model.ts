/**
 * Interface représentant un client dans l'application
 * Définit la structure des données d'un client
 */
export interface Client {
    /** Identifiant unique du client */
    id: number;
    /** Prénom du client */
    firstName: string;
    /** Nom de famille du client */
    lastName: string;
    /** Adresse email du client */
    email: string;
    /** Numéro de téléphone du client */
    phone: string;
}