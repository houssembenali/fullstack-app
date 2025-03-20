/**
 * Interface représentant un produit dans l'application
 */
export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  stock: number;
  createdAt?: Date;
  updatedAt?: Date;
}