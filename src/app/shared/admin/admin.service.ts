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

  deleteAdminById(id:number){
    return this.http.delete<Admin>("http://localhost:8081/mit/admin/remove-admin/"+id);
  }

  editAdmin(admin: Admin){
    return this.http.put<Admin>("http://localhost:8081/mit/admin/modify-admin",admin);
  }

  addAdmin(admin:Admin){
    return this.http.post<Admin>("http://localhost:8081/mit/admin/add-admin",admin);
    }
}
