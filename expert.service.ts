import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Expert } from 'src/app/model/expert';

@Injectable({
  providedIn: 'root'
})
export class ExpertService {

  expertUrl = "http://localhost:8080/mit/expert/retrieve-all-experts";
  constructor(private http : HttpClient) { }
  getExperts() : Observable<Expert[]>{
    return this.http.get<Expert[]>(this.expertUrl);
  }

  deleteExpertById(id:number){
    return this.http.delete<Expert>("http://localhost:8080/mit/expert/remove-expert/"+id);
  }

  editExpert(expert: Expert){
    return this.http.put<Expert>("http://localhost:8080/mit/expert/modify-expert",expert);
  }

  addExpert(expert:Expert){
    return this.http.post<Expert>("http://localhost:8080/mit/expert/add-expert",expert);
    }
}