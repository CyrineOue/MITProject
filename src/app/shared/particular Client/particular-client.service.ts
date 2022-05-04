import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParticularClient } from 'src/app/model/particularClient';

@Injectable({
  providedIn: 'root'
})
export class ParticularClientService {
  ParticularClientUrl = "http://localhost:8081/mit/particularclient";
  constructor(private http : HttpClient) { }
  
  getAllParticularClients() : Observable<ParticularClient[]>{
    return this.http.get<ParticularClient[]>(`${this.ParticularClientUrl}/retrieve-all-particularclients`);
  }

  deleteParticularClient(id:number){

    return this.http.delete(`${this.ParticularClientUrl}/remove-particularclient/${id}`);
    }

  editParticularClient(pc: ParticularClient){
    return this.http.put<ParticularClient>(this.ParticularClientUrl+"/modify-particularclient/",pc);
  }

  addParticularClient(pc:ParticularClient){
    return this.http.post<ParticularClient>(this.ParticularClientUrl+"/add-particularclient",pc);
    }
  getCategoryP(id :number){
    return this.http.get(`${this.ParticularClientUrl}/categorise-particularclient/${id}`)
  }
  getScoreP(id :number){
    return this.http.get(`${this.ParticularClientUrl}/score-particularclient/${id}`)
  }
}
