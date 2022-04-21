import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8081/mit/log/users';
  islogin = false;
  admin = false;
  client = false;
  constructor(private http: HttpClient) { }
  login(login: String): Observable<User[]> {
    
     return this.http.get<User[]>(`${this.baseUrl}/${login}`);
   } 
}
