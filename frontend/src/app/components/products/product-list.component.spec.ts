import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Store } from '@ngrx/store';
import { of } from 'rxjs';
import { ProductListComponent } from './product-list.component';
import { ProductService } from '../../services/product.service';
import * as ProductActions from '../../store/actions/product.actions';
import { Product } from '../../models/product.model';

/**
 * Suite de tests pour le composant ProductListComponent
 */
describe('ProductListComponent', () => {
  let component: ProductListComponent;
  let fixture: ComponentFixture<ProductListComponent>;
  let store: jasmine.SpyObj<Store>;
  let productService: jasmine.SpyObj<ProductService>;

  /** Données de test pour simuler la liste des produits */
  const mockProducts: Product[] = [
    { id: 1, name: 'Test Product 1', description: 'Description 1', price: 99.99, stock: 10 },
    { id: 2, name: 'Test Product 2', description: 'Description 2', price: 149.99, stock: 5 }
  ];

  beforeEach(async () => {
    const storeSpy = jasmine.createSpyObj('Store', ['dispatch', 'select']);
    const productServiceSpy = jasmine.createSpyObj('ProductService', [
      'getProducts',
      'createProduct',
      'updateProduct',
      'deleteProduct'
    ]);

    await TestBed.configureTestingModule({
      declarations: [ProductListComponent],
      imports: [ReactiveFormsModule],
      providers: [
        FormBuilder,
        { provide: Store, useValue: storeSpy },
        { provide: ProductService, useValue: productServiceSpy }
      ]
    }).compileComponents();

    store = TestBed.inject(Store) as jasmine.SpyObj<Store>;
    productService = TestBed.inject(ProductService) as jasmine.SpyObj<ProductService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    store.select.and.returnValue(of(mockProducts));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load products on init', () => {
    component.ngOnInit();
    expect(store.dispatch).toHaveBeenCalledWith(ProductActions.loadProducts());
  });

  it('should create a new product when form is submitted in create mode', () => {
    const newProduct = {
      name: 'New Product',
      description: 'New Description',
      price: 199.99,
      stock: 15
    };

    component.productForm.patchValue(newProduct);
    component.onSubmit();

    expect(store.dispatch).toHaveBeenCalledWith(
      ProductActions.createProduct({ product: newProduct })
    );
  });

  it('should update an existing product when form is submitted in edit mode', () => {
    const updatedProduct = {
      name: 'Updated Product',
      description: 'Updated Description',
      price: 299.99,
      stock: 20
    };

    component.selectedProductId = 1;
    component.productForm.patchValue(updatedProduct);
    component.onSubmit();

    expect(store.dispatch).toHaveBeenCalledWith(
      ProductActions.updateProduct({
        id: 1,
        product: updatedProduct
      })
    );
  });

  it('should delete a product when delete is called', () => {
    const productId = 1;
    component.onDelete(productId);

    expect(store.dispatch).toHaveBeenCalledWith(
      ProductActions.deleteProduct({ id: productId })
    );
  });

  it('should populate form when edit is called', () => {
    const productToEdit = mockProducts[0];
    component.onEdit(productToEdit);

    expect(component.selectedProductId).toBe(productToEdit.id);
    expect(component.productForm.value).toEqual({
      name: productToEdit.name,
      description: productToEdit.description,
      price: productToEdit.price,
      stock: productToEdit.stock
    });
  });
});