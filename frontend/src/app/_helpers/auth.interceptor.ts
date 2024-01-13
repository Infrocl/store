import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpResponse, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token =  window.sessionStorage.getItem('auth-token');
        if (token) {
        const authRequest = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            });
        return next.handle(authRequest);
    } else {
        return next.handle(req);
    }
}
}
