import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProductService } from './product.service';
import { environment } from '../../environments/environment';
import { Product } from '../models/product.model';

/**
 * Suite de tests pour le service ProductService
 */
describe('ProductService', () => {
  let service: ProductService;
  let httpMock: HttpTestingController;
  const apiUrl = `${environment.apiUrl}/products`;

  /** Données de test pour simuler un produit */
  const mockProduct: Product = {
    id: 1,
    name: 'Test Product',
    description: 'Test Description',
    price: 99.99,
    stock: 10
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProductService]
    });

    service = TestBed.inject(ProductService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all products', () => {
    const mockProducts: Product[] = [mockProduct];

    service.getProducts().subscribe(products => {
      expect(products).toEqual(mockProducts);
    });

    const req = httpMock.expectOne(apiUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockProducts);
  });

  it('should get a product by id', () => {
    const id = 1;

    service.getProduct(id).subscribe(product => {
      expect(product).toEqual(mockProduct);
    });

    const req = httpMock.expectOne(`${apiUrl}/${id}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockProduct);
  });

  it('should create a product', () => {
    const newProduct = {
      name: 'New Product',
      description: 'New Description',
      price: 149.99,
      stock: 5
    };

    service.createProduct(newProduct).subscribe(product => {
      expect(product).toEqual({ ...newProduct, id: 1 });
    });

    const req = httpMock.expectOne(apiUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newProduct);
    req.flush({ ...newProduct, id: 1 });
  });

  it('should update a product', () => {
    const id = 1;
    const updatedProduct = {
      name: 'Updated Product',
      description: 'Updated Description',
      price: 199.99,
      stock: 15
    };

    service.updateProduct(id, updatedProduct).subscribe(product => {
      expect(product).toEqual({ ...updatedProduct, id });
    });

    const req = httpMock.expectOne(`${apiUrl}/${id}`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedProduct);
    req.flush({ ...updatedProduct, id });
  });

  it('should delete a product', () => {
    const id = 1;

    service.deleteProduct(id).subscribe(() => {
      expect().nothing();
    });

    const req = httpMock.expectOne(`${apiUrl}/${id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});