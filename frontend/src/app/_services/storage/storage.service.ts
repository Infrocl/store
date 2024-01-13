import { Injectable } from '@angular/core';

const  TOKEN_KEY = 'auth-token';
const  ID_KEY = 'user-id';
const  ROLE_KEY = 'user-role';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  
  public saveId(id: string): void  {
    window.sessionStorage.removeItem(ID_KEY);
    window.sessionStorage.setItem(ID_KEY,  JSON.stringify(id));
  }

  public getId(): string {
    const id = window.sessionStorage.getItem(ID_KEY);
    if (id) {
      return JSON.parse(id);
    }

    return "";
  }

  public saveRole(role: string): void {
    window.sessionStorage.removeItem(ROLE_KEY);
    window.sessionStorage.setItem(ROLE_KEY, JSON.stringify(role));
  }

  public getRole(): string {
    const role = window.sessionStorage.getItem(ROLE_KEY);
    if (role) {
      return JSON.parse(role);
    }

    return "";
  }

  public isLoggedIn(): boolean {
    const token = window.sessionStorage.getItem(TOKEN_KEY);
    if (token) {
      return true;
    }

    return false;
  }
}
