import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

const ORDER_API = 'http://localhost:8080/api/shop/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CartService {
  items: any[] = [];

  constructor(private http: HttpClient) {}

  addToCart(product: any) {
    this.items.push(product);
  }

  removeFromCart(index: number) {
    this.items.splice(index, 1);
  }

  getItems() {
    return this.items;
  }

  clearCart() {
    this.items = [];
    return this.items;
  }

  createOrder(id: string, items: any[], paid: boolean): Observable<any> {
    return this.http.post(
      ORDER_API + id + '/orders/new',
      {
        orderDate: new Date().toISOString(),
        paid,
        items
      },
      httpOptions
    );
  }

}
