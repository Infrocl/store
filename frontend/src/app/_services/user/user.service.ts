import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/shop/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getProfileInfo(id: any): Observable<any> {
    return this.http.get(
      'http://localhost:8080/api/shop/' + id,
      httpOptions
    );
  }

  getOrdersInfo(id: any): Observable<any[]> {
    return this.http.get<any[]>(
      'http://localhost:8080/api/shop/' + id + '/orders',
      httpOptions
    );
 }

 getPackagesInfo(id: any): Observable<any[]> {
  return this.http.get<any[]>(
    'http://localhost:8080/api/shop/' + id + '/packages',
    httpOptions
  );
}

getItemsInfo(): Observable<any[]> {
  return this.http.get<any[]>(
    'http://localhost:8080/api/items',
    httpOptions
  );
}

getItemsByName(name: string): Observable<any[]> {
  return this.http.post<any[]>(
    'http://localhost:8080/api/items/search',
    {
      name
    },
    httpOptions
  );
}

}
