import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from 'src/app/model/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  adminUrl = "http://localhost:8081/mit/admin/retrieve-all-admins";
  constructor(private http : HttpClient) { }
  getAdmins() : Observable<Admin[]>{
    return this.http.get<Admin[]>(this.adminUrl);
  }
}
