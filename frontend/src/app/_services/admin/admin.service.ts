import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API = 'http://localhost:8080/api/admin/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }


  createItem(name: string, price: string): Observable<any> {
    return this.http.post(
      API + 'items/new',
      {
        name,
        price,
      },
      httpOptions
    );
  }

  updateItem(id:string, name: string, price: string): Observable<any> {
    return this.http.put(
      API + 'items/update',
      {
        id,
        name,
        price,
      },
      httpOptions
    );
  }



  getAllUsersInfo(): Observable<any[]> {
    return this.http.get<any[]>(
      API + 'users',
      httpOptions
    );
  }

  createPackage(userId: string, orders: any[], shipmentDate: any, deliveryDate: any): Observable<any> {
    return this.http.post(
      API + userId + '/packages/new',
      {
        orders,
        shipmentDate,
        deliveryDate
      },
      httpOptions
    );
  }
}
