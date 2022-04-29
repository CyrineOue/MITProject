import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
const TOKEN_HEADER_KEY = 'secret';
@Injectable({
  providedIn: 'root'
})
export class BasicAuthInterceptorService implements HttpInterceptor {

  constructor(private token: TokenStorageService) { }

  /*intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (sessionStorage.getItem('email') && sessionStorage.getItem('token')) {
      req = req.clone({
        setHeaders: {
          Authorization: sessionStorage.getItem('token')
        }
      })
    }*/
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      let authReq = req;
      const token = this.token.getToken();
      if (token != null) {
        authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
      }
      return next.handle(authReq);
    

    //return next.handle(req);
    /*let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq);*/

  }
}
