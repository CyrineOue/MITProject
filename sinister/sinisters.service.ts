import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sinister } from 'src/app/model/sinister';

@Injectable({
  providedIn: 'root'
})
export class SinistersService {
  
  sinisterUrl = "http://localhost:8081/mit/admin/retrieve-all-sinisters";
  constructor(private http : HttpClient) { }
  getSinisters() : Observable<Sinister[]>{
    return this.http.get<Sinister[]>(this.sinisterUrl);
  }

  deleteSinisterById(id:number){
    return this.http.delete<Sinister>("http://localhost:8081/mit/sinister/remove-sinister/"+id);
  }

  editSinister(sinister: Sinister){
    return this.http.put<Sinister>("http://localhost:8081/mit/sinister/modify-sinister",sinister);
  }

  addSinister(sinister:Sinister){
    return this.http.post<Sinister>("http://localhost:8081/mit/sinister/add-sinister",sinister);
    }
}
