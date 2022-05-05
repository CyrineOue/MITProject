import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sinister } from 'src/app/model/sinister';

@Injectable({
  providedIn: 'root'
})
export class SinisterService {
  sinisterURL = "api/mit/sinister/retrieve-all-sinisters";
  constructor (private http: HttpClient) {

  }
 getSinisters() : Observable <Sinister[]>{
 return this.http.get<Sinister[]>(this.sinisterURL);
 }
 addSinister(sinister : Sinister): Observable<Sinister> {
   return this.http.post<Sinister>('api/mit/sinister/add-sinister', sinister)
   }

 editSinister(sinister : Sinister): Observable<Sinister> {
     return this.http.put<Sinister>('api/mit/sinister/modify-sinister', sinister)
     }

deleteSinisterById(id:number){
  return this.http.delete<Sinister>("api/mit/sinister/remove-sinister/"+id);
}
 
sinisterSettlementSpeed(id:number){
  return this.http.get<string>("api/mit/sinister/sinisterSettlementSpeed/"+id);
}
mostFrequentSinister() : Observable <string>{
  return this.http.get<string>("api/mit/sinister/mostFrequentSinister");
}
}
