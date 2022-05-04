import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
const TOKEN_HEADER_KEY = 'Authorization';
@Injectable(/*{
  providedIn: 'root'
}*/)
export class BasicAuthInterceptorService implements HttpInterceptor {
  constructor(private token: TokenStorageService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq);
  }
}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptorService, multi: true }
];
 // private readonly token: string;

  /*constructor(private token: TokenStorageService) {
   // this.token = sessionStorage.getItem('auth-user');
   }*/

  //intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    /*if (sessionStorage.getItem('email') && sessionStorage.getItem('token')) {
      req = req.clone({
        setHeaders: {
          Authorization: sessionStorage.getItem('token')
        }
      })
    }*/
     /* if (this.token) {
          const modReq = req.clone({
              setHeaders: {
                  'auth-user': this.token
              }
          });
          return next.handle(modReq);
      }*/
    /*intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      let authReq = req;
     // const token = sessionStorage.getItem('auth-user');
     const token = this.token.getToken();
      if (token != null) {
        authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
        console.log(authReq);
      }
      return next.handle(authReq);
      

    return next.handle(req);

  }
  
}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptorService, multi: true }];*/
