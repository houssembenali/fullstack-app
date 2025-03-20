import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Product } from '../../models/product.model';
import * as ProductActions from '../../store/actions/product.actions';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products$: Observable<Product[]>;
  loading$: Observable<boolean>;
  productForm!: FormGroup;
  editMode = false;
  selectedProductId: number | null = null;

  constructor(
    private store: Store<any>,
    private fb: FormBuilder
  ) {
    this.products$ = this.store.select(state => state.product.products);
    this.loading$ = this.store.select(state => state.product.loading);
    this.initForm();
  }

  ngOnInit() {
    this.store.dispatch(ProductActions.loadProducts());
  }

  private initForm() {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      stock: ['', [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      if (this.editMode && this.selectedProductId) {
        this.store.dispatch(ProductActions.updateProduct({
          id: this.selectedProductId,
          product: this.productForm.value
        }));
      } else {
        this.store.dispatch(ProductActions.createProduct({
          product: this.productForm.value
        }));
      }
      this.resetForm();
    }
  }

  onEdit(product: Product) {
    this.editMode = true;
    this.selectedProductId = product.id;
    this.productForm.patchValue({
      name: product.name,
      description: product.description,
      price: product.price,
      stock: product.stock
    });
  }

  onDelete(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')) {
      this.store.dispatch(ProductActions.deleteProduct({ id }));
    }
  }

  resetForm() {
    this.editMode = false;
    this.selectedProductId = null;
    this.productForm.reset();
  }
}